package punto_venta.views;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.config.Conexion;

public class JpVentas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8706669607118444108L;
	private JScrollPane scrollPane;
	private JTable table;
	public DefaultTableModel modelo;

	/**
	 * Create the panel.
	 */
	public JpVentas() {

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);

		modelo = new DefaultTableModel();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 165, 816, 164);
		this.add(scrollPane);

		scrollPane.setOpaque(false);
		table = new JTable(modelo);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "CODIGO", "Nombre",
				"Precio", "Registro Anterior", "Registro Nuevo", "Transaccion" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(26);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

	}

	private void loadTable() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		Statement stm;
		Conexion conn = new Conexion();
		try {
			stm = conn.ObtConexion().createStatement();
			ResultSet result = stm.executeQuery("Select * from proveedores");
			int NumCol = table.getModel().getColumnCount();

			Object[] fila = new Object[NumCol];
			while (result.next()) {

				fila[0] = result.getString(2);
				fila[1] = result.getString(3);
				fila[2] = result.getString(4);
				fila[3] = result.getString(5);
				fila[4] = result.getString(6);
				fila[5] = result.getString(7);

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
