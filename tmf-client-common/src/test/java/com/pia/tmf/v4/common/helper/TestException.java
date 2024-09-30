package com.pia.tmf.v4.common.helper;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class TestException extends TmfClientException {

  private final Error error;

  public TestException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
    this.error = null;
  }

  public TestException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode);
    this.error = error;
  }

  public TestException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
    this.error = null;
  }
}
