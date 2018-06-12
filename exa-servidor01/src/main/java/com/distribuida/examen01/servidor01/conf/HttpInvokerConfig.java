package com.distribuida.examen01.servidor01.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.distribuida.examen01.servidor01.servicios.SingerService;

@Configuration
public class HttpInvokerConfig {

	@Bean(name = "/singer")
	public HttpInvokerServiceExporter singerHttpInvokerServiceExporter( SingerService servicio ) {
		HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
		invokerService.setService( servicio );
		invokerService.setServiceInterface( SingerService.class );
		return invokerService;
	}
}
