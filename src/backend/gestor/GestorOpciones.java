package gestor;

/**
 * Clase que administra las opciones del gestor. <br>
 */
public enum GestorOpciones {
	/**
	 * Da de alta un registro en la base de datos. <br>
	 */
	ALTA,
	/**
	 * Realiza una consulta en la base de datos. <br>
	 */
	CONSULTA,
	/**
	 * Actualiza un registro en la base de datos. <br>
	 */
	ACTUALIZAR,
	/**
	 * Da de baja un registro en la base de datos. <br>
	 */
	BAJA,
	/**
	 * Ordena los registros en la base de datos. <br>
	 */
	ORDENAR,
	/**
	 * Lista los registros en la base de datos. <br>
	 */
	LISTAR,
	/**
	 * Sale del gestor. <br>
	 */
	SALIR
}
