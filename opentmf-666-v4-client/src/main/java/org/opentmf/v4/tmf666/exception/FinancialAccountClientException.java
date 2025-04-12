package org.opentmf.v4.tmf666.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class FinancialAccountClientException extends TmfClientException {

  public FinancialAccountClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public FinancialAccountClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public FinancialAccountClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
