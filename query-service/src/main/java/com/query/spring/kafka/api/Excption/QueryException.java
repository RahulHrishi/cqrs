package com.query.spring.kafka.api.Excption;


public class QueryException extends RuntimeException {
    public QueryException() {
        super();
    }

    public QueryException(String message) {
        super(message);
    }

    public QueryException(Exception e) {
        super(e);
    }

    public QueryException(String message, Exception e) {
        super(message, e);
    }
}
