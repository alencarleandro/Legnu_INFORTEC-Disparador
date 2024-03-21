package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.util.busca.PegarDatasUtil;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoGrupos {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbGrupos();
		}
	}

	private static void criarTbGrupos() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbgrupos(id SERIAL," + "nome TEXT,"
					+ "link VARCHAR(14)," + "ultimoEnvio DATE," + "fk_Categoria INT," + "PRIMARY KEY (id),"
					+ "FOREIGN KEY (fk_Categoria) " + "REFERENCES interno.tbcategoria (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}

	public static void inserirGrupo(String nome, String link, Integer fk_Categoria) {
		try {
			ComunicaxcaoUtil
					.setSql("INSERT INTO interno.tbgrupos (nome, link ,fk_Categoria, ultimoEnvio) VALUES (?,?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setString(2, link);
			ComunicaxcaoUtil.getPst().setInt(3, fk_Categoria);
			ComunicaxcaoUtil.getPst().setDate(4, PegarDatasUtil.dataPadrao());
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}

	public static void inserirGrupo(String nome, String link) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbgrupos (nome, link, ultimoEnvio) VALUES (?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setString(2, link);
			ComunicaxcaoUtil.getPst().setDate(3, PegarDatasUtil.dataPadrao());
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}

	public static void editarGrupo(Integer id, String nome, Integer fk_Categoria, String link) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbgrupos SET nome=?, link=?, fk_Categoria=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setString(2, link);
			ComunicaxcaoUtil.getPst().setInt(3, fk_Categoria);
			ComunicaxcaoUtil.getPst().setInt(4, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}
	
	public static void editarGrupo(Integer id, String nome) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbgrupos SET nome=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setInt(2, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}

	public static void editarGrupo(Integer id, String nome, String link) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbgrupos SET nome=?, link=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setString(2, link);
			ComunicaxcaoUtil.getPst().setInt(3, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}

	public static void deletarGrupo(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbgrupos WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbGrupos: " + e);
		}
	}

	public static List<Grupos> gerarTabela() {
		List<Grupos> Grupos = new ArrayList<Grupos>();
		try {

			ComunicaxcaoUtil.setSql("SELECT interno.tbgrupos.id as id, " 
								  + " interno.tbgrupos.nome as nome, "
								  + " interno.tbgrupos.link as link, " 
								  + " interno.tbgrupos.fk_categoria as fk_categoria, " 
								  + " interno.tbgrupos.ultimoenvio as ultimoenvio, " 
								  + " interno.tbcategoria.nome as categoria "
								  + "FROM interno.tbgrupos " 
								  + "LEFT JOIN interno.tbcategoria "
								  + "ON interno.tbgrupos.fk_Categoria = interno.tbcategoria.id "
								  + "ORDER BY interno.tbgrupos.id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			int i = 0;
			while (ComunicaxcaoUtil.getRs().next()) {
				Grupos g = new Grupos();

				g.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				g.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				g.setLink(ComunicaxcaoUtil.getRs().getString("link"));
				g.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));
				g.setUltimoEnvio(ComunicaxcaoUtil.getRs().getDate("ultimoenvio"));
				g.setIdCategoria(ComunicaxcaoUtil.getRs().getInt("fk_categoria"));
				g.setPosicao(i);

				Grupos.add(g);
				i++;
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return Grupos;

	}

	public static List<Grupos> gerarTabelaComFiltro() {
		List<Grupos> Grupos = new ArrayList<Grupos>();
		try {

			ComunicaxcaoUtil.setSql("SELECT interno.tbgrupos.id as id, " + " interno.tbgrupos.nome as nome, "
					+ " interno.tbgrupos.link as link, " + " interno.tbcategoria.nome as categoria "
					+ "FROM interno.tbgrupos " + "LEFT JOIN interno.tbcategoria "
					+ "ON interno.tbgrupos.fk_Categoria = interno.tbcategoria.id "
					+ "ORDER BY interno.tbgrupos.id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Grupos g = new Grupos();

				g.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				g.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				g.setLink(ComunicaxcaoUtil.getRs().getString("link"));
				g.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));

				Grupos.add(g);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return Grupos;

	}

	public static List<Grupos> gerarTabelaPorNome(String nome) {
		List<Grupos> Grupos = new ArrayList<Grupos>();
		try {

			ComunicaxcaoUtil.setSql("SELECT interno.tbgrupos.id as id," + " interno.tbgrupos.nome as nome,"
					+ " interno.tbgrupos.link as link," + " interno.tbcategoria.nome as categoria "
					+ "FROM interno.tbgrupos " + "LEFT JOIN interno.tbcategoria "
					+ "ON interno.tbgrupos.fk_Categoria = interno.tbcategoria.id "
					+ "WHERE interno.tbgrupos.nome LIKE ?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome + "%");
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Grupos g = new Grupos();

				g.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				g.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				g.setLink(ComunicaxcaoUtil.getRs().getString("link"));
				g.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));

				Grupos.add(g);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Grupos;

	}

	public static List<Grupos> gerarTabelaPorSQL(String sql) {
		List<Grupos> Grupos = new ArrayList<Grupos>();
		try {

			ComunicaxcaoUtil.setSql(sql);
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Grupos g = new Grupos();

				g.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				g.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				g.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));

				Grupos.add(g);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return Grupos;

	}

	public static void AtualizarUltimoEnvio(Integer ID) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbgrupos SET ultimoEnvio=? WHERE id=?");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setDate(1, PegarDatasUtil.toSqlDate(PegarDatasUtil.getDate()));
			ComunicaxcaoUtil.getPst().setInt(2, ID);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
