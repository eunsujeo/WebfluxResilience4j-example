package com.silverspoon8.springwebfluxresilience4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silverspoon8.springwebfluxresilience4j.service.CircuitResultService;
import com.silverspoon8.springwebfluxresilience4j.service.CircuitService;
import com.silverspoon8.springwebfluxresilience4j.webclient.CustomRequest;
import com.silverspoon8.springwebfluxresilience4j.webclient.CustomResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CircuitController {

	private final CircuitService circuitService;
	private final CircuitResultService circuitResultService;

	@GetMapping("/circuit")
	public Mono<ResponseEntity<CustomResponse>> circuit(CustomRequest customRequest) {
		return circuitService.circuitTest(customRequest)
			.doOnError(e ->
				log.error("에러가 발생했다!!!", e)
			)
			.map(ResponseEntity::ok);
	}

	@GetMapping("/result")
	public Mono<CustomResponse> result(CustomRequest wedulRequest) throws InterruptedException {
		return circuitResultService.result(wedulRequest);
	}
}
