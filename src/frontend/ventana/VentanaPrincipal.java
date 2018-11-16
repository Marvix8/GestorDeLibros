package ventana;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import constantes.Labels;
import constantes.Mensajes;
import constantes.Titulos;
import excepciones.BaseVaciaException;
import excepciones.DatoInexistenteException;
import gestor.Gestor;
import gestor.GestorOpciones;

/**
 * Clase que administra la ventana principal del gestor. <br>
 */
public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Ícono de la aplicación. <br>
	 */
	private BufferedImage icono;
	/**
	 * Gestor de libros. <br>
	 */
	private Gestor gestor;

	/**
	 * Inicia la aplicación. <br>
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Clase para controlar la ventana principal. <br>
	 */
	private class ControlarVentanaPrincipal extends WindowAdapter {
		/**
		 * Controla la salida del gestor con el mismo criterio que el botón para
		 * salir. <br>
		 */
		public void windowClosing(WindowEvent event) {
			salirDelGestor();
		}
	}

	/**
	 * Carga la ventana principal del gestor. <br>
	 */
	public VentanaPrincipal() {
		this.cargarRecursos();
		try {
			this.gestor = new Gestor();
		} catch (IOException e) {
			VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.ERROR_BASE_DATOS.getTitulo(),
					new StringBuilder(Mensajes.ERROR_BASE_DATOS.getMensaje()).append(e.getMessage()).toString(),
					Toolkit.getDefaultToolkit().getImage(
							VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
			ventanaMensaje.setVisible(true);
			System.exit(EXIT_ON_CLOSE);
		}
		setIconImage(this.icono);
		setTitle(Titulos.PRINCIPAL.getTitulo());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		setBounds(100, 100, 310, 315);
		this.addWindowListener(new ControlarVentanaPrincipal());
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 3.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnAltas = new JButton(Labels.ALTAS.getLabel());
		btnAltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				darLibroDeAlta();
			}
		});

		JSeparator separator1 = new JSeparator();
		GridBagConstraints gbc_separator1 = new GridBagConstraints();
		gbc_separator1.insets = new Insets(0, 0, 5, 5);
		gbc_separator1.gridx = 1;
		gbc_separator1.gridy = 0;
		contentPane.add(separator1, gbc_separator1);
		btnAltas.setMaximumSize(new Dimension(160, 28));
		GridBagConstraints gbc_btnAltas = new GridBagConstraints();
		gbc_btnAltas.anchor = GridBagConstraints.NORTH;
		gbc_btnAltas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAltas.insets = new Insets(0, 0, 5, 5);
		gbc_btnAltas.gridx = 1;
		gbc_btnAltas.gridy = 1;
		contentPane.add(btnAltas, gbc_btnAltas);

		JSeparator separator2 = new JSeparator();
		GridBagConstraints gbc_separator2 = new GridBagConstraints();
		gbc_separator2.insets = new Insets(0, 0, 5, 5);
		gbc_separator2.gridx = 1;
		gbc_separator2.gridy = 2;
		contentPane.add(separator2, gbc_separator2);

		JButton btnBajas = new JButton(Labels.BAJAS.getLabel());
		btnBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				darDeBajaLibro();
			}
		});
		GridBagConstraints gbc_btnBajas = new GridBagConstraints();
		gbc_btnBajas.anchor = GridBagConstraints.NORTH;
		gbc_btnBajas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBajas.insets = new Insets(0, 0, 5, 5);
		gbc_btnBajas.gridx = 1;
		gbc_btnBajas.gridy = 3;
		contentPane.add(btnBajas, gbc_btnBajas);

		JSeparator separator3 = new JSeparator();
		GridBagConstraints gbc_separator3 = new GridBagConstraints();
		gbc_separator3.insets = new Insets(0, 0, 5, 5);
		gbc_separator3.gridx = 1;
		gbc_separator3.gridy = 4;
		contentPane.add(separator3, gbc_separator3);

		JButton btnActualizaciones = new JButton(Labels.ACTUALIZACIONES.getLabel());
		btnActualizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarLibro();
			}
		});
		GridBagConstraints gbc_btnActualizaciones = new GridBagConstraints();
		gbc_btnActualizaciones.fill = GridBagConstraints.BOTH;
		gbc_btnActualizaciones.insets = new Insets(0, 0, 5, 5);
		gbc_btnActualizaciones.gridx = 1;
		gbc_btnActualizaciones.gridy = 5;
		contentPane.add(btnActualizaciones, gbc_btnActualizaciones);

		JSeparator separator4 = new JSeparator();
		GridBagConstraints gbc_separator4 = new GridBagConstraints();
		gbc_separator4.insets = new Insets(0, 0, 5, 5);
		gbc_separator4.gridx = 1;
		gbc_separator4.gridy = 6;
		contentPane.add(separator4, gbc_separator4);

		JButton btnConsultas = new JButton(Labels.CONSULTAS.getLabel());
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarLibro();
			}
		});
		GridBagConstraints gbc_btnConsultas = new GridBagConstraints();
		gbc_btnConsultas.fill = GridBagConstraints.BOTH;
		gbc_btnConsultas.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultas.gridx = 1;
		gbc_btnConsultas.gridy = 7;
		contentPane.add(btnConsultas, gbc_btnConsultas);

		JSeparator separator5 = new JSeparator();
		GridBagConstraints gbc_separator5 = new GridBagConstraints();
		gbc_separator5.insets = new Insets(0, 0, 5, 5);
		gbc_separator5.gridx = 1;
		gbc_separator5.gridy = 8;
		contentPane.add(separator5, gbc_separator5);

		JButton btnListarRegistros = new JButton(Labels.LISTAR_REGSTROS.getLabel());
		btnListarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarLibros();
			}
		});
		GridBagConstraints gbc_btnListarRegistros = new GridBagConstraints();
		gbc_btnListarRegistros.fill = GridBagConstraints.BOTH;
		gbc_btnListarRegistros.insets = new Insets(0, 0, 5, 5);
		gbc_btnListarRegistros.gridx = 1;
		gbc_btnListarRegistros.gridy = 9;
		contentPane.add(btnListarRegistros, gbc_btnListarRegistros);

		JSeparator separator6 = new JSeparator();
		GridBagConstraints gbc_separator6 = new GridBagConstraints();
		gbc_separator6.insets = new Insets(0, 0, 5, 5);
		gbc_separator6.gridx = 1;
		gbc_separator6.gridy = 10;
		contentPane.add(separator6, gbc_separator6);

		JButton btnOrdenarRegistros = new JButton(Labels.ORDENAR_REGISTROS.getLabel());
		btnOrdenarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenarLibros();
			}
		});
		GridBagConstraints gbc_btnOrdenarRegistros = new GridBagConstraints();
		gbc_btnOrdenarRegistros.fill = GridBagConstraints.BOTH;
		gbc_btnOrdenarRegistros.insets = new Insets(0, 0, 5, 5);
		gbc_btnOrdenarRegistros.gridx = 1;
		gbc_btnOrdenarRegistros.gridy = 11;
		contentPane.add(btnOrdenarRegistros, gbc_btnOrdenarRegistros);

		JSeparator separator7 = new JSeparator();
		GridBagConstraints gbc_separator7 = new GridBagConstraints();
		gbc_separator7.insets = new Insets(0, 0, 5, 5);
		gbc_separator7.gridx = 1;
		gbc_separator7.gridy = 12;
		contentPane.add(separator7, gbc_separator7);

		JButton btnSalir = new JButton(Labels.SALIR.getLabel());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salirDelGestor();
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalir.fill = GridBagConstraints.BOTH;
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 13;
		contentPane.add(btnSalir, gbc_btnSalir);
	}

	/**
	 * Da un libro de alta. <br>
	 */
	private void darLibroDeAlta() {
		try {
			try {
				this.gestor.controlarBaseVacia(GestorOpciones.ALTA);
			} catch (BaseVaciaException e) {
				// Si está vacía permito que se carguen datos.
			}
			VentanaBuscarISBN ventanaBusqueda = new VentanaBuscarISBN(this.gestor);
			ventanaBusqueda.setVisible(true);
			if (!ventanaBusqueda.isCancelado()) {
				this.gestor.buscarLibroPorISBN();
				// Si encontró el libro es porque ya se encuentra en la base de
				// datos. Aviso al usuario y salgo.
				VentanaMensaje ventanaMensaje = new VentanaMensaje(
						new StringBuilder(Titulos.ISBN_INEXISTENTE.getTitulo()).append(this.gestor.getISBN())
								.toString(),
						Mensajes.LIBRO_YA_EXISTENTE.getMensaje(), Toolkit.getDefaultToolkit().getImage(
								VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
				ventanaMensaje.setVisible(true);
			}
		} catch (DatoInexistenteException e) {
			// De no existir el libro empiezo a cargar la información.
			VentanaInformacionLibro ventanaInformacionLibro = new VentanaInformacionLibro(this.gestor.getNuevoLibro());
			ventanaInformacionLibro.setIconImage(this.icono);
			ventanaInformacionLibro.setVisible(true);
			if (!ventanaInformacionLibro.isCancelado()) {
				this.gestor.cargarLibro();
			}
		}
	}

	/**
	 * Consulta por un libro. <br>
	 */
	private void consultarLibro() {
		try {
			this.gestor.controlarBaseVacia(GestorOpciones.CONSULTA);
			VentanaBuscarISBN ventanaBusqueda = new VentanaBuscarISBN(gestor);
			ventanaBusqueda.setVisible(true);
			if (!ventanaBusqueda.isCancelado()) {
				this.gestor.buscarLibroPorISBN();
				VentanaLibros ventanaLibros = new VentanaLibros(gestor.getLibroActual());
				ventanaLibros.setIconImage(this.icono);
				ventanaLibros.setVisible(true);
			}
		} catch (BaseVaciaException e) {
			this.cargarVentanaBaseVacia();
		} catch (DatoInexistenteException e) {
			this.cargarVentanaLibroInexistente();
		}
	}

	/**
	 * Da de baja un libro. <br>
	 */
	private void darDeBajaLibro() {
		try {
			this.gestor.controlarBaseVacia(GestorOpciones.BAJA);
			VentanaBuscarISBN ventanaBusqueda = new VentanaBuscarISBN(gestor);
			ventanaBusqueda.setVisible(true);
			if (!ventanaBusqueda.isCancelado()) {
				this.gestor.buscarLibroPorISBN();
				this.gestor.darDeBajaLibro();
				this.cargarVentanaExito(Mensajes.REGISTRO_BORRADO.getMensaje());
			}
		} catch (BaseVaciaException e) {
			this.cargarVentanaBaseVacia();
		} catch (DatoInexistenteException e) {
			this.cargarVentanaLibroInexistente();
		}
	}

	/**
	 * Modifica la información de un libro. <br>
	 */
	private void modificarLibro() {
		try {
			this.gestor.controlarBaseVacia(GestorOpciones.ACTUALIZAR);
			VentanaBuscarISBN ventanaBusqueda = new VentanaBuscarISBN(this.gestor);
			ventanaBusqueda.setVisible(true);
			if (!ventanaBusqueda.isCancelado()) {
				this.gestor.buscarLibroPorISBN();
				VentanaInformacionLibro ventanaInformacionLibro = new VentanaInformacionLibro(
						this.gestor.getLibroConsultado());
				ventanaInformacionLibro.setIconImage(this.icono);
				ventanaInformacionLibro.setVisible(true);
			}
		} catch (DatoInexistenteException e) {
			VentanaMensaje ventanaMensaje = new VentanaMensaje(
					new StringBuilder(Titulos.ISBN_INEXISTENTE.getTitulo()).append(gestor.getISBN()).toString(),
					Mensajes.ERROR_BUSQUEDA.getMensaje(), Toolkit.getDefaultToolkit().getImage(
							VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
			ventanaMensaje.setVisible(true);
		} catch (BaseVaciaException e) {
			this.cargarVentanaBaseVacia();
		}
	}

	/**
	 * Lista los libros en la base de datos. <br>
	 */
	private void listarLibros() {
		try {
			this.gestor.controlarBaseVacia(GestorOpciones.LISTAR);
			VentanaLibros ventanaLibros = new VentanaLibros(this.gestor.getLibros());
			ventanaLibros.setIconImage(this.icono);
			ventanaLibros.setVisible(true);
		} catch (BaseVaciaException e) {
			this.cargarVentanaBaseVacia();
		}
	}

	/**
	 * Ordena los libros en la base de datos. <br>
	 */
	private void ordenarLibros() {
		try {
			this.gestor.controlarBaseVacia(GestorOpciones.ORDENAR);
			this.gestor.ordenarLibros();
			VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.EXITO.getTitulo(),
					Mensajes.LIBROS_ORDENADOS.getMensaje(), null);
			ventanaMensaje.setVisible(true);
		} catch (BaseVaciaException e) {
			this.cargarVentanaBaseVacia();
		}
	}

	/**
	 * Sale del gestor. <br>
	 */
	private void salirDelGestor() {
		VentanaConfirmacion ventanaConfirmacion = new VentanaConfirmacion(Titulos.SALIR.getTitulo(),
				Mensajes.CONFIRMACION_SALIR.getMensaje());
		ventanaConfirmacion.setVisible(true);
		if (ventanaConfirmacion.getDecision()) {
			try {
				this.gestor.procesarCambios();
				this.cargarVentanaExito(Mensajes.CONFIRMACION_DATOS_GUARDADOS.getMensaje());
			} catch (IOException exception) {
				VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.ERROR_BASE_DATOS.getTitulo(),
						new StringBuilder(Mensajes.ERROR_DATOS_GUARDADOS.getMensaje()).append(exception.getMessage())
								.toString(),
						Toolkit.getDefaultToolkit().getImage(
								VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
				ventanaMensaje.setVisible(true);
			}
			System.exit(EXIT_ON_CLOSE);
		}
	}

	/**
	 * Carga la ventana de mensajes indicando que una acción fue realizada con
	 * éxito. <br>
	 * 
	 * @param mensaje
	 *            Mensaje a mostrar. <br>
	 */
	private void cargarVentanaExito(final String mensaje) {
		VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.EXITO.getTitulo(), mensaje,
				Toolkit.getDefaultToolkit()
						.getImage(VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		ventanaMensaje.setVisible(true);
	}

	/**
	 * Carga la ventana de mensajes indicando que la base de datos se encuentra
	 * vacía. <br>
	 */
	private void cargarVentanaBaseVacia() {
		VentanaMensaje ventanaMensaje = new VentanaMensaje(
				new StringBuilder(Titulos.ISBN_INEXISTENTE.getTitulo()).append(gestor.getISBN()).toString(),
				Mensajes.BASE_VACIA.getMensaje(), Toolkit.getDefaultToolkit()
						.getImage(VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		ventanaMensaje.setVisible(true);
	}

	/**
	 * Carga la ventana de mensajes indicando que no existe un libro con el ISBN
	 * indicado. <br>
	 */
	private void cargarVentanaLibroInexistente() {
		VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.BASE_DATOS_VACIA.getTitulo(),
				Mensajes.ERROR_BUSQUEDA.getMensaje(), Toolkit.getDefaultToolkit()
						.getImage(VentanaMensaje.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		ventanaMensaje.setVisible(true);
	}

	/**
	 * Carga los recursos del gestor. <br>
	 */
	private void cargarRecursos() {
		try {
			this.icono = ImageIO.read(this.getClass().getResource("/gestor.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
