package com.example.booking_platform.exception;

public class AlreadyRegisteredException extends RuntimeException {

    private static final String MESSAGE = "This %s is already registered";

    public AlreadyRegisteredException(String param) {
        super(String.format(MESSAGE, param));
    }
}
