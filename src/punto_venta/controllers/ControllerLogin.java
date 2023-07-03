package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import punto_venta.models.*;
import punto_venta.views.*;
import punto_venta.objects.*;

//controlador de la vista login
public class ControllerLogin implements ActionListener {

	// se instancia clase userModel
	EmpleadosModel userModel = new EmpleadosModel();
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

		if (e.getSource() == viewLogin.txtUser) {
			viewLogin.txtPass.requestFocus();
		}

		if (e.getSource() == viewLogin.txtPass) {
			viewLogin.btnIniciar.doClick();
		}
	}

	// se define las acciones que se llevaran acabo
	private void actionBtnIniciarSesion() {

		Empleado objUser = new Empleado();

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
			int idEmpleado = userModel.validateUser(objUser);
			if (idEmpleado <= 0) {
				JOptionPane.showMessageDialog(viewLogin, "Usuario invalido");
			} else {
				JOptionPane.showMessageDialog(viewLogin, "Usuario Correcto");
				ViewMainFrame initSistem = new ViewMainFrame();
				initSistem.setVisible(true);
				// se setea de la vista principal el nombre del empleado
				// que inicio sesion
				initSistem.setLblUserName(idEmpleado + " - " + objUser.getUser());
				viewLogin.dispose();
			}
		}
	}

}
