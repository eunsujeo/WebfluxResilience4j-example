package com.silverspoon8.springwebfluxresilience4j.service;

import org.springframework.stereotype.Service;

import com.silverspoon8.springwebfluxresilience4j.webclient.CustomRequest;
import com.silverspoon8.springwebfluxresilience4j.webclient.CustomResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CircuitResultService {
	public Mono<CustomResponse> result(CustomRequest request) throws InterruptedException {
		if (!request.getName().equals("silverspoon8")) {
			throw new RuntimeException("error");
		}

		// Thread.sleep(5000);

		return Mono.just(new CustomResponse(request.getName(), request.getPrice(), true));
	}
}
