package com.pragmaticcoders.webapp.webappone;

import com.pragmaticcoders.loadbalancer.EnableGossips;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableGossips
@EnableWebMvc
@SpringBootApplication
public class WebappOneApplication {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebappOneApplication.class, args);
	}
}
