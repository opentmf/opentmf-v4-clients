package com.pia.tmf.v4.common.exception;

import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class HubClientException extends TmfClientException {

  public HubClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }

  public HubClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public HubClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
