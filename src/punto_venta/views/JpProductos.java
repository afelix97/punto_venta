package punto_venta.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerViewProductos;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class JpProductos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8706669607118444108L;
	private JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel modelo;
	public JTextField txtCodigo;
	public JTextField txtNombre;
	private JLabel lblNombre;
	private JLabel lblProv;
	public JTextField txtPrecio;
	private JLabel lblPrecio;
	public JTextField txtCantidad;
	private JLabel lblRazonSocial;
	public JButton btnGuardar;
	public JButton btnLimpiar;
	public JButton btnActualizar;
	public JButton btnEliminar;
	public JTextField txtDescripcion;
	public JComboBox<String> cmbxProveedor;
	public JButton btnCopy;

	/**
	 * Create the panel.
	 */
	public JpProductos() {
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
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "CODIGO", "NOMBRE", "DESCRIPCION", "PROVEEDOR", "PRECIO", "STOCK" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(25);
		scrollPane.setViewportView(table);

		JPanel jpForm = new JPanel();
		jpForm.setBackground(SystemColor.text);
		jpForm.setBorder(new TitledBorder(new LineBorder(new Color(0, 120, 215), 8, true), " Datos del Producto ",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		jpForm.setBounds(29, 10, 386, 480);
		add(jpForm);
		jpForm.setLayout(null);

		JLabel lblCliente = new JLabel("CODIGO:");
		lblCliente.setBounds(20, 28, 95, 26);
		jpForm.add(lblCliente);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtCodigo = new JTextField();
		txtCodigo.setText("0");
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
				if (txtCodigo.getText().length() >= 8) {
					e.consume();
				}
			}
		});
		txtCodigo.setBounds(202, 28, 162, 26);
		txtCodigo.setColumns(10);
		jpForm.add(txtCodigo);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(202, 82, 162, 26);
		jpForm.add(txtNombre);

		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(20, 82, 162, 26);
		jpForm.add(lblNombre);

		lblProv = new JLabel("PROVEEDOR:");
		lblProv.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProv.setBounds(20, 190, 162, 26);
		jpForm.add(lblProv);

		txtPrecio = new JTextField();
		txtPrecio.setText("0");
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// Verificar si el carácter es un número o el separador decimal
				if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
				if (!txtPrecio.getText().isEmpty()) {
					String[] cantidadSinDecimales = txtPrecio.getText().split("\\.");
					if (cantidadSinDecimales[0].length() >= 5) {
						e.consume();
					}
				}

			}
		});
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(202, 244, 162, 26);
		jpForm.add(txtPrecio);

		lblPrecio = new JLabel("PRECIO:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrecio.setBounds(20, 244, 162, 26);
		jpForm.add(lblPrecio);

		txtCantidad = new JTextField();
		txtCantidad.setText("1");
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
				if (txtCantidad.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(202, 298, 162, 26);
		jpForm.add(txtCantidad);

		lblRazonSocial = new JLabel("CANTIDAD:");
		lblRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRazonSocial.setBounds(20, 298, 162, 26);
		jpForm.add(lblRazonSocial);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setBounds(211, 352, 139, 35);
		jpForm.add(btnGuardar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(SystemColor.windowBorder);
		btnLimpiar.setBounds(36, 352, 139, 35);
		jpForm.add(btnLimpiar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizar.setBorderPainted(false);
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(SystemColor.textHighlight);
		btnActualizar.setBounds(211, 415, 139, 35);
		jpForm.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(220, 20, 60));
		btnEliminar.setBounds(36, 415, 139, 35);
		jpForm.add(btnEliminar);

		cmbxProveedor = new JComboBox<String>();
		cmbxProveedor.setBounds(202, 190, 162, 26);
		jpForm.add(cmbxProveedor);

		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescripcion.setBounds(20, 136, 162, 26);
		jpForm.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(202, 136, 162, 26);
		jpForm.add(txtDescripcion);

		btnCopy = new JButton("copy");
		btnCopy.setBackground(new Color(175, 238, 238));
		btnCopy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCopy.setBounds(150, 28, 51, 26);
		jpForm.add(btnCopy);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(425, 10, 906, 55);
		add(panel_1);

		JLabel lblNewLabel = new JLabel("Productos");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// se hace referencia a los eventos del controlador de la vista
		txtCodigo.addKeyListener(new ControllerViewProductos(this));
		txtNombre.addKeyListener(new ControllerViewProductos(this));
		txtDescripcion.addKeyListener(new ControllerViewProductos(this));
		txtCantidad.addKeyListener(new ControllerViewProductos(this));
		txtPrecio.addKeyListener(new ControllerViewProductos(this));
		cmbxProveedor.addActionListener(new ControllerViewProductos(this));
		btnGuardar.addActionListener(new ControllerViewProductos(this));
		btnLimpiar.addActionListener(new ControllerViewProductos(this));
		btnActualizar.addActionListener(new ControllerViewProductos(this));
		btnEliminar.addActionListener(new ControllerViewProductos(this));
		btnCopy.addActionListener(new ControllerViewProductos(this));

		table.addMouseListener(new ControllerViewProductos(this));
		// evento que se detona cuando el panel se muestra
		addHierarchyListener(new ControllerViewProductos(this));

	}
}
