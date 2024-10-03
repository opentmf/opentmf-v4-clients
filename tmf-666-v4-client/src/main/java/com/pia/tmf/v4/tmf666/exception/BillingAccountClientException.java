package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
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
