package excepciones;

/**
 * Clase que administra las excepciones de cuando el dato solicitado no existe.
 * <br>
 */
public class DatoInexistenteException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatoInexistenteException() {
		super();
	}

	public DatoInexistenteException(String message) {
		super(message);
	}

	public DatoInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatoInexistenteException(Throwable cause) {
		super(cause);
	}
}
