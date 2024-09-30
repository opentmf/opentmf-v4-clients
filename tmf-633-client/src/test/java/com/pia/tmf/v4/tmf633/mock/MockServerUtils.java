package com.pia.tmf.v4.tmf633.mock;

import static com.pia.commons.util.JacksonUtil.objectToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.MediaType.APPLICATION_JSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.pia.commons.util.JacksonUtil;
import com.pia.mockserver.callback.*;
import com.pia.tmf.v4.common.model.TmfClientCommonsConstants;
import java.util.List;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * @author Yusuf Bozkurt
 */
public class MockServerUtils {

  public static final ClientAndServer clientAndServer = new ClientAndServer();
  public static final String BASE_URL = "http://localhost:" + clientAndServer.getLocalPort();

  public static void resetMockServer() {
    if (clientAndServer.isRunning()) {
      clientAndServer.reset();
    }
  }

  public static void setUpDynamicPostCallback(String path) {
    clientAndServer
            .when(request().withMethod(HttpMethod.POST.name()).withPath(path))
            .respond(new DynamicPostCallback());
  }

  public static void setUpDynamicGetCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.GET.name())
                            .withPath(path + "/{id}")
                            .withPathParameter("id", ".*"))
            .respond(new DynamicGetCallback());
  }

  public static void setUpDynamicJsonPatchCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.PATCH.name())
                            .withPath(path + "/{id}")
                            .withPathParameter("id", ".*")
                            .withHeader("Content-Type", TmfClientCommonsConstants.MEDIA_TYPE_JSON_PATCH))
            .respond(new DynamicJsonPatchCallback());
  }

  public static void setUpDynamicMergePatchCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.PATCH.name())
                            .withPath(path + "/{id}")
                            .withPathParameter("id", ".*")
                            .withHeader("Content-Type", TmfClientCommonsConstants.MEDIA_TYPE_MERGE_PATCH))
            .respond(new DynamicMergePatchCallback());
  }

  public static void setUpDynamicGetListCallback(String path) {
    clientAndServer
            .when(request().withMethod(HttpMethod.GET.name()).withPath(path))
            .respond(new DynamicGetListCallback());
  }

  public static void setUpDynamicDeleteCallback(String path) {
    clientAndServer
            .when(
                    request()
                            .withMethod(HttpMethod.DELETE.name())
                            .withPath(path + "/{id}")
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

  public static void deleteDataFromMockServerCache(String path, List<String> ids) {
    ids.forEach(id -> deleteDataFromMockServerCache(path, id));
  }

  public static void deleteDataFromMockServerCache(String path, String id) {
    HttpRequest httpRequest = new HttpRequest().withPath(path + "/" + id);
    DynamicDeleteCallback dynamicDeleteCallback = new DynamicDeleteCallback();
    HttpResponse response = dynamicDeleteCallback.handle(httpRequest);
    assertEquals(204, response.getStatusCode());
  }

  /**
   * Adds id to the original request body and returns as enriched response body
   *
   * @param path path
   * @param responseStatus preferred response status
   */
  public static void postReturnRequestBodyPlusId(String path, HttpStatus responseStatus) {

    clientAndServer
        .when(request().withMethod("POST").withPath(path), Times.once())
        .respond(new RequestBodyPlusIdCallback(responseStatus.value()));
  }

  public static void setupPost(String path, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.POST.name(), path, httpStatus);
  }

  public static void get(String path, int count, HttpStatus responseStatus, Object responseBody) {

    clientAndServer
        .when(request().withMethod("GET").withPath(path), Times.exactly(count))
        .respond(
            response()
                .withContentType(APPLICATION_JSON)
                .withBody(new JsonBody(objectToJson(responseBody)))
                .withStatusCode(responseStatus.value()));
  }

  public static void setUpExpectation(String httpMethod, String path, HttpStatus httpStatus) {

    clientAndServer
        .when(request().withMethod(httpMethod).withPath(path))
        .respond(response().withStatusCode(httpStatus.value()));
  }
}
