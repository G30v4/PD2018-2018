package com.distribuida.examen01.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.distribuida.examen01.controller.servicios.InstrumentoService;
import com.distribuida.examen01.controller.servicios.SingerService;

@Configuration
@ComponentScan(basePackages="com.distribuida.examen01.controller")
public class HttpInvokerConfig {

	public static final String SINGER_SERVICE    = "http://localhost:8180/exa-servidor01/invoker/singer";
	public static final String INSTRUMENT_ERVICE = "http://localhost:8380/exa-servidor03/invoker/instrument";
	
	//---------------------------exportar
	@Bean(name = "/singer")
	public HttpInvokerServiceExporter singerServiceExporter( SingerService servicio ) {
		HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
		invokerService.setService( servicio );
		invokerService.setServiceInterface( SingerService.class );
		return invokerService;
	}
	
	@Bean(name = "/instrument")
	public HttpInvokerServiceExporter instrumentServiceExporter( InstrumentoService servicio ) {
		HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
		invokerService.setService( servicio );
		invokerService.setServiceInterface( InstrumentoService.class );
		return invokerService;
	}

	//---------------------------importar
	@Bean
	public HttpInvokerProxyFactoryBean singerService( ) {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl(SINGER_SERVICE);
		proxy.setServiceInterface(SingerService.class);
		return proxy;
	}
	
	@Bean
	public HttpInvokerProxyFactoryBean instrumentService( ) {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl(INSTRUMENT_ERVICE);
		proxy.setServiceInterface(InstrumentoService.class);
		return proxy;
	}
}
