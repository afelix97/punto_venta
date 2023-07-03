package punto_venta.views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerViewPuntoVenta;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class JpPuntoVenta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8706669607118444108L;
	private JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel modelo;
	private JLabel lblPrecio;
	private JLabel lblCantidad_2;
	public JTextField txtPrecio;
	public JTextField txtCodigo;
	public JTextField txtProducto;
	public JTextField txtCantidad;
	public JTextField txtStock;
	public JTextField txtCliente;
	public JTextField txtTotal;
	public JButton btnAgregar;
	public JButton btnEliminarRow;
	public JButton btnNuevaVenta;
	public JButton btnFacturar;

	String[] userSesion;

	/**
	 * Create the panel.
	 */
	public JpPuntoVenta() {
		setOpaque(false);

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);

		modelo = new DefaultTableModel();

		JLabel lblTotal_1 = new JLabel("$");
		lblTotal_1.setForeground(SystemColor.textHighlight);
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 42));
		lblTotal_1.setBounds(980, 383, 32, 69);
		add(lblTotal_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(183, 201, 995, 164);
		this.add(scrollPane);

		scrollPane.setOpaque(false);
		table = new JTable(modelo);
		table.setDefaultEditor(Object.class, null);
		table.setForeground(new Color(0, 0, 128));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "CODIGO", "NOMBRE", "DESCRIPCION", "CANTIDAD", "PRECIO", "SUBTOTAL" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(230);
		table.getColumnModel().getColumn(2).setPreferredWidth(230);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.setOpaque(false);
		scrollPane.setViewportView(table);

		btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.setForeground(UIManager.getColor("ToolTip.foreground"));
		btnNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevaVenta.setBorderPainted(false);
		btnNuevaVenta.setBorder(null);
		btnNuevaVenta.setBackground(new Color(224, 255, 255));
		btnNuevaVenta.setBounds(1217, 56, 122, 56);
		add(btnNuevaVenta);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCliente.setBounds(183, 396, 249, 26);
		add(lblCliente);

		txtCliente = new JTextField();
		txtCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_DELETE) {
					e.consume();
				}
				if (txtCliente.getText().length() >= 50) {
					e.consume();
				}
			}
		});
		txtCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtCliente.setOpaque(false);
		txtCliente.setForeground(SystemColor.textHighlight);
		txtCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCliente.setColumns(10);
		txtCliente.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		txtCliente.setBounds(183, 426, 249, 26);
		add(txtCliente);

		btnFacturar = new JButton("Finalizar venta");
		btnFacturar.setEnabled(false);
		btnFacturar.setForeground(Color.WHITE);
		btnFacturar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFacturar.setBorderPainted(false);
		btnFacturar.setBorder(null);
		btnFacturar.setBackground(new Color(0, 128, 0));
		btnFacturar.setBounds(616, 396, 130, 56);
		add(btnFacturar);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblTotal.setBounds(892, 391, 89, 56);
		add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setOpaque(false);
		txtTotal.setText("0.00");
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(SystemColor.textHighlight);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 34));
		txtTotal.setColumns(10);
		txtTotal.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		txtTotal.setBounds(980, 391, 198, 56);
		add(txtTotal);

		btnEliminarRow = new JButton("X");
		btnEliminarRow.setEnabled(false);
		btnEliminarRow.setForeground(Color.WHITE);
		btnEliminarRow.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminarRow.setBorderPainted(false);
		btnEliminarRow.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btnEliminarRow.setBackground(new Color(220, 20, 60));
		btnEliminarRow.setBounds(1178, 201, 26, 26);
		add(btnEliminarRow);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(183, 148, 995, 55);
		add(panel_1);

		JLabel lblCarritoDeCompra = new JLabel("Carrito de compra");
		lblCarritoDeCompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarritoDeCompra.setForeground(SystemColor.textHighlight);
		lblCarritoDeCompra.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblCarritoDeCompra.setBackground(Color.WHITE);
		panel_1.add(lblCarritoDeCompra);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(183, 44, 995, 82);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(43, 16, 117, 26);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE
						&& c != KeyEvent.VK_ENTER) {
					e.consume();
				}
				if (txtCodigo.getText().length() >= 8) {
					e.consume();
				}
			}
		});
		txtCodigo.setBounds(43, 46, 117, 26);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setOpaque(false);
		txtCodigo.setForeground(SystemColor.textHighlight);
		txtCodigo.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCodigo.setColumns(10);
		panel.add(txtCodigo);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(203, 16, 249, 26);
		panel.add(lblProducto);
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtProducto = new JTextField();
		txtProducto.setEditable(false);
		txtProducto.setBounds(203, 46, 249, 26);
		txtProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtProducto.setOpaque(false);
		txtProducto.setForeground(SystemColor.textHighlight);
		txtProducto.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtProducto.setColumns(10);
		txtProducto.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		panel.add(txtProducto);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(495, 16, 89, 26);
		panel.add(lblCantidad);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtCantidad = new JTextField();
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
		txtCantidad.setBounds(495, 46, 89, 26);
		txtCantidad.setOpaque(false);
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setForeground(SystemColor.textHighlight);
		txtCantidad.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtCantidad.setColumns(10);
		txtCantidad.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		panel.add(txtCantidad);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(627, 16, 89, 26);
		panel.add(lblPrecio);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(627, 46, 89, 26);
		txtPrecio.setOpaque(false);
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setForeground(SystemColor.textHighlight);
		txtPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPrecio.setColumns(10);
		txtPrecio.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		panel.add(txtPrecio);

		lblCantidad_2 = new JLabel("Stock Dis:");
		lblCantidad_2.setBounds(759, 16, 89, 26);
		lblCantidad_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblCantidad_2);

		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setBounds(759, 46, 89, 26);
		txtStock.setOpaque(false);
		txtStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtStock.setForeground(SystemColor.textHighlight);
		txtStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtStock.setColumns(10);
		txtStock.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 120, 215)));
		panel.add(txtStock);

		btnAgregar = new JButton("+");
		btnAgregar.setBounds(891, 16, 56, 56);
		btnAgregar.setEnabled(false);
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnAgregar.setBorderPainted(false);
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(0, 128, 0));
		panel.add(btnAgregar);

		btnAgregar.addActionListener(new ControllerViewPuntoVenta(this));
		btnEliminarRow.addActionListener(new ControllerViewPuntoVenta(this));
		btnNuevaVenta.addActionListener(new ControllerViewPuntoVenta(this));
		btnFacturar.addActionListener(new ControllerViewPuntoVenta(this));
		txtCodigo.addActionListener(new ControllerViewPuntoVenta(this));

		txtCodigo.addKeyListener(new ControllerViewPuntoVenta(this));
		txtCantidad.addKeyListener(new ControllerViewPuntoVenta(this));
		txtCliente.addKeyListener(new ControllerViewPuntoVenta(this));

		table.addMouseListener(new ControllerViewPuntoVenta(this));
		addHierarchyListener(new ControllerViewPuntoVenta(this));
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
