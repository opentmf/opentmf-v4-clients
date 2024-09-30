package com.pia.tmf.v4.tmf620.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
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

  public ProductOfferingClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
