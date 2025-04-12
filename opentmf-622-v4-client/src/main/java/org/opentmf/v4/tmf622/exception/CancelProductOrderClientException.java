package org.opentmf.v4.tmf622.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Cezmi Aslan
 */
@Getter
public class CancelProductOrderClientException extends TmfClientException {

  public CancelProductOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CancelProductOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public CancelProductOrderClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
