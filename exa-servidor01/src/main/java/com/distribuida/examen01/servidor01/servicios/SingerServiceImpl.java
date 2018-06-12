package com.distribuida.examen01.servidor01.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.distribuida.examen01.servidor01.servicios.dto.Singer;

@Component
@Transactional
public class SingerServiceImpl implements SingerService {

	@Autowired private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void inicializar( ) {
		jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	@Override
	public List<Singer> listar() {
		String sql = "select * from singer";
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Singer> ret = new ArrayList<Singer>();
		
		rows.forEach( s->{
			Singer dto = new Singer( );
			
			dto.setId( (Integer )s.get("id") );
			dto.setFirstName( (String )s.get("first_name") );
			dto.setLastName( (String )s.get("last_name") );
			dto.setBirthDate( (Date )s.get("birth_date") );
			dto.setVersion( (Integer )s.get("version") );
			dto.setMail( (String )s.get("mail") );
			
			ret.add( dto );
		});
		
		return ret;
	}
	
	public void crear( Singer obj ) {
		
		StringBuilder sb = new StringBuilder( );
		
		sb.append( " insert into singer" );
		sb.append( "   (first_name, last_name, birth_date, version, mail)" );
		sb.append( " values" );
		sb.append( "  (?,?,?,?,?)" );
		
		Object[] params = { obj.getFirstName(), obj.getLastName(), obj.getBirthDate(), 1, obj.getMail() };
		
		jdbcTemplate.update( sb.toString( ), params );
	}

}
