package org.opentmf.v4.tmf620.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Cezmi Aslan
 */
@Getter
public class CategoryClientException extends TmfClientException {

  public CategoryClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CategoryClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public CategoryClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
