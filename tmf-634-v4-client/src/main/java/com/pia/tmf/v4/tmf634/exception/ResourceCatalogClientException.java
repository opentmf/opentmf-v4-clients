package com.pia.tmf.v4.tmf634.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
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

  public ResourceCatalogClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
