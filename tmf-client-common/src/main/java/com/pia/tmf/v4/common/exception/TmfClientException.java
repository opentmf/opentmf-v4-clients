package com.pia.tmf.v4.common.exception;

import com.pia.client.common.exception.PiaWebClientException;
import com.pia.tmf.v4.common.model.Error;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Cezmi
 * @author Gokhan Demir
 * @author Gokhan Demir
 */
@Getter
public class TmfClientException extends PiaWebClientException {

  private final Error error;

  public TmfClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
    this.error = null;
  }

  public TmfClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
    this.error = null;
  }

  public TmfClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode);
    this.error = error;
  }
}
