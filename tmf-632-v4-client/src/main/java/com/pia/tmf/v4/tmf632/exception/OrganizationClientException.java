package com.pia.tmf.v4.tmf632.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class OrganizationClientException extends TmfClientException {

  public OrganizationClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public OrganizationClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public OrganizationClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
