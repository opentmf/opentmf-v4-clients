package org.opentmf.v4.tmf663.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ShoppingCartClientException extends TmfClientException {

  public ShoppingCartClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public ShoppingCartClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public ShoppingCartClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
