package com.command.spring.kafka.api.Excption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler(value = AuctionException.class)
    public ResponseEntity<Object> exception(AuctionException exception) {
        return new ResponseEntity<>("custom exception message", HttpStatus.BAD_GATEWAY);
    }
}
