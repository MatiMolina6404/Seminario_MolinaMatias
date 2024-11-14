package homEconomy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import registros.Categorias;
import registros.Gastos;
import registros.Ingresos;
import usuario.Familia;
import usuario.Usuario;
import contacto.ContactarEquipoDesarrollo;
import ahorros.Ahorros;
import ahorros.MovimientosAhorros;
import conexionBD.AhorrosBD;
import conexionBD.CategoriasBD;
import conexionBD.ContactoBD;
import conexionBD.FamiliaBD;
import conexionBD.GastosBD;
import conexionBD.IngresosBD;
import conexionBD.UsuarioBD;
import registros.Informes;

public class Menu {
	
	// Creacion de los objetos
	private Connection conexion;
	private Scanner sc = new Scanner(System.in);
	private Familia fam = new Familia();
	private FamiliaBD famBD = new FamiliaBD();
	private Usuario usu;
	private UsuarioBD usuBD = new UsuarioBD();
	private Categorias cat = new Categorias();
	private CategoriasBD catBD = new CategoriasBD();
	private Gastos gast = new Gastos();
	private GastosBD gastBD = new GastosBD();
	private Ingresos ing = new Ingresos();
	private IngresosBD ingBD = new IngresosBD();
	private ContactarEquipoDesarrollo cont = new ContactarEquipoDesarrollo();
	private ContactoBD contBD = new ContactoBD();
	private Ahorros aho = new Ahorros();
	private AhorrosBD ahoBD = new AhorrosBD();
	private MovimientosAhorros movAho = new MovimientosAhorros();
	private Informes inf = new Informes();

	// Constructor que recibe el objeto usuario y la conexión desde la clase Main
	public Menu(Usuario usuario, Connection conexion) {
		this.usu = usuario;
		this.conexion = conexion;
	}

	// Método para mostrar el menú principal
	public void mostrarMenu() {
		boolean salir = true;

		do {
			try { // Manejo de excepción al ingresar un carácter erróneo
				System.out.println("_____________ HomEconomy _____________");
				System.out.println();
				System.out.println("ID Usuario: " + usu.getIdUsuario());
				System.out.println("Usuario: " + usu.mostUsuario());
				System.out.println("Dinero Disponible: $" + usu.DineroInicial());
				System.out.println();
				System.out.println("Menu de opciones:");
				System.out.println("1- Registrar Familiares");
				System.out.println("2- Mostrar Familiares Registrados");
				System.out.println("3- Crear Categorias");
				System.out.println("4- Mostrar Categorias Registradas");
				System.out.println("5- Registrar Gastos");
				System.out.println("6- Registrar Ingresos");
				System.out.println("7- Ahorros");
				System.out.println("8- Solicitar Informes");
				System.out.println("9- Contactar Equipo de desarrollo");
				System.out.println("0- Salir del sistema");
				int opciones = sc.nextInt();
				sc.nextLine();

				switch (opciones) {
				// Registrar familiares
				case 1:
					fam.registrarFamiliar();
					try {
						famBD.insertarFamiliar(conexion, fam, usu, usu.getIdUsuario());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;

				// Mostrar familiares
				case 2:
					famBD.mostrarFamilia(conexion, usu);
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;

				// Crear categorías
				case 3:
					cat.agregarCategoria();
					catBD.insertarCategoria(conexion, cat, usu, usu.getIdUsuario());
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;

				// Mostrar categorías
				case 4:
					catBD.mostarCategorias(conexion);
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;

				// Registrar gastos
				case 5:
					try {
						gast.registrarGasto(usu, conexion, fam, usuBD);
						gastBD.insertarGasto(conexion, gast, usu.getIdUsuario());
						System.out.println("Presione enter para continuar");
						sc.nextLine();
					} catch (SQLException e) {
						System.out.println("No se registro el gasto: " + e.getMessage());
						System.out.println();
					}
					break;

				// Registrar ingresos
				case 6:
					try {
						ing.registrarIngreso(usu, conexion, fam, usuBD);
						ingBD.insertarIngreso(conexion, ing);
						System.out.println("Presione enter para continuar");
						sc.nextLine();
					} catch (SQLException e) {
						System.out.println("No se registro el ingreso: " + e.getMessage());
						System.out.println();
					}
					break;

				// Apartado ahorros
				case 7:
					boolean volverMenuAho = true;
					do {
						try {
							// Obtiene los datos de ahorro registrados en la base de datos
							Ahorros ahorro = ahoBD.obtenerAhorro(usu.getIdUsuario(), conexion);

							System.out.println();
							if (ahorro.Concepto() != null) {
								System.out.println("Concepto del Ahorro: " + ahorro.Concepto());
								System.out.println("Monto reservado: $" + ahorro.CantReservada());
								System.out.println();
							} else {
								System.out.println("No se ha definido un ahorro para este usuario.");
							}

							System.out.println("Opciones de Ahorros");
							System.out.println("1- Definir Ahorros");
							System.out.println("2- Ingresar dinero");
							System.out.println("3- Retirar dinero");
							System.out.println("4- Volver al menu principal");

							int opcionSub = sc.nextInt();
							sc.nextLine();

							// Submenu
							switch (opcionSub) {

							// Definir un nuevo ahorro
							case 1:
								try {
									aho.definirAhorros(usu, conexion, ahoBD);
									System.out.println("Presione enter para continuar");
									sc.nextLine();
								} catch (SQLException e) {
									System.out.println("No se registro el ahorro");
									System.out.println();
								}
								break;

							// Ingresar dinero al ahorro
							case 2:
								try {
									aho.ingresarAhorros(usu, conexion, ahoBD);
									System.out.println("Presione enter para continuar");
									sc.nextLine();
								} catch (SQLException e) {
									System.out.println("No se registro el movimiento de ahorro");
									System.out.println();
								}
								break;

							// Retirar dinero del ahorro
							case 3:
								try {
									aho.retirarAhorros(usu, conexion, ahoBD, usuBD);
									System.out.println("Presione enter para continuar");
									sc.nextLine();
								} catch (SQLException e) {
									System.out.println("No se registro el movimiento de ahorro");
									System.out.println();
								}

								break;

							// Cerrar submenu
							case 4:
								volverMenuAho = false;
								break;

							}
						} catch (SQLException e) {
							System.out.println("Error al obtener el ahorro: " + e.getMessage());
							volverMenuAho = false;
						}
					} while (volverMenuAho);
					break;

				// Solicitar informes
				case 8:
					boolean volverMenuInf = true;
					do {
						System.out.println();
						System.out.println("Opciones de Informes");
						System.out.println("1- Solicitar informes de Ingresos");
						System.out.println("2- Solicitar informes de Gastos");
						System.out.println("3- Solicitar informes de Ahorros");
						System.out.println("4- Volver al menu principal");

						int opcionSub = sc.nextInt();
						sc.nextLine();

						// Submenu
						switch (opcionSub) {

						// Solicitar informe de ingresos
						case 1:
							inf.SolicitarInformeIngresos(conexion);
							System.out.println("Presione enter para continuar");
							sc.nextLine();
							break;

						// Solicitar informe de gastos
						case 2:
							inf.SolicitarInformeGastos(conexion, usu);
							System.out.println("Presione enter para continuar");
							sc.nextLine();
							break;

						// Solicitar informe de ahorros
						case 3:
							movAho.mostrarMovimientosAhorros(conexion, usu);
							System.out.println("Presione enter para continuar");
							sc.nextLine();
							break;

						// Cerrar submenu
						case 4:
							volverMenuInf = false;
							break;
						}
					} while (volverMenuInf);
					break;

				// Contactar equipo de desarrollo
				case 9:
					cont.desplegarContacto();
					cont.registrarMensaje();
					contBD.insertarMensaje(conexion, cont);
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;

				// Salir del sistema
				case 0:
					// Cerrar la conexion antes de salir
					if (conexion != null && !conexion.isClosed()) {
						conexion.close();
					}

					salir = false;
					System.out.println("Sistema finalizado");
					break;
				}

			} catch (InputMismatchException exc1) {
				System.out.println("Ingrese un valor numerico correcto");
				sc.nextLine();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} while (salir);
		sc.close();
	}
}
