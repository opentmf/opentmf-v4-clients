package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class FinancialAccountClientException extends TmfClientException {

  public FinancialAccountClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public FinancialAccountClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public FinancialAccountClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
