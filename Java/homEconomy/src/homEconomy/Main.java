package homEconomy;
import usuario.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Usuario usuario = new Usuario();
		
	// Crear Usuario o Iniciar Sesión
	     int opcion;
	     boolean salir = false;
	     while (!salir) {
	    	 try {	// Manejo de excepcion al ingresar un caracter erroneo
	        	System.out.println();
	        	System.out.println("Bienvenido a HomEconomy!");
	            System.out.println("Seleccione una opcion:");
	            System.out.println("1- Crear cuenta");
	            System.out.println("2- Iniciar sesion");
	            System.out.println("3- Salir");
	            opcion = sc.nextInt();
	            sc.nextLine();
	            
	            switch(opcion) {
	            
	            // Crear Usuario
	            case 1:
	            	usuario.CrearUsuario();
	                 break;
	                
	            // Iniciar Sesion
	            case 2:
	                usuario.IniciarSesion();
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
	    			System.out.println("Ingrese un valor numerico correcto");
	    			sc.nextLine();
	    		}
	    } 
	     	// Llamada al menu principal
			if (!salir || usuario.isSesionIniciada()) {
		    Menu menuPrincipal = new Menu (usuario);
			menuPrincipal.mostrarMenu();
			}  sc.close();
	}
}
