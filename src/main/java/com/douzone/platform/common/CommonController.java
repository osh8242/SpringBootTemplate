package com.douzone.platform.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.douzone.platform.dto.StatusDTO;

@RestController
@RequestMapping("/sample")
public class CommonController {

	//AliveCheck
	@GetMapping("/alivecheck")
	public ResponseEntity<StatusDTO> aliveCheck(){	
		StatusDTO dto= new StatusDTO();
		dto.setStatus(HttpStatus.OK.value());
		dto.setMessage("success");
		dto.setData("성공");

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
}
