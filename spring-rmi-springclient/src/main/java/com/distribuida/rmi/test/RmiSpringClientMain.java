package com.distribuida.rmi.test;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.distribuida.rmi.servicios.ServicioOperaciones;

/**
 * 
 * @author jsalvador
 *
 */
public class RmiSpringClientMain {
	
	public static final String SERVICE_NAME = "rmi://localhost:1099/Operaciones";
	
	@Configuration
	public static class RmiClientConfig{
		
		@Bean
		public RmiProxyFactoryBean operaciones( ) {
			
			RmiProxyFactoryBean proxy = new RmiProxyFactoryBean( );
			
			proxy.setServiceUrl( SERVICE_NAME );
			
			proxy.setServiceInterface( ServicioOperaciones.class );
			
			return proxy;
		}
	}
	
	public static void main( String args[] ) throws IOException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( RmiClientConfig.class );

		ServicioOperaciones servicio = context.getBean( ServicioOperaciones.class );
		
		System.out.println( "servicio: " + servicio.getClass() );
		
		int respuesta = servicio.sumar( 1, 3 );
		
		System.out.println( "respuesta: " + respuesta );
		
		context.close( );
	}
}




