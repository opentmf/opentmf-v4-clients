package com.pia.tmf.v4.tmf632.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class OrganizationClientException extends TmfClientException {

  public OrganizationClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public OrganizationClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public OrganizationClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
