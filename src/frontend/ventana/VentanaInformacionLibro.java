package ventana;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constantes.Labels;
import constantes.Mensajes;
import constantes.Titulos;
import gestor.Libro;
import validaciones.ControlarAnoPublicacion;
import validaciones.ControlarAutor;
import validaciones.ControlarEdicion;
import validaciones.ControlarEditorial;
import validaciones.ControlarISBN;
import validaciones.ControlarTitulo;

/**
 * Clase que muestra la ventana para modificar información sobre un libro.
 * <p>
 * <i><b>Nota</b>: Si la información sobre el libro no está completa no permite
 * aceptar la modificación sobre el mismo. </i> <br>
 */
public class VentanaInformacionLibro extends JDialog {
	/**
	 * Indica si el proceso de alta/modificación fue cancelado.
	 */
	private boolean cancelado;
	/**
	 * Información original del libro. <br>
	 */
	private Libro libro;
	/**
	 * Textfield del ISBN actual. <br>
	 */
	private JTextField tfISBN;
	/**
	 * Textfield del título actual. <br>
	 */
	private JTextField tfTitulo;
	/**
	 * Textfield del autor actual. <br>
	 */
	private JTextField tfAutor;
	/**
	 * Textfield de la edición actual. <br>
	 */
	private JTextField tfEdicion;
	/**
	 * Textfield del año de la publicación. <br>
	 */
	private JTextField tfAnoPublicacion;
	/**
	 * Textfield de la editorial actual. <br>
	 */
	private JTextField tfEditorial;
	private static final long serialVersionUID = 1L;
	private final JPanel panelDatosLibros = new JPanel();
	/**
	 * Controlador del ISBN. <br>
	 */
	private ControlarISBN controlarISBN;
	/**
	 * Controlador del título. <br>
	 */
	private ControlarTitulo controlarTitulo;
	/**
	 * Controlador del autor. <br>
	 */
	private ControlarAutor controlarAutor;
	/**
	 * Controlador de la edición. <br>
	 */
	private ControlarEdicion controlarEdicion;
	/**
	 * Controlador de la editorial. <br>
	 */
	private ControlarEditorial controlarEditorial;
	/**
	 * Controlador del año de publicación. <br>
	 */
	private ControlarAnoPublicacion controlarAnoPublicacion;

	/**
	 * Carga la información de un libro.
	 * 
	 * @param libro
	 *            Libro actual. <br>
	 */
	public VentanaInformacionLibro(Libro libro) {
		setModal(true);
		this.libro = libro;
		setTitle(Titulos.INFORMACION_LIBRO.getTitulo());
		setBounds(100, 100, 450, 273);
		getContentPane().setLayout(new BorderLayout());
		panelDatosLibros.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelDatosLibros, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatosLibros = new GridBagLayout();
		gbl_panelDatosLibros.columnWidths = new int[] { 0, 0 };
		gbl_panelDatosLibros.rowHeights = new int[] { 0 };
		gbl_panelDatosLibros.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelDatosLibros.rowWeights = new double[] { 0.0 };
		panelDatosLibros.setLayout(gbl_panelDatosLibros);
		JLabel lbISBNDisplay = new JLabel(Labels.ISBN.getLabel());
		GridBagConstraints gbc_lbISBNDisplay = new GridBagConstraints();
		gbc_lbISBNDisplay.anchor = GridBagConstraints.EAST;
		gbc_lbISBNDisplay.fill = GridBagConstraints.VERTICAL;
		gbc_lbISBNDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lbISBNDisplay.gridx = 0;
		gbc_lbISBNDisplay.gridy = 0;
		panelDatosLibros.add(lbISBNDisplay, gbc_lbISBNDisplay);

		this.tfISBN = new JTextField(libro.getISBN());
		this.controlarISBN = new ControlarISBN(this.tfISBN);
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblISBN.insets = new Insets(0, 0, 5, 0);
		gbc_lblISBN.gridx = 1;
		gbc_lblISBN.gridy = 0;
		this.tfISBN.getDocument().addDocumentListener(this.controlarISBN);
		panelDatosLibros.add(tfISBN, gbc_lblISBN);

		JLabel lblTituloDisplay = new JLabel(Labels.TITULO.getLabel());
		GridBagConstraints gbc_lblTituloDisplay = new GridBagConstraints();
		gbc_lblTituloDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblTituloDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloDisplay.gridx = 0;
		gbc_lblTituloDisplay.gridy = 1;
		panelDatosLibros.add(lblTituloDisplay, gbc_lblTituloDisplay);

		this.tfTitulo = new JTextField(libro.getTitulo());
		this.controlarTitulo = new ControlarTitulo(this.tfTitulo);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		this.tfTitulo.getDocument().addDocumentListener(this.controlarTitulo);
		panelDatosLibros.add(tfTitulo, gbc_lblTitulo);

		JLabel lblAutorDisplay = new JLabel(Labels.AUTOR.getLabel());
		GridBagConstraints gbc_lblAutorDisplay = new GridBagConstraints();
		gbc_lblAutorDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblAutorDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutorDisplay.gridx = 0;
		gbc_lblAutorDisplay.gridy = 2;
		panelDatosLibros.add(lblAutorDisplay, gbc_lblAutorDisplay);

		this.tfAutor = new JTextField(libro.getAutor());
		this.controlarAutor = new ControlarAutor(this.tfAutor);
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 0);
		gbc_lblAutor.gridx = 1;
		gbc_lblAutor.gridy = 2;
		this.tfAutor.getDocument().addDocumentListener(this.controlarAutor);
		panelDatosLibros.add(tfAutor, gbc_lblAutor);

		JLabel lblEdicionDisplay = new JLabel(Labels.EDICION.getLabel());
		GridBagConstraints gbc_lblEdicionDisplay = new GridBagConstraints();
		gbc_lblEdicionDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblEdicionDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicionDisplay.gridx = 0;
		gbc_lblEdicionDisplay.gridy = 3;
		panelDatosLibros.add(lblEdicionDisplay, gbc_lblEdicionDisplay);

		this.tfEdicion = new JTextField(String.valueOf(libro.getEdicion()));
		this.controlarEdicion = new ControlarEdicion(this.tfEdicion);
		GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
		gbc_lblEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEdicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdicion.gridx = 1;
		gbc_lblEdicion.gridy = 3;
		this.tfEdicion.getDocument().addDocumentListener(this.controlarEdicion);
		panelDatosLibros.add(tfEdicion, gbc_lblEdicion);

		JLabel lblAnoPublicacionDisplay = new JLabel();
		lblAnoPublicacionDisplay.setText(Labels.ANO_PUBLICACION.getLabel());
		GridBagConstraints gbc_lblAnoPublicacionDisplay = new GridBagConstraints();
		gbc_lblAnoPublicacionDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblAnoPublicacionDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnoPublicacionDisplay.gridx = 0;
		gbc_lblAnoPublicacionDisplay.gridy = 4;
		panelDatosLibros.add(lblAnoPublicacionDisplay, gbc_lblAnoPublicacionDisplay);

		this.tfAnoPublicacion = new JTextField(String.valueOf(libro.getAnioPublicacion()));
		this.controlarAnoPublicacion = new ControlarAnoPublicacion(this.tfAnoPublicacion);
		GridBagConstraints gbc_lblAnoEdicion = new GridBagConstraints();
		gbc_lblAnoEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAnoEdicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnoEdicion.gridx = 1;
		gbc_lblAnoEdicion.gridy = 4;
		this.tfAnoPublicacion.getDocument().addDocumentListener(this.controlarAnoPublicacion);
		panelDatosLibros.add(tfAnoPublicacion, gbc_lblAnoEdicion);

		JLabel lblEditorialDisplay = new JLabel(Labels.EDITORIAL.getLabel());
		GridBagConstraints gbc_lblEditorialDisplay = new GridBagConstraints();
		gbc_lblEditorialDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_lblEditorialDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblEditorialDisplay.gridx = 0;
		gbc_lblEditorialDisplay.gridy = 5;
		panelDatosLibros.add(lblEditorialDisplay, gbc_lblEditorialDisplay);

		this.tfEditorial = new JTextField(libro.getEditorial());
		this.controlarEditorial = new ControlarEditorial(this.tfEditorial);
		GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
		gbc_lblEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEditorial.gridx = 1;
		gbc_lblEditorial.gridy = 5;
		this.tfEditorial.getDocument().addDocumentListener(this.controlarEditorial);
		panelDatosLibros.add(tfEditorial, gbc_lblEditorial);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton(Labels.OK.getLabel());
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarLibro();
					}
				});
				btnOk.setHorizontalTextPosition(SwingConstants.CENTER);
				btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancelar = new JButton(Labels.CANCELAR.getLabel());
				btnCancelar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						descartarCambios();
					}

				});
				btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
				btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonPane.add(btnCancelar);

			}
		}
	}

	/**
	 * Guarda la información administrada. <br>
	 */
	private void guardarLibro() {
		// Controlamos que los datos esten cargados correctamente.
		if (!this.controlarCamposNoVacios()) {
			VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.ERROR_VALIDACION.getTitulo(),
					Mensajes.ERROR_VACIO.getMensaje(), null);
			ventanaMensaje.setVisible(true);
			return;
		}
		if (!this.controlarCamposValidos()) {
			VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.ERROR_VALIDACION.getTitulo(),
					Mensajes.ERROR_VALIDACION.getMensaje(), null);
			ventanaMensaje.setVisible(true);
			return;
		}
		this.cancelado = false;
		// Si se realizaron cambios se pregunta si desea
		// guardarlos.
		if (controlarCambios()) {
			VentanaConfirmacion ventanaConfirmacion = new VentanaConfirmacion(Titulos.DECISION.getTitulo(),
					Mensajes.CONFIRMAR_CAMBIOS.getMensaje());
			ventanaConfirmacion.setVisible(true);
			if (ventanaConfirmacion.getDecision()) {
				VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.EXITO.getTitulo(),
						Mensajes.CONFIRMACION_DATOS_GUARDADOS.getMensaje(), null);
				ventanaMensaje.setVisible(true);
				this.asignarValoresFinales();
			} else {
				return;
			}
		} else {
			VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.EXITO.getTitulo(),
					Mensajes.NINGUN_CAMBIO.getMensaje(), null);
			ventanaMensaje.setVisible(true);
		}
		dispose();
	}

	/**
	 * Descarta los cambios realizados. <br>
	 */
	private void descartarCambios() {
		this.cancelado = true;
		// Si no se realizó ningún cambio puedo salir.
		if (!this.controlarCambios()) {
			setVisible(false);
		} else {
			// Si quedó algo pendiente de cambio consulto.
			if (this.controlarCamposNoVacios()) {
				VentanaConfirmacion ventanaConfirmacion = new VentanaConfirmacion(Titulos.DECISION.getTitulo(),
						Mensajes.DESCARTAR_CAMBIOS.getMensaje());
				ventanaConfirmacion.setVisible(true);
				if (ventanaConfirmacion.getDecision()) {
					setVisible(false);
				} else {
					return;
				}
			}
		}
	}

	/**
	 * Controla los cambios realizados. <br>
	 * 
	 * @return <b>true</b> si se realizó un cambio. <br>
	 *         <b>false</b> de lo contrario. <br>
	 */
	private boolean controlarCambios() {
		if (!this.tfISBN.getText().equals(this.libro.getISBN())) {
			return true;
		}
		if (!this.tfTitulo.getText().equals(this.libro.getTitulo())) {
			return true;
		}
		if (!this.tfAutor.getText().equals(this.libro.getAutor())) {
			return true;
		}
		if (!this.tfEdicion.getText().equals(String.valueOf(this.libro.getEdicion()))) {
			return true;
		}
		if (!this.tfEditorial.getText().equals(this.libro.getEditorial())) {
			return true;
		}
		if (!this.tfAnoPublicacion.getText().equals(String.valueOf(this.libro.getAnioPublicacion()))) {
			return true;
		}
		return false;
	}

	/**
	 * Guarda los datos del libro.
	 * <p>
	 * <i><b>Nota</b>: Como no puede distinguir qué cambios hubo, guarda todo.
	 * </i><br>
	 */
	private void asignarValoresFinales() {
		this.libro.setISBN(this.tfISBN.getText());
		this.libro.setAutor(this.tfAutor.getText());
		this.libro.setTitulo(this.tfTitulo.getText());
		this.libro.setEdicion(Integer.parseInt(this.tfEdicion.getText()));
		this.libro.setEditorial(this.tfEditorial.getText());
		this.libro.setAnioPublicacion(Integer.parseInt(this.tfAnoPublicacion.getText()));
	}

	/**
	 * Controla que los text fields no se encuentren vacios. <br>
	 * 
	 * @return <b>true</b> si no se encuentran vacios. <br>
	 *         <b>false</b> de lo contrario. <br>
	 */
	private boolean controlarCamposNoVacios() {
		if (this.tfISBN.getText().length() != 0 && this.tfTitulo.getText().length() != 0
				&& this.tfAutor.getText().length() != 0 && this.tfEdicion.getText().length() != 0
				&& this.tfEditorial.getText().length() != 0 && this.tfAnoPublicacion.getText().length() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Controla que los campos tengan el formato válido. <br>
	 * 
	 * @return <b>true</b> si es válido. <br>
	 *         <b>false</b> si no es válido. <br>
	 */
	private boolean controlarCamposValidos() {
		if (!this.controlarISBN.isFormatoValido()) {
			return false;
		}
		if (!this.controlarTitulo.isFormatoValido()) {
			return false;
		}
		if (!this.controlarAutor.isFormatoValido()) {
			return false;
		}
		if (!this.controlarEdicion.isFormatoValido()) {
			return false;
		}
		if (!this.controlarEditorial.isFormatoValido()) {
			return false;
		}
		if (!this.controlarAnoPublicacion.isFormatoValido()) {
			return false;
		}
		return true;
	}

	/**
	 * Indica si el proceso de alta/modificación fue cancelado. <br>
	 * 
	 * @return <b>true</b> si fue cancelado. <br>
	 *         <b>false</b> si no fue cancelado. <br>
	 */
	public boolean isCancelado() {
		return this.cancelado;
	}
}
