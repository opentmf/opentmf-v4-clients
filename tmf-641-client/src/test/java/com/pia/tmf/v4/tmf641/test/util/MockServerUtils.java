package com.pia.tmf.v4.tmf641.test.util;

import static com.pia.commons.util.JacksonUtil.objectToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;
import static org.mockserver.model.MediaType.APPLICATION_JSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.pia.commons.util.JacksonUtil;
import com.pia.mockserver.callback.*;
import com.pia.tmf.v4.common.model.TmfClientCommonsConstants;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.MatchType;
import org.mockserver.matchers.Times;
import org.mockserver.model.*;
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

  public static void deleteDataFromMockServerCache(String path, List<String> ids) {
    ids.forEach(id -> deleteDataFromMockServerCache(path, id));
  }

  public static void deleteDataFromMockServerCache(String path, String id) {
    HttpRequest httpRequest = new HttpRequest().withPath(path + "/" + id);
    DynamicDeleteCallback dynamicDeleteCallback = new DynamicDeleteCallback();
    HttpResponse response = dynamicDeleteCallback.handle(httpRequest);
    assertEquals(204, response.getStatusCode());
  }

  public static void setupPostCreated(String path, String returnPayload) {
    expectPostMethod(path, new StringBody(returnPayload, APPLICATION_JSON), HttpStatus.CREATED);
  }

  public static void setupPostNoContent(String path, String requestPayload) {
    setUpExpectation(HttpMethod.POST.name(), path, requestPayload, HttpStatus.NO_CONTENT);
  }

  public static void setupPostNoContent(String path) {
    setupPost(path, HttpStatus.NO_CONTENT);
  }

  public static void setupPost(String path, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.POST.name(), path, httpStatus);
  }

  public static void setupPost(String path, String returnPayload, HttpStatus httpStatus) {
    expectPostMethod(path, new StringBody(returnPayload, APPLICATION_JSON), httpStatus);
  }

  public static void expectPostMethod(
      String path, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.POST.name(), path, body, httpStatus);
  }

  public static void expectPostMethod(
      String path, String requestBody, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.POST.name(), path, requestBody, body, httpStatus);
  }

  public static void expectPostMethodWithHeaders(
      String path,
      String requestBody,
      BodyWithContentType<?> body,
      HttpStatus httpStatus,
      List<Header> headers) {
    setUpExpectation(HttpMethod.POST.name(), path, requestBody, body, httpStatus, headers);
  }

  public static void expectPatchMethod(
      String path, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.PATCH.name(), path, body, httpStatus);
  }

  public static void expectPatchMethod(
      String path, String requestBody, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.PATCH.name(), path, requestBody, body, httpStatus);
  }

  public static void expectGetMethod(
      String path, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.GET.name(), path, body, httpStatus);
  }

  public static void expectGetMethod(
      String path, List<Parameter> parameters, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.GET.name(), path, parameters, body, httpStatus);
  }

  public static void expectGetMethodWithHeaders(
      String path, BodyWithContentType<?> body, HttpStatus httpStatus, List<Header> headers) {
    setUpExpectation(HttpMethod.GET.name(), path, body, httpStatus, headers);
  }

  public static void expectPostMethodWithRetry(
      int numRetries,
      String path,
      HttpStatus beforeRetryHttpStatus,
      BodyWithContentType<?> body,
      HttpStatus afterRetryHttpsStatus) {
    setupRetryExpectations(
        numRetries, HttpMethod.POST, path, beforeRetryHttpStatus, body, afterRetryHttpsStatus);
  }

  public static void expectGetMethodWithRetry(
      int numRetries,
      String path,
      HttpStatus errorHttpStatus,
      BodyWithContentType<?> body,
      HttpStatus successHttpStatus) {
    setupRetryExpectations(
        numRetries, HttpMethod.GET, path, errorHttpStatus, body, successHttpStatus);
  }

  public static void expectGetMethodWithRetry(
      int numRetries,
      String path,
      List<Parameter> parameters,
      BodyWithContentType<?> firstBody,
      BodyWithContentType<?> secondBody,
      HttpStatus status) {
    setupRetryExpectations(
        numRetries, HttpMethod.GET, path, parameters, firstBody, secondBody, status);
  }

  public static void expectPostMethodWithRetry(
      int numRetries,
      String path,
      BodyWithContentType<?> firstBody,
      BodyWithContentType<?> secondBody,
      HttpStatus status) {
    setupRetryExpectations(numRetries, HttpMethod.POST, path, firstBody, secondBody, status);
  }

  public static void oneTimeExpectation(
      String httpMethod,
      String path,
      List<Parameter> requestParameters,
      BodyWithContentType<?> requestBody,
      HttpStatus responseStatus,
      BodyWithContentType<?> responseBody) {

    clientAndServer
        .when(
            request()
                .withMethod(httpMethod)
                .withPath(path)
                .withBody(requestBody)
                .withQueryStringParameters(requestParameters),
            Times.exactly(1))
        .respond(response().withBody(responseBody).withStatusCode(responseStatus.value()));
  }

  public static void get(
      String path,
      List<Parameter> requestParameters,
      int count,
      HttpStatus responseStatus,
      Object responseBody) {

    clientAndServer
        .when(
            request().withMethod("GET").withPath(path).withQueryStringParameters(requestParameters),
            Times.exactly(count))
        .respond(
            response()
                .withBody(new JsonBody(objectToJson(responseBody)))
                .withStatusCode(responseStatus.value()));
  }

  public static void get(
      String path, List<Parameter> requestParameters, int count, HttpStatus responseStatus) {

    clientAndServer
        .when(
            request().withMethod("GET").withPath(path).withQueryStringParameters(requestParameters),
            Times.exactly(count))
        .respond(response().withStatusCode(responseStatus.value()));
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

  public static void post(
      String path, Object requestBody, HttpStatus responseStatus, Object responseBody) {

    clientAndServer
        .when(
            request()
                .withMethod("POST")
                .withPath(path)
                .withBody(json(requestBody, MatchType.ONLY_MATCHING_FIELDS)),
            Times.once())
        .respond(
            response()
                .withBody(new JsonBody(objectToJson(responseBody)))
                .withStatusCode(responseStatus.value()));
  }

  public static void patch(
      String path, int count, Object requestBody, HttpStatus responseStatus, Object responseBody) {

    clientAndServer
        .when(
            request()
                .withMethod("PATCH")
                .withPath(path)
                .withBody(json(requestBody, MatchType.ONLY_MATCHING_FIELDS)),
            Times.exactly(count))
        .respond(
            response()
                .withBody(new JsonBody(objectToJson(responseBody)))
                .withStatusCode(responseStatus.value()));
  }

  public static void patch(String path, int count, HttpStatus responseStatus, Object responseBody) {

    clientAndServer
        .when(request().withMethod("PATCH").withPath(path), Times.exactly(count))
        .respond(
            response()
                .withBody(new JsonBody(objectToJson(responseBody)))
                .withStatusCode(responseStatus.value()));
  }

  public static void patch(String path, HttpStatus responseStatus, Object responseBody) {

    clientAndServer
        .when(request().withMethod("PATCH").withPath(path), Times.exactly(1))
        .respond(
            response()
                .withBody(new JsonBody(objectToJson(responseBody)))
                .withStatusCode(responseStatus.value()));
  }

  private static void setUpExpectation(
      String httpMethod, String path, BodyWithContentType<?> body, HttpStatus httpStatus) {

    clientAndServer
        .when(request().withMethod(httpMethod).withPath(path))
        .respond(response().withBody(body).withStatusCode(httpStatus.value()));
  }

  private static void setUpExpectation(
      String httpMethod,
      String path,
      String requestBody,
      BodyWithContentType<?> body,
      HttpStatus httpStatus,
      List<Header> headers) {

    clientAndServer
        .when(
            request()
                .withMethod(httpMethod)
                .withPath(path)
                .withBody(json(requestBody, MatchType.ONLY_MATCHING_FIELDS))
                .withHeaders(headers))
        .respond(response().withBody(body).withStatusCode(httpStatus.value()));
  }

  private static void setUpExpectation(
      String httpMethod,
      String path,
      BodyWithContentType<?> body,
      HttpStatus httpStatus,
      List<Header> headers) {

    clientAndServer
        .when(request().withMethod(httpMethod).withPath(path).withHeaders(headers))
        .respond(response().withBody(body).withStatusCode(httpStatus.value()));
  }

  public static void setUpExpectation(String httpMethod, String path, HttpStatus httpStatus) {

    clientAndServer
        .when(request().withMethod(httpMethod).withPath(path))
        .respond(response().withStatusCode(httpStatus.value()));
  }

  private static void setUpExpectation(
      String httpMethod,
      String path,
      String requestBody,
      BodyWithContentType<?> body,
      HttpStatus httpStatus) {

    clientAndServer
        .when(
            request()
                .withMethod(httpMethod)
                .withPath(path)
                .withBody(json(requestBody, MatchType.ONLY_MATCHING_FIELDS)))
        .respond(response().withBody(body).withStatusCode(httpStatus.value()));
  }

  private static void setUpExpectation(
      String httpMethod, String path, String requestBody, HttpStatus httpStatus) {
    clientAndServer
        .when(
            request()
                .withMethod(httpMethod)
                .withPath(path)
                .withBody(json(requestBody, MatchType.ONLY_MATCHING_FIELDS)))
        .respond(response().withStatusCode(httpStatus.value()));
  }

  private static void setUpExpectation(
      String httpMethod,
      String path,
      List<Parameter> parameters,
      BodyWithContentType<?> body,
      HttpStatus httpStatus) {

    clientAndServer
        .when(request().withMethod(httpMethod).withPath(path).withQueryStringParameters(parameters))
        .respond(response().withBody(body).withStatusCode(httpStatus.value()));
  }

  public static void setupRetryExpectations(
      int numRetries,
      HttpMethod httpMethod,
      String path,
      HttpStatus beforeRetryHttpStatus,
      BodyWithContentType<?> body,
      HttpStatus afterRetryHttpsStatus) {

    AtomicInteger counter = new AtomicInteger(0);
    // @formatter:off
    clientAndServer
        .when(request().withMethod(httpMethod.name()).withPath(path))
        .respond(
            (HttpRequest httpRequest) -> {
              int retryAttempt = counter.incrementAndGet();
              // The first request should return the simulated error 500_xxx status
              if (numRetries > 1 && retryAttempt >= numRetries) {
                return response().withBody(body).withStatusCode(afterRetryHttpsStatus.value());
              } else {
                return response().withStatusCode(beforeRetryHttpStatus.value());
              }
            });
    // @formatter:on
  }

  public static void setupRetryExpectations(
      int numRetries,
      HttpMethod httpMethod,
      String path,
      List<Parameter> parameters,
      BodyWithContentType<?> firstBody,
      BodyWithContentType<?> secondBody,
      HttpStatus status) {

    AtomicInteger counter = new AtomicInteger(0);
    // @formatter:off
    clientAndServer
        .when(
            request()
                .withMethod(httpMethod.name())
                .withPath(path)
                .withQueryStringParameters(parameters))
        .respond(
            (HttpRequest httpRequest) -> {
              int retryAttempt = counter.incrementAndGet();
              // The first request should return the simulated error 500_xxx status
              if (numRetries > 1 && retryAttempt >= numRetries) {
                return response().withBody(secondBody).withStatusCode(status.value());
              } else {
                return response().withBody(firstBody).withStatusCode(status.value());
              }
            });
    // @formatter:on
  }

  public static void setupRetryExpectations(
      int numRetries,
      HttpMethod httpMethod,
      String path,
      BodyWithContentType<?> firstBody,
      BodyWithContentType<?> secondBody,
      HttpStatus status) {

    AtomicInteger counter = new AtomicInteger(0);
    // @formatter:off
    clientAndServer
        .when(request().withMethod(httpMethod.name()).withPath(path))
        .respond(
            (HttpRequest httpRequest) -> {
              int retryAttempt = counter.incrementAndGet();
              // The first request should return the simulated error 500_xxx status
              if (numRetries > 1 && retryAttempt >= numRetries) {
                return response().withBody(secondBody).withStatusCode(status.value());
              } else {
                return response().withBody(firstBody).withStatusCode(status.value());
              }
            });
    // @formatter:on
  }
}
