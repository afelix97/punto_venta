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

	public static Connection conect = null;
	public static FileReader entrada;
	public static BufferedReader bufferEntrada;
	// Creamos un Arreglo en el cual se guardaran las 3 primeras lineas del fichero
	public static String fichero[] = new String[4];
	public static JFileChooser buscar;
	public static String Direccion;
	public static boolean verificar = true;
	public static FileNameExtensionFilter FiltrarTexto = new FileNameExtensionFilter(".../.txt", "txt");
	public static int Bueltas;
	public static final String URL = "jdbc:mysql://localhost:3306/test";

	public static Connection ObtConexion() {
		buscar = new JFileChooser();
		try {
			Direccion = "C:\\config\\db_config.txt";
			Bueltas = 0;
			do {
				try {

					entrada = new FileReader(Direccion);
					// Insertamos en el BufferedReader El Fichero con la configuracion, para poder
					// leer lo que contiene
					bufferEntrada = new BufferedReader(entrada);
					// Creamos un for para leer lo que se encuentra en las 3 primeras lineas del
					// fichero
					for (int i = 0; i <= 3; i++) {
						// Guardamos en el arreglo lo que se encuentra en el fichero linea por linea.
						fichero[i] = bufferEntrada.readLine();
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
					Bueltas++;
					if (Bueltas > 1) {
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
					Direccion = "" + buscar.getSelectedFile();

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

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn;

		conn = Conexion.ObtConexion();

		if (conn != null) {
			System.out.println("Conectado...");
			System.out.println("Direccion Del Fichero: " + Direccion);
			System.out.println(conn);

			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("Desconectado");
		}

	}

}
