package com.distribuida.s1.servicios;

import java.util.List;

import com.distribuida.s1.servicios.dto.SingerDto;

public interface ServicioSinger {

	public List<SingerDto> listar( );
	public void agregar( SingerDto dto );
}
