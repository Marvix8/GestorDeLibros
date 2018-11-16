package validaciones;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import constantes.Titulos;
import ventana.VentanaMensaje;

/**
 * Clase que administra el control de los textfields. <br>
 */
public abstract class ControlarTextField implements DocumentListener {
	/**
	 * Textfield a controlar. <br>
	 */
	private JTextField jTextField;
	/**
	 * Indica si el formato del textfield es válido. <br>
	 */
	protected boolean formatoValido = true;
	/**
	 * Borde si el dato se encuentra cargado correctamente.
	 */
	private Border borderActual;
	/**
	 * Indica el borde del textfield en caso de faltar un dato. <br>
	 */
	private static final Border DATO_FALTANTE = BorderFactory.createLineBorder(Color.RED, 1);
	/**
	 * Indica el borde del textfield en caso de que el formato del dato no sea
	 * válido. <br>
	 */
	private static final Border DATO_INVALIDO = BorderFactory.createLineBorder(Color.YELLOW, 1);

	/**
	 * Crea un controlador del textfield. <br>
	 * 
	 * @param jTextField
	 *            Textfield a controlar. <br>
	 */
	public ControlarTextField(final JTextField jTextField) {
		this.jTextField = jTextField;
		// Siempre tenemos el formato del borde actual.
		this.borderActual = jTextField.getBorder();
		this.controlarFormato();
		// Indicamos que los campos deben ser completados.
		this.controlarTextfield();
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		this.controlarFormato();
		this.controlarTextfield();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		this.controlarFormato();
		this.controlarTextfield();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		this.controlarFormato();
		this.controlarTextfield();
	}

	/**
	 * Muestra el campo según la información cargada. <br>
	 */
	private void controlarTextfield() {
		if (this.jTextField.getText().equals("")) {
			this.jTextField.setBorder(DATO_FALTANTE);
		} else {
			if (!this.formatoValido) {
				this.jTextField.setBorder(DATO_INVALIDO);
			} else {
				this.jTextField.setBorder(this.borderActual);
			}
		}
	}

	/**
	 * Carga una ventana para indicar un error de validación en el campo
	 * ingresado/modificado. <br>
	 * 
	 * @param mensaje
	 *            Mensaje a mostrar. <br>
	 */
	void mensajeErrorValidacion(final String mensaje) {
		VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.ERROR_VALIDACION.getTitulo(), mensaje,
				Toolkit.getDefaultToolkit()
						.getImage(VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		ventanaMensaje.setVisible(true);
	}

	/**
	 * Indica si el formato del textfield es válido. <br>
	 * 
	 * @return <b>true</b> si es válido. <br>
	 *         <b>false</b> si no es válido. <br>
	 */
	public boolean isFormatoValido() {
		return this.formatoValido;
	}

	/**
	 * Devuelve el textfield. <br>
	 * 
	 * @return Textfield. <br>
	 */
	JTextField getjTextField() {
		return this.jTextField;
	}

	/**
	 * Controla el formato del textfield. <br>
	 */
	public abstract void controlarFormato();
}
