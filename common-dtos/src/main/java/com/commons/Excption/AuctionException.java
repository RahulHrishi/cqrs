package com.commons.Excption;


public class AuctionException extends Exception {
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
