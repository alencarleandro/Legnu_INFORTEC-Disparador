package br.com.legnu_propagar.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.Conexao;

public class ComunicaxcaoPadraoUtil {

	private static Connection conexao = Conexao.ConexaoPadrao();
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	private static String sql;

	public static String getSql() {
		return sql;
	}

	public static void setSql(String sql) {
		ComunicaxcaoPadraoUtil.sql = sql;
	}

	public static Connection getConexao() {
		return conexao;
	}

	public static void setConexao(Connection conexao) {
		ComunicaxcaoPadraoUtil.conexao = conexao;
	}

	public static PreparedStatement getPst() {
		return pst;
	}

	public static void setPst(PreparedStatement pst) {
		ComunicaxcaoPadraoUtil.pst = pst;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		ComunicaxcaoPadraoUtil.rs = rs;
	}

	public static void prepararConexcao() {
		try {
			ComunicaxcaoPadraoUtil.setPst(ComunicaxcaoPadraoUtil.getConexao().prepareStatement(ComunicaxcaoPadraoUtil.getSql()));
		} catch (Exception e) {
		}
	}

	public static void executar() {
		try {
			ComunicaxcaoPadraoUtil.getPst().execute();
		} catch (Exception e) {
		}
	}

	public static void executarQuery() {
		try {
			setRs(ComunicaxcaoPadraoUtil.getPst().executeQuery());
		} catch (Exception e) {
		}
	}

	public static void atualizarQuery() {
		try {
			ComunicaxcaoPadraoUtil.getPst().executeUpdate();
		} catch (Exception e) {
		}
	}

}
