package com.distribuida.examen01.servidor03.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.distribuida.examen01.servidor03.servicios.dto.Instrumento;

@Component
@Transactional
public class InstrumentoServiceImpl implements InstrumentoService {

	@Autowired private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired private JmsTemplate jmsTemplate;
	
	@PostConstruct
	public void inicializar( ) {
		jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	@Override
	public List<Instrumento> listar() {
		String sql = "select * from instrument";
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Instrumento> ret = new ArrayList<Instrumento>();
		
		rows.forEach( s->{
			Instrumento dto = new Instrumento( );
			
			dto.setId( (String )s.get("INSTRUMENT_ID") );
			
			ret.add( dto );
		});
		
		return ret;
	}
	
	public void crear( Instrumento obj ) {
		
		StringBuilder sb = new StringBuilder( );
		
		sb.append( " insert into instrument" );
		sb.append( "   (INSTRUMENT_ID)" );
		sb.append( " values" );
		sb.append( "  (?)" );
		
		Object[] params = { obj.getId() };
		
		jdbcTemplate.update( sb.toString( ), params );
		
		jmsTemplate.send( new MessageCreator( ) {
			
			@Override
			public Message createMessage(Session session) throws JMSException {

				return session.createObjectMessage( obj );
			}
		});
	}

}
