package com.silverspoon8.springwebfluxresilience4j.webclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("silverspoon.client")
public class CustomClientProperties {
	private final int timeout;
	private final int connectTimeout;
	private final int socketTimeout;
	private final String url;
	private final String port;
}
