package org.opentmf.v4.tmf639.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceClientException extends TmfClientException {

  public ResourceClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
