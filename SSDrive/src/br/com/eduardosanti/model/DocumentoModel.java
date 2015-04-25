package br.com.eduardosanti.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.com.eduardosanti.interfaces.DocumentoInterface;

public class DocumentoModel extends UnicastRemoteObject implements DocumentoInterface{

	private String titulo;
	private String conteudo;
	
	public DocumentoModel() throws RemoteException{
		
	}
	
	@Override
	public String getConteudoDocumento() throws RemoteException {
		return this.conteudo;
	}

	@Override
	public void setConteudoDocumento(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String getTituloDocumento() throws RemoteException {
		return this.titulo;
	}

	@Override
	public void setTituloDocumento(String titulo) {
		this.titulo = titulo;
	}

	
}
