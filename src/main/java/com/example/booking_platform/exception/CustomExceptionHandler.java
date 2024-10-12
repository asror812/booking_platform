package com.example.booking_platform.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class CustomExceptionHandler {



    @ExceptionHandler(SpringMVCException.EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(SpringMVCException.EntityNotFoundException e) {
        String msg = e.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        for (ObjectError allError : e.getBindingResult().getAllErrors()) {
            FieldError fieldError = (FieldError) allError;
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();

            errors.put(field, defaultMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }




    @ExceptionHandler(SpringMVCException.class)
    public ModelAndView handleRestException(HttpServletRequest req , SpringMVCException e) {
        String msg = e.getMessage();

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");

        return mav;
    }

}
