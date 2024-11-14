package usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import conexionBD.UsuarioBD;

public class Usuario {
	Scanner sc = new Scanner(System.in);
	int idUsuario;
	String nombreUs;
	String contra;
	int dineroInicial;
	boolean sesionIniciada = false;

	// Constructores
	public Usuario(int idUsuario, String nombreUsuario, String contraseña, int dineroInicial) {
		this.idUsuario = idUsuario;
		this.nombreUs = nombreUsuario;
		this.contra = contraseña;
		this.dineroInicial = dineroInicial;
		this.sesionIniciada = false;
	}

	// Metodo para crear un nuevo Usuario
	public void CrearUsuario() {
		System.out.println("Crear Usuario");

		System.out.println("Ingrese un nombre de usuario");
		nombreUs = sc.nextLine();
		System.out.println("Ingrese una clave");
		contra = sc.nextLine();
		System.out.println("Ingrese un monto de dinero inicial");

		if (sc.hasNextInt() && (dineroInicial = sc.nextInt()) > 0) {
			sc.nextLine();
		} else {
			System.out.println("Ingrese un valor numerico positivo para el monto de dinero inicial");
			sc.nextLine();
		}
	}

	// Metodo para iniciar sesion con el usuario creado
	public void IniciarSesion(Connection conexion) {
		System.out.println("Iniciar Sesion");

		while (!sesionIniciada) {
			System.out.println("Nombre de usuario: ");
			nombreUs = sc.nextLine();
			System.out.println("Ingrese su clave");
			contra = sc.nextLine();

			// Obtiene los datos de usuario registrados en la base de datos
			try {
				UsuarioBD usuarioBD = new UsuarioBD();
				Usuario usu = usuarioBD.iniciarSesionBD(nombreUs, contra, conexion);

				if (usu != null) {
					idUsuario = usu.getIdUsuario(); // Obtener el idUsuario del objeto Usuario
					dineroInicial = usu.DineroInicial();
					sesionIniciada = true;
					System.out.println("Sesion iniciada!");
				} else {
					System.out.println("Revisa tu usuario/clave e intenta nuevamente.");
				}

			} catch (SQLException e) {
				System.out.println("Error al conectar con la base de datos.");
				e.printStackTrace();
			}

			System.out.println("Presione enter para continuar");
			sc.nextLine();
		}
	}

	public Usuario() {
		this.sesionIniciada = false;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String mostUsuario() {
		return nombreUs;
	}

	public String mostContra() {
		return contra;
	}

	public int DineroInicial() {
		return dineroInicial;
	}

	public boolean isSesionIniciada() {
		return sesionIniciada;
	}

	// Metodo para modificar el dinero inicial
	public void setDineroInicial(int nuevoMonto) {
		this.dineroInicial = nuevoMonto;
	}
}
