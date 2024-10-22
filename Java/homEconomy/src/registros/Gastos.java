package registros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import usuario.Familia;
import usuario.Usuario;

public class Gastos {
	
    Scanner sc = new Scanner(System.in);
    ArrayList<Integer> idGasto = new ArrayList<>();
    ArrayList<Integer> valor = new ArrayList<>();
    ArrayList<Integer> categoriaGasto = new ArrayList<>();
    ArrayList<Integer> familiarGasto = new ArrayList<>();
    ArrayList<String> fechaHora = new ArrayList<>();
    ArrayList<String> comentario = new ArrayList<>();
    ArrayList<String> comprobante = new ArrayList<>();
    int gast = 0;

    
    // Metodo para registrar gastos
    public void registrarGasto(Usuario usuario, Categorias categorias, Familia familia) {
    	// Obtencion y formateo de fecha
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaActual.format(formato);
        fechaHora.add(fechaFormateada);
        
        try { //Manejo de excepcion al ingresar un caracter erroneo
        	
        idGasto.add(gast);	
        
        System.out.println("Ingrese el valor del gasto a registrar:");
        int montoGasto = sc.nextInt();
        
        if (usuario.DineroInicial()< montoGasto) {
        	System.out.println("Transaccion cancelada:");
        	System.out.println("No se permite gastar mas dinero que el disponible en la cuenta");
        	System.out.println();
        	sc.nextLine();
        	return; //Cancela el gasto
        }
        
        if (montoGasto < 0) {
        	System.out.println("Transaccion cancelada:");
        	System.out.println("No se permiten valores negativos");
        	System.out.println();
        	sc.nextLine();
        	return; //Cancela el gasto
        }
            
        System.out.println("Seleccione el ID de la categoria:");
        int idCatSeleccionada = sc.nextInt();
        categoriaGasto.add(idCatSeleccionada);
        // Obtener nombre de la categoria
        String nombreCategoria = obtenerNombreCat(idCatSeleccionada, categorias);
        sc.nextLine();
        
        System.out.println("Ingrese un comentario:");
        comentario.add(sc.nextLine());
        
        System.out.println("Seleccione el ID del familiar:");
        int idFamSeleccionado = sc.nextInt();
        familiarGasto.add(idFamSeleccionado);
        // Obtener nombre del familiar
        String nombreFamiliar = obtenerNombreFam(idFamSeleccionado, familia);
        sc.nextLine();
        
        System.out.println("Ingrese un comprobante:");
        comprobante.add(sc.nextLine());
         
        /* El monto del gasto se agrega al final por si 
         * ocurre un error en la carga de los datos.
         */
        valor.add(montoGasto);
        usuario.setDineroInicial(usuario.DineroInicial() - montoGasto);
        
        // Mostrar resumen gasto registrado
        System.out.println();
        System.out.println("\tResumen Gasto");
        System.out.println("Gasto ID: " + idGasto.get(gast));  
        System.out.println("Fecha: " + fechaHora.get(gast)); 
        System.out.println("Valor: $" + valor.get(gast)); 
        System.out.println("Categoria: " + nombreCategoria); 
        System.out.println("Comentario: " + comentario.get(gast)); 
        System.out.println("Familiar: " + nombreFamiliar);
        System.out.println("Comprobante: " + comprobante.get(gast));
        System.out.println();
        System.out.println("Dinero disponible: $" + usuario.DineroInicial());
        gast++;
        
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
    
    
    //Getters
	public ArrayList<Integer> getIdGasto() {
		return idGasto;
	}
	public ArrayList<Integer> getValor() {
		return valor;
	}
	public ArrayList<Integer> getCategoriaGasto() {
		return categoriaGasto;
	}
	public ArrayList<Integer> getFamiliarGasto() {
		return familiarGasto;
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
