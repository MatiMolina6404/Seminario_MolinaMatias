package registros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import usuario.Familia;
import usuario.Usuario;

public class Ingresos {

	Scanner sc = new Scanner(System.in);
	ArrayList<Integer> idIngreso = new ArrayList<>();
	ArrayList<Integer> valor = new ArrayList<>();
	ArrayList<Integer> categoriaIng = new ArrayList<>();
	ArrayList<Integer> familiarIng = new ArrayList<>();
	ArrayList<String> fechaHora = new ArrayList<>();
	ArrayList<String> comentario = new ArrayList<>();
	ArrayList<String> comprobante = new ArrayList<>();
	int ing = 0;

	
	// Metodo para registrar ingresos
	public void registrarIngreso(Usuario usuario, Categorias categorias, Familia familia) {
	    // Obtencion y formateo de fecha
		LocalDateTime fechaActual = LocalDateTime.now();
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String fechaFormateada = fechaActual.format(formato);
	    fechaHora.add(fechaFormateada);
	        
	    try { //Manejo de excepcion al ingresar un caracter erroneo
	        
	    idIngreso.add(ing);
	       
	    System.out.println("Ingrese el valor del ingreso a registrar:");
	    int montoIngreso = sc.nextInt();
	        
	    if (montoIngreso < 0) {
	    	System.out.println("Transaccion cancelada:");
	        System.out.println("No se permiten valores negativos");
	        System.out.println();
	        sc.nextLine();
	        return; //Cancela el gasto
	    }
	        
	    System.out.println("Seleccione el ID de la categoria:");
	    int idCatSeleccionada = sc.nextInt();
	    categoriaIng.add(idCatSeleccionada);
	    // Obtener nombre de la categoria
	    String nombreCategoria = obtenerNombreCat(idCatSeleccionada, categorias);
	    sc.nextLine();
	        
	    System.out.println("Ingrese un comentario:");
	    comentario.add(sc.nextLine());
	        
	    System.out.println("Seleccione el ID del familiar:");
	    int idFamSeleccionado = sc.nextInt();
	    familiarIng.add(idFamSeleccionado);
	    // Obtener nombre del familiar
	    String nombreFamiliar = obtenerNombreFam(idFamSeleccionado, familia);
	    sc.nextLine();
	        
	    System.out.println("Ingrese un comprobante:");
	    comprobante.add(sc.nextLine());
	         
	    /* El monto del ingreso se agrega al final por si 
	     * ocurre un error en la carga de los datos.
	     */
	    valor.add(montoIngreso);
	    usuario.setDineroInicial(usuario.DineroInicial() + montoIngreso);
	        
	    // Mostrar resumen ingreso registrado
	    System.out.println();
	    System.out.println("\tResumen Ingreso");
	    System.out.println("Ingreso ID: " + idIngreso.get(ing)); 
	    System.out.println("Fecha: " + fechaHora.get(ing)); 
	    System.out.println("Valor: $" + valor.get(ing)); 
	    System.out.println("Categoria: " + nombreCategoria); 
	    System.out.println("Comentario: " + comentario.get(ing)); 
	    System.out.println("Familiar: " + nombreFamiliar);
	    System.out.println("Comprobante: " + comprobante.get(ing));
	    System.out.println();
	    System.out.println("Dinero disponible: $" + usuario.DineroInicial());
	    ing++;
	        
	    } catch (InputMismatchException exc1) {
	    	System.out.println("Ingrese los datos correctamente");
	        sc.nextLine();
	    }  
	}
		
	
	// Método auxiliar para obtener el nombre de la categoría
	String obtenerNombreCat (int idCategoria, Categorias categorias) {
		int cate = categorias.idCategoria.indexOf(idCategoria);
	    if (cate != -1) {
	    	return categorias.nombre.get(cate); // Retorna el nombre correspondiente al ID
	    } else {
	    	return "Categoria no encontrada";
	    }
	}
	  
	
	// Metodo auxiliar para obtener el nombre del familiar
	String obtenerNombreFam (int idFamiliar, Familia familia) {
	    int familiar = familia.idFamiliar.indexOf(idFamiliar);
	    if (familiar != -1) {
	        return familia.nombre.get(familiar); // Retorna el nombre correspondiente al ID
	    } else {
	        return "Familiar no encontrado";
	    }
	}
		
	
	// Getters
	public ArrayList<Integer> getIdIngreso() {
		return idIngreso;
	}
	public ArrayList<Integer> getValor() {
		return valor;
	}
	public ArrayList<Integer> getCategoriaIng() {
		return categoriaIng;
	}
	public ArrayList<Integer> getFamiliarIng() {
		return familiarIng;
	}
	public ArrayList<String> getFechaHora() {
		return fechaHora;
	}
	public ArrayList<String> getComentario() {
		return comentario;
	}
	public ArrayList<String> getComprobante() {
		return comprobante;
	}
}


