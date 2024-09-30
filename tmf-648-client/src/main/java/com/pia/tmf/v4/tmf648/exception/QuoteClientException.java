package com.pia.tmf.v4.tmf648.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
@Getter
public class QuoteClientException extends TmfClientException {

  public QuoteClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public QuoteClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public QuoteClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
