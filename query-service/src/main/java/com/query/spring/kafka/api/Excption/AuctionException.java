package com.query.spring.kafka.api.Excption;


public class AuctionException extends RuntimeException {
    public AuctionException() {
        super();
    }

    public AuctionException(String message) {
        super(message);
    }

    public AuctionException(Exception e) {
        super(e);
    }

    public AuctionException(String message, Exception e) {
        super(message, e);
    }
}
