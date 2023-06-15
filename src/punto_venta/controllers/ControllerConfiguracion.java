package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.JOptionPane;

import punto_venta.views.JpAcercaDe;
import java.io.*;

public class ControllerConfiguracion implements HierarchyListener, ActionListener {
	JpAcercaDe jpAcercaDe;
	private final String URL = "C:\\temp\\";
	private final String FILE_NAME = "punto_venta.inc";

	public ControllerConfiguracion(JpAcercaDe view) {
		super();
		this.jpAcercaDe = view;
	}

	private void guardarDatos() {
		try {
			FileWriter writer = new FileWriter(URL + FILE_NAME);
			writer.write(jpAcercaDe.txtNombre.getText() + "," + jpAcercaDe.txtTelefono.getText() + ","
					+ jpAcercaDe.txtDireccion.getText() + "," + jpAcercaDe.txtRazonSocial.getText());

			writer.close();
			JOptionPane.showMessageDialog(jpAcercaDe, "Datos guardados exitosamente");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(jpAcercaDe, "Error al guardar los datos");
		}
	}

	private void cargarDatos() {
		validarExistenciaArchivo();
		try {
			FileReader reader = new FileReader(URL + FILE_NAME);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String linea = bufferedReader.readLine();
			if (linea != null) {
				String[] datos = linea.split(",");
				jpAcercaDe.txtNombre.setText(datos[0]);
				jpAcercaDe.txtTelefono.setText(datos[1]);
				jpAcercaDe.txtDireccion.setText(datos[2]);
				jpAcercaDe.txtRazonSocial.setText(datos[3]);
			} else {
				JOptionPane.showMessageDialog(jpAcercaDe, "No se encontraron datos");
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(jpAcercaDe, "Error al cargar los datos");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == jpAcercaDe.btnGuardar) {
			guardarDatos();
		}
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && jpAcercaDe.isShowing()) {
			// Acción a realizar cuando el JPanel se muestra
			cargarDatos();
		}
	}

	// crea archivo en caso de no existir
	private void validarExistenciaArchivo() {
		File archivo = new File(URL, FILE_NAME);

		if (!archivo.exists()) {
			try {
				archivo.createNewFile();

			} catch (IOException e) {
				System.out.println("Ocurrió un error al crear el archivo.");
				e.printStackTrace();
			}
		}
	}
}
