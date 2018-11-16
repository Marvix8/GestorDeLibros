package validaciones;

import javax.swing.JTextField;

import constantes.Mensajes;

/**
 * Clase para controlar el título. <br>
 */
public class ControlarTitulo extends ControlarTextField {
	/**
	 * Tamaño mínimo del título. <br>
	 */
	private static final int MINIMO_TITULO = 1;
	/**
	 * Tamaño máximo del título. <br>
	 */
	private static final int MAXIMO_TITULO = 100;

	/**
	 * Crea un controlador del título. <br>
	 * 
	 * @param jTextField
	 *            Textfield del título. <br>
	 */
	public ControlarTitulo(JTextField jTextField) {
		super(jTextField);
	}

	@Override
	public void controlarFormato() {
		String titulo = super.getjTextField().getText();
		// Si esta vacio es válido y no debe mostrar nada.
		if (titulo.length() == 0) {
			super.formatoValido = true;
			return;
		}
		if (titulo.length() < MINIMO_TITULO || titulo.length() > MAXIMO_TITULO) {
			super.formatoValido = false;
			super.mensajeErrorValidacion(Mensajes.TITULO_INVALIDO.getMensaje());
		} else {
			super.formatoValido = true;
		}
	}
}
