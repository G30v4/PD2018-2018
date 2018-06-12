package com.distribuida.examen01.servidor03.conf;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Inicializar la aplicación WEB
 * @author jsalvador
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// root appContext
	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { DataServiceConfig.class, ArtemisMqConfig.class, JavaMailConfig.class };

	}

	// web appContext
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { WebConfig.class, HttpInvokerConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		 return new String[]{"/invoker/*"};
	}
	
	 @Override
	 protected Filter[] getServletFilters() {
	   
		 CharacterEncodingFilter cef = new CharacterEncodingFilter();
		 cef.setEncoding("UTF-8");
		 cef.setForceEncoding(true);
	   
		 return new Filter[]{new HiddenHttpMethodFilter(), cef};
	 }


}
