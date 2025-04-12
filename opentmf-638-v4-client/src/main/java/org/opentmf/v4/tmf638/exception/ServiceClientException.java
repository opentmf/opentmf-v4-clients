package org.opentmf.v4.tmf638.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class ServiceClientException extends TmfClientException {

  public ServiceClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public ServiceClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }
  
  public ServiceClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
