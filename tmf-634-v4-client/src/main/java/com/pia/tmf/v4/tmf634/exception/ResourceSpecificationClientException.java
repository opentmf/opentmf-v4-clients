package com.pia.tmf.v4.tmf634.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceSpecificationClientException extends TmfClientException {

  public ResourceSpecificationClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceSpecificationClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceSpecificationClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
