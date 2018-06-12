package com.distribuida.examen01.controller.servicios;

import java.util.List;

import com.distribuida.examen01.servidor03.servicios.dto.Instrumento;

public interface InstrumentoService {

	public List<Instrumento> listar( );
	public void crear( Instrumento obj );
}
