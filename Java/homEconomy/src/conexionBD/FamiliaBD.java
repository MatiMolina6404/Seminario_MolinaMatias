package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import usuario.Familia;
import usuario.Usuario;

public class FamiliaBD {

	// Metodo para insertar los familiares registrados en la base de datos
	public void insertarFamiliar(Connection conexion, Familia familia, Usuario usuario, int idUsuario)
			throws SQLException {
		String FAMILIABD = "INSERT INTO familia(idUsuario, Nombre, Relacion) VALUES (?, ?, ?)";

		try (PreparedStatement familiabd = conexion.prepareStatement(FAMILIABD)) {
			familiabd.setInt(1, idUsuario);
			familiabd.setString(2, familia.nombreFam());
			familiabd.setString(3, familia.relacionFam());
			familiabd.executeUpdate();
			conexion.commit();

			System.out.println();
			System.out.println("Familiar registrado correctamente");
			System.out.println();

		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al guardar familiar: " + e.getMessage());
			throw e;
		}
	}

	// Metodo para mostrar los familiares registrados en la base de datos
	public void mostrarFamilia(Connection conexion, Usuario usuario) throws SQLException {
		String query = "SELECT idFamilia, Nombre, Relacion FROM familia WHERE idUsuario = ?";

		try (PreparedStatement state = conexion.prepareStatement(query)) {
			// Obtener el idUsuario de la sesion actual
			int idUsuario = usuario.getIdUsuario();

			state.setInt(1, idUsuario);

			ResultSet resultSet = state.executeQuery();

			// Verificar si existen registros
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No hay familiares registrados.");
				return;
			}

			System.out.println("Familiares registrados:");
			while (resultSet.next()) {
				int idFamilia = resultSet.getInt("idFamilia");
				String nombre = resultSet.getString("Nombre");
				String relacion = resultSet.getString("Relacion");

				System.out.println("ID: " + idFamilia + ", Nombre: " + nombre + ", Relacion: " + relacion);
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar los datos de la familia: " + e.getMessage());
			throw e;
		}
	}

	// Metodo para obtener el nombre del familiar
	public String obtenerNombreFam(int idFamiliar, Connection conexion) throws SQLException {
		String query = "SELECT nombre FROM familia WHERE idFamilia = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setInt(1, idFamiliar); // Establece el ID del familiar en la consulta
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("Nombre");
			} else {
				return "Familiar no encontrado";
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el nombre del familiar: " + e.getMessage());
			throw e;
		}
	}

}
