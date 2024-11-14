package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import registros.Gastos;
import usuario.Usuario;

public class GastosBD {

	// Metodo para insertar en la base de datos los gastos registrados
	public void insertarGasto(Connection conexion, Gastos gas, int idUsuario) throws SQLException {
		String GASTOSBD = "INSERT INTO gastos(idUsuarioG, fecha, cantidad, idCategoriaG, comentario, idFamiliarG, comprobante) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement gastosBD = conexion.prepareStatement(GASTOSBD)) {
			gastosBD.setInt(1, idUsuario);
			gastosBD.setString(2, gas.FechaHora());
			gastosBD.setInt(3, gas.Valor());

			// Verificar si la categoria existe
			if (categoriaExiste(conexion, gas.CategoriaGasto())) {
				gastosBD.setInt(4, gas.CategoriaGasto());
			} else {
				gastosBD.setNull(4, java.sql.Types.INTEGER);
				// Registra null si no encuentra la categoria ingresada
			}

			gastosBD.setString(5, gas.Comentario());

			// Verificar si el familiar existe
			if (familiarExiste(conexion, gas.FamiliarGasto())) {
				gastosBD.setInt(6, gas.FamiliarGasto());
			} else {
				gastosBD.setNull(6, java.sql.Types.INTEGER);
				// Registra null si no encuentra el familiar ingresado
			}

			gastosBD.setString(7, gas.Comprobante());

			System.out.println();
			System.out.println("Gasto registrado correctamente");
			System.out.println();

			gastosBD.executeUpdate();
			conexion.commit();

		} catch (SQLException e) {
			conexion.rollback(); // Revertir cambios si ocurre un error
			System.out.println("Error al registrar gasto: " + e.getMessage());
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

	// Metodo para mostrar los gastos registrados en la base de datos
	public void mostrarGastos(Connection conexion, Usuario usuario) throws SQLException {
		String query = "SELECT idGasto, fecha, cantidad, idCategoriaG, comentario, idFamiliarG, comprobante FROM gastos WHERE idUsuarioG = ?";

		try (PreparedStatement state = conexion.prepareStatement(query)) {
			// Obtener el idUsuario de la sesion actual
			int idUsuario = usuario.getIdUsuario();
			state.setInt(1, idUsuario);

			ResultSet resultSet = state.executeQuery();

			// Verificar si existen registros
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No hay gastos registrados.");
				return;
			}

			System.out.println("Gastos registrados:");
			while (resultSet.next()) {
				int idGasto = resultSet.getInt("idGasto");
				String fecha = resultSet.getString("fecha");
				int cantidad = resultSet.getInt("cantidad");
				int idCategoriaG = resultSet.getInt("idCategoriaG");
				String comentario = resultSet.getString("comentario");
				int idFamiliarG = resultSet.getInt("idFamiliarG");
				String comprobante = resultSet.getString("comprobante");

				System.out.println(idGasto + "-> Fecha: " + fecha + ", $" + cantidad + ", Categoria: " + idCategoriaG);
				System.out.println(comentario + ", Familiar: " + idFamiliarG + ", Comprobante : " + comprobante);
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Error al mostrar los gastos registrados: " + e.getMessage());
			throw e;
		}
	}
}
