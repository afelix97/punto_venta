package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import punto_venta.models.EmpleadosModel;
import punto_venta.models.ProductosModel;
import punto_venta.models.VentasModel;
import punto_venta.objects.CarritoCompra;
import punto_venta.objects.DetalleVenta;
import punto_venta.objects.Empleado;
import punto_venta.objects.Producto;
import punto_venta.objects.Ticket;
import punto_venta.objects.Venta;
import punto_venta.views.JpPuntoVenta;
import punto_venta.views.VistaPreviaTicket;

public class ControllerViewPuntoVenta implements HierarchyListener, ActionListener, KeyListener, MouseListener {

	// se instancia clase userModel
	VentasModel ventasModel = new VentasModel();
	ProductosModel prodModel = new ProductosModel();
	EmpleadosModel empModel = new EmpleadosModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private JpPuntoVenta jpPuntoVenta;

	public ControllerViewPuntoVenta(JpPuntoVenta view) {
		super();
		this.jpPuntoVenta = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpPuntoVenta.btnAgregar) {
			actionBtnAgregar();

		}
		if (e.getSource() == jpPuntoVenta.btnNuevaVenta) {
			actionBtnNuevaVenta();

		}
		if (e.getSource() == jpPuntoVenta.btnFacturar) {
			actionBtnFacturar();

		}
		if (e.getSource() == jpPuntoVenta.btnEliminarRow) {
			actionBtnDelRow();

		}
		if (e.getSource() == jpPuntoVenta.txtCodigo) {
			actionTxtCodigo(jpPuntoVenta.txtCodigo.getText());

		}
	}

	private void actionTxtCodigo(String codigo) {
		// Buscar los datos del codigo del producto
		Producto prod = prodModel.getProducto(Integer.parseInt(codigo));

		if (prod != null) {
			jpPuntoVenta.txtPrecio.setText(String.valueOf(prod.getPrecio()));
			jpPuntoVenta.txtProducto.setText(prod.getNombre());
			jpPuntoVenta.txtCantidad.setText("1");
			jpPuntoVenta.txtStock.setText(String.valueOf(prod.getStock()));

			jpPuntoVenta.btnAgregar.setEnabled(true);// activa el boton para agregar el articulo a la tabla
		} else {
			JOptionPane.showMessageDialog(jpPuntoVenta.txtCodigo, "El codigo del producto no existe.", "Error",
					JOptionPane.ERROR_MESSAGE);
			cleanFormAdd();
			jpPuntoVenta.btnAgregar.setEnabled(false);// desactiva el boton para agregar el articulo a la tabla
		}
	}

	// Metodo que se detona cuando se da click en la tabla
	private void actionTable() {
		jpPuntoVenta.btnEliminarRow.setEnabled(true);
	}

	private void actionBtnDelRow() {
		// TODO Auto-generated method stub
		int selectedRow = jpPuntoVenta.table.getSelectedRow();
		if (selectedRow != -1) {

			// se obtiene el subtotal del producto a eliminar, para actualizar el monto
			// total a pagar
			float subtotal = Float.parseFloat(jpPuntoVenta.table.getValueAt(selectedRow, 5).toString());
			float total = Float.parseFloat(jpPuntoVenta.txtTotal.getText());

			jpPuntoVenta.txtTotal.setText(String.valueOf(total - subtotal));
			((DefaultTableModel) jpPuntoVenta.table.getModel()).removeRow(selectedRow);
			jpPuntoVenta.btnEliminarRow.setEnabled(false);
		}
	}

	private void actionBtnFacturar() {

		String cliente = jpPuntoVenta.txtCliente.getText();
		String totalVenta = jpPuntoVenta.txtTotal.getText();
		if (cliente.length() < 1) {
			JOptionPane.showMessageDialog(jpPuntoVenta, "Favor de capturar el nombre del cliente.");
		} else {

			int idVenta = generateDatePrimaryKey();// se genera el id de la venta en base a la fecha actual y hora

			// se llenar objetos de venta y venta detalle para guardarlo en base de datos
			Venta newVenta = new Venta();
			newVenta.setId(idVenta);
			newVenta.setCliente(cliente);

			int userSesion = Integer.parseInt(jpPuntoVenta.getUserSesion()[0]);// se obtiene el usuario logueado

			newVenta.setIdEmpleado(userSesion);

			// se recorren los datos obtenidos de la tabla para asignarala a los models
			ArrayList<DetalleVenta> articulosVendidos = new ArrayList<>();

			// arraylist que se utiliza para la generacion del ticket
			ArrayList<Ticket> listaDetalleTicket = new ArrayList<>();

			for (CarritoCompra articuloVendido : getCarritoCompra()) {
				DetalleVenta detalle = new DetalleVenta();
				detalle.setIdArticulo(articuloVendido.getCodigo());
				detalle.setIdVenta(idVenta);
				detalle.setCantidad(articuloVendido.getCantidad());
				detalle.setNombreArticulo(articuloVendido.getNombre());
				detalle.setSubtotal(articuloVendido.getTotal());

				articulosVendidos.add(detalle);

				listaDetalleTicket.add(new Ticket(articuloVendido.getNombre(),
						String.valueOf(articuloVendido.getCantidad()), String.valueOf(articuloVendido.getPrecio()),
						String.valueOf(articuloVendido.getTotal())));
			}
			newVenta.setDetalleVenta(articulosVendidos);

			if (guardarVenta(newVenta)) {
				LocalDateTime fechaActual = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String fechaFormateada = fechaActual.format(formatter);

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea generar ticket de compra?", "Atencion",
						JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {

					// se obtiene el nombre y apellido del usuario logueado
					Empleado emp = empModel.getEmpleado(userSesion);
					String empleadoLogueado = emp.getNombre() + " " + emp.getApellido();

					loadVistaPreviaTicket(listaDetalleTicket, idVenta, cliente, fechaFormateada, totalVenta,
							empleadoLogueado);

				}
			}
		}
	}

	// carga todos los datos necesarios de la venta, para la generacion del ticket
	// de venta
	private void loadVistaPreviaTicket(ArrayList<Ticket> listaDetalleTicket, int idVenta, String client,
			String fechaVenta, String totalVenta, String empleado) {

		VistaPreviaTicket generarTicket = new VistaPreviaTicket();
		generarTicket.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// se setean las etiquetas de la vista de generar ticket
		generarTicket.setLblFolioTicket("#" + idVenta);
		generarTicket.setLblFolioVenta("#" + idVenta);
		generarTicket.setLblCliente(client);
		generarTicket.setLblFecha(fechaVenta);
		generarTicket.setLblTotalVenta("$ " + totalVenta);
		generarTicket.setLblContentEmp(empleado);

		// se setea la tabla de detalle de la venta de la vista de generar ticket
		((DefaultTableModel) generarTicket.tableDetalle.getModel()).setRowCount(0);

		int NumCol = generarTicket.tableDetalle.getModel().getColumnCount();

		Object[] fila = new Object[NumCol];
		int i = 0;
		for (Ticket ticketDetalle : listaDetalleTicket) {
			i++;

			fila[0] = i;
			fila[1] = ticketDetalle.getArticulo();
			fila[2] = ticketDetalle.getCantidad();
			fila[3] = ticketDetalle.getPreciounidad();
			fila[4] = ticketDetalle.getSubtotal();
			((DefaultTableModel) generarTicket.tableDetalle.getModel()).addRow(fila);

			// cuando aya mas de 7 productos cargados en el carrito la vista de detalle de
			// venta se hara mas grande en la vista de generadora de tickets
			if (i > 7) {
				// modificamos el tamaño de contenedor, sin moverlo de su posicion
				generarTicket.scrollDetalle.setBounds(20, 141, 543, generarTicket.scrollDetalle.getHeight() + 15);
				generarTicket.jpPreview.setBounds(8, 43, 583, generarTicket.jpPreview.getHeight() + 15);
				generarTicket.contentPane.setBounds(100, 100, 614, generarTicket.contentPane.getHeight() + 15);

				// modificamos la posicion del componente sin cambiarlo de tamaño
				generarTicket.jpFooter.setBounds(41, generarTicket.jpFooter.getY() + 15, 522, 113);
				generarTicket.btnGenerarTicket.setBounds(204, generarTicket.btnGenerarTicket.getY() + 15, 192, 35);

			}
		}

		generarTicket.setVisible(true);
	}

	private boolean guardarVenta(Venta newVenta) {
		boolean isSave = false;
		// se guarda en base de datos la venta
		if (ventasModel.guardarVenta(newVenta)) {
			isSave = true;
			// se guarda en base de datos cada articulo vendido correspondiente a la venta
			for (DetalleVenta detalle : newVenta.getDetalleVenta()) {

				if (ventasModel.guardarDetalleVenta(detalle)) {
					isSave = true;
				}
			}
		}
		if (isSave) {
			JOptionPane.showMessageDialog(jpPuntoVenta, "Venta realizada con exito!");
			ClearVenta();
		}
		return isSave;
	}

	private void actionBtnNuevaVenta() {
		int rowCount = jpPuntoVenta.table.getRowCount();
		boolean cancelar = false;
		if (rowCount > 0) {
			int respuesta = JOptionPane.showConfirmDialog(null,
					"Al presionar en 'si', se cancelara la compra, ¿Desea continuar?", "Atencion",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {
				cancelar = true;
			}
		} else {
			cancelar = true;
		}

		if (cancelar) {
			ClearVenta();
		}

	}

	private void ClearVenta() {
		jpPuntoVenta.txtCliente.setText("");
		jpPuntoVenta.txtTotal.setText("0.00");
		cleanFormAdd();
		((DefaultTableModel) jpPuntoVenta.table.getModel()).setRowCount(0);// vacia la tabla
		jpPuntoVenta.btnFacturar.setEnabled(false);// deshabilita el boton de facturar
	}

	// metodo que agrega un producto a la tabla
	private void actionBtnAgregar() {

		int NumCol = jpPuntoVenta.table.getModel().getColumnCount();

		Producto prod = prodModel.getProducto(Integer.parseInt(jpPuntoVenta.txtCodigo.getText()));

		String cantidad = jpPuntoVenta.txtCantidad.getText();
		float subtotal = prod.getPrecio() * Integer.parseInt(cantidad);// se calcula el subtotal

		float total = Float.parseFloat(jpPuntoVenta.txtTotal.getText());// se obtiene el total de la venta
		total = total + subtotal; // se suma al total actual, el nuevo subtotal

		Object[] fila = new Object[NumCol];

		fila[0] = jpPuntoVenta.txtCodigo.getText();
		fila[1] = prod.getNombre();
		fila[2] = prod.getDescripcion();
		fila[3] = cantidad;
		fila[4] = prod.getPrecio();
		fila[5] = subtotal;

		jpPuntoVenta.txtTotal.setText(String.valueOf(total));// se actualiza el campo del total de la venta

		((DefaultTableModel) jpPuntoVenta.table.getModel()).addRow(fila);
		cleanFormAdd();// limpia el formulario al agregar el producto

		jpPuntoVenta.btnFacturar.setEnabled(true);// se avilita el boton de facturar para finalizar la venta
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		generateDatePrimaryKey();
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getSource() == jpPuntoVenta.txtCantidad) {
			int cantidad = Integer
					.parseInt(jpPuntoVenta.txtCantidad.getText().isEmpty() ? "0" : jpPuntoVenta.txtCantidad.getText());
			int stock = Integer
					.parseInt(jpPuntoVenta.txtStock.getText().isEmpty() ? "0" : jpPuntoVenta.txtStock.getText());
			// se valida que no introdusca una cantidad mayor a la del stick disponible
			if (cantidad > stock) {
				// si la cantidad sobre pasa el stock, el campo se pondra el total disponible
				// del stock
				jpPuntoVenta.txtCantidad.setText(jpPuntoVenta.txtStock.getText());
			}
		}
	}

	private void cleanFormAdd() {
		jpPuntoVenta.txtPrecio.setText("");
		jpPuntoVenta.txtCodigo.setText("");
		jpPuntoVenta.txtProducto.setText("");
		jpPuntoVenta.txtCantidad.setText("");
		jpPuntoVenta.txtStock.setText("");
		jpPuntoVenta.btnAgregar.setEnabled(false);// desactiva el boton para agregar el articulo a la tabla
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// obtiene el listado de productos agregados en la venta
	public ArrayList<CarritoCompra> getCarritoCompra() {

		ArrayList<CarritoCompra> rows = new ArrayList<>();

		int rowCount = jpPuntoVenta.table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			CarritoCompra carritoCompra = new CarritoCompra();
			carritoCompra.setCodigo(Integer.parseInt(jpPuntoVenta.table.getValueAt(i, 0).toString()));
			carritoCompra.setNombre(jpPuntoVenta.table.getValueAt(i, 1).toString());
			carritoCompra.setDescripcion(jpPuntoVenta.table.getValueAt(i, 2).toString());
			carritoCompra.setCantidad(Integer.parseInt(jpPuntoVenta.table.getValueAt(i, 3).toString()));
			carritoCompra.setPrecio(Float.parseFloat(jpPuntoVenta.table.getValueAt(i, 4).toString()));
			carritoCompra.setTotal(Float.parseFloat(jpPuntoVenta.table.getValueAt(i, 5).toString()));

			rows.add(carritoCompra);
		}
		return rows;
	}

	private int generateDatePrimaryKey() {
		LocalDateTime dateTime = LocalDateTime.now();
		int year = dateTime.getYear();
		int month = dateTime.getMonthValue();
		int day = dateTime.getDayOfMonth();
		int hour = dateTime.getHour();
		int minute = dateTime.getMinute();
		int second = dateTime.getSecond();

		int dateTimeAsInteger = year * 100000000 + month * 1000000 + day * 10000 + hour * 100 + minute + second * 10;

		return dateTimeAsInteger;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jpPuntoVenta.table) {
			actionTable();
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
