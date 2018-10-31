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

/**
 * Clase que carga la ventana de búsqueda de libros por medio de su ISBN. <br>
 */
public class VentanaBuscarISBN extends JDialog {
	/**
	 * Serial. <br>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label de cancelar. <br>
	 */
	private static final String CANCELAR = "Cancelar";
	/**
	 * Label del ISBN. <br>
	 */
	private static final String ISBN = "ISBN";
	/**
	 * Título de la ventana. <br>
	 */
	private static final String TITULO = "Buscar libro (ISBN)";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaBuscarISBN dialog = new VentanaBuscarISBN();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle(TITULO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga la ventana de búsqueda del libro. <br>
	 */
	public VentanaBuscarISBN() {
		setModal(true);
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
			JLabel lblISBN = new JLabel(ISBN);
			lblISBN.setBounds(10, 11, 29, 20);
			contentPanel.add(lblISBN);
		}
		{
			tfISBN = new JTextField();
			tfISBN.setBounds(36, 11, 258, 20);
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
					}
				});
				buttonPane.add(btnBuscar);
				getRootPane().setDefaultButton(btnBuscar);
			}
			{
				JButton btnCancelar = new JButton(Labels.CANCELAR.getLabel());
				buttonPane.add(btnCancelar);
			}
		}
	}
}
