package br.com.legnu_propagar.dao;

import br.com.legnu_propagar.util.busca.PegarDatasUtil;
import br.com.legnu_propagar.util.busca.PegarDatasUtil.escolhas;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

import javax.swing.JOptionPane;

public class DaoVale {

	private static int Larissa_Altair = 2014;
	private static int Sebastião_Alex = 1982;
	private static int Lucas_Pereira = 2008;
	private static int José_Clemente = 1954;
	private static int Luzia_Silva = 1980;
	private static int Vera_Margia = 1959;
	private static int Nathalia_Rezende = 1997;
	private static int Leandro_Clemente = 1979;
	private static int Lara_Margia = 2019;
	private static int Francisca_Altair = 1938;
	private static int Luan_Victor = 2010;
	private static int Leandro_Alencar = 2004;

	public static void criarSchemaExterno() {
		try {
			ComunicaxcaoUtil.setSql("CREATE SCHEMA IF NOT EXISTS externo");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar SchemaExterno: " + e);
		} finally {
			criarTbVale();
		}
	}

	private static void criarTbVale() {
		try {
			ComunicaxcaoUtil.setSql("CREATE TABLE IF NOT EXISTS externo.tbvale(id INT," + "vale VARCHAR(80),"
					+ "vencimento DATE," + "PRIMARY KEY (id));");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Criar TbVale: " + e);
		} finally {
			verificarRegistro();
		}
	}

	private static void verificarRegistro() {
		try {
			ComunicaxcaoUtil.setSql("SELECT id FROM externo.tbvale WHERE id = 1");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next() == false) {
				gerarVale();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Verificar TbVale: " + e);
		} finally {
			checarData();
		}
	}

	private static void checarData() {
		try {
			ComunicaxcaoUtil.setSql("SELECT vencimento FROM externo.tbvale WHERE id = 1");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			if (ComunicaxcaoUtil.getRs().next()) {
				if (PegarDatasUtil.eDepois(PegarDatasUtil.getDate(), ComunicaxcaoUtil.getRs().getDate(1))
						|| PegarDatasUtil.getDate().equals(ComunicaxcaoUtil.getRs().getDate(1))) {
					vale();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro Checar Data TbVale: " + e);
		} finally {
			aviso();
		}
	}

	private static void aviso() {
		try {
			ComunicaxcaoUtil.setSql("SELECT vencimento FROM externo.tbvale WHERE id = 1");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();
			if (ComunicaxcaoUtil.getRs().next()) {
				if ((PegarDatasUtil.getMount() + 1) == (PegarDatasUtil.mandarDateToString(escolhas.MONTH,
						ComunicaxcaoUtil.getRs().getString(1)))) {
					switch (PegarDatasUtil.getDay()) {
					case 4:
						JOptionPane.showMessageDialog(null, "Antepenúltimo dia para vencimento da Senha Mensal.");
						return;
					case 5:
						JOptionPane.showMessageDialog(null, "Penúltimo dia para vencimento da Senha Mensal.");
						return;
					case 6:
						JOptionPane.showMessageDialog(null, "Último dia para vencimento da Senha Mensal.");
						return;
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Avisar TbVale: " + e);
		}
	}

	private static void gerarVale() {
		try {
			ComunicaxcaoUtil.setSql("INSERT INTO externo.tbvale (id) VALUES (1);");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executar();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			vale();
		}

	}

	private static String filtro(String x) {

		if ((PegarDatasUtil.getYear() % 2) == 0) {
			x = x.replace("-", "").replace("1", "X").replace("2", "U").replace("3", "I").replace("4", "H")
					.replace("5", "G").replace("6", "?").replace("7", "@").replace("8", "!").replace("9", ">")
					.replace("0", "§");
		} else {
			x = x.replace("-", "").replace("1", "E").replace("2", "D").replace("3", "Y").replace("4", "B")
					.replace("5", "Z").replace("6", ")").replace("7", "º").replace("8", "*").replace("9", "&")
					.replace("0", "<");
		}

		return x;
	}

	private static int index() {
		switch (PegarDatasUtil.getMount()) {
		case 0:
			return (PegarDatasUtil.getYear() - Larissa_Altair);
		case 1:
			return (PegarDatasUtil.getYear() - Sebastião_Alex);
		case 2:
			return (PegarDatasUtil.getYear() - Lucas_Pereira);
		case 3:
			return (PegarDatasUtil.getYear() - José_Clemente);
		case 4:
			return (PegarDatasUtil.getYear() - Luzia_Silva);
		case 5:
			return (PegarDatasUtil.getYear() - Vera_Margia);
		case 6:
			return (PegarDatasUtil.getYear() - Nathalia_Rezende);
		case 7:
			return (PegarDatasUtil.getYear() - Leandro_Clemente);
		case 8:
			return (PegarDatasUtil.getYear() - Lara_Margia);
		case 9:
			return (PegarDatasUtil.getYear() - Francisca_Altair);
		case 10:
			return (PegarDatasUtil.getYear() - Luan_Victor);
		case 11:
			return (PegarDatasUtil.getYear() - Leandro_Alencar);
		default:
			return 0;
		}
	}

	private static String sigla() {
		switch (PegarDatasUtil.getMount()) {
		case 0:
			return "LA";
		case 1:
			return "SA";
		case 2:
			return "LP";
		case 3:
			return "JC";
		case 4:
			return "LS";
		case 5:
			return "VM";
		case 6:
			return "NR";
		case 7:
			return "LC";
		case 8:
			return "LM";
		case 9:
			return "FA";
		case 10:
			return "LV";
		case 11:
			return "LA";
		default:
			return "LA";
		}
	}

	private static void vale() {
		try {
			ComunicaxcaoUtil.setSql("UPDATE externo.tbvale SET vale=?, vencimento=? WHERE id=1");
			ComunicaxcaoUtil.prepararConexcao();
			if (index() < 10) {
				ComunicaxcaoUtil.getPst().setString(1, filtro(String.valueOf(
						"00" + index() + PegarDatasUtil.getYear() + PegarDatasUtil.mesDuasCasas() + "07" + sigla())));
			} else if (index() < 100 && index() >= 10) {
				ComunicaxcaoUtil.getPst().setString(1, filtro(String.valueOf(
						"0" + index() + PegarDatasUtil.getYear() + PegarDatasUtil.mesDuasCasas() + "07" + sigla())));
			} else {
				ComunicaxcaoUtil.getPst().setString(1, filtro(String
						.valueOf(index() + PegarDatasUtil.getYear() + PegarDatasUtil.mesDuasCasas() + "07" + sigla())));
			}
			ComunicaxcaoUtil.getPst().setDate(2, PegarDatasUtil.proximoVale());
			ComunicaxcaoUtil.atualizarQuery();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error vale:" + e);

		}
	}

	public static String PegarVale() {
		try {
			ComunicaxcaoUtil.setSql("SELECT vale FROM externo.tbvale WHERE id = 1");
			ComunicaxcaoUtil.prepararConexcao();
			ComunicaxcaoUtil.executarQuery();

			while (ComunicaxcaoUtil.getRs().next()) {
				return ComunicaxcaoUtil.getRs().getString("vale");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error vale:" + e);

		}
		return "";
	}
}
