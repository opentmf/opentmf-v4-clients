package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class BillingCycleSpecificationClientException extends TmfClientException {

  public BillingCycleSpecificationClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public BillingCycleSpecificationClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public BillingCycleSpecificationClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
