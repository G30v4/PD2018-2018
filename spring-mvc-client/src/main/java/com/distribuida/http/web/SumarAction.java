package com.distribuida.http.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.distribuida.http.servicios.ServicioOperaciones;

/**
 * 
 * @author jsalvador
 *
 */
@ManagedBean(name="sumarAction")
@SessionScoped
public class SumarAction {

	private int n1;
	private int n2;
	private int respuesta;
	
	private ServicioOperaciones servicio;
	
	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}

	@PostConstruct
	public void inicializar( ) {
		
		ApplicationContext context = 
				FacesContextUtils.getRequiredWebApplicationContext( FacesContext.getCurrentInstance() );
		
		servicio = context.getBean( ServicioOperaciones.class );
	}
	
	public String sumar( ) {
		
		respuesta = servicio.sumar( n1, n2 );
		
		return "";
	}
}
