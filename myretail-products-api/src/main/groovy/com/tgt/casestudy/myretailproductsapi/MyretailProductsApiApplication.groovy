package com.tgt.casestudy.myretailproductsapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication(scanBasePackages = ['com.tgt.casestudy.myretailproductsapi'])
class MyretailProductsApiApplication extends SpringBootServletInitializer {

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate()
	}

	static void main(String[] args) {
		SpringApplication.run MyretailProductsApiApplication, args
	}
}
