package usuario;

import java.util.Scanner;

public class Familia {
	private Scanner sc = new Scanner(System.in);
	public String nombre;
	public String relacion;

	// Metodo para registrar familiares
	public void registrarFamiliar() {
		System.out.println("Ingrese el nombre del familiar a registrar:");
		nombre = sc.nextLine();
		System.out.println("Ingrese su relacion:");
		relacion = sc.nextLine();
	}

	// Metodos para obtener los nombres y relaciones
	public String nombreFam() {
		return nombre;
	}

	public String relacionFam() {
		return relacion;
	}

}
