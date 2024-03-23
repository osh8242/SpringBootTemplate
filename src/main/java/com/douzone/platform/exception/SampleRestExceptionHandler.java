package com.douzone.platform.exception;


import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SampleRestExceptionHandler {

	    //404
	    @ExceptionHandler
	    public ResponseEntity<SampleErrorResponse> handleException(SampleNotFoundException e) {
	    	SampleErrorResponse error = new SampleErrorResponse();
	        
	        error.setStatus(HttpStatus.NOT_FOUND.value());
	        error.setMessage("data not found");
	        error.setComment("request된 파라미터로 찾을 수 있는 데이터가 없습니다.");
	        
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // body, status code
	    }
	    
	    //400
	    @ExceptionHandler(MissingServletRequestParameterException.class)
	    public ResponseEntity<SampleErrorResponse> handleException(MissingServletRequestParameterException ex) {
	    	SampleErrorResponse error = new SampleErrorResponse();
	        error.setStatus(HttpStatus.BAD_REQUEST.value());
	        error.setMessage("parameter has empty value");
	        error.setComment("request된 파라미터 정보가 없습니다.");
	        
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // body, status code
	    }
	    
	    //500
	    @ExceptionHandler
	    public ResponseEntity<SampleErrorResponse> handleException(ConversionNotSupportedException e) {
	    	SampleErrorResponse error = new SampleErrorResponse();
	        error.setStatus(HttpStatus.BAD_REQUEST.value());
	        error.setMessage("internal server error (database error)");
	        error.setComment("API 실행 중 시스템에서 발생한 에러입니다.");
	        
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // body, status code
	    }

}
