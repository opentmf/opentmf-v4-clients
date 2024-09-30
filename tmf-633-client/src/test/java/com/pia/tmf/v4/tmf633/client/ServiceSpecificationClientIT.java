package com.pia.tmf.v4.tmf633.client;

import static com.pia.commons.util.JacksonUtil.contents;
import static com.pia.commons.util.JacksonUtil.fileToObject;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.BASE_URL;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.addDataToMockServerCache;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.deleteDataFromMockServerCache;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.get;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.resetMockServer;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.setUpDynamicDeleteCallback;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.setUpDynamicGetCallback;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.setUpDynamicGetListCallback;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.setUpDynamicJsonPatchCallback;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.setUpDynamicMergePatchCallback;
import static com.pia.tmf.v4.tmf633.mock.MockServerUtils.setUpDynamicPostCallback;
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
import com.pia.tmf.v4.common.config.TmfClientConfigurations;
import com.pia.tmf.v4.common.model.Note;
import com.pia.tmf.v4.common.model.RetrievalContext;
import com.pia.tmf.v4.common.model.TmfOffsetRequest;
import com.pia.tmf.v4.tmf633.client.api.ServiceSpecificationClient;
import com.pia.tmf.v4.tmf633.config.ServiceSpecificationClientProvider;
import com.pia.tmf.v4.tmf633.exception.ServiceSpecificationClientException;
import com.pia.tmf.v4.tmf633.model.ServiceSpecification;
import com.pia.tmf.v4.tmf633.model.ServiceSpecificationCreate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import reactor.test.StepVerifier;

/**
 * @author Gokhan Demir
 */
@SpringBootTest
@EnableConfigurationProperties
class ServiceSpecificationClientIT {
  private String serviceSpecificationPath;
  private static final String APIX_PATH = "/apix";
  private static final String SERVICE_SPECIFICATION_CREATE =
      "payload/serviceSpecification/createServiceSpecificationCreate.json";
  private static final String SH_TMF_CLIENTS_638 = "sh-tmf633";
  private static final String APIX_TMF_CLIENTS_638 = "apix-tmf633";
  private static final String ID_JSON_FILTER = "$.id";
  private static final RetrievalContext ID_RETRIEVAL_CONTEXT =
      RetrievalContext.builder().withServerJsonFilter(ID_JSON_FILTER).build();

  @Autowired private ServiceSpecificationClientProvider serviceSpecificationClientProvider;
  @Autowired private OpenidClientProperties shClientProperties;
  @Autowired private OpenidClientProperties apixClientProperties;
  @Autowired private TmfClientConfigurations tmfClientConfigurations;
  @Autowired private ObjectMapper objectMapper;

  private ServiceSpecificationClient serviceSpecificationClient;

  private ServiceSpecificationClient apixServiceSpecificationClient;

  private static String getServiceSpecPayloadPath(String id) {
    return "payload/serviceSpecification/" + id + ".json";
  }

  @BeforeEach
  void beforeEach() {
    serviceSpecificationPath = "/" + RandomStringUtils.randomAlphabetic(5);
    var shClientConfig = tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS_638);
    shClientConfig.setBaseUrl(BASE_URL);
    shClientConfig.setEndpoint(serviceSpecificationPath);

    var apixClientConfig = tmfClientConfigurations.getTmfClients().get(APIX_TMF_CLIENTS_638);
    apixClientConfig.setBaseUrl(BASE_URL + "/" + APIX_PATH);
    apixClientConfig.setEndpoint(serviceSpecificationPath);

    serviceSpecificationClient =
        serviceSpecificationClientProvider.getTmfClient(
            tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS_638),
            "sh");

    apixServiceSpecificationClient =
        serviceSpecificationClientProvider.getTmfClient(
            tmfClientConfigurations.getTmfClients().get(APIX_TMF_CLIENTS_638),
            "apix");

    shClientProperties.setNumRetries(1);
    shClientProperties.setRetryWaitMillis(100);
    apixClientProperties.setNumRetries(1);
    apixClientProperties.setRetryWaitMillis(100);

    resetMockServer();
  }

  @Test
  void testGetServiceSpecificationById_withValidInput_returns_OK() {
    setUpDynamicGetCallback(serviceSpecificationPath);
    var serviceSpecificationCreate =
        fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, serviceSpecificationCreate);

    var request = serviceSpecificationClient.get(id);
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertEquals(id, serviceSpecification.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceSpecificationById_withStringClassType_returns_OK() {
    setUpDynamicGetCallback(serviceSpecificationPath);
    var serviceSpecificationCreate =
        fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, serviceSpecificationCreate);

    var request = serviceSpecificationClient.get(id, String.class);
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertTrue(serviceSpecification.contains(id));
              assertInstanceOf(String.class, serviceSpecification);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceSpecificationById_withJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);

    var request = serviceSpecificationClient.get(id, ID_RETRIEVAL_CONTEXT);
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertEquals(id, serviceSpecification.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceSpecificationById_withClassTypeAndJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);

    var request = serviceSpecificationClient.get(id, ID_RETRIEVAL_CONTEXT, String.class);
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertTrue(serviceSpecification.contains(id));
              assertInstanceOf(String.class, serviceSpecification);
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceSpecificationById_withFields_shouldReturnOkay() {
    setUpDynamicGetCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);

    var request =
        serviceSpecificationClient.get(id, RetrievalContext.builder().withFields("id").build());
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertEquals(id, serviceSpecification.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceSpecificationById_returnException() {
    setUpDynamicGetCallback(serviceSpecificationPath);
    var serviceSpecificationCreate =
        fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    addDataToMockServerCache(serviceSpecificationPath, serviceSpecificationCreate);

    var request = serviceSpecificationClient.get("unknown-id");
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceSpecificationClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND,
                  ((ServiceSpecificationClientException) error).getStatusCode());
              var errorDetail = ((ServiceSpecificationClientException) error).getError();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_getServiceSpecificationById_withNullID_returnsNotFound() {
    StepVerifier.create(serviceSpecificationClient.get(null))
        .expectErrorMatches(
            error ->
                (error instanceof NullPointerException e)
                    && ("ID can not be empty".equals(e.getMessage())))
        .verify();
  }

  @ParameterizedTest
  @ValueSource(strings = {"gsipGeoNumberSS", "gsipNonGeoNumberSS"})
  void test_getServiceSpecificationById_withValidId_returnsValidResult(String id) {
    setUpDynamicGetCallback(APIX_PATH + serviceSpecificationPath);
    var create = fileToObject(getServiceSpecPayloadPath(id), ServiceSpecification.class);
    addDataToMockServerCache(APIX_PATH + serviceSpecificationPath, create);
    StepVerifier.create(apixServiceSpecificationClient.get(id))
        .assertNext(
            spec -> {
              assertNotNull(spec);
              assertNotNull(spec.getId());
              assertEquals(id, spec.getId());
            })
        .verifyComplete();
  }

  @ParameterizedTest
  @ValueSource(strings = {"gsipGeoNumberSS", "gsipNonGeoNumberSS"})
  void test_getServiceSpecificationById_withValidId_returnsOk_inSecondAttempt(String id) {
    get(
        APIX_PATH + serviceSpecificationPath + "/" + id,
        1,
        HttpStatus.GATEWAY_TIMEOUT,
        contents("payload/error/notFound.json"));
    get(
        APIX_PATH + serviceSpecificationPath + "/" + id,
        1,
        HttpStatus.OK,
        contents(getServiceSpecPayloadPath(id)));

    StepVerifier.create(apixServiceSpecificationClient.get(id))
        .assertNext(
            spec -> {
              assertNotNull(spec);
              assertNotNull(spec.getId());
              assertEquals(id, spec.getId());
            })
        .verifyComplete();
  }

  @Test
  void testGetServiceSpecifications_withValidInput_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.list();

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetServiceSpecifications_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.list(new LinkedMultiValueMap<>());

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetServiceSpecifications_withTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.list(TmfOffsetRequest.of(20, 5));

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(5)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetServiceSpecifications_withTmfOffsetRequestAndMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request =
        serviceSpecificationClient.list(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(5)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetAllServiceSpecifications_withValidData_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.listAll();

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(30)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetAllServiceSpecifications_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.listAll(new LinkedMultiValueMap<>());

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(30)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetAllServiceSpecifications_withValidDataWithTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.listAll(TmfOffsetRequest.of(20, 5));

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetAllServiceSpecifications_withMultiValueMapAndTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request =
        serviceSpecificationClient.listAll(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<ServiceSpecification> serviceSpecifications = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> serviceSpecifications)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetPagedServiceSpecifications_withValidData_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.listPaged();

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetPagedServiceSpecifications_withMultiValueMap_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.listPaged(new LinkedMultiValueMap<>());

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetPagedServiceSpecifications_withValidDataWithTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request = serviceSpecificationClient.listPaged(TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testGetPagedServiceSpecifications_withMultiValueMapAndTmfOffsetRequest_returns_OK() {
    setUpDynamicGetListCallback(serviceSpecificationPath);
    List<String> ids = insertServiceSpecificationToMockCache(serviceSpecificationPath, 30);
    var request =
        serviceSpecificationClient.listPaged(
            new LinkedMultiValueMap<>(), TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(serviceSpecificationPath, ids);
  }

  @Test
  void testPostServiceSpecification_withValidInput_returns_OK() {
    setUpDynamicPostCallback(serviceSpecificationPath);
    var serviceSpecificationCreate =
        fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    var request = serviceSpecificationClient.post(serviceSpecificationCreate);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getId());
            })
        .verifyComplete();
  }

  @Test
  void testPostServiceSpecification_withClassType_returns_OK() {
    setUpDynamicPostCallback(serviceSpecificationPath);
    var serviceSpecificationCreate =
        fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    var request = serviceSpecificationClient.post(serviceSpecificationCreate, String.class);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertTrue(result.contains("id"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchServiceSpecification_withValidData_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = serviceSpecificationClient.patch(id, patch);
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertNotNull(serviceSpecification.getId());
            })
        .verifyComplete();
  }

  @Test
  void test_patchServiceSpecification_withClassType_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = serviceSpecificationClient.patch(id, patch, String.class);
    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertTrue(serviceSpecification.contains("id"));
              assertTrue(serviceSpecification.contains("note"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchServiceSpecification_withValidDataAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);
    var update = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    update.setDescription("Updated Description");
    var request = serviceSpecificationClient.patch(id, update);

    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertNotNull(serviceSpecification.getId());
              assertNotNull(serviceSpecification.getDescription());
              assertEquals("Updated Description", serviceSpecification.getDescription());
            })
        .verifyComplete();
  }

  @Test
  void test_patchServiceSpecification_withClassTypeAndAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);
    var update = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    update.setDescription("Updated Description");
    var request = serviceSpecificationClient.patch(id, update, String.class);

    StepVerifier.create(request)
        .assertNext(
            serviceSpecification -> {
              assertNotNull(serviceSpecification);
              assertTrue(serviceSpecification.contains("id"));
              assertTrue(serviceSpecification.contains("Updated Description"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchServiceSpecification_withNoServiceSpecificationId_shouldNPE()
      throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));

    StepVerifier.create(serviceSpecificationClient.patch(null, patch))
        .expectError(NullPointerException.class)
        .verify();
  }

  @Test
  void
      test_patchServiceSpecification_withInValidServiceSpecificationJsonPatch_shouldThrowServiceSpecificationException()
          throws JsonPointerException {
    setUpDynamicJsonPatchCallback(serviceSpecificationPath);
    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = serviceSpecificationClient.patch("unknown-id", patch);

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceSpecificationClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND,
                  ((ServiceSpecificationClientException) error).getStatusCode());
              var errorDetail = ((ServiceSpecificationClientException) error).getError();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_deleteServiceSpecification_withNoServiceSpecificationId_shouldReturnNPE() {
    StepVerifier.create(serviceSpecificationClient.delete(null))
        .expectError(NullPointerException.class)
        .verify();
  }

  @Test
  void test_deleteServiceSpecification_withValidData_shouldNoContent() {
    setUpDynamicDeleteCallback(serviceSpecificationPath);
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecificationCreate.class);
    String id = addDataToMockServerCache(serviceSpecificationPath, create);

    var request = serviceSpecificationClient.delete(id);
    StepVerifier.create(request).verifyComplete();
  }

  @Test
  void
      test_deleteServiceSpecification_withInValidServiceSpecificationId_shouldReturnServiceSpecificationException() {
    setUpDynamicDeleteCallback(serviceSpecificationPath);
    var request = serviceSpecificationClient.delete("unknown-id");

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ServiceSpecificationClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND,
                  ((ServiceSpecificationClientException) error).getStatusCode());
              return true;
            })
        .verify();
  }

  private List<String> insertServiceSpecificationToMockCache(String path, int count) {
    List<String> ids = new ArrayList<>();
    var create = fileToObject(SERVICE_SPECIFICATION_CREATE, ServiceSpecification.class);
    for (int i = 0; i < count; i++) {
      ids.add(addDataToMockServerCache(path, create));
    }
    return ids;
  }
}
