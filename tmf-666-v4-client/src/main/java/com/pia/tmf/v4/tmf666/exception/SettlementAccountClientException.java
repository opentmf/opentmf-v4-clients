package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class SettlementAccountClientException extends TmfClientException {

  public SettlementAccountClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public SettlementAccountClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public SettlementAccountClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
