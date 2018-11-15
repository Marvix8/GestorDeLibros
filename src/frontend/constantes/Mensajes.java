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
	 * Mensaje para indicar que los libros fueron ordenados correctamente. <br>
	 */
	LIBROS_ORDENADOS("Los libros han sido ordenados por ISBN correctamente."),
	/**
	 * Mensaje para indicar que un libro fue eliminado de la base de datos
	 * exitosamente. <br>
	 */
	REGISTRO_BORRADO("Registro borrado correctamente."),
	/**
	 * Mensaje para consultar si desea confirmar los cambios realizados. <br>
	 */
	CONFIRMAR_CAMBIOS("¿Desea confirmar los cambios realizados?"),
	/**
	 * Mensaje para consultar si desea descartar los cambios realizados. <br>
	 */
	DESCARTAR_CAMBIOS("¿Desea descartar los cambios realizados?"),
	/**
	 * Mensaje para indicar que no hubo cambios. <br>
	 */
	NINGUN_CAMBIO("No hay datos a actualizar."),
	/**
	 * Mensaje indicando que el libro ya existe en la base de datos. <br>
	 */
	LIBRO_YA_EXISTENTE("El libro ya existe."),
	/**
	 * Mensaje para indicar un error a la hora de guardar los datos en la base
	 * de datos. Debe ser seguido por el error que lo generó. <br>
	 */
	ERROR_DATOS_GUARDADOS("Ha ocurrido un error al guardar los datos: "),
	/**
	 * Mensaje para indicar que no hay ningún registro guardado en la base de
	 * datos. <br>
	 */
	BASE_VACIA("No hay registros en la base de datos."),
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
