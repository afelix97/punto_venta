package punto_venta.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerViewDetalleVenta;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class JfDetalleVenta extends JFrame {

	private static final long serialVersionUID = -2510798943016413839L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel tableModel;
	public JTextField txtFiltro;
	public JButton btnBuscar;
	private JTextField txtIdVenta;

	private int idVenta = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				try {
					// para que se ejecute primero la vista principal
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JfDetalleVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		contentPane.setBackground(Color.WHITE);
		// contentPane.setBounds(0, 0, 1362, 500);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 138, 1140, 352);
		scrollPane.setOpaque(false);
		getContentPane().add(scrollPane);

		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.setDefaultEditor(Object.class, null);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "FILA", "ID VENTA", "ID ARTICULO", "CANTIDAD", "SUBTOTAL" }));
		table.setOpaque(false);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(259, 35, 914, 55);
		getContentPane().add(panel_1);

		JLabel lblNewLabel = new JLabel("Detalle de la Venta");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		txtFiltro = new JTextField();
		txtFiltro.setOpaque(false);
		txtFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		txtFiltro.setForeground(SystemColor.textHighlight);
		txtFiltro.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFiltro.setColumns(10);
		txtFiltro.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		txtFiltro.setBounds(146, 98, 249, 37);
		getContentPane().add(txtFiltro);

		JLabel lblFiltrar = new JLabel("Buscar: ");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setForeground(SystemColor.infoText);
		lblFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 33));
		lblFiltrar.setBackground(Color.WHITE);
		lblFiltrar.setBounds(33, 90, 118, 47);
		getContentPane().add(lblFiltrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(0, 128, 0));
		btnBuscar.setBounds(401, 100, 112, 35);
		getContentPane().add(btnBuscar);

		txtIdVenta = new JTextField();
		txtIdVenta.setText("#0000" + idVenta);
		txtIdVenta.setOpaque(false);
		txtIdVenta.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdVenta.setForeground(SystemColor.textHighlight);
		txtIdVenta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		txtIdVenta.setEditable(false);
		txtIdVenta.setColumns(10);
		txtIdVenta.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		txtIdVenta.setBounds(33, 35, 228, 55);
		getContentPane().add(txtIdVenta);

	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		// evento que se detona cuando el panel se muestra
		txtIdVenta.setText("#" + idVenta);
		addWindowListener(new ControllerViewDetalleVenta(this, idVenta));
		btnBuscar.addActionListener(new ControllerViewDetalleVenta(this, idVenta));
		txtFiltro.addKeyListener(new ControllerViewDetalleVenta(this, idVenta));
		this.idVenta = idVenta;
	}

}
