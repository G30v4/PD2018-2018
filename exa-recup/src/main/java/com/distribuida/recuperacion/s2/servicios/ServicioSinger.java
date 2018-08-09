package com.distribuida.recuperacion.s2.servicios;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.distribuida.s1.servicios.dto.SingerDto;

@Service
public class ServicioSinger {
	
	private RestTemplate restTemplate;
	
	@Value("${s1.url:http://127.0.0.1:9090/api/singer/listar}") String url;
	 
	@PostConstruct
	public void init( ) {
		restTemplate = new RestTemplate( );
	}
	
	@Cacheable(cacheNames="cacheCantantes")
	public List<SingerDto> listar( ) {
		
		System.out.println( "Refrescando cache" );

		// cargar los datos desde el REST
		SingerDto[] singers = restTemplate.getForObject( url, SingerDto[].class );

		return Arrays.asList( singers );
	}
	
	@CacheEvict(cacheNames="cacheCantantes")
	public void reset( ) {
	}

}
