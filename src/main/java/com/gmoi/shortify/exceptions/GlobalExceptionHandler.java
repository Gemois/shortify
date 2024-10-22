package com.gmoi.shortify.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ModelAndView handleUrlNotFoundException(UrlNotFoundException e, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("/errors/not-found");
    }

    @ExceptionHandler(UrlMissingException.class)
    public ModelAndView handleUrlMissingException(UrlMissingException e, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("index");
    }

    @ExceptionHandler(UrlMalformedException.class)
    public ModelAndView handleUrlMalformedException(UrlMalformedException e, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("error", e.getMessage());
        return new ModelAndView("index");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Model model) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", "An unexpected error occurred.");
        return new ModelAndView("/errors/internal-server-error");
    }
}
