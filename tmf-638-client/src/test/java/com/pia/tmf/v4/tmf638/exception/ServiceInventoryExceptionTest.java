package com.pia.tmf.v4.tmf638.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pia.tmf.v4.common.model.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ServiceInventoryExceptionTest {

  /** This class tests the ServiceInventoryException methods. */
  @Test
  @DisplayName("ServiceInventoryException test with http status")
  void givenHttpStatus_whenServiceInventoryException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ServiceClientException exception = new ServiceClientException(httpStatus);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
  }

  @Test
  @DisplayName("ServiceInventoryException test with http status and error")
  void givenHttpStatusAndError_whenServiceInventoryException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    Error error = new Error();
    error.setCode("Error code");
    error.setMessage("Error description");
    ServiceClientException exception = new ServiceClientException(httpStatus, error);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
    assertEquals(error, exception.getError());
  }

  @Test
  @DisplayName("ServiceInventoryException test with http status and message")
  void givenHttpStatusAndMessage_whenServiceInventoryException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String message = "This is a test message";
    ServiceClientException exception = new ServiceClientException(httpStatus, message);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
    assertEquals(message, exception.getMessage());
  }
}
