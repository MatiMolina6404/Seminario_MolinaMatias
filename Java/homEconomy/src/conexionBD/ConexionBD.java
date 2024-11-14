package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	// Metodo para conectarse a la base de datos de HomEconomy
	public static Connection conectarBD() {
		Connection conexion = null;
		String host = "jdbc:mysql://127.0.0.1:3306/";
		String bd = "homeconomydb";
		String user = "root";
		String pass = "Mati-molina04";

		System.out.println("Conectando...");

		try {
			conexion = DriverManager.getConnection(host + bd, user, pass);
			conexion.setAutoCommit(false);
			System.out.println("Conexion exitosa");
		} catch (SQLException e) {
			System.out.println("Conexion fallida: " + e.getMessage());
			e.printStackTrace();
		}

		return conexion;
	}
}
