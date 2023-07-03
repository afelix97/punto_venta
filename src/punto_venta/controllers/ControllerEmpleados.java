package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import punto_venta.models.EmpleadosModel;
import punto_venta.objects.Empleado;
import punto_venta.views.JpEmpleados;

public class ControllerEmpleados implements HierarchyListener, ActionListener, KeyListener, MouseListener {
	// se instancia clase userModel
	EmpleadosModel empleadosModel = new EmpleadosModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private JpEmpleados jpEmpleados;

	public ControllerEmpleados(JpEmpleados view) {
		super();
		this.jpEmpleados = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpEmpleados.btnCancelar) {
			actionBtnCancelar();

		}
		if (e.getSource() == jpEmpleados.btnGuardar) {
			actionBtnGuardar();

		}
		if (e.getSource() == jpEmpleados.btnEliminar) {
			actionBtnEliminar();

		}
		if (e.getSource() == jpEmpleados.btnActualizar) {
			actionBtnActualizar();

		}
	}

	private void actionBtnActualizar() {
		int selectedRow = jpEmpleados.table.getSelectedRow();

		if (selectedRow != -1) {

			// se obtiene el id del registro a actualizar
			int idUpdate = (int) jpEmpleados.table.getValueAt(selectedRow, 0);
			// Eliminar la fila seleccionada
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea Actualizar este registro?",
					"Atencion", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {

				// se obtienen los datos de la fila cargada en el formulario
				Empleado objEmpleado = getDataForm();
				objEmpleado.setId(idUpdate);

				if (empleadosModel.actualizarEmpleado(objEmpleado)) {
					JOptionPane.showMessageDialog(jpEmpleados, "Informacion actualizada con exito.");
				}
				int idUserLogeado = Integer
						.parseInt(jpEmpleados.getUserSesion() != null && jpEmpleados.getUserSesion()[0] != null
								? jpEmpleados.getUserSesion()[0]
								: "0");
				loadTable(empleadosModel.getUsers(idUserLogeado));

			}
		}
	}

	private void actionBtnEliminar() {
		int selectedRow = jpEmpleados.table.getSelectedRow();

		if (selectedRow != -1) {

			// se obtiene el id del registro a eliminar
			int idDelete = (int) jpEmpleados.table.getValueAt(selectedRow, 0);
			// Eliminar la fila seleccionada
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este registro?", "Atencion",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {

				if (empleadosModel.deleteEmpleado(idDelete)) {
					JOptionPane.showMessageDialog(jpEmpleados, "Informacion eliminada con exito.");
				}
				int idUserLogeado = Integer
						.parseInt(jpEmpleados.getUserSesion() != null && jpEmpleados.getUserSesion()[0] != null
								? jpEmpleados.getUserSesion()[0]
								: "0");
				loadTable(empleadosModel.getUsers(idUserLogeado));
			}
		}
	}

	private void actionBtnCancelar() {
		jpEmpleados.txtApellido.setText("");
		jpEmpleados.txtNombre.setText("");
		jpEmpleados.txtUser.setText("");
		jpEmpleados.txtPass.setText("");
		validaFormulario();
		jpEmpleados.table.clearSelection();
	}

	// se define las acciones que se llevaran acabo
	private void actionBtnGuardar() {

		Empleado objEmpleado = getDataForm();

		if (objEmpleado.getApellido().isEmpty() || objEmpleado.getNombre().isEmpty() || objEmpleado.getUser().isEmpty()
				|| objEmpleado.getPass().isEmpty()) {

			JOptionPane.showMessageDialog(jpEmpleados, "Todos los campos son obligatorios!", "Advertencia",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (!empleadosModel.guardarEmpleado(objEmpleado)) {
				JOptionPane.showMessageDialog(jpEmpleados, "Ocurrio un problema al guardar la informacion");
			} else {
				JOptionPane.showMessageDialog(jpEmpleados, "Informacion registrada con exito.");
				int idUserLogeado = Integer
						.parseInt(jpEmpleados.getUserSesion() != null && jpEmpleados.getUserSesion()[0] != null
								? jpEmpleados.getUserSesion()[0]
								: "0");
				loadTable(empleadosModel.getUsers(idUserLogeado));
			}
		}
	}

	private Empleado getDataForm() {
		String nombre = jpEmpleados.txtNombre.getText();
		String apellido = jpEmpleados.txtApellido.getText();
		String user = jpEmpleados.txtUser.getText();
		char[] passwordChars = jpEmpleados.txtPass.getPassword();
		String pass = new String(passwordChars);

		Empleado objEmpleado = new Empleado();
		objEmpleado.setNombre(nombre);
		objEmpleado.setApellido(apellido);
		objEmpleado.setUser(user);
		objEmpleado.setPass(pass);

		return objEmpleado;
	}

	private void loadTable(ArrayList<Empleado> empleado) {
		((DefaultTableModel) jpEmpleados.table.getModel()).setRowCount(0);

		int NumCol = jpEmpleados.table.getModel().getColumnCount();

		Object[] fila = new Object[NumCol];

		for (Empleado emp : empleado) {
			fila[0] = emp.getId();
			fila[1] = emp.getNombre();
			fila[2] = emp.getApellido();
			fila[3] = emp.getUser();
			fila[4] = emp.getFechaAlta();
			((DefaultTableModel) jpEmpleados.table.getModel()).addRow(fila);
		}
		actionBtnCancelar();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		validaFormulario();
	}

	private void validaFormulario() {
		String nombres = jpEmpleados.txtNombre.getText();
		String apellidos = jpEmpleados.txtApellido.getText();
		String user = jpEmpleados.txtUser.getText();
		char[] passwordChars = jpEmpleados.txtPass.getPassword();
		String pass = new String(passwordChars);

		if (nombres.isEmpty() || apellidos.isEmpty() || user.isEmpty() || pass.isEmpty()) {

			jpEmpleados.btnGuardar.setEnabled(false);
			jpEmpleados.btnEliminar.setEnabled(false);
			jpEmpleados.btnActualizar.setEnabled(false);
		} else {
			// valida que se activien los botones de cancelar y eliminar si ya esta cargada
			// informacion de un registro
			int selectedRow = jpEmpleados.table.getSelectedRow();
			if (selectedRow != -1) {
				jpEmpleados.btnGuardar.setEnabled(false);
				jpEmpleados.btnEliminar.setEnabled(true);
				jpEmpleados.btnActualizar.setEnabled(true);
			} else {
				jpEmpleados.btnGuardar.setEnabled(true);
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// funcion que se detona cuando el panel de proveeedores se muestra
	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && jpEmpleados.isShowing()) {
			// Acción a realizar cuando el JPanel se muestra
			int idUserLogeado = Integer
					.parseInt(jpEmpleados.getUserSesion() != null && jpEmpleados.getUserSesion()[0] != null
							? jpEmpleados.getUserSesion()[0]
							: "0");
			loadTable(empleadosModel.getUsers(idUserLogeado));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == jpEmpleados.table) {
			actionTable();

		}

	}

	// Metodo que se detona cuando se da click en la tabla
	private void actionTable() {
		int selectedRow = jpEmpleados.table.getSelectedRow();
		if (selectedRow != -1) {

			// se carga la informacion de fila en el formulario
			jpEmpleados.txtNombre.setText((String) jpEmpleados.table.getValueAt(selectedRow, 1));
			jpEmpleados.txtApellido.setText((String) jpEmpleados.table.getValueAt(selectedRow, 2));
			jpEmpleados.txtUser.setText((String) jpEmpleados.table.getValueAt(selectedRow, 3));
			jpEmpleados.txtPass.setText((String) jpEmpleados.table.getValueAt(selectedRow, 4));

			jpEmpleados.btnEliminar.setEnabled(true);
			jpEmpleados.btnActualizar.setEnabled(true);
			jpEmpleados.btnGuardar.setEnabled(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
