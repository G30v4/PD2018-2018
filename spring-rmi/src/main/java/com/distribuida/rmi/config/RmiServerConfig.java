package com.distribuida.rmi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.distribuida.rmi.servicios.ServicioOperaciones;

/**
 * 
 * @author jsalvador
 *
 */
@Configuration
@ComponentScan(basePackages="com.distribuida.rmi.servicios")
public class RmiServerConfig {
	
	@Bean(name="operacionesRmi")
	public RmiServiceExporter operaciones( ServicioOperaciones servicioOperaciones ) {
		
		RmiServiceExporter exporter = new RmiServiceExporter( );
		
		exporter.setServiceName( "Operaciones" );
		exporter.setService( servicioOperaciones );
		exporter.setServiceInterface( ServicioOperaciones.class );
		
		return exporter;
	}
}

