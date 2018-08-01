package com.distribuida.s2.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuida.s2.servicios.dto.AlbumDto;

@RestController
public class ServicioAlbumRest {

	@Autowired private ServicioAlbum servicio;
	
	@GetMapping(path="/listar")
	public List<AlbumDto> listar() {
		return servicio.listar();
	}
}
