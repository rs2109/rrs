package com.tech.bahera.util;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError noHandlerFoundException(
            NoHandlerFoundException ex) {

        int code = 11000;
        String message = "Email address already exists.";
        return new ApiError(code, message);
    }

}
