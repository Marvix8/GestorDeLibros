package validaciones;

import javax.swing.JTextField;

import constantes.Mensajes;

/**
 * Clase para controlar el autor. <br>
 */
public class ControlarAutor extends ControlarTextField {
	/**
	 * Tamaño mínimo del autor. <br>
	 */
	private static final int MINIMO_AUTOR = 1;
	/**
	 * Tamaño máximo del autor. <br>
	 */
	private static final int MAXIMO_AUTOR = 50;

	/**
	 * Crea un controlador del autor. <br>
	 * 
	 * @param jTextField
	 *            Textfield del autor. <br>
	 */
	public ControlarAutor(JTextField jTextField) {
		super(jTextField);
	}

	@Override
	public void controlarFormato() {
		String autor = super.getjTextField().getText();
		// Si esta vacio es válido y no debe mostrar nada.
		if (autor.length() == 0) {
			super.formatoValido = true;
			return;
		}
		if (autor.length() < MINIMO_AUTOR || autor.length() > MAXIMO_AUTOR) {
			super.formatoValido = false;
			super.mensajeErrorValidacion(Mensajes.AUTOR_INVALIDO.getMensaje());
		} else {
			super.formatoValido = true;
		}
	}

}
