package registros;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorias {
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> idCategoria = new ArrayList<>();
    ArrayList<String> nombre = new ArrayList<>();
    ArrayList<String> tipo = new ArrayList<>();
    ArrayList<String> descripcion = new ArrayList<>();
    int cat = 0;
    
    // Metodo para registrar categorias
    public void agregarCategoria() {
        System.out.println("Ingrese el nombre de la categoria a registrar:");
        nombre.add(sc.nextLine());
        System.out.println("Ingrese el tipo de categoria (Gasto o Ingreso): ");
        tipo.add(sc.nextLine());
        System.out.println("Ingrese una descripcion:");
        descripcion.add(sc.nextLine());
        idCategoria.add(cat);
        cat++;
        // Mostrar las categorías registradas
        mostrarCategorias();
    }
    
    
    // Metodo para mostrar las categorias registradas
    public void mostrarCategorias() {
    	if (idCategoria.isEmpty()) {
    		System.out.println();
        	System.out.println("No hay categorias registradas");
        	System.out.println();
        	return;
        }
        System.out.println();
        System.out.println("Categorias registradas:");
        for (int i = 0; i < nombre.size(); i++) {
            System.out.println(idCategoria.get(i) + ": " 
            				   + nombre.get(i) + ", " 
            				   + tipo.get(i) + ", " 
            				   + descripcion.get(i));
        System.out.println();
        }
    }
}

