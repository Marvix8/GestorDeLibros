package constantes;

/**
 * Clase que administra los distintos mensajes a mostrar. <br>
 */
public enum Mensajes {
	/**
	 * Confirma al usuario si desea salir del sistema. <br>
	 */
	CONFIRMACION_SALIR("¿Desea salir? Se guardaran todos los cambios realizados."),
	/**
	 * Indica que los datos fueron guardados exitosamente. <br>
	 */
	CONFIRMACION_DATOS_GUARDADOS("Se han registrado los cambios exitosamente."),
	/**
	 * Mensaje a mostrar indicando que el libro no existe en la base de datos.
	 * <br>
	 */
	ERROR_BUSQUEDA("El libro buscado no existe en la base de datos."),
	/**
	 * Mensaje a mostrar cuando falle la carga inicial de la base de datos. <br>
	 */
	ERROR_BASE_DATOS("Ha ocurrido un error al cargar la base de datos: "),
	/**
	 * Mensaje para salir del sistema. <br>
	 */
	SALIR("Desea salir del sistema");
	/**
	 * Mensaje. <br>
	 */
	private String mensaje;

	/**
	 * Devuelve el mensaje a mostrar. <br>
	 * 
	 * @return Mensaje. <br>
	 */
	public String getMensaje() {
		return this.mensaje;
	}

	/**
	 * Crea un mensaje a mostrar. <br>
	 * 
	 * @param mensaje
	 *            Mensaje. <br>
	 */
	private Mensajes(String mensaje) {
		this.mensaje = mensaje;
	}
}
