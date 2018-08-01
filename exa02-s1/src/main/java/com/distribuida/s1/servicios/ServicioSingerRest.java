package com.distribuida.s1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuida.s1.servicios.dto.SingerDto;

@RestController
public class ServicioSingerRest {

	@Autowired private ServicioSinger servicio;
	
	@GetMapping(path="/listar")
	public List<SingerDto> listar( ) {
		return servicio.listar( );
	}
	
	@PostMapping(path="/agregar")
	public void agregar( SingerDto dto ) {
		servicio.agregar( dto );
	}
}
