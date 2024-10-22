package homEconomy;
import java.util.InputMismatchException;
import java.util.Scanner;

import registros.Categorias;
import registros.Gastos;
import registros.Ingresos;
import registros.Movimientos;
import usuario.Familia;
import usuario.Usuario;
import contacto.ContactarEquipoDesarrollo;
import ahorros.Ahorros;
import ahorros.MovimientosAhorro;
import registros.Informes;

public class Menu {

	Scanner sc=new Scanner(System.in);	
    Familia fam = new Familia();
    Usuario usu;
	Categorias cat = new Categorias();
    Gastos gast = new Gastos();
    Ingresos ing = new Ingresos();
    ContactarEquipoDesarrollo cont = new ContactarEquipoDesarrollo();
    Ahorros aho = new Ahorros();
    Informes inf = new Informes();
	Movimientos mov = new Movimientos();
	MovimientosAhorro movAho = new MovimientosAhorro();
    
    // Constructor que recibe el objeto usuario desde la clase Main
    public Menu (Usuario usuario) {
        this.usu = usuario;
    }
    
    
    // Metodo para mostrar el menu principal
	public void mostrarMenu() {
	boolean salir=true;
	
	do {
		try { // Manejo de excepcion al ingresar un caracter erroneo
			System.out.println("_____________ HomEconomy _____________");
			System.out.println();
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
			int opciones=sc.nextInt();
			sc.nextLine();
			switch (opciones) {
			
			// Registrar familiares
			case 1:
			    fam.registrarFamiliar();
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
			
			// Mostrar familiares
			case 2:
			    fam.mostrarFamilia();
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
				
			// Crear Categorias
			case 3:
				cat.agregarCategoria();
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
				
			// Mostrar Categorias
			case 4:
				cat.mostrarCategorias();
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
				
			// Registrar Gastos
			case 5:
				gast.registrarGasto(usu,cat,fam);
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
			
			// Registrar Ingresos
			case 6:
				ing.registrarIngreso(usu,cat,fam);
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
				
			// Apartado Ahorros
			case 7:
				boolean volverMenuAho= true;
				do {
					System.out.println();
					System.out.println("Concepto del Ahorro: " + aho.getConcepto());
					System.out.println("Monto reservado: $" + aho.cantReservada);
					System.out.println("Opciones de Ahorros");
					System.out.println("1- Definir Ahorros");
					System.out.println("2- Ingresar dinero");
					System.out.println("3- Retirar dinero");
					System.out.println("4- Volver al menu principal");
				
				int opcionSub=sc.nextInt();
				switch (opcionSub) {
					
					// Definir ahorros
					case 1:
					aho.definirAhorros(usu);
					break;
					
					// Ingresar dinero a ahorros
					case 2:
					aho.ingresarAhorros(usu);
					break; 
					
					// Retirar dinero de ahorros
					case 3:
					aho.retirarAhorros(usu);
					break;
					
					//Fin submenu
					case 4:  
					volverMenuAho=false;
				} 
				
				} while (volverMenuAho);
					  break;
						

			// Solicitar Informes
			case 8:
				boolean volverMenuInf= true;
				do {
					System.out.println();
					System.out.println("Opciones de Informes");
					System.out.println("1- Solicitar informes de Ingresos");
					System.out.println("2- Solicitar informes de Gastos");
					System.out.println("3- Solicitar informes de Ahorros");
					System.out.println("4- Volver al menu principal");
				
				int opcionSub=sc.nextInt();
				sc.nextLine();
				switch (opcionSub) {
					
					// Solicitar informes de ingresos
					case 1:
					inf.SolicitarInformeIngresos();
					mov.movimientosIngresos(ing,cat,fam);
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;
					
					// Solicitar informes de gastos
					case 2:
					inf.SolicitarInformeGastos();
					mov.movimientosGastos(gast,cat,fam);
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;
					
					// Solicitar informes de ahorros
					case 3:
					inf.SolicitarInformeAhorros();
					movAho.movimientosAhorros(aho);
					System.out.println("Presione enter para continuar");
					sc.nextLine();
					break;
						
					// Fin submenu
					case 4: 
					volverMenuInf = false;
				}
				
				}while (volverMenuInf);
				  break;
				  
				  
			// Contactar Equipo de Desarrollo
			case 9:
				cont.desplegarContacto();
				System.out.println();
				cont.registrarMensaje();
				System.out.println("Presione enter para continuar");
				sc.nextLine();
				break;
				
			// Salir del sistema
			case 0:
			salir=false;
			System.out.println("Sistema finalizado");
			break;
			} 
			
			} catch (InputMismatchException exc1) {
	    	System.out.println("Ingrese una opcion valida (en numero)");
	    	sc.nextLine();
			}		
		
		} while (salir);
		  sc.close();
	}
}

