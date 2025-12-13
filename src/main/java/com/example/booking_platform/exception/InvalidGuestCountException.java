package com.example.booking_platform.exception;

public class InvalidGuestCountException extends RuntimeException{
    public InvalidGuestCountException(String message){
        super(message);
    }
}
