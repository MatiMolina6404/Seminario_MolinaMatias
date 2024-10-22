package usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class Familia {
    Scanner sc = new Scanner(System.in);
    public ArrayList<Integer> idFamiliar = new ArrayList<>(); 
	public ArrayList<String> nombre = new ArrayList<>(); 
    public ArrayList<String> relacion = new ArrayList<>(); 
    int fam = 0;
    
    
    // Metodo para registrar familiares
    public void registrarFamiliar() {
    	System.out.println("Ingrese el nombre del familiar a registrar:");
        nombre.add(sc.nextLine());
        System.out.println("Ingrese su relacion:");
        relacion.add(sc.nextLine());
        idFamiliar.add(fam);
        fam++;
        // Mostrar los familiares registrados
        mostrarFamilia();
    }
    
    
    // Metodo para mostrar los familiares registrados
    public void mostrarFamilia() {
    	if (idFamiliar.isEmpty()) {
    		System.out.println();
        	System.out.println("No hay familiares registrados");
        	System.out.println();
        	return;
        }
    	System.out.println();
        System.out.println("Familiares registrados:");
        for (int i = 0; i < nombre.size(); i++) {
            System.out.println(idFamiliar.get(i) + ": " 
            				   + nombre.get(i) + ", " 
            				   + relacion.get(i));
        System.out.println();
        }
    }
}