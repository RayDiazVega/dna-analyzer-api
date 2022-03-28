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

  @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class,
      IllegalArgumentException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<Object> badRequestExceptionHandler(Exception ex) {
    ex.printStackTrace();
    Object error = isMethodArgumentNotValidException(ex);
    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("message", "Client error", "error", error,
            "timestamp", LocalDateTime.now()));
  }

  @ExceptionHandler(value = NotMutantException.class)
  public ResponseEntity<Object> notMutantExceptionHandler(NotMutantException ex) {
    ex.printStackTrace();
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> exceptionHandler(Exception ex) {
    ex.printStackTrace();
    return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON)
        .body(Map.of("message", "Server error", "timestamp", LocalDateTime.now()));
  }
}
