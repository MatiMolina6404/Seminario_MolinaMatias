package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import registros.Ingresos;

public class IngresosBD {

	// Metodo para insertar en la base de datos los ingresos registrados
	public void insertarIngreso(Connection conexion, Ingresos ing) throws SQLException {
		String INGRESOSBD = "INSERT INTO ingresos(idUsuarioI, fecha, cantidad, idCategoriaI, comentario, idFamiliarI, comprobante) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement ingresosBD = conexion.prepareStatement(INGRESOSBD)) {
			ingresosBD.setInt(1, 1);
			ingresosBD.setString(2, ing.FechaHora());
			ingresosBD.setInt(3, ing.Valor());

			// Verificar si la categoria existe
			if (categoriaExiste(conexion, ing.CategoriaIng())) {
				ingresosBD.setInt(4, ing.CategoriaIng());
			} else {
				ingresosBD.setNull(4, java.sql.Types.INTEGER); 
				// Registra null si no encuentra la categoria ingresada
			}

			ingresosBD.setString(5, ing.Comentario());

			// Verificar si el familiar existe
			if (familiarExiste(conexion, ing.FamiliarIng())) {
				ingresosBD.setInt(6, ing.FamiliarIng());
			} else {
				ingresosBD.setNull(6, java.sql.Types.INTEGER);
				// Registra null si no encuentra el familiar ingresado
			}

			ingresosBD.setString(7, ing.Comprobante());

			ingresosBD.executeUpdate();
			conexion.commit();

			System.out.println();
			System.out.println("Ingreso registrado correctamente");
			System.out.println();

		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al registrar ingreso: " + e.getMessage());
			throw e;
		}
	}

	// Metodo para verificar si la categoria seleccionada existe
	private boolean categoriaExiste(Connection conexion, int idCategoria) throws SQLException {
		String query = "SELECT 1 FROM categorias WHERE idCategoria = ?";
		try (PreparedStatement ps = conexion.prepareStatement(query)) {
			ps.setInt(1, idCategoria);
			ResultSet rs = ps.executeQuery();
			return rs.next(); // Devuelve true si encuentra un resultado
		}
	}

	// Metodo para verificar si el familiar seleccionado existe
	private boolean familiarExiste(Connection conexion, int idFamiliar) throws SQLException {
		String query = "SELECT 1 FROM familia WHERE idFamilia = ?";
		try (PreparedStatement ps = conexion.prepareStatement(query)) {
			ps.setInt(1, idFamiliar);
			ResultSet rs = ps.executeQuery();
			return rs.next(); // Devuelve true si encuentra un resultado
		}
	}

	// Metodo para mostrar los ingresos registrados en la base de datos
	public void mostrarIngresos(Connection conexion) throws SQLException {
		String query = "SELECT idIngreso, fecha, cantidad, idCategoriaI, comentario, idFamiliarI, comprobante FROM ingresos";

		try (PreparedStatement state = conexion.prepareStatement(query)) {
			ResultSet resultSet = state.executeQuery();

			// Verificar si existen registros
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No hay ingresos registrados.");
				return;
			}

			System.out.println("Ingresos registrados:");
			while (resultSet.next()) {
				int idIngreso = resultSet.getInt("idIngreso");
				String fecha = resultSet.getString("fecha");
				int cantidad = resultSet.getInt("cantidad");
				int idCategoriaI = resultSet.getInt("idCategoriaI");
				String comentario = resultSet.getString("comentario");
				int idFamiliarI = resultSet.getInt("idFamiliarI");
				String comprobante = resultSet.getString("comprobante");

				System.out.println(idIngreso + "-> Fecha: " + fecha + ", $" + cantidad + ", Categoria: " + idCategoriaI);
				System.out.println(comentario + ", Familiar: " + idFamiliarI + ", Comprobante : " + comprobante);
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar los ingresos registrados: " + e.getMessage());
			throw e;
		}
	}
}
