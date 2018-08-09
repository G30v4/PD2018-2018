package com.distribuida.recuperacion.s2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExaRecupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaRecupApplication.class, args);
	}
}
