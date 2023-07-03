package punto_venta.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import punto_venta.config.Conexion;
import punto_venta.objects.Producto;

public class ProductosModel {
	Conexion conn = new Conexion();

	// funcion que devuelve todos los usuarios
	public ArrayList<Producto> getProductos() {
		ArrayList<Producto> productos = new ArrayList<>();
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from articulos where eliminado = 0;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Producto producto = new Producto();
				producto.setCodigo(rs.getInt("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getFloat("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setIdProveedor(rs.getInt("id_proveedor"));
				producto.setFechaAlta(rs.getString("fecha_alta"));

				productos.add(producto);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return productos;
	}

	// funcion que devuelve todos los usuarios
	public Producto getProducto(int id) {
		Producto producto = null;
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from articulos where codigo = " + id + "  AND eliminado = 0 limit 1;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				producto = new Producto();
				producto.setCodigo(rs.getInt("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getFloat("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setIdProveedor(rs.getInt("id_proveedor"));
				producto.setFechaAlta(rs.getString("fecha_alta"));

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return producto;
	}

	public boolean guardarProducto(Producto objProducto) {
		boolean isSave = false;
		try {

			String sql = "INSERT INTO articulos (codigo,nombre, descripcion, precio, stock, id_proveedor) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setInt(1, objProducto.getCodigo());
			statement.setString(2, objProducto.getNombre());
			statement.setString(3, objProducto.getDescripcion());
			statement.setFloat(4, objProducto.getPrecio());
			statement.setInt(5, objProducto.getStock());
			statement.setInt(6, objProducto.getIdProveedor());

			// Ejecutar la sentencia SQL
			statement.executeUpdate();
			isSave = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			conn.closeConnection();
		}
		return isSave;
	}

	public boolean actualizarProducto(Producto objProducto) {
		boolean isUpdate = false;
		try {
			String sql = "UPDATE articulos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, id_proveedor = ? WHERE codigo= ?";

			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setString(1, objProducto.getNombre());
			statement.setString(2, objProducto.getDescripcion());
			statement.setFloat(3, objProducto.getPrecio());
			statement.setInt(4, objProducto.getStock());
			statement.setInt(5, objProducto.getIdProveedor());
			statement.setInt(6, objProducto.getCodigo());

			// Ejecutar la sentencia SQL
			int filasAfectadas = statement.executeUpdate();

			if (filasAfectadas > 0) {
				isUpdate = true;
			}
			isUpdate = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return isUpdate;
	}

	public boolean venderProducto(int codigo, int cantidadVendida) {
		boolean isUpdate = false;

		try {
			String sql = "UPDATE articulos SET stock = (stock- ?) WHERE codigo= ?";

			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setInt(1, cantidadVendida);
			statement.setInt(2, codigo);

			// Ejecutar la sentencia SQL
			int filasAfectadas = statement.executeUpdate();

			if (filasAfectadas > 0) {
				isUpdate = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return isUpdate;
	}

	public boolean deleteProducto(int codigo) {
		boolean isDelete = false;
		try {
			String sql = "UPDATE articulos SET eliminado = 1 WHERE codigo = ?;";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setInt(1, codigo);

			// Ejecutar la sentencia SQL
			int filasAfectadas = statement.executeUpdate();

			if (filasAfectadas > 0) {
				isDelete = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return isDelete;
	}
}
