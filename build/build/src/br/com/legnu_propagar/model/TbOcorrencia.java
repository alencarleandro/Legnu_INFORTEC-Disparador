package br.com.legnu_propagar.model;

public class TbOcorrencia {

	private int id;
	private int fk_idOcorrido;

	private enum tipo {
		GRUPO, CLIENTE
	};

	private String Ocorrencia;

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
		return Ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		Ocorrencia = ocorrencia;
	}
}
