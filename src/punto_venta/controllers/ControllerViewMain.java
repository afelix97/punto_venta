package punto_venta.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import punto_venta.views.*;

//controlador de la vista login
public class ControllerViewMain implements ActionListener, MouseMotionListener, MouseListener {

	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private ViewMainFrame viewMain;

	// load views
	JpAcercaDe jpAcercaDe = new JpAcercaDe();
	JpProveedores jpProveedores = new JpProveedores();
	JpPuntoVenta jpPuntoVenta = new JpPuntoVenta();
	JpProductos jpProductos = new JpProductos();
	JpVentas jpVentas = new JpVentas();
	JpEmpleados jpEmpleados = new JpEmpleados();

	public ControllerViewMain(ViewMainFrame viewMainFrame) {
		super();
		this.viewMain = viewMainFrame;// se carga la vista a manipular
		initHome();// inicia la vista con etiqueta de bienvenido
	}

	// control de eventos botones
	@Override
	public void actionPerformed(ActionEvent e) {

		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == viewMain.btnShowPuntoVenta) {
			clearSelectionMenu();
			viewMain.btnShowPuntoVenta.setBackground(SystemColor.controlShadow);
			clickBtnPuntoVenta();
		}
		if (e.getSource() == viewMain.btnShowProveedores) {
			clearSelectionMenu();
			viewMain.btnShowProveedores.setBackground(SystemColor.controlShadow);
			clickBtnProveedores();
		}
		if (e.getSource() == viewMain.btnShowProductos) {
			clearSelectionMenu();
			viewMain.btnShowProductos.setBackground(SystemColor.controlShadow);
			clickBtnProductos();
		}
		if (e.getSource() == viewMain.btnShowVentas) {
			clearSelectionMenu();
			viewMain.btnShowVentas.setBackground(SystemColor.controlShadow);
			clickBtnVentas();
		}
		if (e.getSource() == viewMain.btnShowConfig) {
			clearSelectionMenu();
			viewMain.btnShowConfig.setBackground(SystemColor.controlShadow);
			clickBtnConfig();
		}
		if (e.getSource() == viewMain.btnCerrarSesion) {
			clickBtnCerrarSesion();
		}
		if (e.getSource() == viewMain.btnShowEmpleados) {
			clearSelectionMenu();
			viewMain.btnShowEmpleados.setBackground(SystemColor.controlShadow);
			clickBtnEmpleados();
		}
	}

	private void clickBtnEmpleados() {
		// Se setea del panel JPEmpleados la variable UserSesion, con el contenido del
		// label UserName de la vista MainFrame
		jpEmpleados.setUserSesion(viewMain.getLblUserName());
		showView(jpEmpleados);
	}

	// quita el efecto de seleccion de opciones del menu
	private void clearSelectionMenu() {
		viewMain.btnShowConfig.setBackground(UIManager.getColor("menu"));
		viewMain.btnShowPuntoVenta.setBackground(UIManager.getColor("menu"));
		viewMain.btnShowProveedores.setBackground(UIManager.getColor("menu"));
		viewMain.btnShowProductos.setBackground(UIManager.getColor("menu"));
		viewMain.btnShowVentas.setBackground(UIManager.getColor("menu"));
		viewMain.btnShowEmpleados.setBackground(UIManager.getColor("menu"));
	}

	private void clickBtnConfig() {

		showView(jpAcercaDe);
	}

	private void clickBtnVentas() {

		showView(jpVentas);
	}

	private void clickBtnProductos() {

		showView(jpProductos);
	}

	private void clickBtnProveedores() {

		showView(jpProveedores);
	}

	private void clickBtnPuntoVenta() {
		// Se setea del panel JPuntoVenta la variable UserSesion, con el contenido del
		// label UserName de la vista MainFrame
		jpPuntoVenta.setUserSesion(viewMain.getLblUserName());
		showView(jpPuntoVenta);
	}

	private void clickBtnCerrarSesion() {
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Atencion",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.OK_OPTION) {
			Login login = new Login();
			login.setVisible(true);
			viewMain.dispose();
		}
	}

	// funcion que se encarga de cambiar el contenido del panel jpBody, para mostrar
	// otro modulo
	private void showView(JPanel view) {
		cleanPanelBody();
		viewMain.jpBody.add(view);
	}

	// limpia el contenido del panel principal
	private void cleanPanelBody() {

		viewMain.jpBody.removeAll();
		viewMain.jpBody.revalidate();
		viewMain.jpBody.repaint();

	}

	// funcion para mostrar la etiqueta de benvenido
	public void initHome() {
		cleanPanelBody();

		JLabel lblBienvenida = new JLabel("¡Bienvenido!");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Yu Gothic", Font.BOLD | Font.ITALIC, 80));
		lblBienvenida.setBounds(75, 95, 1150, 217);
		viewMain.jpBody.add(lblBienvenida);
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		viewMain.btnCerrarSesion.setBackground(new Color(255, 166, 166));
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		viewMain.btnCerrarSesion.setBackground(new Color(220, 20, 60));
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (e.getSource() == viewMain.lblLogo) {
			initHome();
		}
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
