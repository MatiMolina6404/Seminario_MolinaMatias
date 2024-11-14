package ahorros;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import conexionBD.AhorrosBD;
import conexionBD.UsuarioBD;
import usuario.Usuario;

public class Ahorros {
	Scanner sc = new Scanner(System.in);
	int idMovimientoAhorro;
	String concepto;
	String tipoMovimiento;
	String ultimoMovimiento;
	int cantidad;
	int montoAhorro;
	int idMovimiento;
	int cantReservada;
	int idAhorro;

	// Obtencion y formateo de la fecha
	LocalDateTime fechaActual = LocalDateTime.now();
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String fechaHora = fechaActual.format(formato);

	// Metodo para definir el apartado de ahorro
	public void definirAhorros(Usuario usuario, Connection conexion, AhorrosBD ahorrosBD) throws SQLException {
		// Intenta obtener un ahorro ya definido para el usuario
		Ahorros ahorroExistente = ahorrosBD.obtenerAhorro(usuario.getIdUsuario(), conexion);

		if (ahorroExistente.Concepto() != null) {
			System.out.println("Ya existe un ahorro definido con el concepto: " + ahorroExistente.Concepto());
			System.out.println("Monto reservado: $" + ahorroExistente.CantReservada());
			return;
		}

		System.out.println("Ingrese el concepto del ahorro a reservar");
		this.concepto = sc.nextLine();
		System.out.println("Ingrese el valor a reservar:");
		this.montoAhorro = sc.nextInt();

		if (usuario.DineroInicial() < montoAhorro) {
			System.out.println("Transaccion cancelada: No se permite reservar mas dinero del disponible.");
			sc.nextLine();
			return;
		}

		this.cantReservada = montoAhorro; // Asigna el monto reservado
		usuario.setDineroInicial(usuario.DineroInicial() - montoAhorro);

		// Resumen del ahorro
		System.out.println("\tResumen de Ahorro");
		System.out.println("Concepto: " + this.concepto);
		System.out.println("Fecha: " + fechaHora);
		System.out.println("Cantidad reservada: $" + this.cantReservada);
		sc.nextLine();

		ahorrosBD.insertarAhorro(this, conexion, usuario.getIdUsuario());
	}

	// Metodo para ingresar un monto a la cantidad reservada
	public void ingresarAhorros(Usuario usuario, Connection conexion, AhorrosBD ahorrosBD) throws SQLException {
		// Obtener el ahorro desde la base de datos
		Ahorros ahorroExistente = ahorrosBD.obtenerAhorro(usuario.getIdUsuario(), conexion);

		// Verificar si existe un concepto en la base de datos
		if (ahorroExistente.Concepto() == null) {
			System.out.println("Defina un Ahorro para comenzar");
			return;
		}

		// Asignar el idAhorro obtenido
		this.idAhorro = ahorroExistente.getIdAhorro();

		tipoMovimiento = "Ingreso";

		System.out.println("Ingrese el valor a ingresar en ahorros:");
		int montoIngresoAhorro = sc.nextInt();
		if (usuario.DineroInicial() < montoIngresoAhorro) {
			System.out.println("Transaccion cancelada:");
			System.out.println("No se permite reservar mas dinero que el disponible en la cuenta");
			sc.nextLine();
			return; // Cancela el ingreso a ahorros
		}
		if (montoIngresoAhorro < 0) {
			System.out.println("Transaccion cancelada:");
			System.out.println("No se permiten numeros negativos");
			sc.nextLine();
			return; // Cancela el ingreso a ahorros
		}

		// Actualizar los valores de cantidad y cantReservada
		cantidad = montoIngresoAhorro;
		cantReservada = ahorroExistente.CantReservada() + montoIngresoAhorro;
		usuario.setDineroInicial(usuario.DineroInicial() - montoIngresoAhorro);
		sc.nextLine();

		// Mostrar resumen del ingreso
		System.out.println("\tResumen del ingreso");
		System.out.println("Concepto: " + ahorroExistente.Concepto());
		System.out.println("Movimiento ID: " + this.idMovimiento);
		System.out.println("Fecha: " + fechaHora);
		System.out.println("Cantidad ingresada: $" + cantidad);
		System.out.println("Cantidad total reservada: $" + cantReservada);

		// Insertar el movimiento en la base de datos
		ahorrosBD.insertarMovimientoAhorro(this, conexion, usuario.getIdUsuario(), idAhorro);
		ahorrosBD.actualizarCantidadAhorro(usuario.getIdUsuario(), cantReservada, conexion);

	}

	public void retirarAhorros(Usuario usuario, Connection conexion, AhorrosBD ahorrosBD, UsuarioBD usuarioBD) throws SQLException {
		// Obtener el ahorro desde la base de datos
		Ahorros ahorroExistente = ahorrosBD.obtenerAhorro(usuario.getIdUsuario(), conexion);

		// Verificar si existe un concepto en la base de datos
		if (ahorroExistente == null || ahorroExistente.Concepto() == null) {
			System.out.println("Defina un Ahorro para comenzar");
			return;
		}

		// Asignar la cantidad reservada obtenida
		this.cantReservada = ahorroExistente.CantReservada();

		// Asignar el idAhorro obtenido
		this.idAhorro = ahorroExistente.getIdAhorro();
		
		tipoMovimiento = "Retiro";

		System.out.println("Ingrese el valor a retirar de los ahorros:");
		int montoRetiroAhorro = sc.nextInt();

		if (montoRetiroAhorro > cantReservada) {
			System.out.println("Transaccion cancelada:");
			System.out.println("No se permite retirar mas dinero que el reservado");
			sc.nextLine();
			sc.nextLine();
			return;
		}
		if (montoRetiroAhorro < 0) {
			System.out.println("Transaccion cancelada: No se permiten numeros negativos");
			sc.nextLine();
			return;
		}

		// Actualizar los valores de cantidad y cantReservada
		cantidad = montoRetiroAhorro;
		cantReservada = ahorroExistente.CantReservada() - montoRetiroAhorro;
		usuario.setDineroInicial(usuario.DineroInicial() + montoRetiroAhorro);
		sc.nextLine();

		// Mostrar resumen del retiro
		System.out.println("\tResumen del retiro");
		System.out.println("Concepto: " + ahorroExistente.Concepto());
		System.out.println("Movimiento ID: " + this.idMovimiento);
		System.out.println("Fecha: " + fechaHora);
		System.out.println("Cantidad retirada: $" + cantidad);
		System.out.println("Cantidad total reservada: $" + cantReservada);

		// Insertar el movimiento en la base de datos y actualizar el ahorro
		ahorrosBD.insertarMovimientoAhorro(this, conexion, usuario.getIdUsuario(), idAhorro);
		ahorrosBD.actualizarCantidadAhorro(usuario.getIdUsuario(), cantReservada, conexion);
		usuarioBD.actualizarBalance(conexion, usuario.getIdUsuario(), usuario.DineroInicial());
	}

	// Getters y Setters
	public int Cantidad() {
		return cantidad;
	}

	public int getIdAhorro() {
		return idAhorro;
	}

	public int CantReservada() {
		return cantReservada;
	}

	public String TipoMovimiento() {
		return tipoMovimiento;
	}

	public String FechaHora() {
		return fechaHora;
	}

	public String Concepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public void setCantReservada(int cantReservada) {
		this.cantReservada = cantReservada;
	}

	public void setIdAhorro(int idAhorro) {
		this.idAhorro = idAhorro;
	}

	public void setidMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

}
