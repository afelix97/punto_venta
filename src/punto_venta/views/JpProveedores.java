package punto_venta.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class JpProveedores extends JPanel {

	private static final long serialVersionUID = -7688365530795619596L;

	private JTable table;
	public DefaultTableModel modelo;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public JpProveedores() {
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);
		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 37, 913, 426);
		scrollPane.setOpaque(false);
		this.add(scrollPane);

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "RFC",
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

}
