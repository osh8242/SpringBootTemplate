package com.douzone.platform.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HttpErrorResponse {

	private int status;
    private String message;
    private String comment;


}
