package org.opentmf.v4.tmf669.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
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
