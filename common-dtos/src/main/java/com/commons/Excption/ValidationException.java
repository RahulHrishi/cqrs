package com.commons.Excption;

public class ValidationException  extends RuntimeException {

    public ValidationException () {
        super();
    }

    public ValidationException (String message) {
        super(message);
    }

    public ValidationException (Exception e) {
        super(e);
    }
}
