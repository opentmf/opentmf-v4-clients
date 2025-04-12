package org.opentmf.v4.tmf652.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class CancelResourceOrderClientException extends TmfClientException {

  public CancelResourceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CancelResourceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public CancelResourceOrderClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
