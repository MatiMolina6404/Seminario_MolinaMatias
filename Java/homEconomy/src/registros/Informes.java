package registros;

import java.sql.Connection;
import java.sql.SQLException;

import conexionBD.GastosBD;
import conexionBD.IngresosBD;
import usuario.Usuario;

public class Informes {

	int idInforme;
	String fechaInicial;
	String fechaFinal;
	IngresosBD ingBD = new IngresosBD();
	GastosBD gasBD = new GastosBD();

	// Metodo para solicitar informes de Ingresos
	public void SolicitarInformeIngresos(Connection conexion) {
		System.out.println("\tInforme de Ingresos");
		System.out.println();
		try {
			ingBD.mostrarIngresos(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Metodo para solicitar informes de Gastos
	public void SolicitarInformeGastos(Connection conexion, Usuario usu) {
		System.out.println("\tInforme de Gastos");
		System.out.println();
		try {
			gasBD.mostrarGastos(conexion, usu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
