package com.distribuida.s2;

import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.distribuida.s2.servicios.ServicioAlbum;
import com.distribuida.s2.servicios.dto.AlbumDto;
import com.distribuida.s2.servicios.mq.NotificaionSingerListener;


@SpringBootApplication
public class Exa02S2Application implements CommandLineRunner {

	public static final String topicExchangeName = "distribuida-exchange";

    public static final String queueName = "distribuida";

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    public SimpleMessageListenerContainer container1(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        
    	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        
    	container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        
        return container;
    }
    
    @Bean
    public MessageListenerAdapter listenerAdapter(NotificaionSingerListener receiver) {
        return new MessageListenerAdapter( receiver, "receiveMessage" );
    }
    
	public static void main(String[] args) {
		SpringApplication.run(Exa02S2Application.class, args);
	}
	
	//---------------------------------------
	
	@Autowired private ServicioAlbum servicio;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<AlbumDto> albums1 = servicio.listar( );
		List<AlbumDto> albums2 = servicio.listar( );
		List<AlbumDto> albums3 = servicio.listar( );
		List<AlbumDto> albums4 = servicio.listar( );
		List<AlbumDto> albums5 = servicio.listar( );
		
		albums5.forEach( s->{
			System.out.println( "id: " + s.getId() + ", title=" + s.getTitle() + ", singer=" + s.getSingerName() );
		});
	}
}
