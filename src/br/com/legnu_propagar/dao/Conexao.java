package br.com.legnu_propagar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Conexao {
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static Connection CON;

	private static Connection conexao = null;
	private static PreparedStatement pst = null;

	private static Connection ConexaoPadrao() {
		URL = "jdbc:postgresql://localhost:5432/";
		USER = "postgres";
		PASSWORD = "Legnu.131807";

		try {
			Class.forName("org.postgresql.Driver");
			CON = DriverManager.getConnection(URL, USER, PASSWORD);
			return CON;
		} catch (Exception e) {
			return null;
		}
	}

	public static Connection Conexao() {
		URL = "jdbc:postgresql://localhost:5432/dbpropagar";
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
		} catch (Exception e) {
		}
	}

}
