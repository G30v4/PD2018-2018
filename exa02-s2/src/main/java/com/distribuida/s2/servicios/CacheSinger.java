package com.distribuida.s2.servicios;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.distribuida.s1.servicios.dto.SingerDto;

@Service
public class CacheSinger {
	
	private Map<Integer, SingerDto> cache = null;
	
	private RestTemplate restTemplate;
	
	@Value("${s1.url:http://127.0.0.1:9090/api/singer/listar}") String url;
	 
	@PostConstruct
	public void init( ) {
		restTemplate = new RestTemplate( );
	}
	
	public synchronized Map<Integer, SingerDto> refrescar( ) {
		
		if( cache==null ) {
			
			System.out.println( "Refrescando cache" );

			cache = new HashMap<>();
			
			// cargar los datos desde el REST
			SingerDto[] singers = restTemplate.getForObject( url, SingerDto[].class );
						
			agregarTodos( singers );
		}
		
		return cache;
	}
	
	public synchronized void reset( ) {
		cache = null;
	}

	public synchronized void agregar( Integer id, SingerDto singer  ) {
		cache.put( id, singer );
	}
	
	private void agregarTodos( SingerDto[] dtos ) {
		
		cache.clear( );
		
		for( SingerDto s:dtos ) {
			cache.put( s.getId(),s );
		}
	}

}
