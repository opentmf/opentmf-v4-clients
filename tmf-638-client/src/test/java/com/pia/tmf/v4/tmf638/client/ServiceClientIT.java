package com.pia.tmf.v4.tmf638.client;

import static com.pia.commons.util.JacksonUtil.fileToObject;
import static com.pia.commons.util.JacksonUtil.objectToTree;
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
import com.pia.commons.util.JacksonUtil;
import com.pia.dnext.v4.tmf638.config.DnextTmf638JacksonConfig;
import com.pia.dnext.v4.tmf638.model.DnextService;
import com.pia.tmf.v4.common.config.TmfClientConfigurations;
import com.pia.tmf.v4.common.helper.MockServerUtils;
import com.pia.tmf.v4.common.model.GeographicAddress;
import com.pia.tmf.v4.common.model.Note;
import com.pia.tmf.v4.common.model.RetrievalContext;
import com.pia.tmf.v4.common.model.TmfOffsetRequest;
import com.pia.tmf.v4.service.model.Service;
import com.pia.tmf.v4.service.model.ServiceCreate;
import com.pia.tmf.v4.tmf638.client.api.ServiceClient;
import com.pia.tmf.v4.tmf638.config.ServiceClientProvider;
import com.pia.tmf.v4.tmf638.exception.ServiceClientException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Cezmi Aslan
 */
@SpringBootTest
class ServiceClientIT extends MockServerUtils {

  @TestConfiguration
  static class ServiceInventoryClientTestConfiguration {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
      var om = JacksonUtil.getDefaultObjectMapper();
      DnextTmf638JacksonConfig.registerExtensions(om);
      return om;
    }
  }

  private static String TEST_SERVICE_PATH;
  private static final String SH_TMF_CLIENTS_638 = "sh-tmf638";

  private static final String DNEXT_SERVICE_CREATE =
      "payload/serviceInventory/service_create_expectation.json";
  private static final String SERVICE_CREATE = "payload/serviceInventory/service_create.json";

  private static final String ID_JSON_FILTER = "$.id";
  private static final RetrievalContext ID_FILTER =
      RetrievalContext.builder().withServerJsonFilter(ID_JSON_FILTER).build();

  @Autowired private TmfClientConfigurations tmfClientConfigurations;
  @Autowired private ServiceClientProvider serviceClientProvider;
  @Autowired private OpenidClientProperties shClientProperties;

  private ServiceClient serviceClient;

  @BeforeEach
  void beforeEach() {
    TEST_SERVICE_PATH = "/" + RandomStringUtils.randomAlphabetic(5);
    var shClientConfig = tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS_638);
    shClientConfig.setBaseUrl(BASE_URL);
    shClientConfig.setEndpoint(TEST_SERVICE_PATH);

    serviceClient = serviceClientProvider.getTmfClient(shClientConfig, "sh");

    shClientProperties.setNumRetries(1);
    shClientProperties.setRetryWaitMillis(100);

    resetMockServer();
  }

  @Test
  void testGetServiceById_withValidInput_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, serviceCreate);

    var request = serviceClient.get(id);
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertEquals(id, service.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceById_withDnextServiceCreate_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(SERVICE_CREATE, DnextService.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, serviceCreate);

    var request = serviceClient.get(id).map(service -> (DnextService) service);
    verifyOk(request);
  }

  @Test
  void testGetServiceById_withClassType_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(DNEXT_SERVICE_CREATE, DnextService.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, serviceCreate);

    var request = serviceClient.get(id, DnextService.class);
    verifyOk(request);
  }

  @Test
  void testGetServiceById_withStringClassType_returns_OK() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, serviceCreate);

    var request = serviceClient.get(id, String.class);
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertTrue(service.contains(id));
              assertInstanceOf(String.class, service);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceById_withJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);

    var request = serviceClient.get(id, ID_FILTER);
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertEquals(id, service.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceById_withClassTypeAndJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);

    var request = serviceClient.get(id, ID_FILTER, String.class);
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertTrue(service.contains(id));
              assertInstanceOf(String.class, service);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceById_withFields_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);

    var request = serviceClient.get(id, RetrievalContext.builder().withFields("id").build());
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertEquals(id, service.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceById_returnException() {
    setUpDynamicGetCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    addDataToMockServerCache(TEST_SERVICE_PATH, serviceCreate);

    var request = serviceClient.get("unknown-id");
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceClientException.class, error);
              assertSame(HttpStatus.NOT_FOUND, ((ServiceClientException) error).getStatusCode());
              var errorDetail = ((ServiceClientException) error).getError();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void testGetServices_withValidInput_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.list();

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(10).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetServices_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.list(new LinkedMultiValueMap<>());

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(10).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetServices_withTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.list(TmfOffsetRequest.of(20, 5));

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(5).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetServices_withTmfOffsetRequestAndMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.list(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(5).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetAllServices_withValidData_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listAll();

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(30).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetAllServices_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listAll(new LinkedMultiValueMap<>());

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(30).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetAllServices_withValidDataWithTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listAll(TmfOffsetRequest.of(20, 5));

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(10).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetAllServices_withMultiValueMapAndTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listAll(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<Service> services = new ArrayList<>();
    StepVerifier.create(request).recordWith(() -> services).expectNextCount(10).verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetPagedServices_withValidData_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listPaged();

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetPagedServices_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listPaged(new LinkedMultiValueMap<>());

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetPagedServices_withValidDataWithTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listPaged(TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testGetPagedServices_withMultiValueMapAndTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(TEST_SERVICE_PATH);
    List<String> ids = insertServiceToMockCache(TEST_SERVICE_PATH, 30);
    var request = serviceClient.listPaged(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_SERVICE_PATH, ids);
  }

  @Test
  void testPostService_withValidInput_returns_OK() {
    setUpDynamicPostCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    var request = serviceClient.post(serviceCreate);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getId());
            })
        .verifyComplete();
  }

  @Test
  void testPostService_withClassType_returns_OK() {
    setUpDynamicPostCallback(TEST_SERVICE_PATH);
    var serviceCreate = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    var request = serviceClient.post(serviceCreate, String.class);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertTrue(result.contains("id"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchService_withValidData_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);

    var patch =
        new JsonPatch(
            List.of(new AddOperation(new JsonPointer("/note"), objectToTree(List.of(new Note())))));
    var request = serviceClient.patch(id, patch);
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertNotNull(service.getId());
              assertNotNull(service.getNotes());
            })
        .verifyComplete();
  }

  @Test
  void test_patchService_withClassType_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);

    var patch =
        new JsonPatch(
            List.of(new AddOperation(new JsonPointer("/note"), objectToTree(List.of(new Note())))));
    var request = serviceClient.patch(id, patch, String.class);
    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertTrue(service.contains("id"));
              assertTrue(service.contains("note"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchService_withValidDataAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);
    var update = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    update.setDescription("Updated Description");
    var request = serviceClient.patch(id, update);

    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertNotNull(service.getId());
              assertNotNull(service.getDescription());
              assertEquals("Updated Description", service.getDescription());
            })
        .verifyComplete();
  }

  @Test
  void test_patchService_withClassTypeAndAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(TEST_SERVICE_PATH);
    var create = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);
    var update = fileToObject(DNEXT_SERVICE_CREATE, ServiceCreate.class);
    update.setDescription("Updated Description");
    var request = serviceClient.patch(id, update, String.class);

    StepVerifier.create(request)
        .assertNext(
            service -> {
              assertNotNull(service);
              assertTrue(service.contains("id"));
              assertTrue(service.contains("Updated Description"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchService_withNoServiceId_shouldNPE() throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(new AddOperation(new JsonPointer("/note"), objectToTree(List.of(new Note())))));

    StepVerifier.create(serviceClient.patch(null, patch))
        .expectErrorMatches(
            error ->
                (error instanceof NullPointerException e)
                    && ("ID can not be empty".equals(e.getMessage())))
        .verify();
  }

  @Test
  void test_patchService_withInValidServiceJsonPatch_shouldThrowServiceException()
      throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_SERVICE_PATH);
    var patch =
        new JsonPatch(
            List.of(new AddOperation(new JsonPointer("/note"), objectToTree(List.of(new Note())))));
    var request = serviceClient.patch("unknown-id", patch);

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceClientException.class, error);
              assertSame(HttpStatus.NOT_FOUND, ((ServiceClientException) error).getStatusCode());
              var errorDetail = ((ServiceClientException) error).getError();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_deleteService_withNoServiceId_shouldReturnNPE() {
    StepVerifier.create(serviceClient.delete(null))
        .expectErrorMatches(
            error ->
                (error instanceof NullPointerException e)
                    && ("ID can not be empty".equals(e.getMessage())))
        .verify();
  }

  @Test
  void test_deleteService_withValidData_shouldNoContent() {
    setUpDynamicDeleteCallback(TEST_SERVICE_PATH);
    var create = fileToObject(SERVICE_CREATE, ServiceCreate.class);
    String id = addDataToMockServerCache(TEST_SERVICE_PATH, create);

    var request = serviceClient.delete(id);
    StepVerifier.create(request).verifyComplete();
  }

  @Test
  void test_deleteService_withInValidServiceId_shouldReturnServiceException() {
    setUpDynamicDeleteCallback(TEST_SERVICE_PATH);
    var request = serviceClient.delete("unknown-id");

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceClientException.class, error);
              assertSame(HttpStatus.NOT_FOUND, ((ServiceClientException) error).getStatusCode());
              return true;
            })
        .verify();
  }

  void verifyOk(Mono<DnextService> response) {
    StepVerifier.create(response)
        .assertNext(
            returned -> {
              Assertions.assertNotNull(returned);
              Assertions.assertNotNull(returned.getId());
              verifyCommonFields(returned);
              var address = (GeographicAddress) returned.getPlaces().get(0);
              Assertions.assertFalse(address.getGeographicSubAddresses().isEmpty());
              Assertions.assertNotNull(
                  address.getGeographicSubAddresses().get(0).getBuildingName());
              Assertions.assertNotNull(returned.getCreatedBy());
            })
        .verifyComplete();
  }

  void verifyCommonFields(DnextService returned) {
    Assertions.assertNotNull(returned.getServiceSpecification());
    Assertions.assertFalse(returned.getServiceOrderItems().isEmpty());
    Assertions.assertFalse(returned.getPlaces().isEmpty());
  }

  private List<String> insertServiceToMockCache(String path, int count) {
    List<String> ids = new ArrayList<>();
    var create = fileToObject(DNEXT_SERVICE_CREATE, DnextService.class);
    for (int i = 0; i < count; i++) {
      ids.add(addDataToMockServerCache(path, create));
    }
    return ids;
  }
}
