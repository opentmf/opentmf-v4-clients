package com.pia.tmf.v4.tmf641.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Pablo Garcia
 */
public class CancelServiceOrderClientException extends TmfClientException {

  public CancelServiceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CancelServiceOrderClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }

  public CancelServiceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
