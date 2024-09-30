package com.pia.tmf.v4.tmf622.test.util;

import static com.pia.commons.util.JacksonUtil.objectToJson;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.MediaType.APPLICATION_JSON;

import com.pia.tmf.v4.tmf622.mock.RequestBodyPlusIdCallback;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.BodyWithContentType;
import org.mockserver.model.JsonBody;
import org.mockserver.model.StringBody;
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

  public static void setupPostCreated(String path, String returnPayload) {
    expectPostMethod(path, new StringBody(returnPayload, APPLICATION_JSON), HttpStatus.CREATED);
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

  public static void expectGetMethod(
      String path, BodyWithContentType<?> body, HttpStatus httpStatus) {
    setUpExpectation(HttpMethod.GET.name(), path, body, httpStatus);
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

  public static void patch(String path, int count, HttpStatus responseStatus, Object responseBody) {

    clientAndServer
        .when(request().withMethod("PATCH").withPath(path), Times.exactly(count))
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

  public static void setUpExpectation(String httpMethod, String path, HttpStatus httpStatus) {

    clientAndServer
        .when(request().withMethod(httpMethod).withPath(path))
        .respond(response().withStatusCode(httpStatus.value()));
  }
}
