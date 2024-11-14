package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import contacto.ContactarEquipoDesarrollo;

public class ContactoBD {

	/*
	 * Metodo para insertar en la base de datos el mensaje del usuario enviado al
	 * equipo de soporte de HomEconomy
	 */
	public void insertarMensaje(Connection conexion, ContactarEquipoDesarrollo cont) throws SQLException {
		String USUARIOBD = "INSERT INTO contactarequipo(idUsuarioM, mensaje, fecha) VALUES (?, ?, ?)";

		try (PreparedStatement contEquipo = conexion.prepareStatement(USUARIOBD)) {
			contEquipo.setInt(1, 1);
			contEquipo.setString(2, cont.mensaje());
			contEquipo.setNString(3, cont.fecha());
			contEquipo.executeUpdate();
			conexion.commit();

		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al guardar mensaje");
			throw e;
		}
	}
}
