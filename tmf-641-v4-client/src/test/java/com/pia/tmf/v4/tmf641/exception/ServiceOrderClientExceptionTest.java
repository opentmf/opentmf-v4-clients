package com.pia.tmf.v4.tmf641.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pia.tmf.common.model.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ServiceOrderClientExceptionTest {

  /** This class tests the ServiceOrderException methods. */
  @Test
  @DisplayName("ServiceOrderException test with http status")
  void givenHttpStatus_whenServiceOrderException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ServiceOrderClientException serviceOrderClientException = new ServiceOrderClientException(httpStatus);
    assertNotNull(serviceOrderClientException);
    assertEquals(httpStatus, serviceOrderClientException.getStatusCode());
  }

  @Test
  @DisplayName("ServiceOrderException test with http status and error")
  void givenHttpStatusAndError_whenServiceOrderException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorMessage error = new ErrorMessage();
    error.setCode("Error code");
    error.setMessage("Error description");
    ServiceOrderClientException serviceOrderClientException = new ServiceOrderClientException(httpStatus, error);
    assertNotNull(serviceOrderClientException);
    assertEquals(httpStatus, serviceOrderClientException.getStatusCode());
    assertEquals(error, serviceOrderClientException.getErrorMessage());
  }

  @Test
  @DisplayName("ServiceOrderException test with http status and message")
  void givenHttpStatusAndMessage_whenServiceOrderException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String message = "This is a test message";
    ServiceOrderClientException serviceOrderClientException = new ServiceOrderClientException(httpStatus, message);
    assertNotNull(serviceOrderClientException);
    assertEquals(httpStatus, serviceOrderClientException.getStatusCode());
    assertEquals(message, serviceOrderClientException.getMessage());
  }
}
