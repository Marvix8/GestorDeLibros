package validaciones;

import javax.swing.JTextField;

import constantes.Mensajes;

/**
 * Clase para controlar el año de publicación. <br>
 */
public class ControlarAnoPublicacion extends ControlarTextField {
	/**
	 * Año de publicación mínimo. <br>
	 */
	private static final int MINIMO_ANO_PUBLICACION = 1;
	/**
	 * Año de publicación máximo. <br>
	 */
	private static final int MAXIMO_ANO_PUBLICACION = 2147483647;

	/**
	 * Crea un controlador del año de publicación. <br>
	 * 
	 * @param jTextField
	 *            Textfield del año de publicación. <br>
	 */
	public ControlarAnoPublicacion(JTextField jTextField) {
		super(jTextField);
	}

	@Override
	public void controlarFormato() {
		// Si esta vacio es válido y no debe mostrar nada.
		if (super.getjTextField().getText().length() == 0) {
			super.formatoValido = true;
			return;
		}
		int anoPublicacion = Integer.parseInt(super.getjTextField().getText());
		if (anoPublicacion < MINIMO_ANO_PUBLICACION || anoPublicacion > MAXIMO_ANO_PUBLICACION) {
			super.formatoValido = false;
			super.mensajeErrorValidacion(Mensajes.ANO_PUBLICACION_INVALIDO.getMensaje());
		} else {
			super.formatoValido = true;
		}
	}

}
