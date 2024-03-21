package br.com.legnu_propagar.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static Connection CON;

	public static Connection Conexao() {
		URL = "jdbc:postgresql://localhost:5432/postgres";
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

}
