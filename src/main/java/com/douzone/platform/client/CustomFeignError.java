package com.douzone.platform.client;

import com.douzone.platform.exception.SampleNotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

/*
 * Custom Exception Handler
 */
public class CustomFeignError implements ErrorDecoder{

	   @Override
	    public Exception decode(String methodKey, Response response) {
		   
//		   return new IllegalStateException(format("%s 요청이 성공하지 못했습니다. - status: %s, headers: %s", methodKey, response.status(), response.headers()));
	        switch (response.status()) {
	            case 404:
	                return new SampleNotFoundException();
	            case 500:
//	            	return new RetryableException(format("%s 요청이 성공하지 못했습니다. Retry 합니다. - status: %s, headers: %s", methodKey, response.status(), response.headers()), null);
	        }
	        return null;
	    }
	
}
