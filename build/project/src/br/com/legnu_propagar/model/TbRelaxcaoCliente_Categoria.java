package br.com.legnu_propagar.model;

public class TbRelaxcaoCliente_Categoria {

	private int fk_idCliente;
	private int fk_idCategoria;

	public int getFk_idCliente() {
		return fk_idCliente;
	}

	public void setFk_idCliente(int fk_idCliente) {
		this.fk_idCliente = fk_idCliente;
	}

	public int getFk_idCategoria() {
		return fk_idCategoria;
	}

	public void setFk_idCategoria(int fk_idCategoria) {
		this.fk_idCategoria = fk_idCategoria;
	}
}
