package com.distribuida.s1.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.distribuida.s1.servicios.dto.SingerDto;

@Service
@Transactional
public class ServicioSingerImpl implements ServicioSinger {

	public static final String topicExchangeName = "distribuida-exchange";

	@Autowired private RabbitTemplate rabbitTemplate;
	

	@Override
	public List<SingerDto> listar() {
		List<SingerDto> ret = new ArrayList<>();
		
		SingerDto dto = new SingerDto( );
		
		dto.setId( 1 );
		dto.setFirstName( "nombre 1" );
		dto.setLastName( "apellido 1" );
		dto.setVersion( 1 );
		dto.setBirthDate( new Date() );
		
		ret.add( dto );
		
		dto = new SingerDto( );
		
		dto.setId( 2 );
		dto.setFirstName( "nombre 2" );
		dto.setLastName( "apellido 2" );
		dto.setVersion( 2 );
		dto.setBirthDate( new Date() );
		
		ret.add( dto );
		
		return ret;
	}

	@Override
	public void agregar(SingerDto dto) {
		
		// agregar en la base de datos
		
		// notificar
		System.out.println( "Notificando..." );
        
        rabbitTemplate.convertAndSend( ServicioSingerImpl.topicExchangeName, "foo.bar.baz", dto );
	}

}
