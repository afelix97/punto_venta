package punto_venta.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import punto_venta.config.Conexion;
import punto_venta.objects.Proveedor;

public class ProveedorModel {
	Conexion conn = new Conexion();

	// funcion que devuelve todos los usuarios
	public ArrayList<Proveedor> getProveedores() {
		ArrayList<Proveedor> proveedores = new ArrayList<>();
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from proveedores;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Proveedor proveedor = new Proveedor();
				proveedor.setId(rs.getInt("id"));
				proveedor.setNombre(rs.getString("nombre"));
				proveedor.setRfc(rs.getString("rfc"));
				proveedor.setDireccion(rs.getString("direccion"));
				proveedor.setRazonSocial(rs.getString("razon_social"));
				proveedor.setTelefono(rs.getString("telefono"));
				proveedor.setFechaAlta(rs.getString("fecha_alta"));

				proveedores.add(proveedor);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return proveedores;
	}

	// funcion que devuelve todos los usuarios
	public Proveedor getProveedor(int id) {
		Proveedor proveedor = null;
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from proveedores where id =" + id + " limit 1;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setId(rs.getInt("id"));
				proveedor.setNombre(rs.getString("nombre"));
				proveedor.setRfc(rs.getString("rfc"));
				proveedor.setDireccion(rs.getString("direccion"));
				proveedor.setRazonSocial(rs.getString("razon_social"));
				proveedor.setTelefono(rs.getString("telefono"));
				proveedor.setFechaAlta(rs.getString("fecha_alta"));

			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return proveedor;
	}

	public boolean guardarProveedor(Proveedor objProveedor) {
		boolean isSave = false;
		try {
			String sql = "INSERT INTO proveedores (rfc, nombre, telefono, direccion, razon_social) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setString(1, objProveedor.getRfc());
			statement.setString(2, objProveedor.getNombre());
			statement.setString(3, objProveedor.getTelefono());
			statement.setString(4, objProveedor.getDireccion());
			statement.setString(5, objProveedor.getRazonSocial());

			// Ejecutar la sentencia SQL
			statement.executeUpdate();
			isSave = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return isSave;
	}

	public boolean actualizarProveedor(Proveedor objProveedor) {
		boolean isUpdate = false;
		try {
			String sql = "UPDATE proveedores SET rfc = ?, nombre = ?, telefono = ?, direccion = ?, razon_social = ? WHERE id = ?";

			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setString(1, objProveedor.getRfc());
			statement.setString(2, objProveedor.getNombre());
			statement.setString(3, objProveedor.getTelefono());
			statement.setString(4, objProveedor.getDireccion());
			statement.setString(5, objProveedor.getRazonSocial());
			statement.setInt(6, objProveedor.getId());
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

	public boolean deleteProveedor(int id) {
		boolean isDelete = false;
		try {
			String sql = "DELETE FROM PROVEEDORES WHERE id = ?;";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setInt(1, id);

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