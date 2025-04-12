package org.opentmf.v4.tmf629.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class CustomerClientException extends TmfClientException {

  public CustomerClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public CustomerClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public CustomerClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
