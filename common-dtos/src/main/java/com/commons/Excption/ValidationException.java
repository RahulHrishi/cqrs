package com.commons.Excption;

public class ValidationException  extends Exception {

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
