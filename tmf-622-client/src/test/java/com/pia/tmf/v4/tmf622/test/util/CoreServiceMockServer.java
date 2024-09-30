package com.pia.tmf.v4.tmf622.test.util;

import static com.pia.commons.util.JacksonUtil.contents;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.OK;

import com.fasterxml.jackson.databind.JsonNode;
import com.pia.commons.util.JacksonUtil;
import com.pia.mockserver.callback.*;
import com.pia.tmf.v4.common.model.TmfClientCommonsConstants;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.StringBody;
import org.springframework.http.HttpMethod;

/**
 * @author Cezmi Aslan
 */
public class CoreServiceMockServer extends MockServerUtils {

  public static void setUpDynamicPostCallback(String path) {
    clientAndServer
            .when(request().withMethod(HttpMethod.POST.name()).withPath("/" + path))
            .respond(new DynamicPostCallback());
  }

  public static void setUpDynamicGetCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.GET.name())
                            .withPath("/" + path + "/{id}")
                            .withPathParameter("id", ".*"))
            .respond(new DynamicGetCallback());
  }

  public static void setUpDynamicJsonPatchCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.PATCH.name())
                            .withPath("/" + path + "/{id}")
                            .withPathParameter("id", ".*")
                            .withHeader("Content-Type", TmfClientCommonsConstants.MEDIA_TYPE_JSON_PATCH))
            .respond(new DynamicJsonPatchCallback());
  }

  public static void setUpDynamicMergePatchCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.PATCH.name())
                            .withPath("/" + path + "/{id}")
                            .withPathParameter("id", ".*")
                            .withHeader("Content-Type", TmfClientCommonsConstants.MEDIA_TYPE_MERGE_PATCH))
            .respond(new DynamicMergePatchCallback());
  }

  public static void setUpDynamicGetListCallback(String path) {
    clientAndServer
            .when(request().withMethod(HttpMethod.GET.name()).withPath("/" + path))
            .respond(new DynamicGetListCallback());
  }

  public static void setUpDynamicDeleteCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.DELETE.name())
                            .withPath("/" + path + "/{id}")
                            .withPathParameter("id", ".*"))
            .respond(new DynamicDeleteCallback());
  }

  public static String addDataToMockServerCache(String path, Object json) {
    HttpRequest httpRequest =
            new HttpRequest().withPath(path).withBody(JacksonUtil.objectToJson(json));
    DynamicPostCallback dynamicPostCallback = new DynamicPostCallback();
    HttpResponse response = dynamicPostCallback.handle(httpRequest);
    assertEquals(200, response.getStatusCode());
    JsonNode responseJson = JacksonUtil.jsonToTree(response.getBodyAsString());
    assertNotNull(responseJson.get("id").asText());
    return responseJson.get("id").asText();
  }

  public static void deleteDataFromMockServerCache(String path, String id) {
    HttpRequest httpRequest = new HttpRequest().withPath(path + "/" + id);
    DynamicDeleteCallback dynamicDeleteCallback = new DynamicDeleteCallback();
    HttpResponse response = dynamicDeleteCallback.handle(httpRequest);
    assertEquals(204, response.getStatusCode());
  }

  public static void mockPostProductOrder(
      String productOrderId) {
    setupPostCreated(
        "/productOrder",
        contents("payload/productOrder/" + productOrderId + ".json"));
  }

  public static void mockGetProductOrder(
      String productOrderId) {
    setupGetOk(
        "/productOrder",
        productOrderId,
        contents("payload/productOrder/" + productOrderId + ".json"));
  }


  public static void setupGetOk(String path, String id, String responsePayload) {
    StringBody body = new StringBody(responsePayload, APPLICATION_JSON);
    expectGetMethod(path + "/" + id, body, OK);
  }

}
