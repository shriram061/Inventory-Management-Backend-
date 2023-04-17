package com.eureka.einstein.setport;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Port implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

	@Override

	public void customize(ConfigurableWebServerFactory factory) {
		factory.setPort(8761);
	}

}
