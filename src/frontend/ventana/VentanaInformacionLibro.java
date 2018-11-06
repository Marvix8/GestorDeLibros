package ventana;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constantes.Labels;
import constantes.Titulos;
import gestor.Libro;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que muestra la ventana para modificar información sobre un libro.
 * <p>
 * <i><b>Nota</b>: Si la información sobre el libro no está completa no permite
 * aceptar la modificación sobre el mismo. </i> <br>
 */
public class VentanaInformacionLibro extends JDialog {
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
	 * Carga la información de un libro.
	 * 
	 * @param libro
	 *            Libro actual. <br>
	 */
	public VentanaInformacionLibro(Libro libro) {
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

		this.tfISBN = new JTextField();
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblISBN.insets = new Insets(0, 0, 5, 0);
		gbc_lblISBN.gridx = 1;
		gbc_lblISBN.gridy = 0;
		panelDatosLibros.add(tfISBN, gbc_lblISBN);

		JLabel lblTituloDisplay = new JLabel(Labels.TITULO.getLabel());
		GridBagConstraints gbc_lblTituloDisplay = new GridBagConstraints();
		gbc_lblTituloDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblTituloDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloDisplay.gridx = 0;
		gbc_lblTituloDisplay.gridy = 1;
		panelDatosLibros.add(lblTituloDisplay, gbc_lblTituloDisplay);

		this.tfTitulo = new JTextField();
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panelDatosLibros.add(tfTitulo, gbc_lblTitulo);

		JLabel lblAutorDisplay = new JLabel(Labels.AUTOR.getLabel());
		GridBagConstraints gbc_lblAutorDisplay = new GridBagConstraints();
		gbc_lblAutorDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblAutorDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutorDisplay.gridx = 0;
		gbc_lblAutorDisplay.gridy = 2;
		panelDatosLibros.add(lblAutorDisplay, gbc_lblAutorDisplay);

		this.tfAutor = new JTextField();
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 0);
		gbc_lblAutor.gridx = 1;
		gbc_lblAutor.gridy = 2;
		panelDatosLibros.add(tfAutor, gbc_lblAutor);

		JLabel lblEdicionDisplay = new JLabel(Labels.EDICION.getLabel());
		GridBagConstraints gbc_lblEdicionDisplay = new GridBagConstraints();
		gbc_lblEdicionDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblEdicionDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicionDisplay.gridx = 0;
		gbc_lblEdicionDisplay.gridy = 3;
		panelDatosLibros.add(lblEdicionDisplay, gbc_lblEdicionDisplay);

		this.tfEdicion = new JTextField();
		GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
		gbc_lblEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEdicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdicion.gridx = 1;
		gbc_lblEdicion.gridy = 3;
		panelDatosLibros.add(tfEdicion, gbc_lblEdicion);

		JLabel lblAnoPublicacionDisplay = new JLabel();
		lblAnoPublicacionDisplay.setText(Labels.ANO_PUBLICACION.getLabel());
		GridBagConstraints gbc_lblAnoPublicacionDisplay = new GridBagConstraints();
		gbc_lblAnoPublicacionDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblAnoPublicacionDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnoPublicacionDisplay.gridx = 0;
		gbc_lblAnoPublicacionDisplay.gridy = 4;
		panelDatosLibros.add(lblAnoPublicacionDisplay, gbc_lblAnoPublicacionDisplay);

		this.tfAnoPublicacion = new JTextField();
		GridBagConstraints gbc_lblAnoEdicion = new GridBagConstraints();
		gbc_lblAnoEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAnoEdicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnoEdicion.gridx = 1;
		gbc_lblAnoEdicion.gridy = 4;
		panelDatosLibros.add(tfAnoPublicacion, gbc_lblAnoEdicion);

		JLabel lblEditorialDisplay = new JLabel(Labels.EDITORIAL.getLabel());
		GridBagConstraints gbc_lblEditorialDisplay = new GridBagConstraints();
		gbc_lblEditorialDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_lblEditorialDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblEditorialDisplay.gridx = 0;
		gbc_lblEditorialDisplay.gridy = 5;
		panelDatosLibros.add(lblEditorialDisplay, gbc_lblEditorialDisplay);

		this.tfEditorial = new JTextField();
		GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
		gbc_lblEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEditorial.gridx = 1;
		gbc_lblEditorial.gridy = 5;
		panelDatosLibros.add(tfEditorial, gbc_lblEditorial);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton(Labels.OK.getLabel());
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controlarCambios();
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
						confirmarSalirDePantalla();
					}
				});
				btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
				btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
				buttonPane.add(btnCancelar);
			}
		}
	}

	/**
	 * Controla los cambios realizados. <br>
	 */
	public void controlarCambios() {

	}

	/**
	 * Confirma la salida por pantalla. <br>
	 */
	private void confirmarSalirDePantalla() {

	}

}
