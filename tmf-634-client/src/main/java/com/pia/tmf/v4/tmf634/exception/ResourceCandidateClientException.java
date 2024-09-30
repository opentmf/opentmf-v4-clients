package com.pia.tmf.v4.tmf634.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
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

  public ResourceCandidateClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
