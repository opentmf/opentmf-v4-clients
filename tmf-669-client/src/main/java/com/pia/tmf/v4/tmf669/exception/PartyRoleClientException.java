package com.pia.tmf.v4.tmf669.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class PartyRoleClientException extends TmfClientException {

  public PartyRoleClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public PartyRoleClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }

  public PartyRoleClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
