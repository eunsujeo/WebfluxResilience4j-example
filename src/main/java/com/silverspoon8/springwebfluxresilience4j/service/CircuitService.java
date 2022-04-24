package com.silverspoon8.springwebfluxresilience4j.service;

import org.springframework.stereotype.Service;

import com.silverspoon8.springwebfluxresilience4j.webclient.CustomClient;
import com.silverspoon8.springwebfluxresilience4j.webclient.CustomRequest;
import com.silverspoon8.springwebfluxresilience4j.webclient.CustomResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class CircuitService {
	private final CustomClient customClient;

	public Mono<CustomResponse> circuitTest(CustomRequest request) {
		return customClient.isCustom(request);
	}
}
