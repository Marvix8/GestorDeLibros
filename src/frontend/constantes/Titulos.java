package constantes;

/**
 * Clase que administra los distintos títulos de las ventanas. <br>
 */
public enum Titulos {
	/**
	 * Título principal. <br>
	 */
	PRINCIPAL("Gestor de Libros"),
	/**
	 * Título para la lista de libros. <br>
	 */
	LIBROS("Libros"),
	/**
	 * Título sobre la información del libro. <br>
	 */
	INFORMACION_LIBRO("Información del libro"),
	/**
	 * Título para indicar que el libro buscado no existe en la base de datos.
	 * <br>
	 */
	ISBN_INEXISTENTE("Error en búsqueda del ISBN "),
	/**
	 * Título para indicar que se debe tomar una decisión. <br>
	 */
	DECISION("Decisión"),
	/**
	 * Título para buscar el ISBN. <br>
	 */
	BUSCAR_ISBN("Buscar libro (ISBN)"),
	/**
	 * Título para salir del sistema. <br>
	 */
	SALIR("Salir"),
	/**
	 * Mensaje de éxito. <br>
	 */
	EXITO("Exito"),
	/**
	 * Titulo que indica que no hay datos en la base de datos. <br>
	 */
	BASE_DATOS_VACIA("No hay registros"),
	/**
	 * Titulo que indica un error de base de datos. <br>
	 */
	ERROR_BASE_DATOS("Error - Base de Datos");

	/**
	 * Titulo de la ventana. <br>
	 */
	private String titulo;

	/**
	 * Devuelve el título. <br>
	 * 
	 * @return Título. <br>
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Crea un título. <br>
	 * 
	 * @param titulo
	 *            Título. <br>
	 */
	private Titulos(String titulo) {
		this.titulo = titulo;
	}
}
