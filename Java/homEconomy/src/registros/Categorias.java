package registros;

import java.util.Scanner;

public class Categorias {
	Scanner sc = new Scanner(System.in);
	String nombre;
	String tipoCat;
	String descripcion;

	// Metodo para registrar categorias
	public void agregarCategoria() {
		System.out.println("Ingrese el nombre de la categoria a registrar:");
		nombre = sc.nextLine();
		System.out.println("Ingrese el tipo de categoria (Gasto o Ingreso): ");
		tipoCat = sc.nextLine();
		System.out.println("Ingrese una descripcion:");
		descripcion = sc.nextLine();
	}

	public String Nombre() {
		return nombre;
	}

	public String TipoCat() {
		return tipoCat;
	}

	public String Descripcion() {
		return descripcion;
	}

}
