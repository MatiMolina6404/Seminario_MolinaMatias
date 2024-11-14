package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ahorros.Ahorros;

public class AhorrosBD {

	// Metodo para insertar el ahorro creado en la base de datos
	public void insertarAhorro(Ahorros ahorros, Connection conexion, int idUsuario) throws SQLException {
		String AHORROSBD = "INSERT INTO ahorros(idUsuarioAho, concepto, fecha, cantidad) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ahorrosBD = conexion.prepareStatement(AHORROSBD)) {
			ahorrosBD.setInt(1, idUsuario);
			ahorrosBD.setString(2, ahorros.Concepto());
			ahorrosBD.setString(3, ahorros.FechaHora());
			ahorrosBD.setInt(4, ahorros.CantReservada());

			ahorrosBD.executeUpdate();
			conexion.commit();
			System.out.println();
			System.out.println("Ahorro registrado correctamente");
			System.out.println();
		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al registrar ahorro: " + e.getMessage());
			throw e;
		}
	}

	/*
	 * Metodo para insertar los movimientos realizados sobre el ahorro en la base de
	 * datos
	 */
	public void insertarMovimientoAhorro(Ahorros ahorros, Connection conexion, int idUsuarioMov, int idAhorro)
			throws SQLException {
		String AHORROSBD = "INSERT INTO movimientosahorros(idUsuarioMov, idAhorro, tipoMovimiento, cantidad, fecha) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement ahorrosBD = conexion.prepareStatement(AHORROSBD,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ahorrosBD.setInt(1, idUsuarioMov);
			ahorrosBD.setInt(2, idAhorro);
			ahorrosBD.setString(3, ahorros.TipoMovimiento());
			ahorrosBD.setInt(4, ahorros.Cantidad());
			ahorrosBD.setString(5, ahorros.FechaHora());

			ahorrosBD.executeUpdate();

			// Obtener el idMovimiento generado
			try (ResultSet generatedKeys = ahorrosBD.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int idMovimiento = generatedKeys.getInt(1);
					ahorros.setidMovimiento(idMovimiento);
				}
			}

			conexion.commit();
			System.out.println();
			System.out.println("Movimiento registrado correctamente");
		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al registrar el movimiento: " + e.getMessage());
			throw e;
		}
	}

	/*
	 * Metodo para obtener datos del ahorro registrados en la base de datos. Permite
	 * mostrar los datos recuperados en la consola
	 */
	public Ahorros obtenerAhorro(int idUsuario, Connection conexion) throws SQLException {
		String query = "SELECT idAhorro, concepto, cantidad FROM ahorros WHERE idUsuarioAho = ?";
		Ahorros ahorro = null;

		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setInt(1, idUsuario);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ahorro = new Ahorros();
				ahorro.setIdAhorro(rs.getInt("idAhorro"));
				ahorro.setConcepto(rs.getString("concepto"));
				ahorro.setCantReservada(rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener ahorro: " + e.getMessage());
			throw e;
		}

		return ahorro;
	}

	/*
	 * Metodo para actualizar la cantidad ahorrada registrada en la base de datos
	 * tras un movimiento
	 */
	public void actualizarCantidadAhorro(int idUsuario, int nuevaCantidad, Connection conexion) throws SQLException {
		String updateQuery = "UPDATE ahorros SET cantidad = ? WHERE idUsuarioAho = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(updateQuery)) {
			stmt.setInt(1, nuevaCantidad);
			stmt.setInt(2, idUsuario);
			stmt.executeUpdate();
			conexion.commit();
		} catch (SQLException e) {
			conexion.rollback();
			System.out.println("Error al actualizar la cantidad de ahorro: " + e.getMessage());
			throw e;
		}
	}
}
