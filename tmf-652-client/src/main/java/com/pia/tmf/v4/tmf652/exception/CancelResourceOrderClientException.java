package com.pia.tmf.v4.tmf652.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class CancelResourceOrderClientException extends TmfClientException {

  public CancelResourceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CancelResourceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public CancelResourceOrderClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
