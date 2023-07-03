package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.table.*;

import punto_venta.models.ProductosModel;
import punto_venta.models.VentasModel;
import punto_venta.objects.DetalleVenta;
import punto_venta.views.JfDetalleVenta;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class ControllerViewDetalleVenta implements ActionListener, WindowListener, KeyListener {
	// se instancia clase userModel
	VentasModel ventasModel = new VentasModel();
	ProductosModel prodModel = new ProductosModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private JfDetalleVenta jfDetalleVenta;
	private int idVenta;

	public ControllerViewDetalleVenta(JfDetalleVenta view, int idVenta) {
		super();
		this.jfDetalleVenta = view;
		this.idVenta = idVenta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jfDetalleVenta.btnBuscar) {
			filtrarTabla(jfDetalleVenta.txtFiltro.getText());

		}
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jfDetalleVenta.txtFiltro) {
			filtrarTabla(jfDetalleVenta.txtFiltro.getText());

		}
	}

	// actualiza el contenido de la tabla dependiendo que este escrito en el txt del
	// filtro
	private void filtrarTabla(String filtro) {
		ArrayList<DetalleVenta> ventas = ventasModel.getDetalleVenta(idVenta);
		ArrayList<DetalleVenta> listaFiltrada = new ArrayList<DetalleVenta>();
		for (DetalleVenta venta : ventas) {
			if (venta.toString().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(venta);
			}
		}
		loadTable(listaFiltrada);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		filtrarTabla(jfDetalleVenta.txtFiltro.getText());
	}

	private void loadTable(ArrayList<DetalleVenta> detalleVenta) {
		((DefaultTableModel) jfDetalleVenta.table.getModel()).setRowCount(0);

		int NumCol = jfDetalleVenta.table.getModel().getColumnCount();

		Object[] fila = new Object[NumCol];

		for (DetalleVenta detalle : detalleVenta) {
			fila[0] = detalle.getId();
			fila[1] = detalle.getIdVenta();
			fila[2] = detalle.getIdArticulo() + " - " + detalle.getNombreArticulo();
			fila[3] = detalle.getCantidad();
			fila[4] = detalle.getSubtotal();

			((DefaultTableModel) jfDetalleVenta.table.getModel()).addRow(fila);
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

	@Override
	public void windowOpened(WindowEvent e) {
		// Acción a realizar cuando el JPanel se muestra
		loadTable(ventasModel.getDetalleVenta(idVenta));
	}

	@Override
	public void windowClosing(WindowEvent e) {

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
}