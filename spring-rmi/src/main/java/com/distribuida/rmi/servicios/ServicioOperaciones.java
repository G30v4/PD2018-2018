package com.distribuida.rmi.servicios;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author jsalvador
 *
 */
public interface ServicioOperaciones extends Remote {

	public int sumar( int n1, int n2 ) throws RemoteException;
}

