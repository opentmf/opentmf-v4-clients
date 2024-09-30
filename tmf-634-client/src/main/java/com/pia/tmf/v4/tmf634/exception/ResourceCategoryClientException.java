package com.pia.tmf.v4.tmf634.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
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

  public ResourceCategoryClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
