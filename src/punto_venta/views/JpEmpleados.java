package punto_venta.views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerEmpleados;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class JpEmpleados extends JPanel {

	private static final long serialVersionUID = -7688365530795619596L;

	public JTable table;
	public DefaultTableModel modelo;
	private JScrollPane scrollPane;
	public JTextField txtNombre;
	public JTextField txtApellido;
	private JLabel lblNombre;
	public JTextField txtUser;
	private JLabel lblTelefono;
	public JPasswordField txtPass;
	private JLabel lblDireccion;
	public JButton btnGuardar;
	public JButton btnCancelar;
	public JButton btnActualizar;
	public JButton btnEliminar;

	// se almacena el usuario de la sesion
	String[] userSesion;

	/**
	 * Create the panel.
	 */
	public JpEmpleados() {
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
		table.setDefaultEditor(Object.class, null);
		table.setUpdateSelectionOnSort(false);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "NOMBRE", "APELLIDO", "USUARIO", "FECHA ALTA" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

		JPanel jpForm = new JPanel();
		jpForm.setBackground(SystemColor.text);
		jpForm.setBorder(new TitledBorder(new LineBorder(new Color(0, 120, 215), 8, true), " Datos del Empleado",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		jpForm.setBounds(29, 10, 367, 480);
		add(jpForm);
		jpForm.setLayout(null);

		JLabel lblCliente = new JLabel("NOMBRE:");
		lblCliente.setBounds(22, 43, 150, 26);
		jpForm.add(lblCliente);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));

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
		txtNombre.setBounds(194, 43, 150, 26);
		jpForm.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		txtApellido.setColumns(10);
		txtApellido.setBounds(194, 112, 150, 26);
		jpForm.add(txtApellido);

		lblNombre = new JLabel("APELLIDO:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(22, 112, 150, 26);
		jpForm.add(lblNombre);

		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(194, 181, 150, 26);
		jpForm.add(txtUser);

		lblTelefono = new JLabel("USUARIO/MAIL:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono.setBounds(22, 181, 150, 26);
		jpForm.add(lblTelefono);

		txtPass = new JPasswordField();
		txtPass.setColumns(10);
		txtPass.setBounds(194, 250, 150, 26);
		jpForm.add(txtPass);

		lblDireccion = new JLabel("PASSWORD:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDireccion.setBounds(22, 250, 150, 26);
		jpForm.add(lblDireccion);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setBounds(197, 319, 139, 35);
		jpForm.add(btnGuardar);

		btnCancelar = new JButton("Limpiar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(SystemColor.windowBorder);
		btnCancelar.setBounds(29, 319, 139, 35);
		jpForm.add(btnCancelar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizar.setBorderPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(SystemColor.textHighlight);
		btnActualizar.setBounds(197, 397, 139, 35);
		jpForm.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(220, 20, 60));
		btnEliminar.setBounds(29, 397, 139, 35);
		jpForm.add(btnEliminar);

		JPanel jpTitleTable = new JPanel();
		jpTitleTable.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		jpTitleTable.setBackground(SystemColor.text);
		jpTitleTable.setBounds(425, 10, 906, 55);
		add(jpTitleTable);

		JLabel lblTitleTable = new JLabel("Empleados");
		lblTitleTable.setForeground(SystemColor.textHighlight);
		jpTitleTable.add(lblTitleTable);
		lblTitleTable.setBackground(Color.WHITE);
		lblTitleTable.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTitleTable.setHorizontalAlignment(SwingConstants.CENTER);

		// se hace referencia a los eventos del controlador de la vista
		txtApellido.addKeyListener(new ControllerEmpleados(this));
		txtNombre.addKeyListener(new ControllerEmpleados(this));
		txtUser.addKeyListener(new ControllerEmpleados(this));
		txtPass.addKeyListener(new ControllerEmpleados(this));

		btnGuardar.addActionListener(new ControllerEmpleados(this));
		btnCancelar.addActionListener(new ControllerEmpleados(this));
		btnActualizar.addActionListener(new ControllerEmpleados(this));
		btnEliminar.addActionListener(new ControllerEmpleados(this));

		table.addMouseListener(new ControllerEmpleados(this));
		// evento que se detona cuando el panel se muestra
		addHierarchyListener(new ControllerEmpleados(this));
	}

	// obtiene el usuario logueado
	public String[] getUserSesion() {
		return userSesion;
	}

	// se debe setear cuando se manda llammar este jpanel desde otra vista o
	// controlador
	public void setUserSesion(String[] userSesion) {
		this.userSesion = userSesion;
	}
}
