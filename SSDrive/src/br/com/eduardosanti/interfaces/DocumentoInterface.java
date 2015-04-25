package br.com.eduardosanti.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DocumentoInterface extends Remote {
	
	public String getConteudoDocumento() throws RemoteException;
	public void setConteudoDocumento(String conteudoDocumento) throws RemoteException;
	public String getTituloDocumento() throws RemoteException;	
	public void setTituloDocumento(String tituloDocumento) throws RemoteException;
	
}