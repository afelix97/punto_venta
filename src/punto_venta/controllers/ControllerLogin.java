package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import punto_venta.models.*;
import punto_venta.views.*;
import punto_venta.objects.*;

//controlador de la vista login
public class ControllerLogin implements WindowListener, ActionListener {

	// se instancia clase userModel
	UserModel userModel = new UserModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private Login viewLogin;

	public ControllerLogin(Login frameLogin) {
		super();
		this.viewLogin = frameLogin;// se carga la vista a manipular
	}

	// control de eventos botones
	@Override
	public void actionPerformed(ActionEvent e) {

		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == viewLogin.btnIniciar) {
			actionBtnIniciarSesion();

		}
	}

	// metodo que se manda llamar al abrir la ventana a la cual esta ligado el
	// controlador
	@Override
	public void windowOpened(WindowEvent e) {
		userModel.getUsers();
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

	// se define las acciones que se llevaran acabo
	private void actionBtnIniciarSesion() {

		User objUser = new User();

		// se obtiene el usuario y password capturado en la vista
		String user = viewLogin.txtUser.getText();
		char[] passwordChars = viewLogin.txtPass.getPassword();
		String pass = new String(passwordChars);

		objUser.setUser(user);
		objUser.setPass(pass);

		if (objUser.getUser().isEmpty() || objUser.getPass().isEmpty()) {
			JOptionPane.showMessageDialog(viewLogin, "Todos los campos son obligatorios!", "Advertencia",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (!userModel.validateUser(objUser)) {
				JOptionPane.showMessageDialog(viewLogin, "Usuario invalido");
			} else {
				JOptionPane.showMessageDialog(viewLogin, "Usuario Correcto");
				ViewMainFrame initSistem = new ViewMainFrame();
				initSistem.setVisible(true);
				viewLogin.dispose();
			}
		}
	}

}
