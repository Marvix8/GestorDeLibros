package ventana;

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
import javax.swing.border.EmptyBorder;

import constantes.Labels;
import constantes.Titulos;
import gestor.Gestor;
import java.awt.Toolkit;

/**
 * Clase que carga la ventana de búsqueda de libros por medio de su ISBN. <br>
 */
public class VentanaBuscarISBN extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	/**
	 * Textfield del ISBN a buscar. <br>
	 */
	private JTextField tfISBN;
	/**
	 * Indica si la búsqueda fue cancelada. <br>
	 */
	private boolean cancelado;

	/**
	 * Carga la ventana de búsqueda del libro. <br>
	 * 
	 * @param gestor
	 *            Gestor en donde buscar el libro. <br>
	 */
	public VentanaBuscarISBN(Gestor gestor) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaBuscarISBN.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		setModal(true);
		setTitle(Titulos.BUSCAR_ISBN.getTitulo());
		setBounds(100, 100, 320, 121);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 304, 0 };
		gridBagLayout.rowHeights = new int[] { 49, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(this.contentPanel, gbc_contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblISBN = new JLabel(Labels.ISBN.getLabel());
			lblISBN.setBounds(12, 16, 38, 20);
			contentPanel.add(lblISBN);
		}
		{
			tfISBN = new JTextField();
			tfISBN.setBounds(46, 11, 248, 31);
			contentPanel.add(tfISBN);
			tfISBN.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton btnBuscar = new JButton(Labels.BUSCAR.getLabel());
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						gestor.setISBN(tfISBN.getText());
						cancelado = false;
						salirDeVentana();
					}
				});
				buttonPane.add(btnBuscar);
				getRootPane().setDefaultButton(btnBuscar);
			}
			{
				JButton btnCancelar = new JButton(Labels.CANCELAR.getLabel());
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelado = true;
						salirDeVentana();
					}
				});
				buttonPane.add(btnCancelar);
			}
		}
	}

	/**
	 * Sale de la ventana actual. <br>
	 */
	private void salirDeVentana() {
		setVisible(false);
		dispose();
	}

	/**
	 * Controla si la ventana fue cancelada. <br>
	 * 
	 * @return <b>true</b> si fue cancelada. <br>
	 *         <b>false</b> de lo contrario. <br>
	 */
	public boolean isCancelado() {
		return this.cancelado;
	}
}
