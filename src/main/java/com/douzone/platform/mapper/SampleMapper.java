package com.douzone.platform.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.douzone.platform.dto.SampleDTO;

@Mapper
public interface SampleMapper {
	
	int insert(SampleDTO param);
	  
	int updateKeyword(SampleDTO param);
	  
	List<Map<String, Object>> findByExist(SampleDTO param);
	  
	List<SampleDTO> findByAll();
	
}
	  
	 
