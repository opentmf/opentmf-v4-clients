package com.pia.tmf.v4.tmf632.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class IndividualClientException extends TmfClientException {

  public IndividualClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public IndividualClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public IndividualClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
