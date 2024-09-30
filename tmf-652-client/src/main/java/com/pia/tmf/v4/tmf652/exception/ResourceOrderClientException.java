package com.pia.tmf.v4.tmf652.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceOrderClientException extends TmfClientException {

  public ResourceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceOrderClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
