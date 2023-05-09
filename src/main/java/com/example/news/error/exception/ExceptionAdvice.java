package com.example.news.error.exception;

import com.example.news.error.dto.ExceptionResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse noSuchElementExceptionHandler(NoSuchElementException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ExceptionResponse(status.value(),status.name(), e.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new ExceptionResponse(status.value(),status.name(), e.getMessage());
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ExceptionResponse illegalArgumentExceptionHandler(IllegalArgumentException e){
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        return new ExceptionResponse(status.value(), status.name(), e.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ExceptionResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        return new ExceptionResponse(status.value(), status.name(), e.getMessage());
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class})
    public ExceptionResponse handleValidationException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message;
        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getFieldErrors()
                    .get(0)
                    .getDefaultMessage();
        } else {
            message = e.getMessage();
        }
        return new ExceptionResponse(status.value(), status.name(), message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse noSuchRequestHandlingMethodExceptionHandler(
            HttpRequestMethodNotSupportedException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ExceptionResponse(status.value(), status.name(), "해당 Url은 존재하지 않습니다.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionResponse noSuchRequestHandlingMethodExceptionHandler(
            NoHandlerFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ExceptionResponse(status.value(), status.name(), "해당 Url은 존재하지 않습니다.");
    }
}
