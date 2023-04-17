package com.einstein.report.feign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

/**
 * Configures a load-balanced Feign builder with the specified name.
 * 
 * @return A load-balanced Feign builder.
 */
@LoadBalancerClient(name = "inventory-management")
public class LoadBalancerConfig {

	@LoadBalanced
	@Bean
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
