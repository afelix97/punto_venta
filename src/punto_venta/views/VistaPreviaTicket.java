package punto_venta.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import punto_venta.controllers.ControllerViewTicket;

import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class VistaPreviaTicket extends JFrame {

	/**
	 * Vista donde se mostrara el ticket
	 */
	private static final long serialVersionUID = 7307282218011604169L;
	public JPanel contentPane;
	public JTable tableDetalle;
	public JButton btnGenerarTicket;
	public JPanel jpPreview;
	private JLabel lblFecha;
	private JLabel lblCliente;
	public JLabel lblFolioTicket;
	private JLabel lblFolioVenta;
	public JLabel lblTotalVenta;
	public JLabel lblContentNomOrg;
	public JLabel lblTelefonoOrg;
	public JLabel lblContentDirec;
	private JLabel lblContentEmp;
	public JScrollPane scrollDetalle;
	public JPanel jpFooter;

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
	public VistaPreviaTicket() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VistaPreviaTicket.class.getResource("/punto_venta/resources/images/empresa/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 614);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnGenerarTicket = new JButton("GENERAR TICKET");
		btnGenerarTicket.setForeground(Color.WHITE);
		btnGenerarTicket.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGenerarTicket.setBorderPainted(false);
		btnGenerarTicket.setBorder(null);
		btnGenerarTicket.setBackground(new Color(0, 128, 0));
		btnGenerarTicket.setBounds(204, 452, 192, 35);
		contentPane.add(btnGenerarTicket);

		jpPreview = new JPanel();
		jpPreview.setBorder(new LineBorder(SystemColor.textHighlight, 4, true));
		jpPreview.setBackground(Color.WHITE);
		jpPreview.setBounds(8, 43, 583, 399);
		contentPane.add(jpPreview);
		jpPreview.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(
				VistaPreviaTicket.class.getResource("/punto_venta/resources/images/empresa/logo107x100.png")));
		lblNewLabel_1.setBorder(new LineBorder(SystemColor.textHighlight, 3));
		lblNewLabel_1.setBounds(10, 10, 107, 107);
		jpPreview.add(lblNewLabel_1);

		JPanel jpInfoTienda = new JPanel();
		jpInfoTienda.setBackground(Color.WHITE);
		jpInfoTienda.setBounds(147, 10, 288, 107);
		jpPreview.add(jpInfoTienda);
		jpInfoTienda.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("Nombre organización:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2.setBounds(4, 6, 120, 19);
		jpInfoTienda.add(lblNewLabel_2_2);

		lblContentNomOrg = new JLabel("Nombre tienda");
		lblContentNomOrg.setHorizontalAlignment(SwingConstants.CENTER);
		lblContentNomOrg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContentNomOrg.setBounds(115, 6, 160, 19);
		jpInfoTienda.add(lblContentNomOrg);

		JLabel lblNewLabel_2_2_1 = new JLabel("TELEFONO");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2_1.setBounds(4, 31, 120, 19);
		jpInfoTienda.add(lblNewLabel_2_2_1);

		lblTelefonoOrg = new JLabel("numero telefono");
		lblTelefonoOrg.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonoOrg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTelefonoOrg.setBounds(115, 31, 160, 19);
		jpInfoTienda.add(lblTelefonoOrg);

		JLabel lblNewLabel_2_2_1_1 = new JLabel("DIRECCION:");
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2_1_1.setBounds(4, 56, 120, 19);
		jpInfoTienda.add(lblNewLabel_2_2_1_1);

		lblContentDirec = new JLabel("detalle direccion");
		lblContentDirec.setHorizontalAlignment(SwingConstants.CENTER);
		lblContentDirec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContentDirec.setBounds(115, 56, 160, 19);
		jpInfoTienda.add(lblContentDirec);

		JLabel lblNewLabel_2_2_1_2 = new JLabel("EMPLEADO:");
		lblNewLabel_2_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2_1_2.setBounds(4, 81, 120, 19);
		jpInfoTienda.add(lblNewLabel_2_2_1_2);

		lblContentEmp = new JLabel("contenedor empleado");
		lblContentEmp.setHorizontalAlignment(SwingConstants.CENTER);
		lblContentEmp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContentEmp.setBounds(115, 81, 160, 19);
		jpInfoTienda.add(lblContentEmp);

		JLabel lblNewLabel_2_3 = new JLabel("FECHA:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_3.setBounds(439, 10, 49, 30);
		jpPreview.add(lblNewLabel_2_3);

		lblFecha = new JLabel("DD-MM-YYYY");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFecha.setBounds(470, 10, 113, 30);
		jpPreview.add(lblFecha);

		JLabel lblNewLabel_2_3_1_1 = new JLabel("DETALLE DE LA VENTA");
		lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3_1_1.setBounds(136, 115, 310, 30);
		jpPreview.add(lblNewLabel_2_3_1_1);

		scrollDetalle = new JScrollPane();
		scrollDetalle.setBackground(SystemColor.window);
		scrollDetalle.setOpaque(false);
		scrollDetalle.setBounds(20, 141, 543, 142);
		jpPreview.add(scrollDetalle);

		DefaultTableModel modeloDetalle = new DefaultTableModel();
		tableDetalle = new JTable(modeloDetalle);
		tableDetalle.setOpaque(false);
		tableDetalle.setGridColor(SystemColor.textHighlight);
		tableDetalle.setDefaultEditor(Object.class, null);
		tableDetalle.setUpdateSelectionOnSort(false);
		tableDetalle.setForeground(new Color(0, 0, 128));
		tableDetalle.setFont(new Font("Tahoma", Font.BOLD, 12));
		tableDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDetalle.setModel(new DefaultTableModel(new Object[][] { { "1", "Articulo n", "0", "0.00", "0.00" }, },
				new String[] { "#", "ARTICULO", "CANTIDAD", "P. C/U", "SUBTOTAL" }));
		scrollDetalle.setViewportView(tableDetalle);

		JLabel lblNewLabel_2_3_3 = new JLabel("FOLIO:");
		lblNewLabel_2_3_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_3_3.setBounds(439, 47, 49, 30);
		jpPreview.add(lblNewLabel_2_3_3);

		lblFolioTicket = new JLabel("#00000000");
		lblFolioTicket.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolioTicket.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFolioTicket.setBounds(481, 47, 102, 30);
		jpPreview.add(lblFolioTicket);

		JLabel lblNewLabel_2_3_3_1 = new JLabel("CLIENTE:");
		lblNewLabel_2_3_3_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_3_3_1.setBounds(439, 87, 49, 30);
		jpPreview.add(lblNewLabel_2_3_3_1);

		lblCliente = new JLabel("ANONIMO");
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCliente.setBounds(481, 87, 102, 30);
		jpPreview.add(lblCliente);

		jpFooter = new JPanel();
		jpFooter.setBounds(41, 282, 522, 113);
		jpFooter.setOpaque(false);
		jpFooter.setLayout(null);
		jpPreview.add(jpFooter);

		JLabel lblCancelacin = new JLabel("Cancelación");
		lblCancelacin.setBounds(159, 21, 161, 30);
		jpFooter.add(lblCancelacin);
		lblCancelacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelacin.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblTextTotal = new JLabel("TOTAL DE LA VENTA:");
		lblTextTotal.setBounds(218, 0, 176, 30);
		jpFooter.add(lblTextTotal);
		lblTextTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextTotal.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblTotalVenta = new JLabel("$ 0.00");
		lblTotalVenta.setBounds(377, 0, 135, 30);
		jpFooter.add(lblTotalVenta);
		lblTotalVenta.setForeground(new Color(0, 0, 0));
		lblTotalVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVenta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));

		JLabel lblFirma = new JLabel("Firma");
		lblFirma.setBounds(159, 83, 161, 30);
		jpFooter.add(lblFirma);
		lblFirma.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirma.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JSeparator separator = new JSeparator();
		separator.setBounds(118, 83, 242, 8);
		jpFooter.add(separator);
		separator.setForeground(SystemColor.controlText);

		JLabel lblNewLabel = new JLabel("Vista previa de venta con folio :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setBorder(new MatteBorder(4, 4, 4, 0, (Color) SystemColor.textHighlight));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(8, 10, 395, 35);
		contentPane.add(lblNewLabel);

		lblFolioVenta = new JLabel("#00000000");
		lblFolioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolioVenta.setForeground(SystemColor.textHighlight);
		lblFolioVenta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblFolioVenta.setBorder(new MatteBorder(4, 0, 4, 4, (Color) SystemColor.textHighlight));
		lblFolioVenta.setBackground(Color.WHITE);
		lblFolioVenta.setBounds(399, 10, 192, 35);
		contentPane.add(lblFolioVenta);

		btnGenerarTicket.addActionListener(new ControllerViewTicket(this));

		// carga en el ticket la info de la empresa
		addWindowListener(new ControllerViewTicket(this));
	}

	public String getLblFecha() {
		return lblFecha.getText();
	}

	public void setLblFecha(String texto) {
		this.lblFecha.setText(texto);
	}

	public String getLblCliente() {
		return lblCliente.getText();
	}

	public void setLblCliente(String texto) {
		this.lblCliente.setText(texto);
	}

	public String getLblFolioTicket() {
		return lblFolioTicket.getText();
	}

	public void setLblFolioTicket(String texto) {
		this.lblFolioTicket.setText(texto);
	}

	public String getLblFolioVenta() {
		return lblFolioVenta.getText();
	}

	public void setLblFolioVenta(String texto) {
		this.lblFolioVenta.setText(texto);
		;
	}

	public String getLblTotalVenta() {
		return lblTotalVenta.getText();
	}

	public void setLblTotalVenta(String texto) {
		this.lblTotalVenta.setText(texto);
	}

	public String getLblContentEmp() {
		return lblContentEmp.getText();
	}

	public void setLblContentEmp(String texto) {
		this.lblContentEmp.setText(texto);
	}
}
