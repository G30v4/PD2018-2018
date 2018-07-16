package com.distribuida.servidor01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Servidor01Application {
	
	@Autowired private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(Servidor01Application.class, args);
	}
	
	@GetMapping(path="/test")
	public String test() {
		return env.getProperty( "server.port" );
	}
}
