package com.microservice.productsservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProducServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducServiceApplication.class, args);
	}

}
