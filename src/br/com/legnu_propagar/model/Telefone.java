package br.com.legnu_propagar.model;

public class Telefone {

	private int id;
	private String telefone;
	private int idCliente;
	private int fk_idCliente;
	private String Tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getFk_idCliente() {
		return fk_idCliente;
	}

	public void setFk_idCliente(int fk_idCliente) {
		this.fk_idCliente = fk_idCliente;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

}
