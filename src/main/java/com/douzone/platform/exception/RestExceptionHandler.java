package com.douzone.platform.exception;


import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //파라미터 오류 -> 400 리턴
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HttpErrorResponse> handleException(MissingServletRequestParameterException e, Locale locale) {
        HttpErrorResponse error = new HttpErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(getLocaleMessage(HttpStatus.BAD_REQUEST, locale));
        error.setComment(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // body, status code
    }

    // 필수데이터 부재 오류 -> 404 리턴
    @ExceptionHandler(DataNotFoundException.class)
        public ResponseEntity<HttpErrorResponse> handleException(DataNotFoundException e, Locale locale) {
        HttpErrorResponse error = new HttpErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(getLocaleMessage(HttpStatus.NOT_FOUND, locale));
        error.setComment(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // body, status code
    }

    // NullPointer 오류 -> 404 리턴
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<HttpErrorResponse> handleException(NullPointerException e, Locale locale) {
        HttpErrorResponse error = new HttpErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(getLocaleMessage(HttpStatus.INTERNAL_SERVER_ERROR, locale));
        error.setComment(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // body, status code
    }

    // 주로 DB 데이터를 DTO 객체로 변환시 발생하는 오류 ->  500 리턴
    @ExceptionHandler(ConversionNotSupportedException.class)
    public ResponseEntity<HttpErrorResponse> handleException(ConversionNotSupportedException e, Locale locale) {
        HttpErrorResponse error = new HttpErrorResponse();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(getLocaleMessage(HttpStatus.INTERNAL_SERVER_ERROR, locale));
        error.setComment(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); // body, status code
    }

    private String getLocaleMessage(HttpStatus httpStatus, Locale locale){
        return messageSource.getMessage(String.valueOf(httpStatus.value()),null, locale);
    }

}
