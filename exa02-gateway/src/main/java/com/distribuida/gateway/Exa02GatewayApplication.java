package com.distribuida.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Exa02GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Exa02GatewayApplication.class, args);
	}
}
