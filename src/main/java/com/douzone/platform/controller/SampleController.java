package com.douzone.platform.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.platform.dto.StatusDTO;
import com.douzone.platform.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private SampleService sampleService;

	@GetMapping("/basic_sample")
    public ResponseEntity<StatusDTO> getBasicSample(@RequestParam String keyword, @RequestParam String keywordStatus) {
		
		HashMap<String, Object> result = sampleService.getKeyword(keyword, keywordStatus);
		
		StatusDTO dto = new StatusDTO();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		dto.setStatus(HttpStatus.OK.value());
		dto.setMessage("success");
		dto.setData(result);
		return new ResponseEntity<>(dto, header, HttpStatus.OK);
    }
	
	/*
	 * 외부 API 호출
	 * FeignClient 사용
	 */
	@GetMapping("/feign_sample")
    public ResponseEntity<StatusDTO> getSampleFeign(@RequestParam String keyword, @RequestParam String keywordStatus) {
		
		String result = sampleService.getApi(keyword, keywordStatus);
		
		StatusDTO dto = new StatusDTO();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		dto.setStatus(HttpStatus.OK.value());
		dto.setMessage("success");
		dto.setData(result);
		return new ResponseEntity<>(dto, header, HttpStatus.OK);
    }
	
	@GetMapping("/img_download")
    public ResponseEntity<StatusDTO> imgDownload(@RequestParam String imgPath, @RequestParam String downloadPath) {
		
		/*
		 * - example
		 *    imgPath : https://www.water.or.kr/images/egovframework/life/weast/weast044_01.jpg
		 *    downloadPath : files/img_1.jpg
		 *  
		 * - pom.xml
		 *  <dependency>
		      <groupId>org.apache.httpcomponents</groupId>
		      <artifactId>httpclient</artifactId>
		      <version>4.5.14</version>
		    </dependency>
		 *
		 */
		
		StatusDTO dto = new StatusDTO();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		try {
			
			//client 요청
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(imgPath);
			
			//client 응답
	        CloseableHttpResponse httpResponse = httpClient.execute(httpget);
	        
	        HttpEntity entity = httpResponse.getEntity();
	        
	        BufferedInputStream bis = new BufferedInputStream(entity.getContent());
	        String filePath = downloadPath;
	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	        int inByte;
	        while((inByte = bis.read()) != -1) bos.write(inByte);
	        bis.close();
	        bos.close();
	        
		}catch(ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		dto.setStatus(HttpStatus.OK.value());
		dto.setMessage("success");
		dto.setData("성공");
		return new ResponseEntity<>(dto, header, HttpStatus.OK);
    }
    

}
