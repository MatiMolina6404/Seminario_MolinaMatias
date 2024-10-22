package usuario;
import java.util.Scanner;

public class Usuario {
    Scanner sc = new Scanner(System.in);
    String nombreUs; 
    String contra;
    int dineroInicial;
    boolean sesionIniciada = false;

    // Metodo para crear un nuevo Usuario
    public void CrearUsuario() {
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
    public void IniciarSesion() {
    	System.out.println("Iniciar Sesion");
    	boolean inicSesion = false;
    	
    	while(!inicSesion){
        System.out.println("Nombre de usuario: ");
        String usuario = sc.nextLine();
        System.out.println("Ingrese su clave");
        String clave = sc.nextLine();

        	if (usuario.equals(nombreUs) && clave.equals(contra)) {
        		inicSesion = true;
        		System.out.println("Sesion iniciada!");
        		System.out.println();
        		sesionIniciada = true;
        	} else {
        		System.out.println("Revisa tu usuario/clave e intenta nuevamente");
        		System.out.println("Si no posees una cuenta, crea una!");
        		System.out.println();
        		break;
        	}
        System.out.println("Presione enter para continuar");
        sc.nextLine();
    	}
    }
    
    // Metodo para mostrar el nombre del usuario
    public String mostUsuario() {
        return nombreUs;
    }
    // Metodo para obtener el dinero inicial
    public int DineroInicial() {
        return dineroInicial;
    }
    // Metodo para verificar si la sesion esta iniciada
    public boolean isSesionIniciada() {
        return sesionIniciada;
    }
    // Metodo para modificar el dinero inicial
    public void setDineroInicial(int nuevoMonto) {
        this.dineroInicial = nuevoMonto;
    }
}


