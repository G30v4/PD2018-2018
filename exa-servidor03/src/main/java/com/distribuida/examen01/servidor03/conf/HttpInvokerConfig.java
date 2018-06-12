package com.distribuida.examen01.servidor03.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.distribuida.examen01.servidor03.servicios.InstrumentoService;

@Configuration
public class HttpInvokerConfig {

	@Bean(name = "/instrument")
	public HttpInvokerServiceExporter instrumentHttpInvokerServiceExporter( InstrumentoService servicio ) {
		HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
		invokerService.setService( servicio );
		invokerService.setServiceInterface( InstrumentoService.class );
		return invokerService;
	}
}
