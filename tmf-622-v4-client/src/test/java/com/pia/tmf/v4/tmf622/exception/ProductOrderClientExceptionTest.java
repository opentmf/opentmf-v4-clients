package com.pia.tmf.v4.tmf622.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pia.tmf.common.model.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ProductOrderClientExceptionTest {

  /** This class tests the ProductOrderClientException methods. */
  @Test
  @DisplayName("ProductOrderClientException test with http status")
  void givenHttpStatus_whenProductOrderClientException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ProductOrderClientException exception = new ProductOrderClientException(httpStatus);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
  }

  @Test
  @DisplayName("ProductOrderClientException test with http status and error")
  void givenHttpStatusAndError_whenProductOrderClientException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorMessage error = new ErrorMessage();
    error.setCode("Error code");
    error.setMessage("Error description");
    ProductOrderClientException exception = new ProductOrderClientException(httpStatus, error);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
    assertEquals(error, exception.getErrorMessage());
  }

  @Test
  @DisplayName("ProductOrderClientException test with http status and message")
  void givenHttpStatusAndMessage_whenProductOrderClientException_thenExceptionCreated() {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    String message = "This is a test message";
    ProductOrderClientException exception = new ProductOrderClientException(httpStatus, message);
    assertNotNull(exception);
    assertEquals(httpStatus, exception.getStatusCode());
    assertEquals(message, exception.getMessage());
  }
}
