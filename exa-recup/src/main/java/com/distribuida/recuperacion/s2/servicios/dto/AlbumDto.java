package com.distribuida.recuperacion.s2.servicios.dto;

import java.io.Serializable;
import java.util.Date;

public class AlbumDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer singerId;
	private String title;
	private Date releaseDate;
	private Integer version;
	
	// calculado
	private String singerName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSingerId() {
		return singerId;
	}

	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

}
