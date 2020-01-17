package com.edugroupe.ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingsFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsFormApplication.class, args);
	}

}
