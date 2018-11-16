package validaciones;

import javax.swing.JTextField;

import constantes.Mensajes;

/**
 * Clase para controlar la edición. <br>
 */
public class ControlarEdicion extends ControlarTextField {
	/**
	 * Edición mínima. <br>
	 */
	private static final int MINIMA_EDICION = 1;
	/**
	 * Edición máxima. <br>
	 */
	private static final int MAXIMA_EDICION = 2147483647;

	/**
	 * Crea un controlador de la edición. <br>
	 * 
	 * @param jTextField
	 *            Textfield de la edición. <br>
	 */
	public ControlarEdicion(JTextField jTextField) {
		super(jTextField);
	}

	@Override
	public void controlarFormato() {
		// Si esta vacio es válido y no debe mostrar nada.
		if (super.getjTextField().getText().length() == 0) {
			super.formatoValido = true;
			return;
		}
		int edicion = Integer.parseInt(super.getjTextField().getText());
		if (edicion < MINIMA_EDICION || edicion > MAXIMA_EDICION) {
			super.formatoValido = false;
			super.mensajeErrorValidacion(Mensajes.EDICION_INVALIDO.getMensaje());
		} else {
			super.formatoValido = true;
		}
	}

}
