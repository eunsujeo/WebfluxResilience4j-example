package com.silverspoon8.springwebfluxresilience4j.webclient;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomClient {

	private final WebClient customClientApi;
	private final CircuitBreaker circuitBreaker;

	public Mono<CustomResponse> isCustom(CustomRequest request) {
		return customClientApi.get()
			.uri(uriBuilder -> uriBuilder.path("/result")
				.queryParam("name", request.getName())
				.queryParam("price", request.getPrice())
				.build()
			)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(CustomResponse.class)
			.transform(CircuitBreakerOperator.of(circuitBreaker))
			.timeout(Duration.ofMillis(10000))
			.doOnError(error -> {
				log.error("에러 발생!!!", error);
			});
	}
}
