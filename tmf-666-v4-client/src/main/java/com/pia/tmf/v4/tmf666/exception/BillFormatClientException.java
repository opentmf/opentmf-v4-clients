package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class BillFormatClientException extends TmfClientException {

  public BillFormatClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public BillFormatClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public BillFormatClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
