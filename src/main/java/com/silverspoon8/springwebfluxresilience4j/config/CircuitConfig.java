package com.silverspoon8.springwebfluxresilience4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@Configuration
public class CircuitConfig {
	private static final String CIRCUIT_NAME = "silverspoon8";

	@Bean
	public CircuitBreaker circuitBreaker(CircuitBreakerRegistry registry) {
		return registry.circuitBreaker(CIRCUIT_NAME);
	}
}
