package homEconomy;

import usuario.Usuario;
import conexionBD.ConexionBD;
import conexionBD.UsuarioBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Usuario usuario = new Usuario();
		UsuarioBD usuarioBD = new UsuarioBD();
		Connection conexion = null;

		try {
			// Conectar a la base de datos
			conexion = ConexionBD.conectarBD();
			if (conexion == null) {
				System.out.println("No se pudo establecer la conexion a la base de datos.");
				return;
			}
			conexion.setAutoCommit(false);

			int opcion;
			boolean salir = false;

			// Menu
			while (!salir) {
				try {
					System.out.println();
					System.out.println("Bienvenido a HomEconomy!");
					System.out.println("Seleccione una opcion:");
					System.out.println("1- Crear cuenta");
					System.out.println("2- Iniciar sesion");
					System.out.println("3- Salir");

					opcion = sc.nextInt();
					sc.nextLine();

					switch (opcion) {

					// Crear Usuario
					case 1:
						usuario.CrearUsuario();
						usuarioBD.insertarUsuario(conexion, usuario);
						break;

					// Iniciar Sesion
					case 2:
						usuario.IniciarSesion(conexion);
						if (usuario.isSesionIniciada()) {
							salir = true;
						}
						break;

					// Salir del Sistema
					case 3:
						System.out.println("Sistema finalizado");
						salir = true;
						break;
					}

				} catch (InputMismatchException e) {
					System.out.println();
					System.out.println("Ingrese un valor numerico correcto");
					sc.nextLine();
				}
			}

			// Si el usuario ha iniciado sesion correctamente, mostrar el menu principal
			if (usuario.isSesionIniciada()) {
				Menu menuPrincipal = new Menu(usuario, conexion); // Pasar conexion y usuario al menu
				menuPrincipal.mostrarMenu();
			}

		} catch (SQLException e) {
			System.out.println("Error al interactuar con la base de datos.");
			e.printStackTrace();

			if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		} finally {
			sc.close();
		}
	}
}
