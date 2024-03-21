package br.com.legnu_propagar.model;

public class TbRelaxcaoGrupo_Categoria {

	private int fk_idGrupo;
	private int fk_idCategoria;

	public int getFk_idGrupo() {
		return fk_idGrupo;
	}

	public void setFk_idGrupo(int fk_idGrupo) {
		this.fk_idGrupo = fk_idGrupo;
	}

	public int getFk_idCategoria() {
		return fk_idCategoria;
	}

	public void setFk_idCategoria(int fk_idCategoria) {
		this.fk_idCategoria = fk_idCategoria;
	}
}
