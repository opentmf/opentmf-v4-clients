package com.pia.tmf.v4.tmf641.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Pablo Garcia
 */
public class ServiceOrderClientException extends TmfClientException {

  public ServiceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ServiceOrderClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }

  public ServiceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
