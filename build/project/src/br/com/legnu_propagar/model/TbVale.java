package br.com.legnu_propagar.model;

import java.util.Date;

public class TbVale {

	private int id;
	private String Vale;
	private Date Vencimento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVale() {
		return Vale;
	}

	public void setVale(String vale) {
		Vale = vale;
	}

	public Date getVencimento() {
		return Vencimento;
	}

	public void setVencimento(Date vencimento) {
		Vencimento = vencimento;
	}
}
