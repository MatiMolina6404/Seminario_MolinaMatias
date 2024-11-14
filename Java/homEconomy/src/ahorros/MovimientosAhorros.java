package ahorros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usuario.Usuario;

public class MovimientosAhorros {

	// Metodo para mostrar los movimientos de ahorro registrados en la base de datos
	public void mostrarMovimientosAhorros(Connection conexion, Usuario usuario) throws SQLException {
		String query = "SELECT idMovimiento, tipoMovimiento, cantidad, fecha FROM movimientosahorros WHERE idUsuarioMov = ?";

		try (PreparedStatement state = conexion.prepareStatement(query)) {
			// Obtener el idUsuario de la sesión actual
			int idUsuario = usuario.getIdUsuario();

			state.setInt(1, idUsuario);

			ResultSet resultSet = state.executeQuery();

			// Verificar si existen registros
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No hay ahorros registrados.");
				return;
			}

			System.out.println("Movimientos de ahorro registrados:");
			while (resultSet.next()) {
				int idMovimiento = resultSet.getInt("idMovimiento");
				String tipoMovimiento = resultSet.getString("tipoMovimiento");
				String cantidad = resultSet.getString("cantidad");
				String fecha = resultSet.getString("fecha");

				System.out.println(
						"ID: " + idMovimiento + " - " + fecha + " - Tipo: " + tipoMovimiento + " -> $" + cantidad);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar los ahorros: " + e.getMessage());
			throw e;
		}
	}
}
