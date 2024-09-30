package com.pia.tmf.v4.tmf633.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
@Getter
public class ServiceCategoryClientException extends TmfClientException {

  public ServiceCategoryClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ServiceCategoryClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }

  public ServiceCategoryClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
