package com.douzone.platform.client;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;

public class SampleFeignClientConfiguration implements Jackson2ObjectMapperBuilderCustomizer{
	
	/*
	 * Feign의 기본 로그 레벨 설정
	 * NONE: 로그X
	 * BASIC: Request Method, URL, 응답코드, 실행시간
	 * HEADERS: Request Header,Response Header, BASIC의 요청 정보
	 * FULL: Body, meta-data, HEADERS의 요청 정보
	 */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    /*
     * GET에서 RequestParam으로 LocalTime을 보낼 경우 
     * ISO Format을 이용하여 전송
     * 
     * RequestBody에 있을 경우 Spring boot 2.0, web-starter 의존성이 있다면 전송 가능
     */
    @Bean
    public FeignFormatterRegistrar localDateFeignFormatterRegister() {
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }
    
    /*
     * Requeest Header에 값 지정
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }
    
    /*
     * BasicAuth 인증
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("username", "password");
    }

    
    /*
     * 커뮤니케이션 장애를 대비한 알고리즘
     */
    @Bean
    public Retryer retryer() {
    	/*
    	 * period: 1000
    	 * maxPeriod: 2000
    	 * maxAttempts: 3
    	 * 
    	 * 1초를 시작으로 최대 2초로 재시도, 최대 3번
    	 */
	    return new Retryer.Default(1000, 2000, 3);
    }
   
    /*
     * Jackson JSON 설정
     */
    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder
        	//ENUM 값이 존재하지 않으면 null로 설정. Enum 항목이 추가되어도 무시하고 넘어가게 할 때 필요
            .featuresToEnable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
            //모르는 property에 대해 무시하고 넘어감. DTO의 하위 호환성 보장에 필요
            .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    
    /*
     * 커스텀 예외
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignError();
    }

}
