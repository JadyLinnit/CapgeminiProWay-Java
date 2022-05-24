package com.aulas.mvc.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	static Connection con = null;
	public static Connection conectar() {		
		//           "jdbc:postgresql://host:port/database"
		
		String url = "jdbc:postgresql://ec2-3-228-235-79.compute-1.amazonaws.com:5432/d5p871141ije39";
		String user = "zfwbwptodjazkc";
		String password = "089bf8dfd66a58ea35ba2e1ec17c277dc1d9b0cc91cf7bee590537fb6984f92f";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConexao() {
		con = null;
	}
}