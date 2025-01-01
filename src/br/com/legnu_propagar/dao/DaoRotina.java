package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Profiles;
import br.com.legnu_propagar.model.Rotina_de_Disparo;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoRotina {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbRotina();
		}
	}

	private static void criarTbRotina() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbrotina(id SERIAL," 
																			   + "rotina TEXT," 
																			   + "PRIMARY KEY (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbRotina: " + e);
		}
	}

	public static void inserirRotina(String rotina) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbrotina (rotina) VALUES (?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, rotina);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Inserir em tbRotina: " + e);
		}
	}

	public static void editarRotina(Integer id, String rotina) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbrotina SET rotina=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, rotina);
			ComunicaxcaoUtil.getPst().setInt(2, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar tbRotina: " + e);
		}
	}

	public static void deletarRotina() {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbrotina WHERE id >= 0;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Deletar em tbRotina: " + e);
		}
	}

	public static List<Rotina_de_Disparo> gerarTabela() {
		List<Rotina_de_Disparo> Rotina = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbrotina ORDER BY id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Rotina_de_Disparo r = new Rotina_de_Disparo();
				r.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				r.setRotina(ComunicaxcaoUtil.getRs().getString("rotina"));
				Rotina.add(r);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Rotina;

	}
	
	public static Rotina_de_Disparo pegarUltimo() {
		Rotina_de_Disparo r = new Rotina_de_Disparo();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbrotina ORDER BY id DESC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				r.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				r.setRotina(ComunicaxcaoUtil.getRs().getString("rotina"));
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return r;

	}
	
}
