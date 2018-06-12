package com.distribuida.examen01.servidor03.conf;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.distribuida.examen01.servidor01.servicios.SingerService;

@Configuration
public class JavaMailConfig {

	public static final String SINGER_SERVICE = "http://localhost:8180/exa-servidor01/invoker/singer";
	
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("127.0.0.1");
        mailSender.setPort(25);

        mailSender.setUsername("xx@gmail.com");
        mailSender.setPassword("yy");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    
	// importar el servicio SINGER
	@Bean
	public HttpInvokerProxyFactoryBean singerService() {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl(SINGER_SERVICE);
		proxy.setServiceInterface(SingerService.class);
		return proxy;
	}
}

