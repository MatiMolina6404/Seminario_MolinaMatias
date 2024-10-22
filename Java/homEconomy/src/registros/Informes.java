package registros;

public class Informes {
	
	int idInforme;
	String fechaInicial;
	String fechaFinal;
	
	/* Metodo para solicitar informes de Ingresos
	 * A aplicar con consultas a la base de datos
	 */
	public void SolicitarInformeIngresos() {
		System.out.println("\tInforme de Ingresos");
		System.out.println("Fecha inicial: ");
		System.out.println("Fecha final: ");
		System.out.println();
	}
	
	/* Metodo para solicitar informes de Gastos
	 * A aplicar con consultas a la base de datos
	 */
	public void SolicitarInformeGastos() {
		System.out.println("\tInforme de Gastos");
		System.out.println("Fecha inicial: ");
		System.out.println("Fecha final: ");
		System.out.println();
	}
	
	/* Metodo para solicitar informes de Ahorros
	 * A aplicar con consultas a la base de datos
	 */
	public void SolicitarInformeAhorros() {
		System.out.println("\tInforme de Ahorros");
		System.out.println("Fecha inicial: ");
		System.out.println("Fecha final: ");
		System.out.println();
	}
}
