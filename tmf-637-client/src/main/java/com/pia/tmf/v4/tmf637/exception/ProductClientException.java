package com.pia.tmf.v4.tmf637.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ProductClientException extends TmfClientException {

  public ProductClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public ProductClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public ProductClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
