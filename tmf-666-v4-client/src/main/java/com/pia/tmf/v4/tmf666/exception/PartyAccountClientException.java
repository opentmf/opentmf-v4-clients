package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class PartyAccountClientException extends TmfClientException {

  public PartyAccountClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public PartyAccountClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public PartyAccountClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
