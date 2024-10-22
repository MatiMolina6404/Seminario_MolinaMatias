package registros;

import usuario.Familia;

public class Movimientos {
	
	// Metodo para obtener y mostrar los movimientos de la clase ingresos
    public void movimientosIngresos(Ingresos ingresos, Categorias categorias, Familia familia) {
        if (ingresos.getIdIngreso().isEmpty()) {
            System.out.println("No hay ingresos registrados.");
        } else {
            System.out.println("Ingresos Registrados");
            for (int i = 0; i < ingresos.getIdIngreso().size(); i++) {
                System.out.println();
                System.out.println("Ingreso ID: " + ingresos.getIdIngreso().get(i));
                System.out.println("Fecha: " + ingresos.getFechaHora().get(i));
                System.out.println("Valor: " + ingresos.getValor().get(i));
                System.out.println("Categoria: (" + ingresos.getCategoriaIng().get(i) + ") " + ingresos.obtenerNombreCat(ingresos.getCategoriaIng().get(i), categorias));
                System.out.println("Comentario: " + ingresos.getComentario().get(i));
                System.out.println("Familiar: (" + ingresos.getFamiliarIng().get(i) + ") " + ingresos.obtenerNombreFam(ingresos.getFamiliarIng().get(i), familia));
                System.out.println("Comprobante: " + ingresos.getComprobante().get(i));
                System.out.println("-------------------------------");
            }
        }
    }

    
    // Metodo para obtener y mostrar los movimientos de la clase gastos
	public void movimientosGastos(Gastos gastos, Categorias categorias, Familia familia) {
		if (gastos.getIdGasto().isEmpty()) {
            System.out.println("No hay Gastos registrados.");
        } else {
            System.out.println("Gastos Registrados");
            for (int i = 0; i < gastos.getIdGasto().size(); i++) {
                System.out.println();
                System.out.println("Gasto ID: " + gastos.getIdGasto().get(i));
                System.out.println("Fecha: " + gastos.getFechaHora().get(i));
                System.out.println("Valor: " + gastos.getValor().get(i));
                System.out.println("Categoria: (" + gastos.getCategoriaGasto().get(i) + ") " + gastos.obtenerNombreCat(gastos.getCategoriaGasto().get(i), categorias));
                System.out.println("Comentario: " + gastos.getComentario().get(i));
                System.out.println("Familiar: (" + gastos.getFamiliarGasto().get(i) + ") " + gastos.obtenerNombreFam(gastos.getFamiliarGasto().get(i), familia));
                System.out.println("Comprobante: " + gastos.getComprobante().get(i));
                System.out.println("-------------------------------");
            }
        }
    }	
	
}
