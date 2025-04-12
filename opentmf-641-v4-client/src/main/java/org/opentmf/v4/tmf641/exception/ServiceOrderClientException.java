package org.opentmf.v4.tmf641.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Pablo Garcia
 */
public class ServiceOrderClientException extends TmfClientException {

  public ServiceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ServiceOrderClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }

  public ServiceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
