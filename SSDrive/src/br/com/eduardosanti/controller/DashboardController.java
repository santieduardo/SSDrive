package br.com.eduardosanti.controller;

public class DashboardController {
	
	private String tituloDocumento;
	private String conteudoDocumento;
	
	public DashboardController(){
		
	}
	
	public DashboardController(String titulo, String conteudo){
		this.tituloDocumento = titulo;
		this.conteudoDocumento = conteudo;
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
