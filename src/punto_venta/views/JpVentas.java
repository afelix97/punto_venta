package punto_venta.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import punto_venta.config.Conexion;
import javax.swing.border.MatteBorder;

public class JpVentas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8706669607118444108L;
	private JScrollPane scrollPane;
	private JTable table;
	public DefaultTableModel modelo;
	private JTextField textField;

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
		scrollPane.setBounds(111, 138, 1140, 352);
		scrollPane.setOpaque(false);
		this.add(scrollPane);

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "CODIGO", "NOMBRE", "PROVEEDOR", "PRECIO", "STOCK" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(111, 35, 1140, 55);
		add(panel_1);

		JLabel lblNewLabel = new JLabel("Ventas");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setOpaque(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(SystemColor.textHighlight);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		textField.setBounds(226, 100, 249, 37);
		add(textField);
		
		JLabel lblFiltrar = new JLabel("Buscar: ");
		lblFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrar.setForeground(SystemColor.infoText);
		lblFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 33));
		lblFiltrar.setBackground(Color.WHITE);
		lblFiltrar.setBounds(111, 89, 118, 47);
		add(lblFiltrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(0, 128, 0));
		btnBuscar.setBounds(483, 100, 112, 35);
		add(btnBuscar);
		
		loadTable();
	}

	private void loadTable() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		Statement stm;
		Conexion conn = new Conexion();
		try {
			stm = conn.ObtConexion().createStatement();
			ResultSet result = stm.executeQuery("Select * from ventas");
			int NumCol = table.getModel().getColumnCount();

			Object[] fila = new Object[NumCol];
			while (result.next()) {

				fila[0] = result.getString(1);
				fila[1] = result.getString(2);
				fila[2] = result.getString(3);
				fila[3] = result.getString(4);
				fila[4] = result.getString(5);
				fila[5] = result.getString(6);

				((DefaultTableModel) table.getModel()).addRow(fila);
			}
			stm.close();
			result.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Se Han Econtraron Errores: " + e, "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
