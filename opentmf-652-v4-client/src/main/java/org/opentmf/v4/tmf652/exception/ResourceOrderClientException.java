package org.opentmf.v4.tmf652.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceOrderClientException extends TmfClientException {

  public ResourceOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceOrderClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
