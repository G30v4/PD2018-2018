package com.distribuida.examen01.servidor01.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackages = { "com.distribuida.examen01.servidor01.servicios" })
public class DataServiceConfig {

	@Bean
	public DataSource dataSource() {
		try {
			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
			
			EmbeddedDatabase dataSource = 
				dbBuilder.setType(EmbeddedDatabaseType.DERBY)
					.generateUniqueName( true )
					.addScript( "classpath:sql/schema.sql" )
					.addScript( "classpath:sql/test-data.sql" )
					.build();
			
			return dataSource;
			
		} catch (Exception e) {
			e.printStackTrace( );
			return null;
		}
	}
}
