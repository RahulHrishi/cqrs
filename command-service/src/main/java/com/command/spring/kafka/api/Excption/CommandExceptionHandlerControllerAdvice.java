package com.command.spring.kafka.api.Excption;

import com.commons.Excption.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommandExceptionHandlerControllerAdvice {

    @ExceptionHandler(value = CommandException.class)
    public ResponseEntity<Object> exception(CommandException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> exception(ValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
