package com.distribuida.examen01.servidor03.servicios.mq;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.distribuida.examen01.servidor01.servicios.SingerService;
import com.distribuida.examen01.servidor01.servicios.dto.Singer;
import com.distribuida.examen01.servidor03.servicios.dto.Instrumento;

@Component(value="reporteMqListener")
public class ReporteMqListener implements MessageListener {

	@Autowired @Qualifier("singerService") private SingerService singerService;
	
	@Autowired private JavaMailSender mailSender;
	
	
	@Autowired ApplicationContext ctx;
	

	@PostConstruct
	public void kk( ) {
	
		String[] nn = ctx.getBeanDefinitionNames();
		
		for( String n:nn ) {
			System.out.println( "***************" + n );
		}
	}
	
    public void onMessage(Message message) {
    	
        if (message instanceof ObjectMessage ) {
        	
        	try 
            {
            	ObjectMessage objectMessage = (ObjectMessage )message;

            	Instrumento obj = (Instrumento )objectMessage.getObject( );

            	System.out.println( "Instrumento insertado-->" + obj.getId( ) );
            	
            	notificar( obj );
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    private void notificar( Instrumento obj ) {
    	
    	List<Singer> cantantes = singerService.listar( );
    	
    	cantantes.forEach( s->{
    		String mail = s.getMail( );
    		
    		System.out.println( "  notificando a: " + s.getFirstName( ) );
    		
    		enviarMail( mail, obj );
    		
    	});
    }
    
    private void enviarMail( String  mail, Instrumento obj ) {
    	
    	if( mail==null ) {
    		return ;
    	}
    	
    	SimpleMailMessage msg = new SimpleMailMessage( );
    	
        msg.setTo( mail );
        msg.setText( "Nuevo instrumento: " + obj.getId( ) );
        
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());            
        }
    }

}
