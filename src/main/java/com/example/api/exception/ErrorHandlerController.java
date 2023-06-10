package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler({UserNotFoundException.class})  //  独自例外
    @ResponseStatus(HttpStatus.NOT_FOUND)  // レスポンスのステータスコード、ここでは404
    public ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        return new ErrorResponse("notFound", "User with id:"+e.getMessage()+" was not found.");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // レスポンスのステータスコード、ここでは500
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse("systemError", "System error occurred.");
    }
}