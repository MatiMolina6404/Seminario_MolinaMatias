package registros;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import conexionBD.CategoriasBD;
import conexionBD.FamiliaBD;
import conexionBD.UsuarioBD;
import usuario.Familia;
import usuario.Usuario;

public class Ingresos {

	Scanner sc = new Scanner(System.in);
	CategoriasBD categoriaBD = new CategoriasBD();
	FamiliaBD familiaBD = new FamiliaBD();
	int valor;
	int categoriaIng;
	int familiarIng;
	String comentario;
	String comprobante;

	// Obtencion y formateo de fecha
	LocalDateTime fechaActual = LocalDateTime.now();
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String fechaHora = fechaActual.format(formato);

	// Metodo para registrar ingresos
	public void registrarIngreso(Usuario usuario, Connection conexion, Familia familia, UsuarioBD usuarioBD)
			throws SQLException {
		try {
			System.out.println("Ingrese el valor del ingreso a registrar:");
			valor = sc.nextInt();

			if (valor < 0) {
				System.out.println("Transaccion cancelada.");
				sc.nextLine();
				throw new SQLException("Valor negativo no permitido.");
			}

			System.out.println("Seleccione el ID de la categoria:");
			categoriaIng = sc.nextInt();
			String nombreCategoria = categoriaBD.obtenerNombreCat(categoriaIng, conexion);
			sc.nextLine();

			System.out.println("Ingrese un comentario:");
			comentario = sc.nextLine();

			System.out.println("Seleccione el ID del familiar:");
			familiarIng = sc.nextInt();
			String nombreFamiliar = familiaBD.obtenerNombreFam(familiarIng, conexion);
			sc.nextLine();

			System.out.println("Ingrese un comprobante:");
			comprobante = sc.nextLine();

			// Agregar el ingreso al balance del usuario
			usuario.setDineroInicial(usuario.DineroInicial() + valor);
			usuarioBD.actualizarBalance(conexion, usuario.getIdUsuario(), usuario.DineroInicial());

			// Mostrar resumen ingreso registrado
			System.out.println("\tResumen Ingreso");
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
			throw new SQLException("Datos de entrada invalidos.");
		}
	}

	// Getters
	public int Valor() {
		return valor;
	}

	public int CategoriaIng() {
		return categoriaIng;
	}

	public int FamiliarIng() {
		return familiarIng;
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
