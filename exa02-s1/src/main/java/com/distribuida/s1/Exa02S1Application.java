package com.distribuida.s1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.distribuida.s1.servicios.ServicioSinger;
import com.distribuida.s1.servicios.dto.SingerDto;

@SpringBootApplication
public class Exa02S1Application implements CommandLineRunner {

	@Autowired ServicioSinger servicio;
	
	public static void main(String[] args) {
		SpringApplication.run(Exa02S1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		SingerDto dto = new SingerDto( );
		
		servicio.agregar( dto );
	}
}
