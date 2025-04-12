package org.opentmf.v4.tmf620.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Cezmi Aslan
 */
@Getter
public class ProductOfferingClientException extends TmfClientException {

  public ProductOfferingClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ProductOfferingClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }

  public ProductOfferingClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }
}
