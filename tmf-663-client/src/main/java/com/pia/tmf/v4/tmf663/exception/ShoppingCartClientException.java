package com.pia.tmf.v4.tmf663.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ShoppingCartClientException extends TmfClientException {

  public ShoppingCartClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public ShoppingCartClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public ShoppingCartClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
