package br.com.legnu_propagar.model;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class Endereco {
	public static String Rua;
	public static int Numero;
	public static String Complemento;
	public static String Bairro;
	public static String Cidade;
	public static String Pais;
	public static int Cep;
	public static String Estado;
	public static int idCliente;

	public static String getRua() {
		return Rua;
	}

	public static void setRua(String rua) {
		Rua = rua;
	}

	public static int getNumero() {
		return Numero;
	}

	public static void setNumero(int numero) {
		Numero = numero;
	}

	public static String getComplemento() {
		return Complemento;
	}

	public static void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public static String getBairro() {
		return Bairro;
	}

	public static void setBairro(String bairro) {
		Bairro = bairro;
	}

	public static String getCidade() {
		return Cidade;
	}

	public static void setCidade(String cidade) {
		Cidade = cidade;
	}

	public static String getPais() {
		return Pais;
	}

	public static void setPais(String pais) {
		Pais = pais;
	}

	public static int getCep() {
		return Cep;
	}

	public static void setCep(int cep) {
		Cep = cep;
	}

	public static String getEstado() {
		return Estado;
	}

	public static void setEstado(String estado) {
		Estado = estado;
	}
}
