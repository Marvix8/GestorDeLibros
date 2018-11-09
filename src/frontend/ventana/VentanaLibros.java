package ventana;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import constantes.Labels;
import constantes.Titulos;
import gestor.Libro;

/**
 * Clase que carga los libros en base de datos.
 */
public class VentanaLibros extends JFrame {
	/**
	 * Label del ISBN actual. <br>
	 */
	private JLabel lblISBN;
	/**
	 * Label del título actual. <br>
	 */
	private JLabel lblTitulo;
	/**
	 * Label del autor actual. <br>
	 */
	private JLabel lblAutor;
	/**
	 * Label de la edición actual. <br>
	 */
	private JLabel lblEdicion;
	/**
	 * Label del año de la publicación. <br>
	 */
	private JLabel lblAnoPublicacion;
	/**
	 * Label de la editorial actual. <br>
	 */
	private JLabel lblEditorial;
	/**
	 * Display de la lista de libros. <br>
	 */
	private JList<String> listLibrosDisplay;
	/**
	 * Libros disponibles. <br>
	 */
	private Vector<Libro> libros;
	/**
	 * Titulos de los libros. <br>
	 */
	private Vector<String> titulos = new Vector<String>();
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una ventana con los libros actuales. <br>
	 * 
	 * @param listaLibros
	 *            Lista con los libros a mostrar. <br>
	 */
	public VentanaLibros(final Vector<Libro> listaLibros) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAlwaysOnTop(true);
		setTitle(Titulos.LIBROS.getTitulo());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.libros = listaLibros;
		for (Libro libro : listaLibros) {
			this.titulos.addElement(libro.getTitulo());
		}
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 186, 254, 0 };
		gbl_contentPane.rowHeights = new int[] { 251, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		listLibrosDisplay = new JList<String>(this.titulos);
		listLibrosDisplay.setBorder(new LineBorder(new Color(0, 0, 0)));
		listLibrosDisplay.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				actualizarLabelsLibro();
			}
		});
		GridBagConstraints gbc_listLibrosDisplay = new GridBagConstraints();
		gbc_listLibrosDisplay.fill = GridBagConstraints.BOTH;
		gbc_listLibrosDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_listLibrosDisplay.gridx = 0;
		gbc_listLibrosDisplay.gridy = 0;
		contentPane.add(listLibrosDisplay, gbc_listLibrosDisplay);

		JPanel panelDatosLibros = new JPanel();
		panelDatosLibros.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		GridBagConstraints gbc_panelDatosLibros = new GridBagConstraints();
		gbc_panelDatosLibros.fill = GridBagConstraints.BOTH;
		gbc_panelDatosLibros.gridx = 1;
		gbc_panelDatosLibros.gridy = 0;
		contentPane.add(panelDatosLibros, gbc_panelDatosLibros);
		GridBagLayout gbl_panelDatosLibros = new GridBagLayout();
		gbl_panelDatosLibros.columnWidths = new int[] { 93, 156, 0 };
		gbl_panelDatosLibros.rowHeights = new int[] { 21, 0, 0, 0, 0, 0, 0 };
		gbl_panelDatosLibros.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelDatosLibros.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelDatosLibros.setLayout(gbl_panelDatosLibros);

		JLabel lbISBNDisplay = new JLabel(Labels.ISBN.getLabel());
		GridBagConstraints gbc_lbISBNDisplay = new GridBagConstraints();
		gbc_lbISBNDisplay.anchor = GridBagConstraints.EAST;
		gbc_lbISBNDisplay.fill = GridBagConstraints.VERTICAL;
		gbc_lbISBNDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lbISBNDisplay.gridx = 0;
		gbc_lbISBNDisplay.gridy = 0;
		panelDatosLibros.add(lbISBNDisplay, gbc_lbISBNDisplay);

		this.lblISBN = new JLabel();
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.anchor = GridBagConstraints.WEST;
		gbc_lblISBN.insets = new Insets(0, 0, 5, 0);
		gbc_lblISBN.gridx = 1;
		gbc_lblISBN.gridy = 0;
		panelDatosLibros.add(lblISBN, gbc_lblISBN);

		JLabel lblTituloDisplay = new JLabel(Labels.TITULO.getLabel());
		GridBagConstraints gbc_lblTituloDisplay = new GridBagConstraints();
		gbc_lblTituloDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblTituloDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloDisplay.gridx = 0;
		gbc_lblTituloDisplay.gridy = 1;
		panelDatosLibros.add(lblTituloDisplay, gbc_lblTituloDisplay);

		this.lblTitulo = new JLabel();
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.WEST;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panelDatosLibros.add(lblTitulo, gbc_lblTitulo);

		JLabel lblAutorDisplay = new JLabel(Labels.AUTOR.getLabel());
		GridBagConstraints gbc_lblAutorDisplay = new GridBagConstraints();
		gbc_lblAutorDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblAutorDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutorDisplay.gridx = 0;
		gbc_lblAutorDisplay.gridy = 2;
		panelDatosLibros.add(lblAutorDisplay, gbc_lblAutorDisplay);

		this.lblAutor = new JLabel();
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.anchor = GridBagConstraints.WEST;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 0);
		gbc_lblAutor.gridx = 1;
		gbc_lblAutor.gridy = 2;
		panelDatosLibros.add(lblAutor, gbc_lblAutor);

		JLabel lblEdicionDisplay = new JLabel(Labels.EDICION.getLabel());
		GridBagConstraints gbc_lblEdicionDisplay = new GridBagConstraints();
		gbc_lblEdicionDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblEdicionDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicionDisplay.gridx = 0;
		gbc_lblEdicionDisplay.gridy = 3;
		panelDatosLibros.add(lblEdicionDisplay, gbc_lblEdicionDisplay);

		this.lblEdicion = new JLabel();
		GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
		gbc_lblEdicion.anchor = GridBagConstraints.WEST;
		gbc_lblEdicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblEdicion.gridx = 1;
		gbc_lblEdicion.gridy = 3;
		panelDatosLibros.add(lblEdicion, gbc_lblEdicion);

		JLabel lblAnoPublicacionDisplay = new JLabel();
		lblAnoPublicacionDisplay.setText(Labels.ANO_PUBLICACION.getLabel());
		GridBagConstraints gbc_lblAnoPublicacionDisplay = new GridBagConstraints();
		gbc_lblAnoPublicacionDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblAnoPublicacionDisplay.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnoPublicacionDisplay.gridx = 0;
		gbc_lblAnoPublicacionDisplay.gridy = 4;
		panelDatosLibros.add(lblAnoPublicacionDisplay, gbc_lblAnoPublicacionDisplay);

		this.lblAnoPublicacion = new JLabel();
		GridBagConstraints gbc_lblAnoEdicion = new GridBagConstraints();
		gbc_lblAnoEdicion.anchor = GridBagConstraints.WEST;
		gbc_lblAnoEdicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnoEdicion.gridx = 1;
		gbc_lblAnoEdicion.gridy = 4;
		panelDatosLibros.add(lblAnoPublicacion, gbc_lblAnoEdicion);

		JLabel lblEditorialDisplay = new JLabel(Labels.EDITORIAL.getLabel());
		GridBagConstraints gbc_lblEditorialDisplay = new GridBagConstraints();
		gbc_lblEditorialDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_lblEditorialDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblEditorialDisplay.gridx = 0;
		gbc_lblEditorialDisplay.gridy = 5;
		panelDatosLibros.add(lblEditorialDisplay, gbc_lblEditorialDisplay);

		this.lblEditorial = new JLabel();
		GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
		gbc_lblEditorial.anchor = GridBagConstraints.WEST;
		gbc_lblEditorial.gridx = 1;
		gbc_lblEditorial.gridy = 5;
		panelDatosLibros.add(lblEditorial, gbc_lblEditorial);
	}

	/**
	 * Actualiza los labels de los libros cada vez que se seleccione un libro.
	 * <br>
	 */
	private void actualizarLabelsLibro() {
		int posicionActual = this.listLibrosDisplay.getSelectedIndex();
		Libro libro = this.libros.elementAt(posicionActual);
		this.lblISBN.setText(libro.getISBN());
		this.lblAutor.setText(libro.getAutor());
		this.lblTitulo.setText(libro.getTitulo());
		this.lblAnoPublicacion.setText("" + libro.getAnioPublicacion());
		this.lblEdicion.setText("" + libro.getEdicion());
		this.lblEditorial.setText(libro.getEditorial());
	}
}
