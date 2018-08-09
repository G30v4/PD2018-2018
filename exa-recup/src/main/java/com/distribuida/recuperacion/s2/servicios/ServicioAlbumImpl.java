package com.distribuida.recuperacion.s2.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.distribuida.recuperacion.s2.servicios.dto.AlbumDto;
import com.distribuida.s1.servicios.dto.SingerDto;

@Service
@Transactional
public class ServicioAlbumImpl implements ServicioAlbum {

	@Autowired private ServicioSinger servicioSinger;

	@Override
	public List<AlbumDto> listar() {
		
		List<SingerDto> singers = servicioSinger.listar( );
		
		// listar los albums
		List<AlbumDto> albums = new ArrayList<>( );
				
		AlbumDto dto = new AlbumDto( );
		
		dto.setId( 1 );
		dto.setSingerId( 1 );
		dto.setTitle( "album 01" );
		
		albums.add( dto );
		
		albums.forEach( s->{
			Integer id = s.getSingerId( );
			
			// buscar el cantante
			SingerDto singer = singers.stream()
				.filter( obj->obj.getId().equals(id) )
				.findFirst()
				.orElse( null );
			
			if( singer!=null ) {
				s.setSingerName( singer.getFirstName( ) + " " + singer.getLastName( ) );
			}
		});
		
		return albums;
	}

}
