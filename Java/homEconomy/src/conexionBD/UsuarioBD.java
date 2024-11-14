package conexionBD;

import usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBD {

	// Metodo para insertar en la base de datos los usuarios creados
	public void insertarUsuario(Connection conexion, Usuario usuario) throws SQLException {
		String USUARIOBD = "INSERT INTO usuario(nombre, contraseña,dineroInicial) VALUES (?, ?, ?)";

		try (PreparedStatement usuariobd = conexion.prepareStatement(USUARIOBD)) {
			usuariobd.setString(1, usuario.mostUsuario());
			usuariobd.setString(2, usuario.mostContra());
			usuariobd.setInt(3, usuario.DineroInicial());
			usuariobd.executeUpdate();
			conexion.commit();

		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al guardar usuario");
			throw e;
		}
	}

	// Metodo que obtiene la informacion necesaria para iniciar sesion
	public Usuario iniciarSesionBD(String nombreUsuario, String contraseña, Connection conexion) throws SQLException {
		String query = "SELECT idUsuario, dineroInicial FROM usuario WHERE nombre = ? AND contraseña = ?";
		try (PreparedStatement state = conexion.prepareStatement(query)) {
			state.setString(1, nombreUsuario);
			state.setString(2, contraseña);

			try (ResultSet set = state.executeQuery()) {
				if (set.next()) {
					int idUsuario = set.getInt("idUsuario");
					int dineroInicial = set.getInt("dineroInicial");
					Usuario usuario = new Usuario(idUsuario, nombreUsuario, contraseña, dineroInicial);
					return usuario;
				} else {
					return null; // Usuario no encontrado
				}
			}
		}
	}

	// Metodo para actualizar el balance del usuario en la base de datos
	public void actualizarBalance(Connection conexion, int idUsuario, int nuevoBalance) throws SQLException {
		String query = "UPDATE usuario SET dineroInicial = ? WHERE idUsuario = ?";

		try (PreparedStatement ps = conexion.prepareStatement(query)) {
			ps.setInt(1, nuevoBalance);
			ps.setInt(2, idUsuario);
			ps.executeUpdate();
			conexion.commit();
		} catch (SQLException e) {
			conexion.rollback();
			System.out.println("Error al actualizar el balance del usuario: " + e.getMessage());
			throw e;
		}
	}

}
