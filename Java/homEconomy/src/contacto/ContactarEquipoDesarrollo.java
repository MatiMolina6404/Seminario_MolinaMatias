package contacto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ContactarEquipoDesarrollo {
	String mensaje;
	Scanner sc = new Scanner(System.in);

	// Obtencion y formateo de fecha
	LocalDate fechaActual = LocalDate.now();
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String fechaFormateada = fechaActual.format(formato);

	// Metodo para mostrar los medios de contacto de la empresa
	public void desplegarContacto() {
		System.out.println("\tDatos de contacto Economycs:");
		System.out.println("Correo Electronico: homEconomy@economycs.com.ar");
		System.out.println("WhatsApp: +54 9 351 456 7890");
		System.out.println("Linea Fija: 0800 800 1234");
		System.out.println();
	}

	// Metodo para registrar un mensaje con el reclamo, consulta o sugerencia del usuario
	public void registrarMensaje() {
		System.out.println("Escriba su reclamo, consulta o sugerencia en la siguiente casilla");
		mensaje = sc.nextLine();
		// Mostrar resumen Mensaje registrado
		System.out.println();
		System.out.println("Mensaje registrado:");
		System.out.println("'" + mensaje + "'");
		System.out.println("Fecha: " + fechaFormateada);
		System.out.println();
	}

	public String mensaje() {
		return mensaje;
	}

	public String fecha() {
		return fechaFormateada;
	}

}
