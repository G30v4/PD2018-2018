package com.distribuida.http.client.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Inicializar la aplicación WEB
 * @author jsalvador
 *
 */
public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		System.out.println( "Inicializando contexto..." );
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext( );
		
		context.register( HttpClientConfig.class );
		
		servletContext.addListener( new ContextLoaderListener(context) );
	} 
}
