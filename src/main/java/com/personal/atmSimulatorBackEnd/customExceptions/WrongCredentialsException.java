package com.personal.atmSimulatorBackEnd.customExceptions;

public class WrongCredentialsException extends Exception{
    public WrongCredentialsException() {

    }

    public WrongCredentialsException(String message) {
        super(message);
    }

    public WrongCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCredentialsException(Throwable cause) {
        super(cause);
    }

    public WrongCredentialsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
