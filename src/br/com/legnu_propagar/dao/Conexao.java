package br.com.legnu_propagar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.util.dao.ComunicaxcaoPadraoUtil;
import br.com.legnu_propagar.util.dao.ComunicaxcaoUtil;

public class Conexao {
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static Connection CON;

	private static Connection conexao = null;
	private static PreparedStatement pst = null;

	public static Connection ConexaoPadrao() {
		URL = "jdbc:postgresql://localhost:5432/";
		USER = "postgres";
		PASSWORD = "Legnu.131807";

		try {
			Class.forName("org.postgresql.Driver");
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			return CON;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	public static Connection Conexao() {	
		
		if(uri() == null){
			CriarBanco();
			return null;
		}
		
		URL = "jdbc:postgresql://"+uri()+":5432/dbpropagar";
		USER = "postgres";
		PASSWORD = "Legnu.131807";

		try {			
			Class.forName("org.postgresql.Driver");
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			return CON;
		} catch (Exception e) {
			CriarBanco();
			return null;
		}
	}

	private static void CriarBanco() {
		try {
			conexao = ConexaoPadrao();
			String sqo = "CREATE DATABASE dbpropagar";
			pst = CON.prepareStatement(sqo);
			pst.execute();
			
			pst = CON.prepareStatement("""
					CREATE TABLE IF NOT EXISTS 
						tburi(
							id SERIAL,
							uri VARCHAR(200), 
							PRIMARY KEY (id));
					""");
			pst.execute();
			
			ComunicaxcaoPadraoUtil.setSql("INSERT INTO tburi (uri) VALUES (?);");
			ComunicaxcaoPadraoUtil.prepararConexcao();
			ComunicaxcaoPadraoUtil.getPst().setString(1, "localhost");
			ComunicaxcaoPadraoUtil.executar();
			
		} catch (Exception e) {
		}
	}
	
	private static String uri() {
		try {
			
			ComunicaxcaoPadraoUtil.setSql("SELECT uri FROM tburi where id = 1");
			ComunicaxcaoPadraoUtil.prepararConexcao();
			ComunicaxcaoPadraoUtil.executarQuery();
			

			while (ComunicaxcaoPadraoUtil.getRs().next()) {
				String uri = ComunicaxcaoPadraoUtil.getRs().getString("uri");
				return uri;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
		
	}

}
