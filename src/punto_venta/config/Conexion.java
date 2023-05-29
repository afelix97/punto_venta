package punto_venta.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Conexion {

	private Connection conect = null;
	private FileReader entrada;
	private BufferedReader bufferEntrada;
	// Creamos un Arreglo en el cual se guardaran las 3 primeras lineas del fichero
	private String fichero[] = new String[4];
	private JFileChooser buscar;
	public String urlFichero;
	private boolean verificar = true;
	private FileNameExtensionFilter FiltrarTexto = new FileNameExtensionFilter(".../.txt", "txt");
	private int intentos;

	public Connection ObtConexion() {
		buscar = new JFileChooser();
		try {
			urlFichero = "C:\\config\\db_config.txt";
			intentos = 0;
			do {
				try {

					entrada = new FileReader(urlFichero);
					// Insertamos en el BufferedReader El Fichero con la configuracion, para poder
					// leer lo que contiene
					bufferEntrada = new BufferedReader(entrada);
					// Creamos un for para leer lo que se encuentra en las 3 primeras lineas del
					// fichero
					for (int i = 0; i <= 3; i++) {
						// Guardamos en el arreglo lo que se encuentra en el fichero linea por linea.
						String line = bufferEntrada.readLine();
						fichero[i] = line != null ? line : "";
						System.out.println(fichero[i]);
					}

					/*
					 * Ejemplo En el arreglo en la posision [0] o en la primera posision se guardara
					 * la primer linea del fichero en la posision [1] o en la segunda posision se
					 * guardara el usuario con el que se accedera, y en la tercera posision se
					 * guardara la contrase�a
					 */

					entrada.close();
					verificar = true;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"No Se A Encontrado El Fichero En La Direccion Especificada, Seleccione El Archivo",
							"Error De Busqueda", JOptionPane.ERROR_MESSAGE);
					intentos++;
					if (intentos > 1) {
						int Confir = JOptionPane.showConfirmDialog(null,
								"Es Posible Que Haya Un Error En La Configuracion Del Fichero,Desea Intentar De Nuevo?",
								"Warning...!!!", JOptionPane.YES_NO_OPTION);
						if (Confir == JOptionPane.NO_OPTION) {
							System.exit(0);

						}
					}
					buscar.setFileFilter(FiltrarTexto);
					buscar.showDialog(null, "Seleccione El Fichero De Configuracion");
					verificar = false;
					urlFichero = "" + buscar.getSelectedFile();

				}
			} while (verificar == false);

			Class.forName("com.mysql.cj.jdbc.Driver");
			conect = DriverManager.getConnection(fichero[0] + fichero[1], fichero[2], fichero[3]);

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			JOptionPane.showMessageDialog(null,
					"Hubo Un Problema Al Intentar Conectarse A La Base De Datos, Verifique El Nombre De La Base De Datos, El Usuario & Contrase�a Correcta",
					"Error Al Conectar", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,
					"Base De Datos: " + fichero[1] + " , Usuario: " + fichero[2] + " , Password: " + fichero[3]);
			JOptionPane.showMessageDialog(null,
					"Direccion Del JDBC Completa: " + fichero[0] + fichero[1] + "," + fichero[2] + "," + fichero[3]);
			JOptionPane.showMessageDialog(null, "Error: " + ex, "Error De Conexion ", JOptionPane.ERROR_MESSAGE);
		}
		return conect;

	}

	public void closeConnection() {
		try {
			conect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	public static void main(String[] args) {
		Conexion classCon = new Conexion();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Conexion conn = new Conexion();

		if (conn.ObtConexion() != null) {
			System.out.println("Conectado...");
			System.out.println("Direccion Del Fichero: " + classCon.urlFichero);
			System.out.println(conn);

		} else {
			System.out.println("Desconectado");
		}

	}

}
