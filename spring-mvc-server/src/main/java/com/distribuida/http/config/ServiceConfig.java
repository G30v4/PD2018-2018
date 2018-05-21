package com.distribuida.http.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author jsalvador
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.distribuida.http..servicios" })
public class ServiceConfig {

}
