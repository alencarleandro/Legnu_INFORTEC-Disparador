package br.com.legnu_propagar.model;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class Email {

	private static int id;
	private static String email;
	private static int fk_idCliente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFk_idCliente() {
		return fk_idCliente;
	}

	public void setFk_idCliente(int fk_idCliente) {
		this.fk_idCliente = fk_idCliente;
	}
}
