package com.pia.tmf.v4.tmf681.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class CommunicationsMessageClientException extends TmfClientException {

  public CommunicationsMessageClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public CommunicationsMessageClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public CommunicationsMessageClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
