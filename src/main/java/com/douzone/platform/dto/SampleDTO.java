package com.douzone.platform.dto;

public class SampleDTO {
	
	int id;
	String keyword;
	String keywordStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeywordStatus() {
		return keywordStatus;
	}
	public void setKeywordStatus(String keywordStatus) {
		this.keywordStatus = keywordStatus;
	}
	@Override
	public String toString() {
		return "SampleDTO [id=" + id + ", keyword=" + keyword + ", keywordStatus=" + keywordStatus + "]";
	}
	
	
	

}
