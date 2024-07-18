package com.progresssoft.deals.fx.exceptions;

public class RequestAlreadyDefinedException extends RuntimeException{
    public RequestAlreadyDefinedException(String msg) {
        super(msg);
    }
    public RequestAlreadyDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
}
