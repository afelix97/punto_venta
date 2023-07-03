package punto_venta.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerViewVentas;

import javax.swing.border.MatteBorder;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class JpVentas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8706669607118444108L;
	private JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel tableModel;
	public JTextField txtFiltro;
	public JButton btnBuscar;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public JpVentas() {
		setOpaque(false);

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(111, 138, 1140, 319);
		scrollPane.setOpaque(false);
		this.add(scrollPane);

		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.setDefaultEditor(Object.class, null);
		table.setDragEnabled(true);
		table.setAutoCreateRowSorter(true);
		table.setShowHorizontalLines(false);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "CLIENTE", "FECHA", "ID_EMPLEADO", "TOTAL" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(111, 35, 1140, 55);
		add(panel_1);

		JLabel lblNewLabel = new JLabel("Ventas");
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
		txtFiltro.setBounds(226, 100, 249, 37);
		add(txtFiltro);

		JLabel lblFiltrar = new JLabel("Buscar: ");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setForeground(SystemColor.infoText);
		lblFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 33));
		lblFiltrar.setBackground(Color.WHITE);
		lblFiltrar.setBounds(111, 89, 118, 47);
		add(lblFiltrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(0, 128, 0));
		btnBuscar.setBounds(483, 100, 112, 35);
		add(btnBuscar);

		// evento que se detona cuando el panel se muestra
		addHierarchyListener(new ControllerViewVentas(this));
		btnBuscar.addActionListener(new ControllerViewVentas(this));
		txtFiltro.addKeyListener(new ControllerViewVentas(this));
		table.addMouseListener(new ControllerViewVentas(this));

		lblNewLabel_1 = new JLabel("Nota: Hacer doble click en la fila para ver los detalles de la venta.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(803, 452, 448, 25);
		add(lblNewLabel_1);
	}
}
