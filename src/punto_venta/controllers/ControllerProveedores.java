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

import punto_venta.views.JpProveedores;
import punto_venta.models.ProveedorModel;
import punto_venta.objects.Proveedor;

public class ControllerProveedores implements HierarchyListener, ActionListener, KeyListener, MouseListener {
	// se instancia clase userModel
	ProveedorModel proveedorModel = new ProveedorModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private JpProveedores jpProveedores;

	public ControllerProveedores(JpProveedores view) {
		super();
		this.jpProveedores = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpProveedores.btnCancelar) {
			actionBtnCancelar();

		}
		if (e.getSource() == jpProveedores.btnGuardar) {
			actionBtnGuardar();

		}
		if (e.getSource() == jpProveedores.btnEliminar) {
			actionBtnEliminar();

		}
		if (e.getSource() == jpProveedores.btnActualizar) {
			actionBtnActualizar();

		}
	}

	private void actionBtnActualizar() {
		int selectedRow = jpProveedores.table.getSelectedRow();

		if (selectedRow != -1) {

			// se obtiene el id del registro a actualizar
			int idUpdate = (int) jpProveedores.table.getValueAt(selectedRow, 0);
			// Eliminar la fila seleccionada
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea Actualizar este registro?",
					"Atencion", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {

				// se obtienen los datos de la fila cargada en el formulario
				Proveedor objProveedor = getDataForm();
				objProveedor.setId(idUpdate);

				if (proveedorModel.actualizarProveedor(objProveedor)) {
					JOptionPane.showMessageDialog(jpProveedores, "Informacion actualizada con exito.");
				}
				loadTable(proveedorModel.getProveedores());

			}
		}
	}

	private void actionBtnEliminar() {
		int selectedRow = jpProveedores.table.getSelectedRow();

		if (selectedRow != -1) {

			// se obtiene el id del registro a eliminar
			int idDelete = (int) jpProveedores.table.getValueAt(selectedRow, 0);
			// Eliminar la fila seleccionada
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este registro?", "Atencion",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {
				
				if (proveedorModel.deleteProveedor(idDelete)) {
					JOptionPane.showMessageDialog(jpProveedores, "Informacion eliminada con exito.");
				}
				loadTable(proveedorModel.getProveedores());

			}
		}
	}

	private void actionBtnCancelar() {
		jpProveedores.txtRuc.setText("");
		jpProveedores.txtNombre.setText("");
		jpProveedores.txtTelefono.setText("");
		jpProveedores.txtDireccion.setText("");
		jpProveedores.txtRazonSocial.setText("");
		validaFormulario();
		jpProveedores.table.clearSelection();
	}

	// se define las acciones que se llevaran acabo
	private void actionBtnGuardar() {

		Proveedor objProveedor = getDataForm();

		if (objProveedor.getRfc().isEmpty() || objProveedor.getNombre().isEmpty()
				|| objProveedor.getTelefono().isEmpty() || objProveedor.getDireccion().isEmpty()
				|| objProveedor.getRazonSocial().isEmpty()) {

			JOptionPane.showMessageDialog(jpProveedores, "Todos los campos son obligatorios!", "Advertencia",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (!proveedorModel.guardarProveedor(objProveedor)) {
				JOptionPane.showMessageDialog(jpProveedores, "Ocurrio un problema al guardar la informacion");
			} else {
				JOptionPane.showMessageDialog(jpProveedores, "Informacion registrada con exito.");
				loadTable(proveedorModel.getProveedores());
			}
		}
	}

	private Proveedor getDataForm() {
		String ruc = jpProveedores.txtRuc.getText();
		String nombre = jpProveedores.txtNombre.getText();
		String telefono = jpProveedores.txtTelefono.getText();
		String direccion = jpProveedores.txtDireccion.getText();
		String razonSocial = jpProveedores.txtRazonSocial.getText();

		Proveedor objProveedor = new Proveedor();
		objProveedor.setRfc(ruc);
		objProveedor.setNombre(nombre);
		objProveedor.setTelefono(telefono);
		objProveedor.setDireccion(direccion);
		objProveedor.setRazonSocial(razonSocial);

		return objProveedor;
	}

	private void loadTable(ArrayList<Proveedor> proveedores) {
		((DefaultTableModel) jpProveedores.table.getModel()).setRowCount(0);

		int NumCol = jpProveedores.table.getModel().getColumnCount();

		Object[] fila = new Object[NumCol];

		for (Proveedor proveedor : proveedores) {
			fila[0] = proveedor.getId();
			fila[1] = proveedor.getRfc();
			fila[2] = proveedor.getNombre();
			fila[3] = proveedor.getTelefono();
			fila[4] = proveedor.getDireccion();
			fila[5] = proveedor.getRazonSocial();
			((DefaultTableModel) jpProveedores.table.getModel()).addRow(fila);
		}
		actionBtnCancelar();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		validaFormulario();
	}

	private void validaFormulario() {
		String ruc = jpProveedores.txtRuc.getText();
		String nombre = jpProveedores.txtNombre.getText();
		String telefono = jpProveedores.txtTelefono.getText();
		String direccion = jpProveedores.txtDireccion.getText();
		String razonSocial = jpProveedores.txtRazonSocial.getText();

		if (ruc.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || razonSocial.isEmpty()) {

			jpProveedores.btnGuardar.setEnabled(false);
			jpProveedores.btnEliminar.setEnabled(false);
			jpProveedores.btnActualizar.setEnabled(false);
		} else {
			// valida que se activien los botones de cancelar y eliminar si ya esta cargada
			// informacion de un registro
			int selectedRow = jpProveedores.table.getSelectedRow();
			if (selectedRow != -1) {
				jpProveedores.btnGuardar.setEnabled(false);
				jpProveedores.btnEliminar.setEnabled(true);
				jpProveedores.btnActualizar.setEnabled(true);
			} else {
				jpProveedores.btnGuardar.setEnabled(true);
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
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && jpProveedores.isShowing()) {
			// Acción a realizar cuando el JPanel se muestra
			loadTable(proveedorModel.getProveedores());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == jpProveedores.table) {
			actionTable();

		}

	}
//Metodo que se detona cuando se da click en la tabla

	private void actionTable() {
		int selectedRow = jpProveedores.table.getSelectedRow();
		if (selectedRow != -1) {

			// se carga la informacion de fila en el formulario
			jpProveedores.txtRuc.setText((String) jpProveedores.table.getValueAt(selectedRow, 1));
			jpProveedores.txtNombre.setText((String) jpProveedores.table.getValueAt(selectedRow, 2));
			jpProveedores.txtTelefono.setText((String) jpProveedores.table.getValueAt(selectedRow, 3));
			jpProveedores.txtDireccion.setText((String) jpProveedores.table.getValueAt(selectedRow, 4));
			jpProveedores.txtRazonSocial.setText((String) jpProveedores.table.getValueAt(selectedRow, 5));

			jpProveedores.btnEliminar.setEnabled(true);
			jpProveedores.btnActualizar.setEnabled(true);
			jpProveedores.btnGuardar.setEnabled(false);
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
