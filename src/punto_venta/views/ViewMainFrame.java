package punto_venta.views;

import java.awt.Color;
import punto_venta.utilidades.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import punto_venta.controllers.ControllerViewMain;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class ViewMainFrame extends JFrame {

	/**
	 * Vista principal donde se actualizara el contenido de las demas vistas
	 */
	private static final long serialVersionUID = -1905325338726936233L;
	private JPanel contentPane;
	public JPanel jpBody;
	public JButton btnShowProveedores;
	public JButton btnShowProductos;
	public JButton btnShowPuntoVenta;
	public JButton btnShowVentas;
	public JButton btnShowConfig;
	public JButton btnCerrarSesion;
	public JLabel lblLogo;
	private final JPanel panel = new JPanel();
	private JLabel lblFondo;
	private JSeparator separator_3;
	public JButton btnShowEmpleados;
	private JLabel lblNewLabel;
	private JLabel lblUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				try {
					Login frame = new Login();
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
	public ViewMainFrame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ViewMainFrame.class.getResource("/punto_venta/resources/images/empresa/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1374, 748);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setToolTipText("ir a vista principal");
		lblLogo.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		lblLogo.setSize(new Dimension(200, 200));
		lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(
				ViewMainFrame.class.getResource("/punto_venta/resources/images/empresa/logo210x196.png")));
		lblLogo.setBounds(0, 0, 212, 180);
		contentPane.add(lblLogo);

		JPanel jpMenu = new JPanel();
		jpMenu.setOpaque(false);
		jpMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jpMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jpMenu.setBounds(199, 91, 1163, 73);
		contentPane.add(jpMenu);
		jpMenu.setLayout(null);

		btnShowProveedores = new JButton("Proveedores");
		btnShowProveedores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowProveedores.setBounds(611, 16, 134, 39);
		btnShowProveedores.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowProveedores.setForeground(new Color(25, 25, 112));
		btnShowProveedores.setBackground(UIManager.getColor("menu"));
		jpMenu.add(btnShowProveedores);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(SystemColor.windowBorder);
		separator.setForeground(SystemColor.windowBorder);
		separator.setBounds(771, 16, 9, 39);
		jpMenu.add(separator);

		btnShowProductos = new JButton("Productos");
		btnShowProductos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowProductos.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowProductos.setBounds(221, 16, 134, 39);
		btnShowProductos.setForeground(new Color(25, 25, 112));
		btnShowProductos.setBackground(UIManager.getColor("menu"));
		jpMenu.add(btnShowProductos);

		btnShowPuntoVenta = new JButton("Nueva Venta");
		btnShowPuntoVenta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowPuntoVenta.setForeground(new Color(25, 25, 112));
		btnShowPuntoVenta.setBackground(UIManager.getColor("menu"));
		btnShowPuntoVenta.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowPuntoVenta.setBounds(26, 16, 134, 39);
		jpMenu.add(btnShowPuntoVenta);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(SystemColor.windowBorder);
		separator_2.setBackground(SystemColor.windowBorder);
		separator_2.setBounds(186, 16, 9, 39);
		jpMenu.add(separator_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(SystemColor.windowBorder);
		separator_2_1.setBackground(SystemColor.windowBorder);
		separator_2_1.setBounds(576, 16, 9, 39);
		jpMenu.add(separator_2_1);

		btnShowVentas = new JButton("Ventas");
		btnShowVentas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowVentas.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowVentas.setBounds(806, 16, 134, 39);
		btnShowVentas.setForeground(new Color(25, 25, 112));
		btnShowVentas.setBackground(UIManager.getColor("menu"));
		jpMenu.add(btnShowVentas);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(SystemColor.windowBorder);
		separator_1.setBackground(SystemColor.windowBorder);
		separator_1.setBounds(966, 16, 9, 39);
		jpMenu.add(separator_1);

		btnShowConfig = new JButton("Configuracion");
		btnShowConfig.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowConfig.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowConfig.setBounds(1001, 16, 134, 39);
		btnShowConfig.setForeground(new Color(25, 25, 112));
		btnShowConfig.setBackground(UIManager.getColor("menu"));
		jpMenu.add(btnShowConfig);

		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(SystemColor.windowBorder);
		separator_3.setBackground(SystemColor.windowBorder);
		separator_3.setBounds(381, 16, 9, 39);
		jpMenu.add(separator_3);

		btnShowEmpleados = new JButton("Empleados");
		btnShowEmpleados.setForeground(new Color(25, 25, 112));
		btnShowEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowEmpleados.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowEmpleados.setBackground(UIManager.getColor("menu"));
		btnShowEmpleados.setBounds(416, 16, 134, 39);
		jpMenu.add(btnShowEmpleados);

		jpBody = new JPanel();
		jpBody.setOpaque(false);
		jpBody.setBackground(Color.WHITE);
		jpBody.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		jpBody.setBounds(0, 180, 1362, 500);
		contentPane.add(jpBody);
		jpBody.setLayout(null);

		JLabel lblTitle = new JLabel("Punto de Venta");
		lblTitle.setForeground(SystemColor.text);
		lblTitle.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setBounds(217, 20, 392, 73);
		contentPane.add(lblTitle);
		lblTitle.setFont(new Font("Sylfaen", Font.BOLD, 55));
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 681, 1360, 30);
		contentPane.add(panel);
		panel.setLayout(null);

		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBounds(1225, 3, 125, 26);
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBackground(new Color(255, 166, 166));
		btnCerrarSesion.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnCerrarSesion.addMouseListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnCerrarSesion.addMouseMotionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		panel.add(btnCerrarSesion);

		lblNewLabel = new JLabel("USUARIO: ");
		lblNewLabel.setForeground(new Color(245, 255, 250));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 1, 118, 27);
		panel.add(lblNewLabel);

		lblUserName = new JLabel("USER NAME");
		lblUserName.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(240, 255, 255)));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setForeground(new Color(245, 255, 250));
		lblUserName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUserName.setBounds(115, 1, 248, 27);
		panel.add(lblUserName);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) SystemColor.textHighlight));
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(1132, 20, 218, 36);
		contentPane.add(panel_1);

		JLabel lblDateTime = new JLabel("Fecha/Hora");
		lblDateTime.setForeground(SystemColor.textHighlight);
		lblDateTime.setBackground(Color.WHITE);
		panel_1.add(lblDateTime);
		lblDateTime.setFont(new Font("Source Serif Pro Black", Font.BOLD, 20));

		DateTimeThread clockThread = new DateTimeThread(lblDateTime);

		lblFondo = new JLabel("");
		lblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondo.setIcon(new ImageIcon(ViewMainFrame.class.getResource("/punto_venta/resources/images/fondo2.jpg")));
		lblFondo.setBounds(-344, -56, 1961, 957);
		contentPane.add(lblFondo);
		clockThread.start();

		lblLogo.addMouseListener(new ControllerViewMain(this));
		btnShowPuntoVenta.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowProveedores.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowProductos.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowVentas.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowConfig.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowEmpleados.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
	}

	public void setLblUserName(String lblUserNameText) {
		this.lblUserName.setText(lblUserNameText);
	}

	public String[] getLblUserName() {
		return lblUserName.getText().split(" - ");
	}
}
