package punto_venta.views;

import java.awt.Color;
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
					ViewMainFrame frame = new ViewMainFrame();
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
		jpMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jpMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jpMenu.setBounds(199, 91, 1163, 73);
		contentPane.add(jpMenu);
		jpMenu.setLayout(null);

		btnShowProveedores = new JButton("Proveedores");
		btnShowProveedores.setBounds(270, 16, 150, 39);
		btnShowProveedores.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		jpMenu.add(btnShowProveedores);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(SystemColor.windowBorder);
		separator.setForeground(SystemColor.windowBorder);
		separator.setBounds(690, 16, 9, 39);
		jpMenu.add(separator);

		btnShowProductos = new JButton("Productos");
		btnShowProductos.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowProductos.setBounds(503, 16, 150, 39);
		jpMenu.add(btnShowProductos);

		btnShowPuntoVenta = new JButton("Nueva Venta");
		btnShowPuntoVenta.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowPuntoVenta.setBounds(37, 16, 150, 39);
		jpMenu.add(btnShowPuntoVenta);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(SystemColor.windowBorder);
		separator_2.setBackground(SystemColor.windowBorder);
		separator_2.setBounds(457, 16, 9, 39);
		jpMenu.add(separator_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(SystemColor.windowBorder);
		separator_2_1.setBackground(SystemColor.windowBorder);
		separator_2_1.setBounds(224, 16, 9, 39);
		jpMenu.add(separator_2_1);

		btnShowVentas = new JButton("Ventas");
		btnShowVentas.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowVentas.setBounds(736, 16, 150, 39);
		jpMenu.add(btnShowVentas);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(SystemColor.windowBorder);
		separator_1.setBackground(SystemColor.windowBorder);
		separator_1.setBounds(923, 16, 9, 39);
		jpMenu.add(separator_1);

		btnShowConfig = new JButton("Configuracion");
		btnShowConfig.setBorder(new LineBorder(SystemColor.desktop, 4, true));
		btnShowConfig.setBounds(969, 16, 150, 39);
		jpMenu.add(btnShowConfig);

		jpBody = new JPanel();
		jpBody.setBackground(Color.WHITE);
		jpBody.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		jpBody.setBounds(0, 180, 1362, 500);
		contentPane.add(jpBody);
		jpBody.setLayout(null);

		JLabel lblTitle = new JLabel("Punto de Venta");
		lblTitle.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setBounds(227, 20, 325, 73);
		contentPane.add(lblTitle);
		lblTitle.setFont(new Font("Sylfaen", Font.BOLD, 47));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 681, 1360, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		
				btnCerrarSesion = new JButton("Cerrar Sesion");
				btnCerrarSesion.setBounds(1225, 0, 125, 26);
				panel.add(btnCerrarSesion);
				btnCerrarSesion.setForeground(Color.WHITE);
				btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnCerrarSesion.setBorderPainted(false);
				btnCerrarSesion.setBorder(null);
				btnCerrarSesion.setBackground(new Color(255, 166, 166));
				btnCerrarSesion.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
				btnCerrarSesion.addMouseListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
				btnCerrarSesion.addMouseMotionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento

		addWindowListener(new ControllerViewMain(this));
		lblLogo.addMouseListener(new ControllerViewMain(this));
		btnShowPuntoVenta.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowProveedores.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowProductos.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowVentas.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
		btnShowConfig.addActionListener(new ControllerViewMain(this));// se pone el boton a la escucha del evento
	}

}
