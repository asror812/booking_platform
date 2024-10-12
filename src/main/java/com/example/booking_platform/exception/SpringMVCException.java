package com.example.booking_platform.exception;

public class SpringMVCException extends RuntimeException {

    public SpringMVCException(String message){
        super(message);
    }

    public static class EntityNotFoundException extends SpringMVCException {
        private static final String MESSAGE = "%s entity not found with id %s";
        public EntityNotFoundException(String entityName , String id ){
            super(String.format(MESSAGE, entityName , id ));
        }
    }

    public static class RoleNotFoundException extends SpringMVCException {
        private static final String MESSAGE = "Role not found with name %s";

        public RoleNotFoundException(String roleName ){
            super(String.format(MESSAGE, roleName ));
        }
    }

    public static class DuplicateEntityException extends SpringMVCException {
        public DuplicateEntityException(String s) {
            super(s);
        }
    }
}
