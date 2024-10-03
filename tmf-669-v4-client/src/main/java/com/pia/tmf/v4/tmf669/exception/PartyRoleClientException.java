package com.pia.tmf.v4.tmf669.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class PartyRoleClientException extends TmfClientException {

  public PartyRoleClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public PartyRoleClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public PartyRoleClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
