package com.pia.tmf.v4.tmf622.mock;

import static com.pia.commons.util.JacksonUtil.jsonToMap;
import static com.pia.commons.util.JacksonUtil.objectToJson;

import java.util.Map;
import java.util.UUID;
import org.mockserver.mock.action.ExpectationResponseCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;

/**
 * @author Cezmi Aslan
 */
public class RequestBodyPlusIdCallback implements ExpectationResponseCallback {

  private final int statusCode;

  public RequestBodyPlusIdCallback(int statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public HttpResponse handle(HttpRequest httpRequest) {
    String body = httpRequest.getBodyAsString();
    Map<String, Object> parsedBody = jsonToMap(body);
    String id = UUID.randomUUID().toString();
    parsedBody.put("id", id);
    String newJson = objectToJson(parsedBody);
    return HttpResponse.response()
        .withStatusCode(statusCode)
        .withContentType(MediaType.APPLICATION_JSON)
        .withBody(newJson);
  }
}
