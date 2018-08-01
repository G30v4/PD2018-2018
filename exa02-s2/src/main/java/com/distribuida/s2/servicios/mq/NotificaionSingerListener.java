package com.distribuida.s2.servicios.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.distribuida.s1.servicios.dto.SingerDto;
import com.distribuida.s2.servicios.CacheSinger;

@Component
public class NotificaionSingerListener {

	@Autowired private CacheSinger cache;
	
	public void receiveMessage( SingerDto obj ) {
		
		System.out.println("Notificacion de refresco de cache recibida: <" + obj + ">");
		
		if( obj==null ) 
		{
			// viene del web
			cache.reset( );
		}
		else
		{
			cache.agregar( obj.getId(), obj );
		}
		
		// volver a cargar todos los datos
		//cache.reset( );
		
		// agregar el objeto agregado
		// cache.agregar( obj.getId(), obj );
	}
}
