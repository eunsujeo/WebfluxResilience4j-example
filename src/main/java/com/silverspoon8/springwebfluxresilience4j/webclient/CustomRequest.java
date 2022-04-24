package com.silverspoon8.springwebfluxresilience4j.webclient;

import lombok.Getter;

@Getter
public class CustomRequest {
	private final String name;
	private final long price;

	public CustomRequest(String name, long price) {
		this.name = name;
		this.price = price;
	}
}
