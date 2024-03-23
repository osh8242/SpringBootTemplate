package com.douzone.platform.service;

import java.util.HashMap;

public interface SampleService {

	String getApi(String keyword, String keywordStatus);

	HashMap<String, Object> getKeyword(String keyword, String keywordStatus);

}
