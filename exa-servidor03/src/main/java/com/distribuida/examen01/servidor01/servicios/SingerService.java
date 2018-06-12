package com.distribuida.examen01.servidor01.servicios;

import java.util.List;

import com.distribuida.examen01.servidor01.servicios.dto.Singer;

public interface SingerService {

	public List<Singer> listar( );
	public void crear( Singer obj );
}
