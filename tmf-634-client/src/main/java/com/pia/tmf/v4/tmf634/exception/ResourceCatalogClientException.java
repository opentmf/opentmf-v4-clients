package com.pia.tmf.v4.tmf634.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ResourceCatalogClientException extends TmfClientException {

  public ResourceCatalogClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ResourceCatalogClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ResourceCatalogClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
