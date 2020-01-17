package com.edugroupe.livreinfos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LivreinfosFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivreinfosFormApplication.class, args);
	}

}
