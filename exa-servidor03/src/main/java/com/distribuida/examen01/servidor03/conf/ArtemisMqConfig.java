package com.distribuida.examen01.servidor03.conf;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.api.jms.JMSFactoryType;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@EnableJms
public class ArtemisMqConfig {
	
	@Bean
	public JmsTemplate jmsTemplate( ConnectionFactory cf ) {
		JmsTemplate jmsTemplate = new JmsTemplate( cf );
		jmsTemplate.setDefaultDestinationName( "/queue/singer" );
		return jmsTemplate;
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		Map<String, Object> connDetails = new HashMap<>();
		
		connDetails.put(TransportConstants.HOST_PROP_NAME, "0.0.0.0");
		connDetails.put(TransportConstants.PORT_PROP_NAME, "61616");
		connDetails.put(TransportConstants.PROTOCOLS_PROP_NAME, "tcp");

		TransportConfiguration transportConfiguration = new TransportConfiguration(
				NettyConnectorFactory.class.getName(), connDetails);
		
		return ActiveMQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF,transportConfiguration);
		
		//return new ActiveMQConnectionFactory(false, transportConfiguration);
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		
		factory.setConnectionFactory(connectionFactory());
		//factory.setDestinationResolver(new BeanFactoryDestinationResolver(springContextBeanFactory));
		factory.setConcurrency("3-10");
		
		return factory;
	}
	
	//--------------------------------------------------------------------------------------------------
	//01. -- configuracion listener
	@Bean
	public DefaultMessageListenerContainer singerContainer( 
		ConnectionFactory connectionFactory, 
		@Qualifier("reporteMqListener") MessageListener listener ) {
		
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer( );
		container.setDestinationName( "/queue/singer" );
		container.setConnectionFactory( connectionFactory );
		container.setMessageListener( listener );
		
		return container;
	}
}
