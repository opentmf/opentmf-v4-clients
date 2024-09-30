package com.pia.tmf.v4.tmf633.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
@Getter
public class ServiceCatalogClientException extends TmfClientException {

  public ServiceCatalogClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ServiceCatalogClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }

  public ServiceCatalogClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
