package org.opentmf.v4.tmf637.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ProductClientException extends TmfClientException {

  public ProductClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public ProductClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public ProductClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
