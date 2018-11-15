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

/**
 * Ventana que consulta al usuario por una decisión. <br>
 */
public class VentanaConfirmacion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label del botón Si.
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * Indica la decisión tomada.<br>
	 * <b>true</b> para si, <b>false</b> para no. <br>
	 */
	private boolean decision;

	/**
	 * Carga una ventana de confirmación por parte del usuario. <br>
	 * 
	 * @param titulo
	 *            Titulo de la ventana. <br>
	 * @param mensaje
	 *            Mensaje a mostrar. <br>
	 */
	public VentanaConfirmacion(final String titulo, final String mensaje) {
		setTitle(titulo);
		setModal(true);
		setBounds(100, 100, 389, 159);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblMensaje = new JLabel("<html><center>" + mensaje + "</center></html>");
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
						aceptarRespuestaUsuario(true);
					}
				});
				buttonPane.add(btnSi);
				getRootPane().setDefaultButton(btnSi);
			}
			{
				JButton btnNo = new JButton(Labels.NO.getLabel());
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptarRespuestaUsuario(false);
					}
				});
				buttonPane.add(btnNo);
			}
		}
	}

	/**
	 * Indica la respuesta tomada por el usuario. <br>
	 * Finaliza la ventana. <br>
	 * 
	 * @param decision
	 *            Decisión tomada por el usuario. <br>
	 */
	private void aceptarRespuestaUsuario(final boolean decision) {
		this.decision = decision;
		setVisible(false);
		dispose();
	}

	/**
	 * Devuelve la decisión tomada. <br>
	 * 
	 * @return <b>true</b> para si. <br>
	 *         <b>false</b> para no. <br>
	 */
	public boolean getDecision() {
		return this.decision;
	}
}
