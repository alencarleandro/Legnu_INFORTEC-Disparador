package br.com.legnu_propagar.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class DaoCategoria {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbCategoria();
		}
	}

	private static void criarTbCategoria() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbcategoria(id SERIAL," + "nome VARCHAR(200),"
					+ "PRIMARY KEY (id), UNIQUE(nome));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbCategoria: " + e);
		}
	}

	public static void inserirCategoria(String nome) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbcategoria (nome) VALUES (?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbCategoria: " + e);
		}
	}

	public static void editarCategoria(Integer id, String nome) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbcategoria SET nome=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setInt(2, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbCategoria: " + e);
		}
	}

	public static void deletarCategoria(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbclientes SET fk_Categoria=null WHERE fk_Categoria=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();

			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbcategoria WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbCategoria: " + e);
		}
	}

	public static Integer idCategoria(String nome) {
		int id = -1;
		try {

			ComunicaxcaoUtil.setSql("SELECT id FROM interno.tbcategoria WHERE nome = ?");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				id = (ComunicaxcaoUtil.getRs().getInt("id"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}

	public static List<Categoria> gerarTabela() {
		List<Categoria> Categorias = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbcategoria ORDER BY id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Categoria c = new Categoria();
				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				Categorias.add(c);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Categorias;

	}

}
