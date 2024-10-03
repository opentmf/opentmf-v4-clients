package com.pia.tmf.v4.tmf622.client;

import static com.pia.commons.util.JacksonUtil.fileToObject;
import static com.pia.tmf.v4.tmf622.client.ProductOrderClientTestUtil.verifyCommonFields;
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
import com.pia.dnext.v4.tmf622.model.DnextProductOrder;
import com.pia.dnext.v4.tmf622.model.DnextProductOrderCreate;
import com.pia.tmf.common.config.TmfClientConfigurations;
import com.pia.tmf.common.model.RetrievalContext;
import com.pia.tmf.common.model.TmfOffsetRequest;
import com.pia.tmf.v4.common.model.Note;
import com.pia.tmf.v4.tmf622.client.api.ProductOrderClient;
import com.pia.tmf.v4.tmf622.config.ProductOrderClientProvider;
import com.pia.tmf.v4.tmf622.exception.ProductOrderClientException;
import com.pia.tmf.v4.tmf622.model.ProductOrder;
import com.pia.tmf.v4.tmf622.model.ProductOrderCreate;
import com.pia.tmf.v4.tmf622.model.ProductOrderUpdate;
import com.pia.tmf.v4.tmf622.test.util.CoreServiceMockServer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.model.MediaType;
import org.mockserver.model.StringBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author Gokhan Demör
 */
@SpringBootTest
class ProductOrderClientIT extends CoreServiceMockServer {
  private static final String PRODUCT_ORDER_CREATE_JSON = "payload/product_order_create.json";
  private static final String APIX_PATH = "apix";
  private static final String SH_TMF_CLIENTS = "sh-tmf622";
  private static final String APIX_TMF_CLIENTS = "apix-tmf622";
  private static final String ID_JSON_FILTER = "$.id";
  private static final RetrievalContext ID_RETRIEVAL_CONTEXT =
      RetrievalContext.builder().withServerJsonFilter(ID_JSON_FILTER).build();

  private static final String PRODUCT_ORDER_STATE_CHANGE_EVENT_PATH =
      "productOrderStateChangeEvent";

  private static String TEST_PRODUCT_ORDER_PATH = "productOrder";

  @Autowired private TmfClientConfigurations tmfClientConfigurations;

  @Autowired private ProductOrderClientProvider productOrderClientProvider;

  @Autowired private ObjectMapper objectMapper;

  private ProductOrderClient shProductOrderClient;
  private ProductOrderClient apixProductOrderClient;

  @BeforeEach
  void beforeEach() {
    TEST_PRODUCT_ORDER_PATH = RandomStringUtils.randomAlphabetic(5);

    var shClientConfig = tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS);
    shClientConfig.setBaseUrl(BASE_URL);
    shClientConfig.setEndpoint("/" + TEST_PRODUCT_ORDER_PATH);
    shProductOrderClient =
        productOrderClientProvider.getTmfClient(
            tmfClientConfigurations.getTmfClients().get(SH_TMF_CLIENTS), "sh");

    var apixClientConfig = tmfClientConfigurations.getTmfClients().get(APIX_TMF_CLIENTS);
    apixClientConfig.setBaseUrl(BASE_URL + "/" + APIX_PATH);
    apixProductOrderClient =
        productOrderClientProvider.getTmfClient(
            tmfClientConfigurations.getTmfClients().get(APIX_TMF_CLIENTS), "sh");

    resetMockServer();
  }

  @Test
  void test_getProductOrderById_withErrorHTTPCode_shouldReturnProductOrderException() {
    setUpDynamicGetCallback(TEST_PRODUCT_ORDER_PATH);
    var request = shProductOrderClient.get("unknown-id");
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ProductOrderClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND, ((ProductOrderClientException) error).getStatusCode());
              var errorDetail = ((ProductOrderClientException) error).getErrorMessage();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_getProductOrderById_withValidId_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    Mono<ProductOrder> request = shProductOrderClient.get(id);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertEquals(id, productOrder.getId());
            })
        .verifyComplete();
  }

  @Test
  void test_getProductOrderById_withClassType_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    shProductOrderClient.get("1");

    var request = shProductOrderClient.get(id, String.class);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertTrue(productOrder.contains(id));
            })
        .verifyComplete();
  }

  @Test
  void test_getProductOrderById_withJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    Mono<ProductOrder> request = shProductOrderClient.get(id, ID_RETRIEVAL_CONTEXT);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertEquals(id, productOrder.getId());
            })
        .verifyComplete();
  }

  @Test
  void test_getProductOrderById_withClassTypeAndJsonPath_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    var request = shProductOrderClient.get(id, ID_RETRIEVAL_CONTEXT, String.class);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertTrue(productOrder.contains(id));
            })
        .verifyComplete();
  }

  @Test
  void test_getProductOrderById_withFields_shouldReturnOkay() {
    setUpDynamicGetCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    Mono<ProductOrder> request =
        shProductOrderClient.get(id, RetrievalContext.builder().withFields("id").build());
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertEquals(id, productOrder.getId());
            })
        .verifyComplete();
  }

  @Test
  void test_getProductOrder_withValidData_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request = shProductOrderClient.list();

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getProductOrder_withMultiValueMap_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request = shProductOrderClient.list(new LinkedMultiValueMap<>());

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getProductOrder_withTmfOffsetRequest_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request = shProductOrderClient.list(TmfOffsetRequest.of(20, 5));

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(5)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getProductOrder_withTmfOffsetRequestAndMultiValueMap_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request =
        shProductOrderClient.list(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(5)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getAllProductOrders_withValidData_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request = shProductOrderClient.listAll();

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(30)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getAllProductOrders_withMultiValueMap_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request = shProductOrderClient.listAll(new LinkedMultiValueMap<>());

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(30)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getAllProductOrders_withTmfOffsetRequest_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request = shProductOrderClient.listAll(TmfOffsetRequest.of(20, 5));

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getAllProductOrders_withTmfOffsetReqeustAndMultiValueMap_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    Flux<ProductOrder> request =
        shProductOrderClient.listAll(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(20, 5));

    List<ProductOrder> productOrders = new ArrayList<>();
    StepVerifier.create(request)
        .recordWith(() -> productOrders)
        .expectNextCount(10)
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getAllProductOrders_withValidDataErrorHTTPCode_shouldThrowProductOrderException() {
    expectGetMethod(
        "/" + TEST_PRODUCT_ORDER_PATH,
        new StringBody("{\"code\":\"Code\"}", MediaType.APPLICATION_JSON),
        HttpStatus.INTERNAL_SERVER_ERROR);
    Flux<ProductOrder> request = shProductOrderClient.listAll();

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ProductOrderClientException.class, error);
              assertSame(
                  HttpStatus.INTERNAL_SERVER_ERROR,
                  ((ProductOrderClientException) error).getStatusCode());
              var errorDetail = ((ProductOrderClientException) error).getErrorMessage();
              assertNotNull(errorDetail);
              assertEquals("Code", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_getPagedProductOrders_withValidData_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    var request = shProductOrderClient.listPaged();

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();

    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getPagedProductOrders_withMultiValueMap_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    var request = shProductOrderClient.listPaged(new LinkedMultiValueMap<>());

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(10, result.getSize());
            })
        .verifyComplete();
    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getPagedProductOrders_withdPageRequest_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    var request = shProductOrderClient.listPaged(TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();
    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_getPagedProductOrders_withMultiValueMapAndPageRequest_shouldReturnOkay() {
    setUpDynamicGetListCallback(TEST_PRODUCT_ORDER_PATH);
    List<String> ids = insertProductOrderToMockCache(TEST_PRODUCT_ORDER_PATH, 30);
    var request =
        shProductOrderClient.listPaged(new LinkedMultiValueMap<>(), TmfOffsetRequest.of(10, 5));

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getContent());
              assertEquals(30, result.getTotalElements());
              assertEquals(5, result.getSize());
            })
        .verifyComplete();
    deleteDataFromMockServerCache(TEST_PRODUCT_ORDER_PATH, ids);
  }

  @Test
  void test_postProductOrder_withValidData_shouldReturnCreated() {
    setUpDynamicPostCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    Mono<ProductOrder> request = shProductOrderClient.post(create);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertNotNull(result.getId());
            })
        .verifyComplete();
  }

  @Test
  void test_postProductOrder_withValidDataClassType_shouldReturnCreated() {
    setUpDynamicPostCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    Mono<String> request = shProductOrderClient.post(create, String.class);

    StepVerifier.create(request)
        .assertNext(
            result -> {
              assertNotNull(result);
              assertTrue(result.contains("id"));
            })
        .verifyComplete();
  }

  @Test
  void test_postProductOrder_withDnextProductOrder_shouldReturnCreated() {
    setUpDynamicPostCallback(APIX_PATH + "/productOrder");
    var productOrderCreate = fileToObject(PRODUCT_ORDER_CREATE_JSON, DnextProductOrderCreate.class);
    var response = this.apixProductOrderClient.post(productOrderCreate, DnextProductOrder.class);
    ProductOrderClientTestUtil.verifyOk(response);
  }

  @Test
  void test_postProductOrder_withNoRequestPayload_shouldReturnNPE() {
    var request = shProductOrderClient.post(null);
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(NullPointerException.class, error);
              assertEquals("Empty body received from ProductOrder", error.getMessage());
              return true;
            })
        .verify();
  }

  @Test
  void test_patchProductOrder_withValidData_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    Mono<ProductOrder> request = shProductOrderClient.patch(id, patch);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertNotNull(productOrder.getId());
              assertNotNull(productOrder.getNotes());
            })
        .verifyComplete();
  }

  @Test
  void test_patchProductOrder_withClassType_shouldReturnOkay() throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = shProductOrderClient.patch(id, patch, String.class);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertTrue(productOrder.contains("id"));
              assertTrue(productOrder.contains("note"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchProductOrder_withDnextProductOrderClassType_shouldReturnOkay()
      throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_PRODUCT_ORDER_PATH);
    DnextProductOrderCreate create =
        fileToObject(PRODUCT_ORDER_CREATE_JSON, DnextProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = shProductOrderClient.patch(id, patch, DnextProductOrder.class);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              verifyCommonFields(productOrder);
            })
        .verifyComplete();
  }

  @Test
  void test_patchProductOrder_withValidDataAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    ProductOrderUpdate update = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderUpdate.class);
    update.setDescription("Updated Description");
    Mono<ProductOrder> request = shProductOrderClient.patch(id, update);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertNotNull(productOrder.getId());
              assertNotNull(productOrder.getDescription());
              assertEquals("Updated Description", productOrder.getDescription());
            })
        .verifyComplete();
  }

  @Test
  void test_patchProductOrder_withClassTypeAndAndMergePatch_shouldReturnOkay() {
    setUpDynamicMergePatchCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    ProductOrderUpdate update = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderUpdate.class);
    update.setDescription("Updated Description");
    var request = shProductOrderClient.patch(id, update, String.class);
    StepVerifier.create(request)
        .assertNext(
            productOrder -> {
              assertNotNull(productOrder);
              assertTrue(productOrder.contains("id"));
              assertTrue(productOrder.contains("Updated Description"));
            })
        .verifyComplete();
  }

  @Test
  void test_patchProductOrder_withNoProductOrderId_shouldNPE() throws JsonPointerException {
    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));

    var request = shProductOrderClient.patch(null, patch);
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(NullPointerException.class, error);
              assertEquals("ID can not be empty", error.getMessage());
              return true;
            })
        .verify();
  }

  @Test
  void test_patchProductOrder_withInValidProductOrderJsonPatch_shouldThrowProductOrderException()
      throws JsonPointerException {
    setUpDynamicJsonPatchCallback(TEST_PRODUCT_ORDER_PATH);
    var patch =
        new JsonPatch(
            List.of(
                new AddOperation(
                    new JsonPointer("/note"), objectMapper.valueToTree(List.of(new Note())))));
    var request = shProductOrderClient.patch("unknown-id", patch);

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ProductOrderClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND, ((ProductOrderClientException) error).getStatusCode());
              var errorDetail = ((ProductOrderClientException) error).getErrorMessage();
              assertNotNull(errorDetail);
              assertEquals("404", errorDetail.getCode());
              return true;
            })
        .verify();
  }

  @Test
  void test_deleteProductOrder_withNoProductOrderId_shouldReturnNPE() {
    var request = shProductOrderClient.delete(null);
    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(NullPointerException.class, error);
              assertEquals("ID can not be empty", error.getMessage());
              return true;
            })
        .verify();
  }

  @Test
  void test_deleteProductOrder_withValidData_shouldNoContent() {
    setUpDynamicDeleteCallback(TEST_PRODUCT_ORDER_PATH);
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    String id = addDataToMockServerCache(TEST_PRODUCT_ORDER_PATH, create);

    var request = shProductOrderClient.delete(id);
    StepVerifier.create(request).verifyComplete();
  }

  @Test
  void test_deleteProductOrder_withInValidProductOrderId_shouldReturnProductOrderException() {
    setUpDynamicDeleteCallback(TEST_PRODUCT_ORDER_PATH);
    var request = shProductOrderClient.delete("unknown-id");

    StepVerifier.create(request)
        .expectErrorMatches(
            error -> {
              assertInstanceOf(ProductOrderClientException.class, error);
              assertSame(
                  HttpStatus.NOT_FOUND, ((ProductOrderClientException) error).getStatusCode());
              return true;
            })
        .verify();
  }

  private URI getCallbackUri() {
    return URI.create(BASE_URL + "/listener/" + PRODUCT_ORDER_STATE_CHANGE_EVENT_PATH);
  }

  private List<String> insertProductOrderToMockCache(String path, int count) {
    List<String> ids = new ArrayList<>();
    ProductOrderCreate create = fileToObject(PRODUCT_ORDER_CREATE_JSON, ProductOrderCreate.class);
    for (int i = 0; i < count; i++) {
      ids.add(addDataToMockServerCache(path, create));
    }
    return ids;
  }
}
