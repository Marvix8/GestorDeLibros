package validaciones;

import javax.swing.JTextField;

import constantes.Mensajes;

/**
 * Clase para controlar la editorial. <br>
 */
public class ControlarEditorial extends ControlarTextField {
	/**
	 * Tamaño mínimo de la editorial. <br>
	 */
	private static final int MINIMO_EDITORIAL = 1;
	/**
	 * Tamaño máximo de la editorial. <br>
	 */
	private static final int MAXIMO_EDITORIAL = 50;

	/**
	 * Crea un controlador de la editorial. <br>
	 * 
	 * @param jTextField
	 *            Textfield de la editorial. <br>
	 */
	public ControlarEditorial(JTextField jTextField) {
		super(jTextField);
	}

	@Override
	public void controlarFormato() {
		String editorial = super.getjTextField().getText();
		// Si esta vacio es válido y no debe mostrar nada.
		if (editorial.length() == 0) {
			super.formatoValido = true;
			return;
		}
		if (editorial.length() == 0 || editorial.length() < MINIMO_EDITORIAL || editorial.length() > MAXIMO_EDITORIAL) {
			super.formatoValido = false;
			super.mensajeErrorValidacion(Mensajes.EDITORIAL_INVALIDO.getMensaje());
		} else {
			super.formatoValido = true;
		}
	}

}
