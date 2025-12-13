package com.example.booking_platform.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleEntityNotFound(
            EntityNotFoundException ex,
            HttpServletRequest request) {

        log.warn("Entity not found: {}", ex.getMessage());

        ModelAndView mav = new ModelAndView("error/404");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("path", request.getRequestURI());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneralException(
            Exception ex,
            HttpServletRequest request) {

        log.error("Unexpected error", ex);

        ModelAndView mav = new ModelAndView("error/500");
        mav.addObject(
                "errorMessage",
                "Something went wrong. Please try again later.");
        mav.addObject("path", request.getRequestURI());
        return mav;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException e,
            RedirectAttributes redirectAttributes) {
        log.warn("Invalid parameters: {}", e.getMessage());
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(InvalidGuestCountException.class)
    public String handleInvalidGuestCount(InvalidGuestCountException e,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        log.warn("Invalid guest count: {}", e.getMessage());
        redirectAttributes.addFlashAttribute("error", e.getMessage());

        // Preserve search parameters
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
