package org.opentmf.v4.tmf666.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class BillingAccountClientException extends TmfClientException {

  public BillingAccountClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public BillingAccountClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public BillingAccountClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
