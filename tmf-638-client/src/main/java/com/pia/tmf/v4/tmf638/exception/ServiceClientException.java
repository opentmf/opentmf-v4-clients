package com.pia.tmf.v4.tmf638.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ServiceClientException extends TmfClientException {

  public ServiceClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public ServiceClientException(HttpStatusCode httpStatus, Error error) {
    super(httpStatus, error);
  }
  
  public ServiceClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
