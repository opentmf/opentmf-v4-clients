package org.opentmf.v4.tmf634.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceCandidateClientException extends TmfClientException {

  public ResourceCandidateClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceCandidateClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceCandidateClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
