package org.opentmf.v4.tmf648.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
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

  public QuoteClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
