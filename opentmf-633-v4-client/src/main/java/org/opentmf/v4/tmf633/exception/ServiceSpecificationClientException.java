package org.opentmf.v4.tmf633.exception;

import org.opentmf.common.exception.TmfClientException;
import org.opentmf.common.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * @author Gokhan Demir
 */
@Getter
public class ServiceSpecificationClientException extends TmfClientException {

  public ServiceSpecificationClientException(HttpStatusCode httpStatusCode) {
    super(httpStatusCode);
  }

  public ServiceSpecificationClientException(HttpStatusCode httpStatusCode, ErrorMessage error) {
    super(httpStatusCode, error);
  }

  public ServiceSpecificationClientException(HttpStatusCode httpStatusCode, String message) {
    super(httpStatusCode, message);
  }
}
