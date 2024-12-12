package com.zachholt.WeatherAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder().interceptors(
				(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
					//request.getHeaders().set("Accepts", MediaType.APPLICATION_JSON_VALUE);
					request.getHeaders().set("x-rapidapi-host", "weatherapi-com.p.rapidapi.com");
					request.getHeaders().set("x-rapidapi-key", "99bd0833d9msh5f05d32423c7a4dp1fe286jsna11d6dc8a7cc");

					return execution.execute(request, body);
				}
		).build();
	}
}
