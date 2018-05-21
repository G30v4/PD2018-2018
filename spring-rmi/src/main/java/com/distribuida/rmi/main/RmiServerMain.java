package com.distribuida.rmi.main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.distribuida.rmi.config.RmiServerConfig;

/**
 * 
 * @author jsalvador
 *
 */
public class RmiServerMain {
	
	public static void main( String args[] ) throws IOException {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext( RmiServerConfig.class );

		System.out.println( "Servidor RMI iniciado..." );
		
		System.in.read( );
		
		context.close( );
	}
}


