package com.copili.feeder.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfiguration {

	private final static Logger log = LoggerFactory.getLogger( PropertiesConfiguration.class );

	@Bean( name = "chanchiProperties" )
	public PropertiesFactoryBean chanchitestProperties() {
		log.debug( "Instanciando chanchi.properties" );
		return instantiateProperties( "chanchi" );
	}

	@Bean( name = "databaseProperties" )
	public PropertiesFactoryBean databaseProperties() {
		log.debug( "Instanciando database.properties" );
		return instantiateProperties( "database" );
	}

	@Bean( name = "mailerProperties" )
	public PropertiesFactoryBean mailerProperties() {
		log.debug( "Instanciando email.properties" );
		return instantiateProperties( "email" );
	}

	private PropertiesFactoryBean instantiateProperties( String propertiesFileName ) {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocation( new ClassPathResource( propertiesFileName + ".properties" ) );
		return properties;
	}

}
