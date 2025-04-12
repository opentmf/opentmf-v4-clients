package org.opentmf.v4.tmf622.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Cezmi Aslan
 */
@Getter
public class ProductOrderClientException extends TmfClientException {

  public ProductOrderClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ProductOrderClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ProductOrderClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
