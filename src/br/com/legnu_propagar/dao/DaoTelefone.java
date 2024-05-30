package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.controller.Controller_CadastroCliente;
import br.com.legnu_propagar.controller.Controller_Introduxcao;
import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.model.Telefone;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DaoTelefone {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbTelefone();
		}
	}

	private static void criarTbTelefone() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbtelefone(id SERIAL," + "telefone TEXT,"
					+ "tipo VARCHAR(15)," + "idCliente INT," + "PRIMARY KEY (id),"
					+ "FOREIGN KEY (idCliente) REFERENCES interno.tbclientes (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbTelefone: " + e);
		}
	}

	public static void inserirTelefone(String tipo, String telefone, int idCliente) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbtelefone (tipo,telefone,idCliente) VALUES (?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();

			ComunicaxcaoUtil.getPst().setString(1, tipo);
			ComunicaxcaoUtil.getPst().setString(2, telefone);
			ComunicaxcaoUtil.getPst().setInt(3, idCliente);

			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbTelefone: " + e);
		}
	}

	public static void editarTelefone(Integer id, String tipo, String telefone) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbtelefone SET tipo=?, telefone=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();

			ComunicaxcaoUtil.getPst().setString(1, tipo);
			ComunicaxcaoUtil.getPst().setString(2, telefone);
			ComunicaxcaoUtil.getPst().setInt(3, id);

			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbTelefone: " + e);
		}
	}

	public static void deletarTelefone(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbtelefone WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();

			ComunicaxcaoUtil.getPst().setInt(1, id);

			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbTelefone: " + e);
		}
	}

	public static List<Telefone> gerarTabela() {
		List<Telefone> Telefones = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbtelefone WHERE idCliente = ? ORDER BY id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, Controller_CadastroCliente.getLblID());
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Telefone t = new Telefone();

				t.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				t.setTipo(ComunicaxcaoUtil.getRs().getString("tipo"));
				t.setTelefone(ComunicaxcaoUtil.getRs().getString("telefone"));

				Telefones.add(t);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return Telefones;

	}

}
