package com.pia.tmf.v4.tmf633.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
@Getter
public class ServiceSpecificationClientException extends TmfClientException {

  public ServiceSpecificationClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ServiceSpecificationClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }

  public ServiceSpecificationClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
