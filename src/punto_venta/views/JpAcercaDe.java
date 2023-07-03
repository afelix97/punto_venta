package punto_venta.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import punto_venta.controllers.ControllerConfiguracion;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class JpAcercaDe extends JPanel {

	/**
	 * Vista que muestra informacion hacerca de la empresa
	 */
	private static final long serialVersionUID = -8706669607118444108L;
	public JTextField txtDireccion;
	public JTextField txtNombre;
	private JLabel lblNombre;
	public JTextField txtTelefono;
	private JLabel lblDireccion;
	public JTextField txtRazonSocial;
	private JLabel lblRazonSocial;
	public JButton btnGuardar;

	/**
	 * Create the panel.
	 */
	public JpAcercaDe() {

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);
		this.setOpaque(false);

		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setBounds(0, 0, 1362, 500);
		this.setLayout(null);

		JPanel jpForm = new JPanel();
		jpForm.setBackground(SystemColor.text);
		jpForm.setBorder(new LineBorder(new Color(0, 120, 215), 8, true));
		jpForm.setBounds(400, 75, 561, 417);
		add(jpForm);
		jpForm.setLayout(null);

		JLabel lblCliente = new JLabel("DIRECCION: ");
		lblCliente.setBounds(56, 190, 162, 35);
		jpForm.add(lblCliente);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 18));

		txtDireccion = new JTextField();
		txtDireccion.setForeground(SystemColor.textHighlight);
		txtDireccion.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtDireccion.setBounds(274, 190, 231, 35);
		jpForm.add(txtDireccion);
		txtDireccion.setColumns(10);

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
		txtNombre.setForeground(SystemColor.textHighlight);
		txtNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtNombre.setColumns(10);
		txtNombre.setBounds(274, 46, 231, 35);
		jpForm.add(txtNombre);

		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombre.setBounds(56, 46, 162, 35);
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
		txtTelefono.setForeground(SystemColor.textHighlight);
		txtTelefono.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(274, 118, 231, 35);
		jpForm.add(txtTelefono);

		lblDireccion = new JLabel("TELEFONO:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDireccion.setBounds(56, 118, 162, 35);
		jpForm.add(lblDireccion);

		txtRazonSocial = new JTextField();
		txtRazonSocial.setForeground(SystemColor.textHighlight);
		txtRazonSocial.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(274, 253, 231, 35);
		jpForm.add(txtRazonSocial);

		lblRazonSocial = new JLabel("RAZON SOCIAL:");
		lblRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRazonSocial.setBounds(56, 253, 162, 35);
		jpForm.add(lblRazonSocial);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBorderPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(0, 128, 0));
		btnGuardar.setBounds(135, 334, 290, 35);
		jpForm.add(btnGuardar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(64, 64, 64), 4, true));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(228, 10, 906, 55);
		add(panel_1);

		JLabel lblNewLabel = new JLabel("Datos de la Empresa");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		btnGuardar.addActionListener(new ControllerConfiguracion(this));
		addHierarchyListener(new ControllerConfiguracion(this));
	}
}
