package com.distribuida.s2.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.distribuida.s1.servicios.dto.SingerDto;
import com.distribuida.s2.servicios.dto.AlbumDto;

@Service
@Transactional
public class ServicioAlbumImpl implements ServicioAlbum {

	@Autowired private CacheSinger cache;
	
	@Override
	public List<AlbumDto> listar() {
		
		Map<Integer, SingerDto> singers = cache.refrescar( );
		
		// listar los albums
		List<AlbumDto> albums = new ArrayList<>( );
				
		AlbumDto dto = new AlbumDto( );
		
		dto.setId( 1 );
		dto.setSingerId( 1 );
		dto.setTitle( "album 01" );
		
		albums.add( dto );
		
		albums.forEach( s->{
			Integer id = s.getId( );
			
			SingerDto singer = singers.get( id );
			
			if( singer!=null ) {
				s.setSingerName( singer.getFirstName( ) + " " + singer.getLastName( ) );
			}
		});
		
		return albums;
	}

}
