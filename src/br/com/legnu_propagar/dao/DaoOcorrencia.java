package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Ocorrencia;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoOcorrencia {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbOcorrencia();
		}
	}

	private static void criarTbOcorrencia() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbocorrencia(id SERIAL," 
																			   + "fk_idOcorrido int,"
																			   + "tipo TEXT," 
																			   + "ocorrencia TEXT," 
																			   + "PRIMARY KEY (id));");
						
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbOcorrencia: " + e);
		}
	}

	public static void inserirOcorrencia(Integer idItem, String tipo, String ocorrencia) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbocorrencia (fk_idOcorrido,tipo,ocorrencia) VALUES (?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, idItem);
			ComunicaxcaoUtil.getPst().setString(2, tipo);
			ComunicaxcaoUtil.getPst().setString(3, ocorrencia);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbOcorrencia: " + e);
		}
	}

	public static void editarOcorrencia(Integer id, Integer idItem, String tipo, String ocorrencia) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbocorrencia SET fk_idOcorrido=?, tipo=?, ocorrencia=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, idItem);
			ComunicaxcaoUtil.getPst().setString(2, tipo);
			ComunicaxcaoUtil.getPst().setString(3, ocorrencia);
			ComunicaxcaoUtil.getPst().setInt(4, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbOcorrencia: " + e);
		}
	}

	public static void deletarOcorrencia(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbocorrencia WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbOcorrencia: " + e);
		}
	}
	
	public static void limparOcorrencias() {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbocorrencia WHERE id >= 0;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbOcorrencia: " + e);
		}
	}

	public static List<Ocorrencia> gerarOcorrenciasDeClientes() {
		List<Ocorrencia> Ocorrencias = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbocorrencia where tipo = 'Cliente'");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Ocorrencia o = new Ocorrencia();
				
				o.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				o.setTipo(ComunicaxcaoUtil.getRs().getString("tipo"));
				o.setFk_idOcorrido(ComunicaxcaoUtil.getRs().getInt("fk_idOcorrido"));
				o.setOcorrencia(ComunicaxcaoUtil.getRs().getString("ocorrencia"));
								
				Ocorrencias.add(o);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Ocorrencias;

	}
	
	public static List<Ocorrencia> gerarOcorrenciasDeGrupos() {
		List<Ocorrencia> Ocorrencias = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbocorrencia where tipo = 'Grupo'");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Ocorrencia o = new Ocorrencia();
				
				o.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				o.setTipo(ComunicaxcaoUtil.getRs().getString("tipo"));
				o.setFk_idOcorrido(ComunicaxcaoUtil.getRs().getInt("fk_idOcorrido"));
				o.setOcorrencia(ComunicaxcaoUtil.getRs().getString("ocorrencia"));
								
				Ocorrencias.add(o);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Ocorrencias;

	}
	
}
