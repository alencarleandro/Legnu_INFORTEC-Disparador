package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Profiles;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoProfiles {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbProfile();
		}
	}

	private static void criarTbProfile() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbprofile(id SERIAL," + "titulo TEXT,"
					+ "path TEXT," + "PRIMARY KEY (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbProfile: " + e);
		}
	}

	public static void inserirProfile(String titulo, String path) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbprofile (titulo,path) VALUES (?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, titulo);
			ComunicaxcaoUtil.getPst().setString(2, path);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbProfile: " + e);
		}
	}

	public static void editarProfile(Integer id, String titulo, String path) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbprofile SET titulo=?, path=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, titulo);
			ComunicaxcaoUtil.getPst().setString(2, path);
			ComunicaxcaoUtil.getPst().setInt(3, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbProfile: " + e);
		}
	}

	public static void deletarProfile(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbprofile WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbProfile: " + e);
		}
	}

	public static List<Profiles> gerarTabela() {
		List<Profiles> Profiles = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbprofile ORDER BY id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Profiles p = new Profiles();
				p.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				p.setTitulo(ComunicaxcaoUtil.getRs().getString("titulo"));
				p.setPath(ComunicaxcaoUtil.getRs().getString("path"));
				Profiles.add(p);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Profiles;

	}

	public static List<Profiles> gerarTabelaPorTitulo(String titulo) {
		List<Profiles> Profiles = new ArrayList<Profiles>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbprofile "
					+ "WHERE interno.tbprofile.titulo LIKE ? ORDER BY interno.tbprofile.id ASC;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, titulo + "%");
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Profiles p = new Profiles();
				p.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				p.setTitulo(ComunicaxcaoUtil.getRs().getString("titulo"));
				p.setPath(ComunicaxcaoUtil.getRs().getString("path"));
				Profiles.add(p);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Profiles;

	}

}
