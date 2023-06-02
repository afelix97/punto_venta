package punto_venta.models;

import punto_venta.objects.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import punto_venta.config.*;

//clase modelo la cual se encarga de hacer la interaccion con la base de datos
public class UserModel {
	Conexion conn = new Conexion();

	// se conecta a base de datos, para validar el usuario
	public boolean validateUser(User user) {
		boolean validate = false;
		Statement stmt = null;
		try {
			stmt = conn.ObtConexion().createStatement();
			String query = "select 1 as result from users where user = '" + user.getUser() + "' and pass = '"
					+ user.getPass() + "';";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				if (rs.getString("result") != null) {
					validate = true;
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
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select * from users;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUser(rs.getString("user"));
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
		System.out.println(users.toString());
		return users;
	}

}
