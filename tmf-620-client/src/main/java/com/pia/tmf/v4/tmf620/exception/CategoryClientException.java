package com.pia.tmf.v4.tmf620.exception;

import com.pia.tmf.v4.common.exception.TmfClientException;
import com.pia.tmf.v4.common.model.Error;
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

  public CategoryClientException(HttpStatusCode httpStatusCode, Error error) {
    super(httpStatusCode, error);
  }
}
