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
	 * Código de ISBN. <br>
	 */
	ISBN("ISBN:"),
	/**
	 * Título del libro. <br>
	 */
	TITULO("Titulo:"),
	/**
	 * Autor del libro. <br>
	 */
	AUTOR("Autor:"),
	/**
	 * Editorial del libro. <br>
	 */
	EDITORIAL("Editorial:"),
	/**
	 * Edición del libro. <br>
	 */
	EDICION("Edición:"),
	/**
	 * Año de publicación del libro. <br>
	 */
	ANO_PUBLICACION("Año publicación:"),
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
	/**
	 * Label del mensaje. <br>
	 */
	private String label;

	/**
	 * Devuelve el label con el mensaje. <br>
	 * 
	 * @return Label. <br>
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Crea un label. <br>
	 * 
	 * @param label
	 *            Label. <br>
	 */
	private Labels(String label) {
		this.label = label;
	}
}
