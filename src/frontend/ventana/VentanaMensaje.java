package ventana;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constantes.Labels;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase que muestra una ventana con un mensaje de una acción realizada. <br>
 */
public class VentanaMensaje extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Carga la ventana con el mensaje a mostrar. <br>
	 * 
	 * @param titulo
	 *            Título de la ventana. <br>
	 * @param mensaje
	 *            Mensaje a mostrar. <br>
	 */
	public VentanaMensaje(final String titulo, final String mensaje) {
		setTitle(titulo);
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 323, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensaje = new JLabel();
			lblMensaje.setVerticalAlignment(SwingConstants.CENTER);
			lblMensaje.setText("<html><center>" + mensaje + "</center></html>");
			lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
			lblMensaje.setBounds(10, 5, 297, 81);
			contentPanel.add(lblMensaje);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnOk = new JButton(Labels.OK.getLabel());
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
		}
	}
}
