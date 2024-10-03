package com.pia.tmf.v4.hub.exception;

import com.pia.tmf.common.exception.TmfClientException;
import com.pia.tmf.common.model.ErrorMessage;
import java.io.Serial;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
public class HubClientException extends TmfClientException {

  @Serial
  private static final long serialVersionUID = 1L;

  public HubClientException(HttpStatusCode httpStatusCode, ErrorMessage errorMessage) {
    super(httpStatusCode, errorMessage);
  }

  public HubClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public HubClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
