package org.opentmf.v4.tmf620.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Cezmi Aslan
 */
@Getter
public class CatalogClientException extends TmfClientException {

  public CatalogClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public CatalogClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public CatalogClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
