package com.douzone.platform.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.platform.client.SampleFeignClient;
import com.douzone.platform.dto.SampleDTO;
import com.douzone.platform.mapper.SampleMapper;


@Service
public class SampleServiceImpl implements SampleService{

	/*
	 * FeignClient 사용
	 * 외부 API 연결
	 */
	@Autowired
	private SampleFeignClient feignClient;

	@Override
	public String getApi(String keyword, String keywordStatus) {
		
		String requestResult = feignClient.request(keyword, keywordStatus);
		
		return requestResult;
		
	}

	/*
	 * MyBatis 사용
	 * 쿼리 연동
	 */
	@Autowired
	private SampleMapper sampleMapper;
	
	@Override
	public HashMap<String, Object> getKeyword(String keyword, String keywordStatus) {
		
		String resultStr = "";
		
		SampleDTO sample = new SampleDTO();
		sample.setKeyword(keyword);
		sample.setKeywordStatus(keywordStatus);
		
		HashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("keyword", sample.getKeyword());
		map.put("keywordStatus", sample.getKeywordStatus());
		
//		mapper랑 연동
//		List<Map<String, Object>> resultList = sampleMapper.findByExist(sample);
		
		return map;
		
	}

	
	
	
	

	
	
}
