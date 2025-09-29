package com.example.booking_platform.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "%s entity not found with id %s";

    public EntityNotFoundException(String entityName, String id) {
        super(String.format(MESSAGE, entityName, id));
    }
}
