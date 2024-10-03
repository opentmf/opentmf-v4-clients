package com.pia.tmf.v4.tmf633.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pia.tmf.common.model.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ServiceSpecificationClientExceptionTests {

  /** This class tests the ServiceSpecificationClientException methods. */
  @Test
  @DisplayName("ServiceSpecificationClientException test with http status")
  void givenHttpStatus_whenServiceSpecificationClientException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ServiceSpecificationClientException exception = new ServiceSpecificationClientException(httpStatus);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
  }

  @Test
  @DisplayName("ServiceSpecificationClientException test with http status and error")
  void givenHttpStatusAndError_whenServiceSpecificationClientException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorMessage error = new ErrorMessage();
    error.setCode("Error code");
    error.setMessage("Error description");
    ServiceSpecificationClientException exception = new ServiceSpecificationClientException(httpStatus, error);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
    assertEquals(error, exception.getErrorMessage());
  }

  @Test
  @DisplayName("ServiceSpecificationClientException test with http status and message")
  void givenHttpStatusAndMessage_whenServiceSpecificationClientException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String message = "This is a test message";
    ServiceSpecificationClientException exception = new ServiceSpecificationClientException(httpStatus, message);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
    assertEquals(message, exception.getMessage());
  }
}
