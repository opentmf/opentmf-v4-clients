package org.opentmf.v4.tmf632.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class IndividualClientException extends TmfClientException {

  public IndividualClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public IndividualClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public IndividualClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
