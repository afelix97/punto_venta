package punto_venta.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import punto_venta.models.*;
import punto_venta.views.*;

//controlador de la vista login
public class ControllerViewMain implements WindowListener, ActionListener, MouseMotionListener, MouseListener {

	// se instancia clase userModel
	UserModel userModel = new UserModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private ViewMainFrame viewMain;

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
			clickBtnPuntoVenta();
		}
		if (e.getSource() == viewMain.btnShowProveedores) {
			clickBtnProveedores();
		}
		if (e.getSource() == viewMain.btnShowProductos) {
			clickBtnProductos();
		}
		if (e.getSource() == viewMain.btnShowVentas) {
			clickBtnVentas();
		}
		if (e.getSource() == viewMain.btnShowConfig) {
			clickBtnConfig();
		}
		if (e.getSource() == viewMain.btnCerrarSesion) {
			clickBtnCerrarSesion();
		}
	}

	private void clickBtnConfig() {
		JpAcercaDe jpAcercaDe = new JpAcercaDe();
		showView(jpAcercaDe);
	}

	private void clickBtnVentas() {
		JpVentas jpVentas = new JpVentas();
		showView(jpVentas);
	}

	private void clickBtnProductos() {
		JpProductos jpProductos = new JpProductos();
		showView(jpProductos);
	}

	private void clickBtnProveedores() {
		JpProveedores jpProveedores = new JpProveedores();
		showView(jpProveedores);
	}

	private void clickBtnPuntoVenta() {
		JpPuntoVenta jpPuntoVenta = new JpPuntoVenta();
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

	// metodo que se manda llamar al abrir la ventana a la cual esta ligado el
	// controlador
	@Override
	public void windowOpened(WindowEvent e) {
		userModel.getUsers();
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (e.getSource() == viewMain.lblLogo) {
			initHome();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

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
