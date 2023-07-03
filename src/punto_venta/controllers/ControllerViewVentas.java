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

import javax.swing.JFrame;
import javax.swing.table.*;

import punto_venta.models.EmpleadosModel;
import punto_venta.models.VentasModel;
import punto_venta.objects.Venta;
import punto_venta.views.JfDetalleVenta;
import punto_venta.views.JpVentas;

/**
 * @author 190090072@upve.edu.mx
 *
 */
public class ControllerViewVentas implements ActionListener, HierarchyListener, KeyListener, MouseListener {
	// se instancia clase userModel
	VentasModel ventasModel = new VentasModel();
	EmpleadosModel empModel = new EmpleadosModel();
	// se instancia la vista Login, para poder ser manipulada desde el controlador
	private JpVentas jpVentas;

	// Intervalo máximo entre dos clics para considerarse doble click
	private static final int DOUBLE_CLICK_INTERVAL = 300;
	private long lastClickTime = 0;

	public ControllerViewVentas(JpVentas view) {
		super();
		this.jpVentas = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpVentas.btnBuscar) {
			filtrarTabla(jpVentas.txtFiltro.getText());

		}
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpVentas.txtFiltro) {
			filtrarTabla(jpVentas.txtFiltro.getText());

		}
	}

	// actualiza el contenido de la tabla dependiendo que este escrito en el txt del
	// filtro
	private void filtrarTabla(String filtro) {
		ArrayList<Venta> ventas = ventasModel.getVentas();
		ArrayList<Venta> listaFiltrada = new ArrayList<Venta>();
		for (Venta venta : ventas) {
			if (venta.toString().toLowerCase().contains(filtro.toLowerCase())) {
				listaFiltrada.add(venta);
			}
		}
		loadTable(listaFiltrada);
	}

	// funcion que se detona cuando el panel de ventas se muestra
	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && jpVentas.isShowing()) {
			// Acción a realizar cuando el JPanel se muestra
			loadTable(ventasModel.getVentas());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		filtrarTabla(jpVentas.txtFiltro.getText());
	}

	private void loadTable(ArrayList<Venta> ventas) {
		((DefaultTableModel) jpVentas.table.getModel()).setRowCount(0);

		int NumCol = jpVentas.table.getModel().getColumnCount();

		Object[] fila = new Object[NumCol];

		for (Venta venta : ventas) {
			fila[0] = venta.getId();
			fila[1] = venta.getCliente();
			fila[2] = venta.getFecha();
			fila[3] = venta.getIdEmpleado() + " - " + empModel.getEmpleado(venta.getIdEmpleado()).getNombre();
			fila[4] = venta.getTotal();

			((DefaultTableModel) jpVentas.table.getModel()).addRow(fila);
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
	public void mouseClicked(MouseEvent e) {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - lastClickTime) <= DOUBLE_CLICK_INTERVAL) {
			// Doble clic detectado
			if (e.getSource() == jpVentas.table) {
				int selectedRow = jpVentas.table.getSelectedRow();
				if (selectedRow != -1) {

					// se carga la informacion de fila en el formulario
					int idVenta = Integer.parseInt(jpVentas.table.getValueAt(selectedRow, 0).toString());

					// se manda llamar la nueva vista y se le pasa el id venta
					JfDetalleVenta viewDetalleVenta = new JfDetalleVenta();
					viewDetalleVenta.setIdVenta(idVenta);
					// esto sirve para no cerrar los frames abiertos si no solo este
					viewDetalleVenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					viewDetalleVenta.setVisible(true);
				}
			}
		} else {
			// Primer clic
			lastClickTime = currentTime;
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