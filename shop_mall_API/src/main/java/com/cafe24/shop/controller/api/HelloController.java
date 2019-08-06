package com.cafe24.shop.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shop.dto.JSONResult;
import com.cafe24.shop.vo.CustomerVo;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public ResponseEntity<JSONResult> hello(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success("Hello World"));
	}

	@PostMapping("/hello2")
	public ResponseEntity<JSONResult> hello2(@RequestBody CustomerVo customervo){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success("Hello World2"));
	}

	@GetMapping("/hello3")
	public ResponseEntity<JSONResult> hello3(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success("Hello World"));
	}
	
}