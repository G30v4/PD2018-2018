package com.distribuida.http.client.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.distribuida.http.servicios.ServicioOperaciones;

/**
 * 
 * @author jsalvador
 *
 */
@Configuration
@PropertySource(value="classpath:cliente.properties")
public class HttpClientConfig {

	@Autowired Environment env;
	
	@Bean
	public HttpInvokerProxyFactoryBean operaciones() {
		
		// mirar cliente.properties
		String url = env.getProperty( "servicio.sumar" );
		
		System.out.println( "URL del servicio==>" + url );
		
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        
        proxy.setServiceUrl( url );
        proxy.setServiceInterface( ServicioOperaciones.class);
        
        return proxy;
	}
}
