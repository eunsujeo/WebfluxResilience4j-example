package com.silverspoon8.springwebfluxresilience4j.webclient;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomResponse {
	private String name;
	private Long price;
	private Boolean exist;

	public CustomResponse(String name, Long price, Boolean exist) {
		this.name = name;
		this.price = price;
		this.exist = exist;
	}
}
