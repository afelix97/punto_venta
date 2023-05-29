package punto_venta.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.config.Conexion;

public class Productos extends JFrame {

	/**
	 * Vista CRUD de producto
	 */
	private static final long serialVersionUID = -1121269883026846017L;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel modelo;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				try {
					Productos frame = new Productos();
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
	public Productos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 618);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 77, 913, 426);
		scrollPane.setOpaque(false);
		contentPane.add(scrollPane);

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Fecha De Transaccion", "Tabla",
				"Usuario", "Registro Anterior", "Registro Nuevo", "Transaccion" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(1).setPreferredWidth(26);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(318);
		table.getColumnModel().getColumn(4).setPreferredWidth(297);
		table.getColumnModel().getColumn(5).setPreferredWidth(23);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

	}

	private void CargarTablaBitacora() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		Statement stm;
		Conexion conn = new Conexion();
		try {
			stm = conn.ObtConexion().createStatement();
			ResultSet result = stm.executeQuery("Select * from productos");
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
