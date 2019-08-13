package com.cafe24.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BootApp {
	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);

	}

	
}
