package br.com.legnu_propagar.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.util.busca.PegarDatasUtil;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoClientes {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbClientes();
		}
	}

	private static void criarTbClientes() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbclientes(id SERIAL," + "nome TEXT,"
					+ "genero VARCHAR(14)," + "fk_Categoria INT," + "ultimoEnvio DATE," + "PRIMARY KEY (id),"
					+ "FOREIGN KEY (fk_Categoria) REFERENCES interno.tbcategoria (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbClientes: " + e);
		}
	}

	public static void inserirCliente(String nome, Integer fk_Categoria) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbclientes (nome, fk_Categoria, ultimoEnvio) VALUES (?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setInt(2, fk_Categoria);
			ComunicaxcaoUtil.getPst().setDate(3, PegarDatasUtil.dataPadrao());
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbClientes: " + e);
		}
	}

	public static void inserirCliente(String nome) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbclientes (nome, ultimoEnvio) VALUES (?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setDate(2, PegarDatasUtil.dataPadrao());
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbClientes: " + e);
		}
	}

	public static void editarCliente(Integer id, String nome, Integer fk_Categoria) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbclientes SET nome=?,fk_Categoria=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setInt(2, fk_Categoria);
			ComunicaxcaoUtil.getPst().setInt(3, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbClientes: " + e);
		}
	}

	public static void editarCliente(Integer id, String nome) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbclientes SET nome=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setInt(2, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbClientes: " + e);
		}
	}

	public static void deletarCliente(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbtelefone " + "WHERE idCliente=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();

			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbclientes WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbClientes: " + e);
		}
	}

	public static List<Clientes> gerarTabela() {
		List<Clientes> Clientes = new ArrayList<Clientes>();
		try {

			ComunicaxcaoUtil.setSql("SELECT interno.tbclientes.id as id, "
					  + "interno.tbclientes.nome as nome, "
					  + "interno.tbclientes.ultimoenvio as ultimoenvio, "
					  + "interno.tbclientes.fk_categoria as fk_categoria, "
					  + "interno.tbcategoria.nome as categoria "
					  + "FROM interno.tbclientes "
					  + "LEFT JOIN interno.tbcategoria "
					  + "ON interno.tbclientes.fk_Categoria = interno.tbcategoria.id "
					  + "ORDER BY interno.tbclientes.id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			int i = 0;
			while (ComunicaxcaoUtil.getRs().next()) {
				Clientes c = new Clientes();

				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				c.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));
				c.setUltimoEnvio(ComunicaxcaoUtil.getRs().getDate("ultimoenvio"));
				c.setIdCategoria(ComunicaxcaoUtil.getRs().getInt("fk_categoria"));
				c.setPosicao(i);
				
				Clientes.add(c);
				i++;
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return Clientes;

	}

	public static List<Clientes> gerarTabelaPorNome(String nome) {
		List<Clientes> Clientes = new ArrayList<Clientes>();
		try {

			ComunicaxcaoUtil.setSql("SELECT interno.tbclientes.id as id, "
								  + "interno.tbclientes.nome as nome, "
								  + "interno.tbclientes.ultimoenvio as ultimoenvio, "
								  + "interno.tbcategoria.nome as categoria "
								  + "FROM interno.tbclientes "
								  + "LEFT JOIN interno.tbcategoria "
								  + "ON interno.tbclientes.fk_Categoria = interno.tbcategoria.id "
								  + "WHERE interno.tbclientes.nome LIKE ?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome + "%");
			ComunicaxcaoUtil.executarQuery();

			int i = 0;
			while (ComunicaxcaoUtil.getRs().next()) {
				Clientes c = new Clientes();

				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				c.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));
				c.setUltimoEnvio(ComunicaxcaoUtil.getRs().getDate("ultimoenvio"));
				c.setPosicao(i);
				
				Clientes.add(c);
				i++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Clientes;

	}

	public static Clientes pegarUltimo() {
		Clientes c = new Clientes();
		try {

			ComunicaxcaoUtil.setSql(
					"SELECT interno.tbclientes.id as id, interno.tbclientes.nome as nome, interno.tbcategoria.nome as categoria "
							+ "FROM interno.tbclientes " + "LEFT JOIN interno.tbcategoria "
							+ "ON interno.tbclientes.fk_Categoria = interno.tbcategoria.id "
							+ "ORDER BY interno.tbclientes.id Desc");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {

				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				c.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));

				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return c;

	}

	public static void AtualizarUltimoEnvio(Integer ID) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbclientes SET ultimoEnvio=? WHERE id=?");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setDate(1, PegarDatasUtil.toSqlDate(PegarDatasUtil.getDate()));
			ComunicaxcaoUtil.getPst().setInt(2, ID);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/***
	 * Serviços de Disparo
	 *******************************************************************************************************************************************************************************************************************/

	public static List<Clientes> gerarTabelaPorSQL(String sql) {
		List<Clientes> Clientes = new ArrayList<Clientes>();
		try {

			ComunicaxcaoUtil.setSql(sql);
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Clientes c = new Clientes();

				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				c.setCategoria(ComunicaxcaoUtil.getRs().getString("categoria"));

				Clientes.add(c);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return Clientes;

	}
}
