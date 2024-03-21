package br.com.legnu_propagar.dao;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoEndereco {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbEndereco();
		}
	}

	private static void criarTbEndereco() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbendereco(id INT," + "rua TEXT,"
					+ "numero INT," + "complemento TEXT," + "bairro TEXT," + "cidade TEXT," + "pais TEXT," + "cep INT,"
					+ "estado VARCHAR(2)," + "idCliente INT," + "PRIMARY KEY (id),"
					+ "FOREIGN KEY (idCliente) REFERENCES interno.tbclientes (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbEndereco: " + e);
		}
	}

}
