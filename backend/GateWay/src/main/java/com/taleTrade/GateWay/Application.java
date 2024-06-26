package com.taleTrade.GateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "userRemoteAddressResolver")
	public KeyResolver userKeyResolver() {
		return exchange -> {
			return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
		};
	}


}
