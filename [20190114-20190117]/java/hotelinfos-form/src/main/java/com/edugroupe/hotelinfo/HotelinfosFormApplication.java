package com.edugroupe.hotelinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelinfosFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelinfosFormApplication.class, args);
	}

}
