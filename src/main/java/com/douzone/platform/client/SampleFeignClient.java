package com.douzone.platform.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="feign", url="${feign.url.test}", configuration = SampleFeignClientConfiguration.class)
public interface SampleFeignClient {

    @GetMapping(value = "/endpoint/url")
	String request(@RequestParam("keyword")String keyword, @RequestParam("keywordStatus")String keywordStatus);
    
}
