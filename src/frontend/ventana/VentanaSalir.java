package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constantes.Labels;
import constantes.Mensajes;
import constantes.Titulos;
import gestor.Gestor;

/**
 * Ventana que consulta al usuario si desea salir del sistema. <br>
 */
public class VentanaSalir extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label del botón Si.
	 */
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		try {
			VentanaSalir dialog = new VentanaSalir(new Gestor());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga la ventana para salir del gestor de libros. <br>
	 * 
	 * @param gestor
	 *            Gestor de libros. <br>
	 */
	public VentanaSalir(final Gestor gestor) {
		setTitle(Titulos.SALIR.getTitulo());
		setModal(true);
		setBounds(100, 100, 261, 152);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblMensaje = new JLabel(
					"<html><center>" + Mensajes.CONFIRMACION_SALIR.getMensaje() + "</center></html>");
			lblMensaje.setBounds(contentPanel.getBounds());
			lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblMensaje);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSi = new JButton(Labels.SI.getLabel());
				btnSi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String salida = gestor.procesarCambios();
						if (salida == null) {
							VentanaMensaje ventanaMensaje = new VentanaMensaje(Titulos.SALIR.getTitulo(),
									Mensajes.CONFIRMACION_DATOS_GUARDADOS.getMensaje());
							ventanaMensaje.setVisible(true);
						} else {
							// TODO Mostrar salida como warning.
						}
						System.exit(EXIT_ON_CLOSE);
					}
				});
				buttonPane.add(btnSi);
				getRootPane().setDefaultButton(btnSi);
			}
			{
				JButton btnNo = new JButton(Labels.NO.getLabel());
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(btnNo);
			}
		}
	}
}
