package com.pia.tmf.v4.tmf641.client.serviceorder;

import static com.pia.commons.util.JacksonUtil.fileToObject;
import static com.pia.tmf.v4.hub.helper.MockServerUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.AddOperation;
import com.github.fge.jsonpatch.JsonPatch;
import com.pia.client.openid.model.OpenidClientProperties;
import com.pia.dnext.v4.tmf641.model.DnextServiceOrder;
import com.pia.tmf.common.config.TmfClientConfigurations;
import com.pia.tmf.common.model.RetrievalContext;
import com.pia.tmf.common.model.TmfOffsetRequest;
import com.pia.tmf.v4.common.model.Note;
import com.pia.tmf.v4.tmf641.client.api.ServiceOrderClient;
import com.pia.tmf.v4.tmf641.config.ServiceOrderClientProvider;
import com.pia.tmf.v4.tmf641.exception.ServiceOrderClientException;
import com.pia.tmf.v4.tmf641.model.ServiceOrder;
import com.pia.tmf.v4.tmf641.model.ServiceOrderCreate;
import com.pia.tmf.v4.tmf641.model.ServiceOrderUpdate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import reactor.test.StepVerifier;

/**
 * @author Pablo Garcia
 */
@SpringBootTest
class ServiceOrderClientIT {

  private static String TEST_SERVICE_ORDER_PATH = "serviceOrder";
  private static final String SH_TMF_CLIENTS_641 = "sh-tmf641";
  private static final String APIX_TMF_CLIENTS_641 = "apix-tmf641";

  static final String SERVICE_ORDER_CREATE =
      "payload/serviceOrder/digicom-service-order-create-tata-geo.json";

  @Autowired private OpenidClientProperties shClientProperties;
  @Autowired private TmfClientConfigurations tmfClientConfigurations;
  @Autowired private ServiceOrderClientProvider serviceOrderClientProvider;
  @Autowired private ObjectMapper objectMapper;

  private ServiceOrderClient shServiceOrderClient;

  @BeforeEach
  void beforeEach() {
    TEST_SERVICE_ORDER_PATH = "/" + RandomStringUtils.randomAlphabetic(5);
    tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS_641).setBaseUrl(BASE_URL);
    tmfClientConfigurations.getTmfClients().get(APIX_TMF_CLIENTS_641).setBaseUrl(BASE_URL);

    var shClientConfig = tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS_641);
    shClientConfig.setEndpoint(TEST_SERVICE_ORDER_PATH);
    var apixClientConfig = tmfClientConfigurations.getTmfClients().get(APIX_TMF_CLIENTS_641);
    apixClientConfig.setEndpoint(TEST_SERVICE_ORDER_PATH);

    shServiceOrderClient =
        serviceOrderClientProvider.getTmfClient(
            tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS_641), "sh");
    shClientProperties.setNumRetries(1);
    shClientProperties.setRetryWaitMillis(100);

    resetMockServer();
  }

  @Test
  void testGetServiceOrderById_withValidInput_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var serviceCreate = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, serviceCreate);

    var request = shServiceOrderClient.get(id);
    StepVerifier.create(request)
        .assertNext(
            serviceOrder -> {
              assertNotNull(serviceOrder);
              assertEquals(id, serviceOrder.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceOrderById_withClassType_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var serviceCreate = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, serviceCreate);

    var request = shServiceOrderClient.get(id, DnextServiceOrder.class);
    StepVerifier.create(request)
        .assertNext(
            serviceOrder -> {
              assertNotNull(serviceOrder);
              assertEquals(id, serviceOrder.getId());
              assertInstanceOf(DnextServiceOrder.class, serviceOrder);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceOrderById_withStringClassType_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var serviceCreate = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, serviceCreate);

    var request = shServiceOrderClient.get(id, String.class);
    StepVerifier.create(request)
        .assertNext(
            serviceOrder -> {
              assertNotNull(serviceOrder);
              assertTrue(serviceOrder.contains(id));
              assertInstanceOf(String.class, serviceOrder);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceOrderById_withJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);

    var request =
        shServiceOrderClient.get(
            id, RetrievalContext.builder().withServerJsonFilter("$.id").build());
    StepVerifier.create(request)
        .assertNext(
            serviceOrder -> {
              assertNotNull(serviceOrder);
              assertEquals(id, serviceOrder.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceOrderById_withClassTypeAndJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);

    var request =
        shServiceOrderClient.get(
            id, RetrievalContext.builder().withServerJsonFilter("$.id").build(), String.class);
    StepVerifier.create(request)
        .assertNext(
            serviceOrder -> {
              assertNotNull(serviceOrder);
              assertTrue(serviceOrder.contains(id));
              assertInstanceOf(String.class, serviceOrder);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceOrderById_withFields_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);
    var request = shServiceOrderClient.get(id, RetrievalContext.builder().withFields("id").build());
    StepVerifier.create(request)
        .assertNext(
            serviceOrder -> {
              assertNotNull(serviceOrder);
              assertEquals(id, serviceOrder.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceOrderById_returnException() {
    setUpDynamicGetCallback(TEST_SERVICE_ORDER_PATH);
    var serviceCreate = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, serviceCreate);

    var request = shServiceOrderClient.get("unknown-id");
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceOrderClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND, ((ServiceOrderClientException) error).getStatusCode());
              var errorDetail = ((ServiceOrderClientException) error).getErrorMessage();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void testGetServiceOrders_withValidInput_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.list();

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetServiceOrders_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.list(new LinkedMultiValueMap<>());

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetServiceOrders_withTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.list(TmfOffsetRequest.of(20, 5));

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(5)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetServiceOrders_withTmfOffsetRequestAndMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request =
        shServiceOrderClient.list(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(5)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetAllServiceOrders_withValidData_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.listAll();

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(30)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetAllServiceOrders_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.listAll(new LinkedMultiValueMap<>());

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(30)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetAllServiceOrders_withValidDataWithTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.listAll(TmfOffsetRequest.of(20, 5));

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetAllServiceOrders_withMultiValueMapAndTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request =
        shServiceOrderClient.listAll(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<ServiceOrder> serviceOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetPagedServiceOrders_withValidData_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.listPaged();

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetPagedServiceOrders_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.listPaged(new LinkedMultiValueMap<>());

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetPagedServiceOrders_withValidDataWithTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request = shServiceOrderClient.listPaged(TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testGetPagedServiceOrders_withMultiValueMapAndTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_ORDER_PATH);
    List<String> ids = insertServiceOrderToMockCache(TEST_SERVICE_ORDER_PATH, 30);
    var request =
        shServiceOrderClient.listPaged(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_ORDER_PATH, ids);
  }

  @Test
  void testPostServiceOrder_withValidInput_returns_OK() {
    setUpDynamicPostCallback(TEST_SERVICE_ORDER_PATH);
    var serviceCreate = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    var request = shServiceOrderClient.post(serviceCreate);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getId());
            })
        .verifyComplete();
  }

  @Test
  void testPostServiceOrder_withClassType_returns_OK() {
    setUpDynamicPostCallback(TEST_SERVICE_ORDER_PATH);
    var serviceCreate = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    var request = shServiceOrderClient.post(serviceCreate, String.class);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertTrue(result.contains("id"));
            })
        .verifyComplete();
  }

  @Test
  void test_patch_withValidData_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = shServiceOrderClient.patch(id, patch);
    StepVerifier.create(request)
        .assertNext(
            resourceOrder -> {
              assertNotNull(resourceOrder);
              assertNotNull(resourceOrder.getId());
              assertNotNull(resourceOrder.getNotes());
            })
        .verifyComplete();
  }

  @Test
  void test_patchResourceOrder_withClassType_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = shServiceOrderClient.patch(id, patch, String.class);
    StepVerifier.create(request)
        .assertNext(
            resourceOrder -> {
              assertNotNull(resourceOrder);
              assertTrue(resourceOrder.contains("id"));
              assertTrue(resourceOrder.contains("note"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchResourceOrder_withValidDataAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);
    var update = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderUpdate.class);
    update.setDescription("Updated Description");
    var request = shServiceOrderClient.patch(id, update);

    StepVerifier.create(request)
        .assertNext(
            resourceOrder -> {
              assertNotNull(resourceOrder);
              assertNotNull(resourceOrder.getId());
              assertNotNull(resourceOrder.getDescription());
              assertEquals("Updated Description", resourceOrder.getDescription());
            })
        .verifyComplete();
  }

  @Test
  void test_patchResourceOrder_withClassTypeAndAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);
    var update = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderUpdate.class);
    update.setDescription("Updated Description");
    var request = shServiceOrderClient.patch(id, update, String.class);

    StepVerifier.create(request)
        .assertNext(
            resourceOrder -> {
              assertNotNull(resourceOrder);
              assertTrue(resourceOrder.contains("id"));
              assertTrue(resourceOrder.contains("Updated Description"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchResourceOrder_withNoResourceOrderId_shouldNPE() throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));

    StepVerifier.create(shServiceOrderClient.patch(null, patch))
        .expectError(NullPointerException.class)
        .verify();
  }

  @Test
  void test_patchResourceOrder_withInValidResourceOrderJsonPatch_shouldThrowResourceOrderException()
      throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_SERVICE_ORDER_PATH);
    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = shServiceOrderClient.patch("unknown-id", patch);

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceOrderClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND, ((ServiceOrderClientException) error).getStatusCode());
              var errorDetail = ((ServiceOrderClientException) error).getErrorMessage();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_deleteResourceOrder_withNoResourceOrderId_shouldReturnNPE() {
    StepVerifier.create(shServiceOrderClient.delete(null))
        .expectErrorMatches(
            throwable ->
                throwable instanceof NullPointerException
                    && "ID can not be empty".equals(throwable.getMessage()))
        .verify();
  }

  @Test
  void test_deleteResourceOrder_withValidData_shouldNoContent() {
    setUpDynamicDeleteCallback(TEST_SERVICE_ORDER_PATH);
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_ORDER_PATH, create);

    var request = shServiceOrderClient.delete(id);
    StepVerifier.create(request).verifyComplete();
  }

  @Test
  void test_deleteResourceOrder_withInValidResourceOrderId_shouldReturnResourceOrderException() {
    setUpDynamicDeleteCallback(TEST_SERVICE_ORDER_PATH);
    var request = shServiceOrderClient.delete("unknown-id");

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceOrderClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND, ((ServiceOrderClientException) error).getStatusCode());
              return true;
            })
        .verify();
  }

  private List<String> insertServiceOrderToMockCache(String path, int count) {
    List<String> ids = new ArrayList<>();
    var create = fileToObject(SERVICE_ORDER_CREATE, ServiceOrderCreate.class);
    for (int i = 0; i < count; i++) {
      ids.add(addDataToMockServerCache(path, create));
    }
    return ids;
  }
}
