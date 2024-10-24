package ahorros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import usuario.Usuario;

public class Ahorros {
	Scanner sc = new Scanner (System.in);
	ArrayList<Integer> idMovimientoAhorro = new ArrayList<>();
	ArrayList<String> concepto = new ArrayList<>();
	ArrayList<Integer> cantidad = new ArrayList<>();
	ArrayList<String> tipoMovimiento = new ArrayList<>();
	ArrayList<String> ultimoMovimiento = new ArrayList<>();
	ArrayList<String> fechaHora = new ArrayList<>();
	public int montoAhorro;
	int idMovimiento;
	int idAhorro;
	public int cantReservada;
	
	// Metodo para definir el apartado de ahorro
	public void definirAhorros(Usuario usuario) {
		
		if (concepto.isEmpty()) {
		// Obtencion y formateo de fecha
	    LocalDateTime fechaActual = LocalDateTime.now();
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String fechaFormateada = fechaActual.format(formato);
	    fechaHora.add(fechaFormateada);
	        
	    try { //Manejo de excepcion al ingresar un caracter erroneo

	        System.out.println("Ingrese el concepto del ahorro a reservar");
	        String conc = sc.nextLine();
	        
	        System.out.println("Ingrese el valor a reservar:");
	        montoAhorro = sc.nextInt();
	        if (usuario.DineroInicial()< montoAhorro) {
	        	System.out.println("Transaccion cancelada:");
	        	System.out.println("No se permite reservar mas dinero que el disponible en la cuenta");
	        	sc.nextLine();
	        	return; //Cancela el ahorro
	        	}
	        
	        /* El monto y el concepto se agregan al final por si 
	         * ocurre un error en la carga de los datos.
	         */
	        concepto.add(conc);
	        cantReservada = montoAhorro;
	        usuario.setDineroInicial(usuario.DineroInicial() - montoAhorro);
	        sc.nextLine(); 
	        
	        // Mostrar resumen ahorro registrado
	        System.out.println();
	        System.out.println("\tResumen definicion de Ahorro");
	        System.out.println("Concepto: " + concepto.get(idAhorro));
	        System.out.println("Ahorro ID: " + idAhorro); 
	        System.out.println("Fecha: " + fechaHora.get(idAhorro)); 
	        System.out.println("Cantidad reservada: $" + cantReservada);
	        System.out.println();
			System.out.println("Presione enter para continuar");
			sc.nextLine();
	        idAhorro++;
	        return;
	        } catch (InputMismatchException exc1) {
	        	System.out.println("Ingrese los datos correctamente");
	        	sc.nextLine();
	        }
	    } 	// Mensaje temporal, hasta que se implemente la base de datos
			System.out.println("Ya ha definido un ahorro");
	}
	
	
	// Metodo para ingresar un monto a la cantidad ahorrada
	public void ingresarAhorros(Usuario usuario) {
		if (concepto.isEmpty()) {
			System.out.println("Defina un Ahorro para comenzar");
			return;
		}
		
		// Obtencion y formateo de la fecha
		LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaActual.format(formato);
        ultimoMovimiento.add(fechaFormateada);
        
        idMovimientoAhorro.add(idMovimiento);
        tipoMovimiento.add("Ingreso");
        
        System.out.println("Ingrese el valor a ingresar en ahorros:");
        int montoIngresoAhorro = sc.nextInt();
        if (usuario.DineroInicial()< montoIngresoAhorro) {
        	System.out.println("Transaccion cancelada:");
        	System.out.println("No se permite reservar mas dinero que el disponible en la cuenta");
        	sc.nextLine();
        	return; //Cancela el ingreso a ahorros
        }
        cantidad.add(montoIngresoAhorro);
        cantReservada += montoIngresoAhorro;
        usuario.setDineroInicial(usuario.DineroInicial() - montoIngresoAhorro);
        
        sc.nextLine(); 
        
        // Mostrar resumen del ingreso
        System.out.println("\tResumen del ingreso");
        System.out.println("Movimiento ID: " + idMovimientoAhorro.get(idMovimiento));
        System.out.println("Fecha: " + fechaFormateada);
        System.out.println("Cantidad ingresada: $" + cantidad.get(idMovimiento));
        System.out.println("Cantidad total reservada: $" + cantReservada);
        System.out.println();
		System.out.println("Presione enter para continuar");
		sc.nextLine();
        
		idMovimiento++;
	}
	
	
	// Metodo para retirar un monto de la cantidad ahorrada
	public void retirarAhorros(Usuario usuario) {
		if (concepto.isEmpty()) {
			System.out.println("Defina un Ahorro para comenzar");
			return;
		} 
		// Obtencion y formateo de la fecha
		LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaActual.format(formato);
        ultimoMovimiento.add(fechaFormateada);
        
        idMovimientoAhorro.add(idMovimiento);
        tipoMovimiento.add("Retiro");
        
        System.out.println("Ingrese el valor a retirar de ahorros:");
        int montoRetiroAhorro = sc.nextInt();
        if (montoRetiroAhorro > cantReservada) {
        	System.out.println("Transaccion cancelada:");
        	System.out.println("No se permite retirar mas dinero que el disponible en las reservas");
        	sc.nextLine();
        	return; //Cancela el retiro de ahorros
        }
        cantidad.add(montoRetiroAhorro);
        cantReservada -= montoRetiroAhorro;
        usuario.setDineroInicial(usuario.DineroInicial() + montoRetiroAhorro);
        sc.nextLine();
        
        // Mostrar resumen del retiro
        System.out.println("\tResumen del retiro");
        System.out.println("Movimiento ID: " + idMovimientoAhorro.get(idMovimiento));
        System.out.println("Fecha: " + fechaFormateada);
        System.out.println("Cantidad retirada: $" + cantidad.get(idMovimiento));
        System.out.println("Cantidad total reservada: $" + cantReservada);
        System.out.println();
		System.out.println("Presione enter para continuar");
		sc.nextLine();
		
		idMovimiento++;
	}

	
	//Getters
	public ArrayList<Integer> getIdMovimientoAhorro() {
		return idMovimientoAhorro;
	}
	public ArrayList<Integer> getCantidad() {
		return cantidad;
	}
	public ArrayList<String> getTipoMovimiento() {
		return tipoMovimiento;
	}
	public ArrayList<String> getUltimoMovimiento() {
		return ultimoMovimiento;
	}
	public ArrayList<String> getConcepto(){
		return concepto;
	}
}
