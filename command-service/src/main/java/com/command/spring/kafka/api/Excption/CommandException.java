package com.command.spring.kafka.api.Excption;


import org.springframework.http.HttpStatus;

public class CommandException extends RuntimeException {
    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Exception e) {
        super(e);
    }

    public CommandException(String message, Exception e) {
        super(message, e);
    }

}
