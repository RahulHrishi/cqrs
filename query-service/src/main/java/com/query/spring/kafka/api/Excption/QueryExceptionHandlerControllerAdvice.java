package com.query.spring.kafka.api.Excption;

import com.commons.Excption.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QueryExceptionHandlerControllerAdvice {
    @ExceptionHandler(value = QueryException.class)
    public ResponseEntity<Object> exception(QueryException exception) {
        return new ResponseEntity<>("custom exception message", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> exception(ValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
