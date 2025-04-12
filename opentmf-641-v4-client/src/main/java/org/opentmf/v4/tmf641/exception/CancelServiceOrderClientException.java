package org.opentmf.v4.tmf641.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Pablo Garcia
 */
public class CancelServiceOrderClientException extends TmfClientException {

  public CancelServiceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CancelServiceOrderClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }

  public CancelServiceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
