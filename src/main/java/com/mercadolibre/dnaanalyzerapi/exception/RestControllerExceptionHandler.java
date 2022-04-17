package com.mercadolibre.dnaanalyzerapi.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  private Object isMethodArgumentNotValidException(Exception ex) {
    if (ex instanceof MethodArgumentNotValidException) {
      return ((MethodArgumentNotValidException) ex).getAllErrors().parallelStream()
          .map(ObjectError::getDefaultMessage).distinct().toArray();
    }
    return ex.getMessage();
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<Object> badRequest(Exception ex) {
    ex.printStackTrace();
    Object error = isMethodArgumentNotValidException(ex);
    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("message", "Client error", "error", error,
            "timestamp", LocalDateTime.now()));
  }

  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Object> methodNotAllowed(HttpRequestMethodNotSupportedException ex) {
    ex.printStackTrace();
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .contentType(MediaType.APPLICATION_JSON).body(Map.of("timestamp", LocalDateTime.now(),
            "message", "Client error", "error", ex.getMessage()));
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> internalServerError(Exception ex) {
    ex.printStackTrace();
    return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("message", "Server error", "timestamp", LocalDateTime.now()));
  }
}
