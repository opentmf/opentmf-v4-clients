package com.pia.tmf.v4.tmf629.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class CustomerClientException extends TmfClientException {

  public CustomerClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public CustomerClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public CustomerClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
