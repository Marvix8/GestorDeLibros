package excepciones;

/**
 * Clase que administra las excepciones de cuando la base de datos se encuentra
 * vacia. <br>
 */
public class BaseVaciaException extends Exception {
	private static final long serialVersionUID = 1L;

	public BaseVaciaException() {
		super();
	}

	public BaseVaciaException(String message) {
		super(message);
	}

	public BaseVaciaException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseVaciaException(Throwable cause) {
		super(cause);
	}
}
