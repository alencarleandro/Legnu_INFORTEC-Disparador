package br.com.legnu_propagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Configuracao;
import br.com.legnu_propagar.model.Profiles;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class DaoConfiguracao {
	public static void criarSchemaInterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS interno");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaInterno: " + e);
		} finally {
			criarTbConfiguracao();
		}
	}

	private static void criarTbConfiguracao() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS interno.tbconfiguracao(id INT," + "nome TEXT,"
					+ "executavelFirefox TEXT," + "executavelGecko TEXT," + "conf1 TEXT," + "conf2 TEXT,"
					+ "conf3 TEXT," + "conf4 TEXT," + "conf5 TEXT," + "conf6 TEXT," + "conf7 TEXT," + "conf8 TEXT,"
					+ "conf9 TEXT," + "conf10 TEXT," + "conf11 TEXT," + "conf12 TEXT," + "conf13 TEXT," + "conf14 TEXT,"
					+ "conf15 TEXT," + "conf16 TEXT," + "conf17 TEXT," + "conf18 TEXT," + "conf19 TEXT,"
					+ "conf20 TEXT," + "conf21 TEXT," + "conf22 TEXT," + "conf23 TEXT," + "conf24 TEXT,"
					+ "conf25 TEXT," + "conf26 TEXT," + "conf27 TEXT," + "conf28 TEXT," + "conf29 TEXT,"
					+ "conf30 TEXT," + "PRIMARY KEY (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbConfiguração: " + e);
		} finally {
			instanciarConfiguracoes();
		}
	}

	public static void instanciarConfiguracoes() {
		try {
			ComunicaxcaoUtil.setSql("SELECT id FROM interno.tbconfiguracao WHERE id = 1");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next() == false) {
				inserirConfiguracao("Sistema", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "","1");
			}

			ComunicaxcaoUtil.setSql("SELECT id FROM interno.tbconfiguracao WHERE id = 2");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next() == false) {
				inserirConfiguracao("Whatsapp", "", "", "60000", "20000", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "","2");
			}

			ComunicaxcaoUtil.setSql("SELECT id FROM interno.tbconfiguracao WHERE id = 3");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next() == false) {
				inserirConfiguracao("Facebook", "", "", "60000", "20000", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "","3");
			}
			
			ComunicaxcaoUtil.setSql("SELECT id FROM interno.tbconfiguracao WHERE id = 4");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next() == false) {
				inserirConfiguracao("Instagram", "", "", "60000", "20000", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "","4");
			}
			
			ComunicaxcaoUtil.setSql("SELECT id FROM interno.tbconfiguracao WHERE id = 5");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next() == false) {
				inserirConfiguracao("Gmail", "", "", "60000", "20000", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
						"", "", "", "", "", "", "", "", "", "", "", "", "", "","5");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void inserirConfiguracao(String nome, String executavelFirefox, String executavelGecko, String conf1,
			String conf2, String conf3, String conf4, String conf5, String conf6, String conf7, String conf8,
			String conf9, String conf10, String conf11, String conf12, String conf13, String conf14, String conf15,
			String conf16, String conf17, String conf18, String conf19, String conf20, String conf21, String conf22,
			String conf23, String conf24, String conf25, String conf26, String conf27, String conf28, String conf29,
			String conf30, String id) {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO interno.tbconfiguracao (nome,executavelFirefox,executavelGecko,"
					+ "conf1,conf2,conf3,conf4,conf5,conf6,conf7,conf8,conf9,conf10,"
					+ "conf11,conf12,conf13,conf14,conf15,conf16,conf17,conf18,conf19,conf20,"
					+ "conf21,conf22,conf23,conf24,conf25,conf26,conf27,conf28,conf29,conf30,id) VALUES (?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,?);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, nome);
			ComunicaxcaoUtil.getPst().setString(2, executavelFirefox);
			ComunicaxcaoUtil.getPst().setString(3, executavelGecko);
			ComunicaxcaoUtil.getPst().setString(4, conf1);
			ComunicaxcaoUtil.getPst().setString(5, conf2);
			ComunicaxcaoUtil.getPst().setString(6, conf3);
			ComunicaxcaoUtil.getPst().setString(7, conf4);
			ComunicaxcaoUtil.getPst().setString(8, conf5);
			ComunicaxcaoUtil.getPst().setString(9, conf6);
			ComunicaxcaoUtil.getPst().setString(10, conf7);
			ComunicaxcaoUtil.getPst().setString(11, conf8);
			ComunicaxcaoUtil.getPst().setString(12, conf9);
			ComunicaxcaoUtil.getPst().setString(13, conf10);
			ComunicaxcaoUtil.getPst().setString(14, conf11);
			ComunicaxcaoUtil.getPst().setString(15, conf12);
			ComunicaxcaoUtil.getPst().setString(16, conf13);
			ComunicaxcaoUtil.getPst().setString(17, conf14);
			ComunicaxcaoUtil.getPst().setString(18, conf15);
			ComunicaxcaoUtil.getPst().setString(19, conf16);
			ComunicaxcaoUtil.getPst().setString(20, conf17);
			ComunicaxcaoUtil.getPst().setString(21, conf18);
			ComunicaxcaoUtil.getPst().setString(22, conf19);
			ComunicaxcaoUtil.getPst().setString(23, conf20);
			ComunicaxcaoUtil.getPst().setString(24, conf21);
			ComunicaxcaoUtil.getPst().setString(25, conf22);
			ComunicaxcaoUtil.getPst().setString(26, conf23);
			ComunicaxcaoUtil.getPst().setString(27, conf24);
			ComunicaxcaoUtil.getPst().setString(28, conf25);
			ComunicaxcaoUtil.getPst().setString(29, conf26);
			ComunicaxcaoUtil.getPst().setString(30, conf27);
			ComunicaxcaoUtil.getPst().setString(31, conf28);
			ComunicaxcaoUtil.getPst().setString(32, conf29);
			ComunicaxcaoUtil.getPst().setString(33, conf30);
			ComunicaxcaoUtil.getPst().setInt(34, Integer.parseInt(id));
			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbConfiguração: " + e);
		}
	}

	public static void editarConfiguracao(String executavelFirefox, String executavelGecko, String conf1, String conf2,
			String conf3, String conf4, String conf5, String conf6, String conf7, String conf8, String conf9,
			String conf10, String conf11, String conf12, String conf13, String conf14, String conf15, String conf16,
			String conf17, String conf18, String conf19, String conf20, String conf21, String conf22, String conf23,
			String conf24, String conf25, String conf26, String conf27, String conf28, String conf29, String conf30,
			Integer id) {
		try {
			ComunicaxcaoUtil.setSql("UPDATE interno.tbconfiguracao SET executavelFirefox=?,executavelGecko=?,"
					+ "conf1=?,conf2=?,conf3=?,conf4=?,conf5=?,conf6=?,conf7=?,conf8=?,conf9=?,conf10=?,"
					+ "conf11=?,conf12=?,conf13=?,conf14=?,conf15=?,conf16=?,conf17=?,conf18=?,conf19=?,conf20=?,"
					+ "conf21=?,conf22=?,conf23=?,conf24=?,conf25=?,conf26=?,conf27=?,conf28=?,conf29=?,conf30=? WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setString(1, executavelFirefox);
			ComunicaxcaoUtil.getPst().setString(2, executavelGecko);
			ComunicaxcaoUtil.getPst().setString(3, conf1);
			ComunicaxcaoUtil.getPst().setString(4, conf2);
			ComunicaxcaoUtil.getPst().setString(5, conf3);
			ComunicaxcaoUtil.getPst().setString(6, conf4);
			ComunicaxcaoUtil.getPst().setString(7, conf5);
			ComunicaxcaoUtil.getPst().setString(8, conf6);
			ComunicaxcaoUtil.getPst().setString(9, conf7);
			ComunicaxcaoUtil.getPst().setString(10, conf8);
			ComunicaxcaoUtil.getPst().setString(11, conf9);
			ComunicaxcaoUtil.getPst().setString(12, conf10);
			ComunicaxcaoUtil.getPst().setString(13, conf11);
			ComunicaxcaoUtil.getPst().setString(14, conf12);
			ComunicaxcaoUtil.getPst().setString(15, conf13);
			ComunicaxcaoUtil.getPst().setString(16, conf14);
			ComunicaxcaoUtil.getPst().setString(17, conf15);
			ComunicaxcaoUtil.getPst().setString(18, conf16);
			ComunicaxcaoUtil.getPst().setString(19, conf17);
			ComunicaxcaoUtil.getPst().setString(20, conf18);
			ComunicaxcaoUtil.getPst().setString(21, conf19);
			ComunicaxcaoUtil.getPst().setString(22, conf20);
			ComunicaxcaoUtil.getPst().setString(23, conf21);
			ComunicaxcaoUtil.getPst().setString(24, conf22);
			ComunicaxcaoUtil.getPst().setString(25, conf23);
			ComunicaxcaoUtil.getPst().setString(26, conf24);
			ComunicaxcaoUtil.getPst().setString(27, conf25);
			ComunicaxcaoUtil.getPst().setString(28, conf26);
			ComunicaxcaoUtil.getPst().setString(29, conf27);
			ComunicaxcaoUtil.getPst().setString(30, conf28);
			ComunicaxcaoUtil.getPst().setString(31, conf29);
			ComunicaxcaoUtil.getPst().setString(32, conf30);
			ComunicaxcaoUtil.getPst().setInt(33, id);

			ComunicaxcaoUtil.executar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbConfiguração: " + e);
		}
	}

	public static void deletarProfile(Integer id) {
		try {
			ComunicaxcaoUtil.setSql("DELETE FROM interno.tbconfiguracao WHERE id=?;");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar tbConfiguração: " + e);
		}
	}

	public static List<Configuracao> gerarTabela() {
		List<Configuracao> Configuracao = new ArrayList<>();
		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbconfiguracao ORDER BY id ASC");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				Configuracao c = new Configuracao();

				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));

				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				c.setExecutavelFirefox(ComunicaxcaoUtil.getRs().getString("executavelfirefox"));
				c.setExecutavelGecko(ComunicaxcaoUtil.getRs().getString("executavelgecko"));

				c.setConf1(ComunicaxcaoUtil.getRs().getString("conf1"));
				c.setConf2(ComunicaxcaoUtil.getRs().getString("conf2"));
				c.setConf3(ComunicaxcaoUtil.getRs().getString("conf3"));
				c.setConf4(ComunicaxcaoUtil.getRs().getString("conf4"));
				c.setConf5(ComunicaxcaoUtil.getRs().getString("conf5"));
				c.setConf6(ComunicaxcaoUtil.getRs().getString("conf6"));
				c.setConf7(ComunicaxcaoUtil.getRs().getString("conf7"));
				c.setConf8(ComunicaxcaoUtil.getRs().getString("conf8"));
				c.setConf9(ComunicaxcaoUtil.getRs().getString("conf9"));
				c.setConf10(ComunicaxcaoUtil.getRs().getString("conf10"));
				c.setConf11(ComunicaxcaoUtil.getRs().getString("conf11"));
				c.setConf12(ComunicaxcaoUtil.getRs().getString("conf12"));
				c.setConf13(ComunicaxcaoUtil.getRs().getString("conf13"));
				c.setConf14(ComunicaxcaoUtil.getRs().getString("conf14"));
				c.setConf15(ComunicaxcaoUtil.getRs().getString("conf15"));
				c.setConf16(ComunicaxcaoUtil.getRs().getString("conf16"));
				c.setConf17(ComunicaxcaoUtil.getRs().getString("conf17"));
				c.setConf18(ComunicaxcaoUtil.getRs().getString("conf18"));
				c.setConf19(ComunicaxcaoUtil.getRs().getString("conf19"));
				c.setConf20(ComunicaxcaoUtil.getRs().getString("conf20"));
				c.setConf21(ComunicaxcaoUtil.getRs().getString("conf21"));
				c.setConf22(ComunicaxcaoUtil.getRs().getString("conf22"));
				c.setConf23(ComunicaxcaoUtil.getRs().getString("conf23"));
				c.setConf24(ComunicaxcaoUtil.getRs().getString("conf24"));
				c.setConf25(ComunicaxcaoUtil.getRs().getString("conf25"));
				c.setConf26(ComunicaxcaoUtil.getRs().getString("conf26"));
				c.setConf27(ComunicaxcaoUtil.getRs().getString("conf27"));
				c.setConf28(ComunicaxcaoUtil.getRs().getString("conf28"));
				c.setConf29(ComunicaxcaoUtil.getRs().getString("conf29"));
				c.setConf30(ComunicaxcaoUtil.getRs().getString("conf30"));

				Configuracao.add(c);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Configuracao;

	}

	public static Configuracao pegarConfiguracao(Integer id) {
		Configuracao c = new Configuracao();

		try {

			ComunicaxcaoUtil.setSql("SELECT * FROM interno.tbconfiguracao WHERE id = ?");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.getPst().setInt(1, id);
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {

				c.setId(ComunicaxcaoUtil.getRs().getInt("id"));

				c.setNome(ComunicaxcaoUtil.getRs().getString("nome"));
				c.setExecutavelFirefox(ComunicaxcaoUtil.getRs().getString("executavelfirefox"));
				c.setExecutavelGecko(ComunicaxcaoUtil.getRs().getString("executavelgecko"));

				c.setConf1(ComunicaxcaoUtil.getRs().getString("conf1"));
				c.setConf2(ComunicaxcaoUtil.getRs().getString("conf2"));
				c.setConf3(ComunicaxcaoUtil.getRs().getString("conf3"));
				c.setConf4(ComunicaxcaoUtil.getRs().getString("conf4"));
				c.setConf5(ComunicaxcaoUtil.getRs().getString("conf5"));
				c.setConf6(ComunicaxcaoUtil.getRs().getString("conf6"));
				c.setConf7(ComunicaxcaoUtil.getRs().getString("conf7"));
				c.setConf8(ComunicaxcaoUtil.getRs().getString("conf8"));
				c.setConf9(ComunicaxcaoUtil.getRs().getString("conf9"));
				c.setConf10(ComunicaxcaoUtil.getRs().getString("conf10"));
				c.setConf11(ComunicaxcaoUtil.getRs().getString("conf11"));
				c.setConf12(ComunicaxcaoUtil.getRs().getString("conf12"));
				c.setConf13(ComunicaxcaoUtil.getRs().getString("conf13"));
				c.setConf14(ComunicaxcaoUtil.getRs().getString("conf14"));
				c.setConf15(ComunicaxcaoUtil.getRs().getString("conf15"));
				c.setConf16(ComunicaxcaoUtil.getRs().getString("conf16"));
				c.setConf17(ComunicaxcaoUtil.getRs().getString("conf17"));
				c.setConf18(ComunicaxcaoUtil.getRs().getString("conf18"));
				c.setConf19(ComunicaxcaoUtil.getRs().getString("conf19"));
				c.setConf20(ComunicaxcaoUtil.getRs().getString("conf20"));
				c.setConf21(ComunicaxcaoUtil.getRs().getString("conf21"));
				c.setConf22(ComunicaxcaoUtil.getRs().getString("conf22"));
				c.setConf23(ComunicaxcaoUtil.getRs().getString("conf23"));
				c.setConf24(ComunicaxcaoUtil.getRs().getString("conf24"));
				c.setConf25(ComunicaxcaoUtil.getRs().getString("conf25"));
				c.setConf26(ComunicaxcaoUtil.getRs().getString("conf26"));
				c.setConf27(ComunicaxcaoUtil.getRs().getString("conf27"));
				c.setConf28(ComunicaxcaoUtil.getRs().getString("conf28"));
				c.setConf29(ComunicaxcaoUtil.getRs().getString("conf29"));
				c.setConf30(ComunicaxcaoUtil.getRs().getString("conf30"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return c;

	}
}
