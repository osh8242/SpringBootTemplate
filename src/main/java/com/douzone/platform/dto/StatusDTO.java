package com.douzone.platform.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class StatusDTO {
	private int status;
	private String message;
	private Object data;
	
	public StatusDTO() {}
	
	public StatusDTO(int status, String message, Object data) {
		this.status = status;
		this.message = message;;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int httpStatus) {
		this.status = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "StatusDTO [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

	
	
	
}
