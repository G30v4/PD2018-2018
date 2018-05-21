package com.distribuida.http.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.distribuida.http.servicios.ServicioOperaciones;

/**
 * 
 * @author jsalvador
 *
 */
@Configuration
public class HttpInvokerConfig {
	
	@Bean(name = "/operaciones")
	public HttpInvokerServiceExporter op( ServicioOperaciones servicio ) {
		HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
		invokerService.setService( servicio );
		invokerService.setServiceInterface( ServicioOperaciones.class );
		return invokerService;
	}

}
