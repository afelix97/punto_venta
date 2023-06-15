package punto_venta.views;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import punto_venta.controllers.ControllerLogin;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class Login extends JFrame {

	/**
	 * Vista que muestra el inicio de sesion de la aplicacion
	 */
	private static final long serialVersionUID = -6856823744384764198L;
	private JPanel contentPane;
	public JTextField txtUser;
	public JPasswordField txtPass;
	public JButton btnIniciar;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Login.class.getResource("/punto_venta/resources/images/empresa/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 499);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 120, 215), 4, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(412, 13, 299, 436);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTitle = new JLabel("Inicio de Sesion");
		lblTitle.setBounds(20, 170, 259, 52);
		panel.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));

		JLabel lblUser = new JLabel("Usuario: ");
		lblUser.setBounds(55, 232, 188, 27);
		panel.add(lblUser);
		lblUser.setForeground(SystemColor.textHighlight);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblPass = new JLabel("Contraseña: ");
		lblPass.setBounds(55, 303, 188, 27);
		panel.add(lblPass);
		lblPass.setForeground(SystemColor.textHighlight);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 18));

		txtUser = new JTextField();
		txtUser.setBounds(55, 259, 188, 27);
		panel.add(txtUser);
		txtUser.setHorizontalAlignment(SwingConstants.LEFT);
		txtUser.setToolTipText("Escribe aquí...");
		txtUser.setForeground(SystemColor.textHighlight);
		txtUser.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.textHighlight));
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.setColumns(10);

		txtPass = new JPasswordField();
		txtPass.setEchoChar('*');
		txtPass.setBounds(55, 329, 188, 27);
		panel.add(txtPass);
		txtPass.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass.setToolTipText("Escribe aquí...");
		txtPass.setForeground(SystemColor.textHighlight);
		txtPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.textHighlight));

		btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.setBounds(62, 373, 175, 35);
		panel.add(btnIniciar);
		btnIniciar.setBorderPainted(false);
		btnIniciar.setBorder(null);
		btnIniciar.setForeground(SystemColor.text);
		btnIniciar.setBackground(SystemColor.textHighlight);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(44, 0, 210, 214);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/punto_venta/resources/images/empresa/logo210x196.png")));
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/punto_venta/resources/images/login400x400.png")));
		lblNewLabel_1.setBounds(50, 75, 312, 312);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/punto_venta/resources/images/fondo2.jpg")));
		lblNewLabel_2.setBounds(-475, 0, 1370, 939);
		contentPane.add(lblNewLabel_2);
		
		btnIniciar.addActionListener(new ControllerLogin(this));// se pone el boton a la escucha del evento
		txtPass.addActionListener(new ControllerLogin(this));// se pone el boton a la escucha del evento
		txtUser.addActionListener(new ControllerLogin(this));// se pone el boton a la escucha del evento

		// se agrega controlador de la vista
		addWindowListener(new ControllerLogin(this)); // se pone el frame a la escucha del evento
	}

}
