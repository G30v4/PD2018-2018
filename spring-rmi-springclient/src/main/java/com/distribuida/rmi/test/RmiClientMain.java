package com.distribuida.rmi.test;

import java.rmi.Naming;

import com.distribuida.rmi.servicios.ServicioOperaciones;

/**
 * 
 * @author jsalvador
 *
 */
public class RmiClientMain {
	
	public static final String SERVICE_NAME = "rmi://localhost:1099/Operaciones";
	
	public static void main( String args[] ) throws Exception {
		
		ServicioOperaciones servicio = (ServicioOperaciones )Naming.lookup( SERVICE_NAME );
		
		System.out.println( "servicio: " + servicio.getClass() );
		
		int respuesta = servicio.sumar( 1, 3 );
		
		System.out.println( "respuesta: " + respuesta );
	}
}


