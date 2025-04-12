package org.opentmf.v4.tmf634.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceCategoryClientException extends TmfClientException {

  public ResourceCategoryClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceCategoryClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceCategoryClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
