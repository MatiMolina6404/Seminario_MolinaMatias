package contacto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactarEquipoDesarrollo {
	int mensj;
	ArrayList<Integer> idMensaje = new ArrayList<>();
	ArrayList<String> mensaje = new ArrayList<>();
	ArrayList<String> fechaHora = new ArrayList<>();
	Scanner sc = new Scanner (System.in);
	
	// Metodo para mostrar los medios de contacto de la empresa
	public void desplegarContacto() {
		System.out.println("Datos de contacto Economycs:");
		System.out.println("Correo Electronico: homEconomy@economycs.com.ar");
		System.out.println("WhatsApp: +54 9 351 456 7890");
		System.out.println("Linea Fija: 0800 800 1234");
	}
	
	
	// Metodo para registrar un mensaje con el reclamo, consulta o sugerencia del usuario
	public void registrarMensaje() {
		LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaActual.format(formato);
        fechaHora.add(fechaFormateada);
        
		System.out.println("Escriba su reclamo, consulta o sugerencia en la siguiente casilla");
		mensaje.add (sc.nextLine());
		idMensaje.add(mensj);
		
		// Mostrar resumen Mensaje registrado
		System.out.println();
        System.out.println("Mensaje registrado nro " + idMensaje.get(mensj) + ":");
        System.out.println("'" + mensaje.get(mensj) + "'");
        System.out.println("Fecha: " + fechaHora.get(mensj));
		
		mensj++;
	}
}
