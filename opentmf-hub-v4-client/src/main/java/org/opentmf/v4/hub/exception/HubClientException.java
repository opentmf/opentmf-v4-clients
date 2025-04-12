package org.opentmf.v4.hub.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
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
