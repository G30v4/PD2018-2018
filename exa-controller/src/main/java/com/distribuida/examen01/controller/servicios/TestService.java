package com.distribuida.examen01.controller.servicios;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distribuida.examen01.servidor01.servicios.dto.Singer;
import com.distribuida.examen01.servidor03.servicios.dto.Instrumento;

@Component
public class TestService {
	
	@Autowired private SingerService singerService;
	@Autowired private InstrumentoService instrumentService;
	
	@PostConstruct
	public void init( ) {
		System.out.println( "Inicializado controller" );

		System.out.println( singerService );
		System.out.println( instrumentService );
		
		List<Singer> cantantes = singerService.listar( );
		
		System.out.println( "*************Lista de cantantes" );
		cantantes.forEach( s->{
			System.out.println( s.getFirstName( ) );
		});
		
		//-- crear un instrumento
		Instrumento obj = new Instrumento();
		
		obj.setId( "guitarra" );
		
		instrumentService.crear( obj );
	}
}
