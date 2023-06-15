package punto_venta.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerProveedores;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JpProveedores extends JPanel {

	private static final long serialVersionUID = -7688365530795619596L;

	public JTable table;
	public DefaultTableModel modelo;
	private JScrollPane scrollPane;
	public JTextField txtRuc;
	public JTextField txtNombre;
	private JLabel lblNombre;
	public JTextField txtTelefono;
	private JLabel lblTelefono;
	public JTextField txtDireccion;
	private JLabel lblDireccion;
	public JTextField txtRazonSocial;
	private JLabel lblRazonSocial;
	public JButton btnGuardar;
	public JButton btnCancelar;
	public JButton btnActualizar;
	public JButton btnEliminar;

	/**
	 * Create the panel.
	 */
	public JpProveedores() {
		setOpaque(false);
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 69, 906, 421);
		scrollPane.setOpaque(false);
		this.add(scrollPane);

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setUpdateSelectionOnSort(false);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "RUC", "NOMBRE", "TELEFONO", "DIRECCION", "RAZON SOCIAL" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

		JPanel jpForm = new JPanel();
		jpForm.setBackground(SystemColor.text);
		jpForm.setBorder(new TitledBorder(new LineBorder(new Color(0, 120, 215), 8, true), " Datos del proveedor ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		jpForm.setBounds(29, 10, 367, 480);
		add(jpForm);
		jpForm.setLayout(null);

		JLabel lblCliente = new JLabel("RUC:");
		lblCliente.setBounds(22, 35, 150, 26);
		jpForm.add(lblCliente);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtRuc = new JTextField();
		txtRuc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
				if (txtRuc.getText().length() >= 10) {
					e.consume();
				}
			}

		});
		txtRuc.setBounds(194, 35, 150, 26);
		jpForm.add(txtRuc);
		txtRuc.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(194, 96, 150, 26);
		jpForm.add(txtNombre);

		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(22, 96, 150, 26);
		jpForm.add(lblNombre);

		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
				if (txtTelefono.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(194, 157, 150, 26);
		jpForm.add(txtTelefono);

		lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono.setBounds(22, 157, 150, 26);
		jpForm.add(lblTelefono);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(194, 218, 150, 26);
		jpForm.add(txtDireccion);

		lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion.setBounds(22, 218, 150, 26);
		jpForm.add(lblDireccion);

		txtRazonSocial = new JTextField();
		txtRazonSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(194, 279, 150, 26);
		jpForm.add(txtRazonSocial);

		lblRazonSocial = new JLabel("RAZON SOCIAL:");
		lblRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRazonSocial.setBounds(22, 279, 150, 26);
		jpForm.add(lblRazonSocial);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setBounds(197, 340, 139, 35);
		jpForm.add(btnGuardar);

		btnCancelar = new JButton("Limpiar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(SystemColor.windowBorder);
		btnCancelar.setBounds(29, 340, 139, 35);
		jpForm.add(btnCancelar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizar.setBorderPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(SystemColor.textHighlight);
		btnActualizar.setBounds(197, 410, 139, 35);
		jpForm.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(220, 20, 60));
		btnEliminar.setBounds(29, 410, 139, 35);
		jpForm.add(btnEliminar);

		JPanel jpTitleTable = new JPanel();
		jpTitleTable.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		jpTitleTable.setBackground(SystemColor.text);
		jpTitleTable.setBounds(425, 10, 906, 55);
		add(jpTitleTable);

		JLabel lblTitleTable = new JLabel("Proveedores");
		lblTitleTable.setForeground(SystemColor.textHighlight);
		jpTitleTable.add(lblTitleTable);
		lblTitleTable.setBackground(Color.WHITE);
		lblTitleTable.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitleTable.setHorizontalAlignment(SwingConstants.CENTER);

		// se hace referencia a los eventos del controlador de la vista
		txtRuc.addKeyListener(new ControllerProveedores(this));
		txtNombre.addKeyListener(new ControllerProveedores(this));
		txtTelefono.addKeyListener(new ControllerProveedores(this));
		txtDireccion.addKeyListener(new ControllerProveedores(this));
		txtRazonSocial.addKeyListener(new ControllerProveedores(this));
		btnGuardar.addActionListener(new ControllerProveedores(this));
		btnCancelar.addActionListener(new ControllerProveedores(this));
		btnActualizar.addActionListener(new ControllerProveedores(this));
		btnEliminar.addActionListener(new ControllerProveedores(this));

		table.addMouseListener(new ControllerProveedores(this));
		// evento que se detona cuando el panel se muestra
		addHierarchyListener(new ControllerProveedores(this));
	}
}
