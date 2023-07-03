package punto_venta.controllers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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

import punto_venta.models.ProductosModel;
import punto_venta.models.ProveedorModel;
import punto_venta.objects.Producto;
import punto_venta.objects.Proveedor;
import punto_venta.views.JpProductos;

public class ControllerViewProductos implements HierarchyListener, ActionListener, KeyListener, MouseListener {
	// se instancia clase userModel
	ProductosModel productosModel = new ProductosModel();
	ProveedorModel proveedoresModel = new ProveedorModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private JpProductos jpProductos;

	public ControllerViewProductos(JpProductos view) {
		super();
		this.jpProductos = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpProductos.btnLimpiar) {
			actionBtnLimpiar();

		}
		if (e.getSource() == jpProductos.btnGuardar) {
			actionBtnGuardar();

		}
		if (e.getSource() == jpProductos.btnEliminar) {
			actionBtnEliminar();

		}
		if (e.getSource() == jpProductos.btnActualizar) {
			actionBtnActualizar();

		}
		if (e.getSource() == jpProductos.cmbxProveedor) {
			validaFormulario();
		}

		if (e.getSource() == jpProductos.btnCopy) {
			String codigo = jpProductos.txtCodigo.getText();
			if (!codigo.isEmpty() && !codigo.equals("0")) {
				StringSelection selection = new StringSelection(codigo);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, null);
			}
		}
	}

	private void actionBtnActualizar() {
		int selectedRow = jpProductos.table.getSelectedRow();

		if (selectedRow != -1) {

			if (validaFormulario()) {

				// se obtiene el id del registro a actualizar
				int idUpdate = (int) jpProductos.table.getValueAt(selectedRow, 0);
				// Eliminar la fila seleccionada
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea Actualizar este registro?",
						"Atencion", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {

					// se obtienen los datos de la fila cargada en el formulario
					Producto objProducto = getDataForm();
					objProducto.setCodigo(idUpdate);

					if (productosModel.actualizarProducto(objProducto)) {
						JOptionPane.showMessageDialog(jpProductos, "Informacion actualizada con exito.");
					}
					loadTable(productosModel.getProductos());

				}
			}
		}
	}

	private Producto getDataForm() {

		int codigo = Integer.parseInt(jpProductos.txtCodigo.getText());
		String nombre = jpProductos.txtNombre.getText();
		String descripcion = jpProductos.txtDescripcion.getText();
		float precio = Float.parseFloat(jpProductos.txtPrecio.getText());
		int stock = Integer.parseInt(jpProductos.txtCantidad.getText());

		// Dividir la frase en palabras utilizando el espacio como delimitador
		String[] palabras = jpProductos.cmbxProveedor.getSelectedItem().toString().split(" - ");
		int id_prov = Integer.parseInt(palabras[0]);

		Producto producto = new Producto();
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setStock(stock);
		producto.setIdProveedor(id_prov);

		return producto;
	}

	private void loadTable(ArrayList<Producto> productos) {
		((DefaultTableModel) jpProductos.table.getModel()).setRowCount(0);

		int NumCol = jpProductos.table.getModel().getColumnCount();

		Object[] fila = new Object[NumCol];

		for (Producto producto : productos) {
			fila[0] = producto.getCodigo();
			fila[1] = producto.getNombre();
			fila[2] = producto.getDescripcion();
			Proveedor prov = proveedoresModel.getProveedor(producto.getIdProveedor());
			fila[3] = prov.getId() + " - " + prov.getNombre();// se obtiene el nombre del
			fila[4] = producto.getPrecio();
			fila[5] = producto.getStock();

			((DefaultTableModel) jpProductos.table.getModel()).addRow(fila);
		}
		actionBtnCancelar();
	}

	private void actionBtnCancelar() {
		jpProductos.txtCodigo.setText("0");
		jpProductos.txtNombre.setText("");
		jpProductos.txtDescripcion.setText("");
		jpProductos.txtPrecio.setText("0");
		jpProductos.txtCantidad.setText("1");
		jpProductos.cmbxProveedor.setSelectedIndex(0);
		validaFormulario();
		jpProductos.table.clearSelection();
	}

	private boolean validaFormulario() {
		boolean isValid = false;
		int codigo = Integer
				.parseInt(!jpProductos.txtCodigo.getText().isEmpty() ? jpProductos.txtCodigo.getText() : "0");
		String nombre = jpProductos.txtNombre.getText();
		String descripcion = jpProductos.txtDescripcion.getText();
		float precio = Float
				.parseFloat(!jpProductos.txtPrecio.getText().isEmpty() ? jpProductos.txtPrecio.getText() : "0");
		int stock = !jpProductos.txtCantidad.getText().isEmpty() ? Integer.parseInt(jpProductos.txtCantidad.getText())
				: 0;

		String[] infoProv = jpProductos.cmbxProveedor.getSelectedItem().toString().split(" - ");
		int id_prov = Integer.parseInt(infoProv[0]);

		if (codigo == 0 || nombre.isEmpty() || descripcion.isEmpty() || precio == 0 || stock == 0 || id_prov == 0) {

			jpProductos.btnGuardar.setEnabled(false);
			jpProductos.btnEliminar.setEnabled(false);
			jpProductos.btnActualizar.setEnabled(false);
		} else {
			isValid = true;// sera true cuando todos los campos del formulario esten llenados

			// valida que se activien los botones de cancelar y eliminar si ya esta cargada
			// informacion de un registro
			int selectedRow = jpProductos.table.getSelectedRow();
			if (selectedRow != -1) {
				jpProductos.btnGuardar.setEnabled(false);
				jpProductos.btnEliminar.setEnabled(true);
				jpProductos.btnActualizar.setEnabled(true);
			} else {
				jpProductos.btnGuardar.setEnabled(true);
			}

		}

		return isValid;
	}

	private void actionBtnEliminar() {
		int selectedRow = jpProductos.table.getSelectedRow();

		if (selectedRow != -1) {

			// se obtiene el id del registro a eliminar
			int idDelete = (int) jpProductos.table.getValueAt(selectedRow, 0);
			// Eliminar la fila seleccionada
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este registro?", "Atencion",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {

				if (productosModel.deleteProducto(idDelete)) {
					JOptionPane.showMessageDialog(jpProductos, "Informacion eliminada con exito.");
				}
				loadTable(productosModel.getProductos());

			}
		}
	}

	// se define las acciones que se llevaran acabo
	private void actionBtnGuardar() {

		Producto objProducto = getDataForm();

		if (!validaFormulario()) {

			JOptionPane.showMessageDialog(jpProductos, "Todos los campos son obligatorios!", "Advertencia",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (!productosModel.guardarProducto(objProducto)) {
				JOptionPane.showMessageDialog(jpProductos, "Ocurrio un problema al guardar la informacion");
			} else {
				JOptionPane.showMessageDialog(jpProductos, "Informacion registrada con exito.");
				loadTable(productosModel.getProductos());
			}
		}
	}

	private void actionBtnLimpiar() {
		jpProductos.txtCodigo.setText("0");
		jpProductos.txtNombre.setText("");
		jpProductos.txtDescripcion.setText("");
		jpProductos.txtPrecio.setText("0");
		jpProductos.txtCantidad.setText("1");
		jpProductos.cmbxProveedor.setSelectedIndex(0);

		validaFormulario();
		jpProductos.table.clearSelection();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == jpProductos.table) {
			actionTable();

		}

	}

	private void actionTable() {
		int selectedRow = jpProductos.table.getSelectedRow();
		if (selectedRow != -1) {

			// se carga la informacion de fila en el formulario
			jpProductos.txtCodigo.setText(jpProductos.table.getValueAt(selectedRow, 0).toString());
			jpProductos.txtNombre.setText(jpProductos.table.getValueAt(selectedRow, 1).toString());
			jpProductos.txtDescripcion.setText(jpProductos.table.getValueAt(selectedRow, 2).toString());
			jpProductos.txtPrecio.setText(jpProductos.table.getValueAt(selectedRow, 4).toString());
			jpProductos.txtCantidad.setText(jpProductos.table.getValueAt(selectedRow, 5).toString());
			jpProductos.cmbxProveedor.setSelectedItem(jpProductos.table.getValueAt(selectedRow, 3));

			jpProductos.btnEliminar.setEnabled(true);
			jpProductos.btnActualizar.setEnabled(true);
			jpProductos.btnGuardar.setEnabled(false);
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		validaFormulario();
	}

	// funcion que se detona cuando el panel de proveeedores se muestra
	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && jpProductos.isShowing()) {
			// Acción a realizar cuando el JPanel se muestra
			loadTable(productosModel.getProductos());
			loadProveedores();
		}
	}

	private void loadProveedores() {
		ArrayList<Proveedor> proveedores = proveedoresModel.getProveedores();
		for (Proveedor proveedor : proveedores) {

			jpProductos.cmbxProveedor.addItem(proveedor.getId() + " - " + proveedor.getNombre());
		}
	}
}
