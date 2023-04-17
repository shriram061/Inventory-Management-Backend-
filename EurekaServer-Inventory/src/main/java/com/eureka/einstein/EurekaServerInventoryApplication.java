package com.eureka.einstein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerInventoryApplication.class, args);
	}

}
