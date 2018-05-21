package com.distribuida.http.servicios;

import org.springframework.stereotype.Component;

/**
 * 
 * @author jsalvador
 *
 */
@Component(value="servicioOp")
public class ServicioOperacionesImpl implements ServicioOperaciones {

	public int sumar( int n1, int n2 ) {
		
		return n1 + n2;
	}
}


