package br.com.eduardosanti.controller;

import java.io.Serializable;

import br.com.eduardosanti.server.Cliente;

public class DashboardController {
	
	private String tituloDocumento;
	private String conteudoDocumento;
	
	public DashboardController(){
		
	}
	
	public DashboardController(String titulo, String conteudo){
		this.tituloDocumento = titulo;
		this.conteudoDocumento = conteudo;
		
		Cliente c = new Cliente();
		c.chamaCli(this.tituloDocumento, this.conteudoDocumento);
	}
	
	public String getConteudoDocumento() {
		return this.conteudoDocumento;
	}
	
	public void setConteudoDocumento(String conteudoDocumento) {
		this.conteudoDocumento = conteudoDocumento;
	}
	
	public String getTituloDocumento() {
		return this.tituloDocumento;
	}
	
	public void setTituloDocumento(String tituloDocumento) {
		this.tituloDocumento = tituloDocumento;
	}

}
