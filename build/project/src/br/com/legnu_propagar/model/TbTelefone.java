package br.com.legnu_propagar.model;

public class TbTelefone {

	private int id;
	private int telefone;

	private enum tipo {
		CELULAR, FIXO, SEM_FORMATAXCAO
	};

	private int fk_idCliente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public int getFk_idCliente() {
		return fk_idCliente;
	}

	public void setFk_idCliente(int fk_idCliente) {
		this.fk_idCliente = fk_idCliente;
	}
}
