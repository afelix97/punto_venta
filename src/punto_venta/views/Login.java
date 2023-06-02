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

public class Login extends JFrame {

	/**
	 * Vista que muestra el inicio de sesion de la aplicacion
	 */
	private static final long serialVersionUID = -6856823744384764198L;
	private JPanel contentPane;
	public JTextField txtUser;
	public JPasswordField txtPass;
	public JButton btnIniciar;

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
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Inicio de Sesion");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 35));
		lblTitle.setBounds(69, 0, 298, 52);
		contentPane.add(lblTitle);

		JLabel lblUser = new JLabel("Usuario: ");
		lblUser.setForeground(SystemColor.textHighlight);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUser.setBounds(95, 72, 125, 27);
		contentPane.add(lblUser);

		JLabel lblPass = new JLabel("Contraseña: ");
		lblPass.setForeground(SystemColor.textHighlight);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPass.setBounds(95, 123, 125, 27);
		contentPane.add(lblPass);

		txtUser = new JTextField();
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtUser.setToolTipText("Escribe aquí...");
		txtUser.setForeground(SystemColor.textHighlight);
		txtUser.setBorder(new LineBorder(SystemColor.textHighlight, 3, true));
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.setBounds(218, 72, 139, 27);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPass = new JPasswordField();
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setToolTipText("Escribe aquí...");
		txtPass.setForeground(SystemColor.textHighlight);
		txtPass.setBorder(new LineBorder(SystemColor.textHighlight, 3, true));
		txtPass.setBounds(218, 123, 139, 27);
		contentPane.add(txtPass);

		btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.setBorderPainted(false);
		btnIniciar.setBorder(null);
		btnIniciar.setForeground(SystemColor.text);
		btnIniciar.setBackground(SystemColor.textHighlight);
		btnIniciar.setBounds(148, 204, 139, 35);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnIniciar);

		// se agrega controlador de la vista
		addWindowListener(new ControllerLogin(this)); // se pone el frame a la escucha del evento
		btnIniciar.addActionListener(new ControllerLogin(this));// se pone el boton a la escucha del evento
	}

}
