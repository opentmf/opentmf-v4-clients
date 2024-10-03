package com.pia.tmf.v4.tmf666.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class BillPresentationMediaClientException extends TmfClientException {

  public BillPresentationMediaClientException(HttpStatusCode httpStatus) {
    super(httpStatus);
  }

  public BillPresentationMediaClientException(HttpStatusCode httpStatus, ErrorMessage error) {
    super(httpStatus, error);
  }

  public BillPresentationMediaClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
