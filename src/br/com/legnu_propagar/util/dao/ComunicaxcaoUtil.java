package br.com.legnu_propagar.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.Conexao;

public class ComunicaxcaoUtil {

	private static Connection conexao = Conexao.Conexao();
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	private static String sql;

	public static String getSql() {
		return sql;
	}

	public static void setSql(String sql) {
		ComunicaxcaoUtil.sql = sql;
	}

	public static Connection getConexao() {
		return conexao;
	}

	public static void setConexao(Connection conexao) {
		ComunicaxcaoUtil.conexao = conexao;
	}

	public static PreparedStatement getPst() {
		return pst;
	}

	public static void setPst(PreparedStatement pst) {
		ComunicaxcaoUtil.pst = pst;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		ComunicaxcaoUtil.rs = rs;
	}

	public static void prepararConexcao() {
		try {
			ComunicaxcaoUtil.setPst(ComunicaxcaoUtil.getConexao().prepareStatement(ComunicaxcaoUtil.getSql()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "prepararConexcao UtilComunicacão Error:" + e);
		}
	}

	public static void executar() {
		try {
			ComunicaxcaoUtil.getPst().execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "executar UtilComunicacão Error:" + e);
		}
	}

	public static void executarQuery() {
		try {
			setRs(ComunicaxcaoUtil.getPst().executeQuery());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "executarQuery UtilComunicacão Error:" + e);
		}
	}

	public static void atualizarQuery() {
		try {
			ComunicaxcaoUtil.getPst().executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "atualizarQuery UtilComunicacão Error:" + e);
		}
	}

}
