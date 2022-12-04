package com.intech.hero.errors;

import lombok.extern.java.Log;
import java.util.logging.Level;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.intech.hero.errors.api.ErrorResponse;
import com.intech.hero.errors.codes.ErrorCodes;
import com.intech.hero.errors.exceptions.HeroNotFoundException;

@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HeroNotFoundException.class)
    public @ResponseBody
    ErrorResponse handleNotFound(HeroNotFoundException e) {
        log.log(Level.WARNING, String.format(ErrorCodes.HERO_NOT_FOUND.getMessage(), e.getRelatedId()));

        ErrorResponse response = new ErrorResponse();
        response.setMessage(String.format(ErrorCodes.HERO_NOT_FOUND.getMessage(), e.getRelatedId()));

        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorResponse handleValidationFailed(MethodArgumentNotValidException e){
        log.log(Level.WARNING, ErrorCodes.HERO_WRONG_FORMAT.getMessage());

        ErrorResponse response = new ErrorResponse();
        response.setMessage(ErrorCodes.HERO_WRONG_FORMAT.getMessage());

        return response;

    }

}
