package constantes;

/**
 * Clase que administra distintos labels. <br>
 */
public enum Labels {
	/**
	 * Se da de alta un libro. <br>
	 */
	ALTAS("Altas"),
	/**
	 * Se consulta un libro en la base de datos. <br>
	 */
	CONSULTAS("Consultas"),
	/**
	 * Se actualizan datos de un libro. <br>
	 */
	ACTUALIZACIONES("Actualizaciones"),
	/**
	 * Se da de baja un libro en la base. <br>
	 */
	BAJAS("Bajas"),
	/**
	 * Se ordenan los libros en la base. <br>
	 */
	ORDENAR_REGISTROS("Ordenar registros"),
	/**
	 * Se muestran los libros en base. <br>
	 */
	LISTAR_REGSTROS("Listar registros"),
	/**
	 * Sale del gestor. <br>
	 */
	SALIR("Salir"),
	/**
	 * Label de buscar. <br>
	 */
	BUSCAR("Buscar"),
	/**
	 * 
	 */
	OK("Ok"),
	/**
	 * 
	 */
	SI("Si"),
	/**
	 * 
	 */
	NO("No"),
	/**
	 * 
	 */
	CANCELAR("Cancelar");

	private String label;

	public String getLabel() {
		return this.label;
	}

	private Labels(String label) {
		this.label = label;
	}
}
