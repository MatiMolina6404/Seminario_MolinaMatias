package ahorros;

public class MovimientosAhorro {

	// Metodo para obtener y mostrar los movimientos de la clase ahorro
	public void movimientosAhorros(Ahorros ahorros) {
		if (ahorros.getIdMovimientoAhorro().isEmpty()) {
            System.out.println("No hay movimientos de Ahorro registrados.");
        } else {
            System.out.println("Movimientos de Ahorros Registrados");
            for (int i = 0; i < ahorros.getIdMovimientoAhorro().size(); i++) {
                System.out.println();
                System.out.println("Movimiento Ahorro ID: " + ahorros.getIdMovimientoAhorro().get(i));
                System.out.println("Fecha: " + ahorros.getUltimoMovimiento().get(i));
                System.out.println("Valor: $" + ahorros.getCantidad().get(i));
                System.out.println("Tipo Movimiento: " + ahorros.getTipoMovimiento().get(i));
                System.out.println("-------------------------------");
            }
        }
    }	
}
