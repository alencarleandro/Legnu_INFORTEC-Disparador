package br.com.legnu_propagar.model;

public class Ocorrencia {

	private int id;
	private int fk_idOcorrido;	
	private String ocorrencia;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_idOcorrido() {
		return fk_idOcorrido;
	}

	public void setFk_idOcorrido(int fk_idOcorrido) {
		this.fk_idOcorrido = fk_idOcorrido;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	
}
