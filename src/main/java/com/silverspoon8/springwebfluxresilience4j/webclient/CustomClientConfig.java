package com.silverspoon8.springwebfluxresilience4j.webclient;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableConfigurationProperties(CustomClientProperties.class)
public class CustomClientConfig {

	@Bean
	public WebClient customClientApi(CustomClientProperties properties) {
		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(HttpClient.create()
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
					.doOnConnected(conn -> conn
						.addHandlerLast(
							new ReadTimeoutHandler(properties.getSocketTimeout(), TimeUnit.MILLISECONDS))
						.addHandlerLast(
							new WriteTimeoutHandler(properties.getSocketTimeout(), TimeUnit.MILLISECONDS))
					)
				)
			)
			.uriBuilderFactory(new DefaultUriBuilderFactory(
				UriComponentsBuilder
					.newInstance()
					.scheme("http")
					.host(properties.getUrl())
					.port(properties.getPort())))
			.build();
	}
}
