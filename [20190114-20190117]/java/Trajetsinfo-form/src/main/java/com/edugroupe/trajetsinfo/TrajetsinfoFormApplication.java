package com.edugroupe.trajetsinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrajetsinfoFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrajetsinfoFormApplication.class, args);
	}

}
