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
	 * Título para salir del sistema. <br>
	 */
	SALIR("Salir"),
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
