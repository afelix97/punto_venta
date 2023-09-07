package punto_venta.models;

import punto_venta.objects.Empleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import punto_venta.config.*;

//clase modelo la cual se encarga de hacer la interaccion con la base de datos
public class EmpleadosModel {
	Conexion conn = new Conexion();

	// se conecta a base de datos, para validar el usuario
	public int validateUser(Empleado user) {
		int validate = 0;
		Statement stmt = null;
		try {
			stmt = conn.ObtConexion().createStatement();
			String query = "select id as result from empleados where usuario = '" + user.getUser() + "' and pass = '"
					+ user.getPass() + "' and eliminado = 0;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				if (rs.getString("result") != null) {
					validate = rs.getInt("result");
				}
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return validate;
	}

	// funcion que devuelve todos los usuarios
	public ArrayList<Empleado> getUsers(int idUserLogueado) {
		ArrayList<Empleado> users = new ArrayList<>();
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from empleados where id <> " + idUserLogueado + " and eliminado = 0;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Empleado user = new Empleado();
				user.setId(rs.getInt("id"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setUser(rs.getString("usuario"));
				user.setPass(rs.getString("pass"));
				user.setFechaAlta(rs.getString("fecha_alta"));

				users.add(user);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return users;
	}

	// funcion que devuelve el empleado
	public Empleado getEmpleado(int id) {
		Empleado empleado = null;
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from empleados where id =" + id + " limit 1;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				empleado = new Empleado();
				empleado.setId(rs.getInt("id"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleado.setUser(rs.getString("usuario"));
				empleado.setPass(rs.getString("pass"));
				empleado.setFechaAlta(rs.getString("fecha_alta"));
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return empleado;
	}

	public boolean guardarEmpleado(Empleado objEmpleado) {
		boolean isSave = false;
		try {
			String sql = "INSERT INTO empleados (nombre, apellido, usuario, pass) " + "VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setString(1, objEmpleado.getNombre());
			statement.setString(2, objEmpleado.getApellido());
			statement.setString(3, objEmpleado.getUser());
			statement.setString(4, objEmpleado.getPass());

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

	public boolean actualizarEmpleado(Empleado objEmpleado) {
		boolean isUpdate = false;
		try {
			String sql = "UPDATE empleados SET  nombre = ?, apellido = ?, usuario = ?, pass = ? WHERE id = ?";

			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setString(1, objEmpleado.getNombre());
			statement.setString(2, objEmpleado.getApellido());
			statement.setString(3, objEmpleado.getUser());
			statement.setString(4, objEmpleado.getPass());
			statement.setInt(5, objEmpleado.getId());
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

	public boolean deleteEmpleado(int id) {
		boolean isDelete = false;
		try {
			String sql = "UPDATE empleados SET eliminado = 1 WHERE id = ?;";
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
