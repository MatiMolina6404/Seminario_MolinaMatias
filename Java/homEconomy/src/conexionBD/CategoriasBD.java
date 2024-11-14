package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import registros.Categorias;
import usuario.Usuario;

public class CategoriasBD {

	// Metodo para insertar una nueva categoria creada en la base de datos
	public void insertarCategoria(Connection conexion, Categorias categorias, Usuario usuario, int idUsuario) throws SQLException {
		String CATEGORIABD = "INSERT INTO categorias(tipo, nombre, descripcion, idUsuarioCat) VALUES (?, ?, ?, ?)";

		try (PreparedStatement categoriabd = conexion.prepareStatement(CATEGORIABD)) {
			categoriabd.setString(1, categorias.TipoCat());
			categoriabd.setString(2, categorias.Nombre());
			categoriabd.setString(3, categorias.Descripcion());
			categoriabd.setInt(4, idUsuario);
			categoriabd.executeUpdate();
			conexion.commit();

			System.out.println();
			System.out.println("Categoria registrada correctamente");
			System.out.println();

		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al guardar usuario: " + e.getMessage());
			throw e;
		}
	}

	// Metodo para mostrar las categorias registradas en la base de datos
	public void mostarCategorias(Connection conexion) throws SQLException {
		String query = "SELECT idCategoria, tipo, nombre, descripcion FROM categorias";

		try (PreparedStatement state = conexion.prepareStatement(query)) {
			ResultSet resultSet = state.executeQuery();

			// Verificar si existen registros
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No hay categorias registradas.");
				return;
			}

			System.out.println("Categorias registradas:");
			while (resultSet.next()) {
				int idCategoria = resultSet.getInt("idCategoria");
				String tipo = resultSet.getString("tipo");
				String nombre = resultSet.getString("Nombre");
				String descripcion = resultSet.getString("descripcion");

				System.out.println("ID: " + idCategoria + "- " + tipo + "- " + nombre + ": " + descripcion);
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar las categorias: " + e.getMessage());
			throw e;
		}
	}

	// Metodo para obtener el nombre de la categoria
	public String obtenerNombreCat(int idCategoria, Connection conexion) throws SQLException {
		String query = "SELECT nombre FROM categorias WHERE idCategoria = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setInt(1, idCategoria); // Establece el ID de la categoria en la consulta
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("Nombre");
			} else {
				return "Categoria no encontrada";
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener el nombre de la categoria: " + e.getMessage());
			throw e;
		}
	}

}
