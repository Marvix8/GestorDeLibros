package validaciones;

import javax.swing.JTextField;

import constantes.Mensajes;

/**
 * Clase para controlar el ISBN. <br>
 */
public class ControlarISBN extends ControlarTextField {
	/**
	 * Crea un controlador del ISBN. <br>
	 * 
	 * @param jTextField
	 *            Textfield del ISBN. <br>
	 */
	public ControlarISBN(JTextField jTextField) {
		super(jTextField);
	}

	@Override
	public void controlarFormato() {
		String isbn = super.getjTextField().getText();
		// Si esta vacio es válido y no debe mostrar nada.
		if (isbn.length() == 0) {
			super.formatoValido = true;
			return;
		}
		if (isbn.length() != 10 && isbn.length() != 13) {
			super.formatoValido = false;
			super.mensajeErrorValidacion(Mensajes.ISBN_INVALIDO.getMensaje());
		} else {
			super.formatoValido = true;
		}
	}
}
