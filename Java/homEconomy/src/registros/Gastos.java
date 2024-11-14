package registros;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import usuario.Familia;
import usuario.Usuario;
import conexionBD.CategoriasBD;
import conexionBD.FamiliaBD;
import conexionBD.UsuarioBD;

public class Gastos {

	Scanner sc = new Scanner(System.in);
	CategoriasBD categoriaBD = new CategoriasBD();
	FamiliaBD familiaBD = new FamiliaBD();
	int valor;
	int categoriaGasto;
	int familiarGasto;
	String comentario;
	String comprobante;

	// Obtencion y formateo de fecha
	LocalDateTime fechaActual = LocalDateTime.now();
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String fechaHora = fechaActual.format(formato);

	// Metodo para registrar gastos
	public void registrarGasto(Usuario usuario, Connection conexion, Familia familia, UsuarioBD usuarioBD)
			throws SQLException {
		try {
			System.out.println("Ingrese el valor del gasto a registrar:");
			valor = sc.nextInt();

			if (usuario.DineroInicial() < valor) {
				System.out.println("Transaccion cancelada.");
				sc.nextLine();
				throw new SQLException("Fondos insuficientes.");
			}

			if (valor < 0) {
				System.out.println("Transaccion cancelada.");
				sc.nextLine();
				throw new SQLException("Valor negativo no permitido.");
			}

			System.out.println("Seleccione el ID de la categoria:");
			categoriaGasto = sc.nextInt();
			String nombreCategoria = categoriaBD.obtenerNombreCat(categoriaGasto, conexion);
			sc.nextLine();

			System.out.println("Ingrese un comentario:");
			comentario = sc.nextLine();

			System.out.println("Seleccione el ID del familiar:");
			familiarGasto = sc.nextInt();
			String nombreFamiliar = familiaBD.obtenerNombreFam(familiarGasto, conexion);
			sc.nextLine();

			System.out.println("Ingrese un comprobante:");
			comprobante = sc.nextLine();

			// Reducir el gasto al balance del usuario
			usuario.setDineroInicial(usuario.DineroInicial() - valor);
			usuarioBD.actualizarBalance(conexion, usuario.getIdUsuario(), usuario.DineroInicial());

			// Mostrar resumen gasto registrado
			System.out.println("\tResumen Gasto");
			System.out.println("Fecha: " + fechaHora);
			System.out.println("Valor: $" + valor);
			System.out.println("Categoria: " + nombreCategoria);
			System.out.println("Comentario: " + comentario);
			System.out.println("Familiar: " + nombreFamiliar);
			System.out.println("Comprobante: " + comprobante);
			System.out.println("Dinero disponible: $" + usuario.DineroInicial());

		} catch (InputMismatchException exc1) {
			System.out.println("Ingrese los datos correctamente.");
			sc.nextLine();
			throw new SQLException("Datos de entrada inválidos.");
		}
	}

	// Getters
	public int Valor() {
		return valor;
	}

	public int CategoriaGasto() {
		return categoriaGasto;
	}

	public int FamiliarGasto() {
		return familiarGasto;
	}

	public String FechaHora() {
		return fechaHora;
	}

	public String Comentario() {
		return comentario;
	}

	public String Comprobante() {
		return comprobante;
	}

}
