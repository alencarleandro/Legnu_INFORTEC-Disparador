package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.util.busca.PegarDatasUtil;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoMensagens {

	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbMensagens();
		}
	}

	private static void criarTbMensagens() {
		try {
			ComunicaxcaoUtil
					.setSql("CREATE TABLE IF NOT EXISTS interno.tbmensagem(id SERIAL," + "titulo TEXT," + "midia TEXT,"
							+ "arquivo TEXT," + "conteudo TEXT," + "conteudoArquivo TEXT," + "tituloEnquete TEXT,"
							+ "conteudoEnquete_1 TEXT," + "conteudoEnquete_2 TEXT," + "conteudoEnquete_3 TEXT,"
							+ "conteudoEnquete_4 TEXT," + "conteudoEnquete_5 TEXT," + "conteudoEnquete_6 TEXT,"
							+ "conteudoEnquete_7 TEXT," + "conteudoEnquete_8 TEXT," + "conteudoEnquete_9 TEXT,"
							+ "conteudoEnquete_10 TEXT," + "variasRespostas BOOLEAN," + "PRIMARY KEY (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbMensagens: " + e);
		}
	}

	public static void inserirMensagen(String titulo, String midia, String arquivo, String conteudo,
			String conteudoArquivo, String tituloEnquete, String conteudoEnquete_1, String conteudoEnquete_2,
			String conteudoEnquete_3, String conteudoEnquete_4, String conteudoEnquete_5, String conteudoEnquete_6,
			String conteudoEnquete_7, String conteudoEnquete_8, String conteudoEnquete_9, String conteudoEnquete_10,
			boolean variasRespostas) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbmensagem (titulo,midia,arquivo,conteudo,conteudoArquivo,"
					+ "tituloEnquete,"
					+ "conteudoEnquete_1,conteudoEnquete_2,conteudoEnquete_3,conteudoEnquete_4,conteudoEnquete_5,"
					+ "conteudoEnquete_6,conteudoEnquete_7,conteudoEnquete_8,conteudoEnquete_9,conteudoEnquete_10,variasRespostas) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, titulo);
			ComunicaxcaoUtil.getPst().setString(2, midia);
			ComunicaxcaoUtil.getPst().setString(3, arquivo);
			ComunicaxcaoUtil.getPst().setString(4, conteudo);
			ComunicaxcaoUtil.getPst().setString(5, conteudoArquivo);

			ComunicaxcaoUtil.getPst().setString(6, tituloEnquete);
			ComunicaxcaoUtil.getPst().setString(7, conteudoEnquete_1);
			ComunicaxcaoUtil.getPst().setString(8, conteudoEnquete_2);
			ComunicaxcaoUtil.getPst().setString(9, conteudoEnquete_3);
			ComunicaxcaoUtil.getPst().setString(10, conteudoEnquete_4);
			ComunicaxcaoUtil.getPst().setString(11, conteudoEnquete_5);
			ComunicaxcaoUtil.getPst().setString(12, conteudoEnquete_6);
			ComunicaxcaoUtil.getPst().setString(13, conteudoEnquete_7);
			ComunicaxcaoUtil.getPst().setString(14, conteudoEnquete_8);
			ComunicaxcaoUtil.getPst().setString(15, conteudoEnquete_9);
			ComunicaxcaoUtil.getPst().setString(16, conteudoEnquete_10);
			ComunicaxcaoUtil.getPst().setBoolean(17, variasRespostas);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbMensagens: " + e);
		}
	}

	public static void editarMensagen(Integer id, String titulo, String midia, String arquivo, String conteudo,
			String conteudoArquivo, String tituloEnquete, String conteudoEnquete_1, String conteudoEnquete_2,
			String conteudoEnquete_3, String conteudoEnquete_4, String conteudoEnquete_5, String conteudoEnquete_6,
			String conteudoEnquete_7, String conteudoEnquete_8, String conteudoEnquete_9, String conteudoEnquete_10,
			boolean variasRespostas) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbmensagem "
					+ "SET titulo=?,midia=?,arquivo=?,conteudo=?,conteudoArquivo=?," 
					+ "tituloEnquete=?,"
					+ "conteudoEnquete_1=?,conteudoEnquete_2=?,conteudoEnquete_3=?,conteudoEnquete_4=?,conteudoEnquete_5=?,"
					+ "conteudoEnquete_6=?,conteudoEnquete_7=?,conteudoEnquete_8=?,conteudoEnquete_9=?,conteudoEnquete_10=?,"
					+ "variasRespostas=? "
					+ "WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, titulo);
			ComunicaxcaoUtil.getPst().setString(2, midia);
			ComunicaxcaoUtil.getPst().setString(3, arquivo);
			ComunicaxcaoUtil.getPst().setString(4, conteudo);
			ComunicaxcaoUtil.getPst().setString(5, conteudoArquivo);

			ComunicaxcaoUtil.getPst().setString(6, tituloEnquete);
			ComunicaxcaoUtil.getPst().setString(7, conteudoEnquete_1);
			ComunicaxcaoUtil.getPst().setString(8, conteudoEnquete_2);
			ComunicaxcaoUtil.getPst().setString(9, conteudoEnquete_3);
			ComunicaxcaoUtil.getPst().setString(10, conteudoEnquete_4);
			ComunicaxcaoUtil.getPst().setString(11, conteudoEnquete_5);
			ComunicaxcaoUtil.getPst().setString(12, conteudoEnquete_6);
			ComunicaxcaoUtil.getPst().setString(13, conteudoEnquete_7);
			ComunicaxcaoUtil.getPst().setString(14, conteudoEnquete_8);
			ComunicaxcaoUtil.getPst().setString(15, conteudoEnquete_9);
			ComunicaxcaoUtil.getPst().setString(16, conteudoEnquete_10);
			ComunicaxcaoUtil.getPst().setBoolean(17, variasRespostas);

			ComunicaxcaoUtil.getPst().setInt(18, id);
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbMensagens: " + e);
		}
	}

	public static void deletarMensagen(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbmensagem WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbMensagens: " + e);
		}
	}

	public static List<Mensagens> gerarTabela() {
		List<Mensagens> Mensagens = new ArrayList<Mensagens>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbmensagem ORDER BY id ASC;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Mensagens m = new Mensagens();

				m.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				m.setTitulo(ComunicaxcaoUtil.getRs().getString("titulo"));
				m.setMidia(ComunicaxcaoUtil.getRs().getString("midia"));
				m.setArquivo(ComunicaxcaoUtil.getRs().getString("arquivo"));
				m.setConteudo(ComunicaxcaoUtil.getRs().getString("conteudo"));
				m.setConteudoArquivo(ComunicaxcaoUtil.getRs().getString("conteudoArquivo"));

				m.setTituloEnquete(ComunicaxcaoUtil.getRs().getString("tituloenquete"));
				m.setConteudoEnquete_1(ComunicaxcaoUtil.getRs().getString("conteudoenquete_1"));
				m.setConteudoEnquete_2(ComunicaxcaoUtil.getRs().getString("conteudoenquete_2"));
				m.setConteudoEnquete_3(ComunicaxcaoUtil.getRs().getString("conteudoenquete_3"));
				m.setConteudoEnquete_4(ComunicaxcaoUtil.getRs().getString("conteudoenquete_4"));
				m.setConteudoEnquete_5(ComunicaxcaoUtil.getRs().getString("conteudoenquete_5"));
				m.setConteudoEnquete_6(ComunicaxcaoUtil.getRs().getString("conteudoenquete_6"));
				m.setConteudoEnquete_7(ComunicaxcaoUtil.getRs().getString("conteudoenquete_7"));
				m.setConteudoEnquete_8(ComunicaxcaoUtil.getRs().getString("conteudoenquete_8"));
				m.setConteudoEnquete_9(ComunicaxcaoUtil.getRs().getString("conteudoenquete_9"));
				m.setConteudoEnquete_10(ComunicaxcaoUtil.getRs().getString("conteudoenquete_10"));
				m.setVariasRespostas(ComunicaxcaoUtil.getRs().getBoolean("variasrespostas"));

				Mensagens.add(m);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return Mensagens;

	}

	public static List<Mensagens> gerarTabelaPorTitulo(String titulo) {
		List<Mensagens> Mensagens = new ArrayList<Mensagens>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbmensagem "
					+ "WHERE interno.tbmensagem.titulo LIKE ? ORDER BY interno.tbmensagem.id ASC;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, titulo + "%");
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Mensagens m = new Mensagens();

				m.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				m.setTitulo(ComunicaxcaoUtil.getRs().getString("titulo"));
				m.setMidia(ComunicaxcaoUtil.getRs().getString("midia"));
				m.setArquivo(ComunicaxcaoUtil.getRs().getString("arquivo"));
				m.setConteudo(ComunicaxcaoUtil.getRs().getString("conteudo"));
				m.setConteudoArquivo(ComunicaxcaoUtil.getRs().getString("conteudoArquivo"));

				m.setTituloEnquete(ComunicaxcaoUtil.getRs().getString("tituloenquete"));
				m.setConteudoEnquete_1(ComunicaxcaoUtil.getRs().getString("conteudoenquete_1"));
				m.setConteudoEnquete_2(ComunicaxcaoUtil.getRs().getString("conteudoenquete_2"));
				m.setConteudoEnquete_3(ComunicaxcaoUtil.getRs().getString("conteudoenquete_3"));
				m.setConteudoEnquete_4(ComunicaxcaoUtil.getRs().getString("conteudoenquete_4"));
				m.setConteudoEnquete_5(ComunicaxcaoUtil.getRs().getString("conteudoenquete_5"));
				m.setConteudoEnquete_6(ComunicaxcaoUtil.getRs().getString("conteudoenquete_6"));
				m.setConteudoEnquete_7(ComunicaxcaoUtil.getRs().getString("conteudoenquete_7"));
				m.setConteudoEnquete_8(ComunicaxcaoUtil.getRs().getString("conteudoenquete_8"));
				m.setConteudoEnquete_9(ComunicaxcaoUtil.getRs().getString("conteudoenquete_9"));
				m.setConteudoEnquete_10(ComunicaxcaoUtil.getRs().getString("conteudoenquete_10"));
				m.setVariasRespostas(ComunicaxcaoUtil.getRs().getBoolean("variasrespostas"));

				Mensagens.add(m);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Mensagens;

	}

	public static Mensagens pegarPorID(Integer id) {
		Mensagens m = new Mensagens();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbmensagem WHERE id = ? ORDER BY id ASC ;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				m.setId(ComunicaxcaoUtil.getRs().getInt("id"));
				m.setTitulo(ComunicaxcaoUtil.getRs().getString("titulo"));
				m.setMidia(ComunicaxcaoUtil.getRs().getString("midia"));
				m.setArquivo(ComunicaxcaoUtil.getRs().getString("arquivo"));
				m.setConteudo(ComunicaxcaoUtil.getRs().getString("conteudo"));
				m.setConteudoArquivo(ComunicaxcaoUtil.getRs().getString("conteudoArquivo"));

				m.setTituloEnquete(ComunicaxcaoUtil.getRs().getString("tituloenquete"));
				m.setConteudoEnquete_1(ComunicaxcaoUtil.getRs().getString("conteudoenquete_1"));
				m.setConteudoEnquete_2(ComunicaxcaoUtil.getRs().getString("conteudoenquete_2"));
				m.setConteudoEnquete_3(ComunicaxcaoUtil.getRs().getString("conteudoenquete_3"));
				m.setConteudoEnquete_4(ComunicaxcaoUtil.getRs().getString("conteudoenquete_4"));
				m.setConteudoEnquete_5(ComunicaxcaoUtil.getRs().getString("conteudoenquete_5"));
				m.setConteudoEnquete_6(ComunicaxcaoUtil.getRs().getString("conteudoenquete_6"));
				m.setConteudoEnquete_7(ComunicaxcaoUtil.getRs().getString("conteudoenquete_7"));
				m.setConteudoEnquete_8(ComunicaxcaoUtil.getRs().getString("conteudoenquete_8"));
				m.setConteudoEnquete_9(ComunicaxcaoUtil.getRs().getString("conteudoenquete_9"));
				m.setConteudoEnquete_10(ComunicaxcaoUtil.getRs().getString("conteudoenquete_10"));
				m.setVariasRespostas(ComunicaxcaoUtil.getRs().getBoolean("variasrespostas"));

				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return m;
	}

}
