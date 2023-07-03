package punto_venta.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import punto_venta.utilidades.Helper;
import punto_venta.views.VistaPreviaTicket;

public class ControllerViewTicket implements ActionListener, WindowListener {
	VistaPreviaTicket vistaPreviaTicket;

	private final String URL = "C:\\temp\\";
	private final String FILE_NAME = "punto_venta.inc";

	public ControllerViewTicket(VistaPreviaTicket view) {
		super();
		this.vistaPreviaTicket = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dependiendo a que boton se le esta haciendo click sera la accion a ejecutar
		if (e.getSource() == vistaPreviaTicket.btnGenerarTicket) {
			Helper helper = new Helper();
			String folio = vistaPreviaTicket.lblFolioTicket.getText().replace("#", "");
			helper.generarTicket(vistaPreviaTicket.jpPreview, folio);

		}
	}

	private void cargarDatosEmpresa() {
		validarExistenciaArchivo();
		try {
			FileReader reader = new FileReader(URL + FILE_NAME);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String linea = bufferedReader.readLine();
			if (linea != null) {
				String[] datos = linea.split("&");
				vistaPreviaTicket.lblContentDirec.setText(datos[2]);
				vistaPreviaTicket.lblContentNomOrg.setText(datos[0]);
				vistaPreviaTicket.lblTelefonoOrg.setText(datos[1]);

			} else {
				JOptionPane.showMessageDialog(vistaPreviaTicket, "No se encontraron datos");
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(vistaPreviaTicket, "Error al cargar los datos");
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

	@Override
	public void windowOpened(WindowEvent e) {
		cargarDatosEmpresa();
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
}
