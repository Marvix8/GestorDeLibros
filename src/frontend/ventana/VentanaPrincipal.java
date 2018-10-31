package ventana;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constantes.Labels;
import constantes.Mensajes;
import constantes.Titulos;
import gestor.Gestor;
import javax.swing.JSeparator;

/**
 * Clase que administra la ventana principal del gestor. <br>
 */
public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	 * Carga la ventana principal del gestor. <br>
	 */
	public VentanaPrincipal() {
		try {
			this.gestor = new Gestor();
		} catch (IOException e) {
			VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.ERROR_BASE_DATOS.getTitulo(),
					new StringBuilder(Mensajes.ERROR_BASE_DATOS.getMensaje()).append(e.getMessage()).toString());
			ventanaMensaje.setVisible(true);
			System.exit(EXIT_ON_CLOSE);
		}
		setTitle(Titulos.PRINCIPAL.getTitulo());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 315);
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

			}
		});

		JSeparator separator_6 = new JSeparator();
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 1;
		gbc_separator_6.gridy = 0;
		contentPane.add(separator_6, gbc_separator_6);
		btnAltas.setMaximumSize(new Dimension(160, 28));
		GridBagConstraints gbc_btnAltas = new GridBagConstraints();
		gbc_btnAltas.anchor = GridBagConstraints.NORTH;
		gbc_btnAltas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAltas.insets = new Insets(0, 0, 5, 5);
		gbc_btnAltas.gridx = 1;
		gbc_btnAltas.gridy = 1;
		contentPane.add(btnAltas, gbc_btnAltas);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 2;
		contentPane.add(separator_2, gbc_separator_2);

		JButton btnBajas = new JButton(Labels.BAJAS.getLabel());
		GridBagConstraints gbc_btnBajas = new GridBagConstraints();
		gbc_btnBajas.anchor = GridBagConstraints.NORTH;
		gbc_btnBajas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBajas.insets = new Insets(0, 0, 5, 5);
		gbc_btnBajas.gridx = 1;
		gbc_btnBajas.gridy = 3;
		contentPane.add(btnBajas, gbc_btnBajas);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 4;
		contentPane.add(separator_1, gbc_separator_1);

		JButton btnActualizaciones = new JButton(Labels.ACTUALIZACIONES.getLabel());
		GridBagConstraints gbc_btnActualizaciones = new GridBagConstraints();
		gbc_btnActualizaciones.fill = GridBagConstraints.BOTH;
		gbc_btnActualizaciones.insets = new Insets(0, 0, 5, 5);
		gbc_btnActualizaciones.gridx = 1;
		gbc_btnActualizaciones.gridy = 5;
		contentPane.add(btnActualizaciones, gbc_btnActualizaciones);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 6;
		contentPane.add(separator, gbc_separator);

		JButton btnConsultas = new JButton(Labels.CONSULTAS.getLabel());
		GridBagConstraints gbc_btnConsultas = new GridBagConstraints();
		gbc_btnConsultas.fill = GridBagConstraints.BOTH;
		gbc_btnConsultas.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultas.gridx = 1;
		gbc_btnConsultas.gridy = 7;
		contentPane.add(btnConsultas, gbc_btnConsultas);

		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 1;
		gbc_separator_3.gridy = 8;
		contentPane.add(separator_3, gbc_separator_3);

		JButton btnListarRegistros = new JButton(Labels.LISTAR_REGSTROS.getLabel());
		GridBagConstraints gbc_btnListarRegistros = new GridBagConstraints();
		gbc_btnListarRegistros.fill = GridBagConstraints.BOTH;
		gbc_btnListarRegistros.insets = new Insets(0, 0, 5, 5);
		gbc_btnListarRegistros.gridx = 1;
		gbc_btnListarRegistros.gridy = 9;
		contentPane.add(btnListarRegistros, gbc_btnListarRegistros);

		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 1;
		gbc_separator_4.gridy = 10;
		contentPane.add(separator_4, gbc_separator_4);

		JButton btnOrdenarRegistros = new JButton(Labels.ORDENAR_REGISTROS.getLabel());
		GridBagConstraints gbc_btnOrdenarRegistros = new GridBagConstraints();
		gbc_btnOrdenarRegistros.fill = GridBagConstraints.BOTH;
		gbc_btnOrdenarRegistros.insets = new Insets(0, 0, 5, 5);
		gbc_btnOrdenarRegistros.gridx = 1;
		gbc_btnOrdenarRegistros.gridy = 11;
		contentPane.add(btnOrdenarRegistros, gbc_btnOrdenarRegistros);

		JSeparator separator_5 = new JSeparator();
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.insets = new Insets(0, 0, 5, 5);
		gbc_separator_5.gridx = 1;
		gbc_separator_5.gridy = 12;
		contentPane.add(separator_5, gbc_separator_5);

		JButton btnSalir = new JButton(Labels.SALIR.getLabel());
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSalir ventanaSalir = new VentanaSalir(gestor);
				ventanaSalir.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalir.fill = GridBagConstraints.BOTH;
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 13;
		contentPane.add(btnSalir, gbc_btnSalir);
	}

}
