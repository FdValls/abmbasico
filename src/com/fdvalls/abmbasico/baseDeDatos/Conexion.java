package com.fdvalls.abmbasico.baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Conexion instance;
	private final Connection connection;

	private Conexion() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/cobra_kai?useTimezone=true&serverTimezone=UTC";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = (Connection) DriverManager.getConnection(url, "root", "35323873");
	}

	// singleton: googlealo /////// Cuando funque todo esto googleo los detalles
	public static Connection getInstance() {
		if (instance == null) {
			try {
				instance = new Conexion();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance.connection;
	}

}
