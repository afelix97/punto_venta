package punto_venta.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import punto_venta.config.Conexion;
import punto_venta.objects.DetalleVenta;
import punto_venta.objects.Venta;

public class VentasModel {
	Conexion conn = new Conexion();
	ProductosModel prodMod = null;

	// funcion que devuelve todas la ventas
	public ArrayList<Venta> getVentas() {
		ArrayList<Venta> ventas = new ArrayList<>();
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select vent.id, vent.cliente, vent.fecha, vent.id_empleado from ventas vent;";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Venta venta = new Venta();
				venta.setId(rs.getInt("id"));
				venta.setCliente(rs.getString("cliente"));
				venta.setFecha(rs.getDate("fecha"));
				venta.setIdEmpleado(rs.getInt("id_empleado"));
				
				venta.setTotal(getTotalVenta(rs.getInt("id")));

				// se obtiene la lista de articulos vendidos en esta venta
				venta.setDetalleVenta(getDetalleVenta(rs.getInt("id")));

				ventas.add(venta);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return ventas;
	}

	// obtiene los articulos vendidos por id de venta
	public ArrayList<DetalleVenta> getDetalleVenta(int idVenta) {
		ArrayList<DetalleVenta> detallesVenta = new ArrayList<>();
		prodMod = new ProductosModel();
		try {
			Statement stmt = conn.ObtConexion().createStatement();
			String query = "select vnt.*, (select (vnt.cantidad * art.precio) from articulos art where art.codigo = vnt.id_articulo) as subtotal from detalle_ventas vnt where vnt.id_venta = "
					+ idVenta + ";";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				DetalleVenta detalleVenta = new DetalleVenta();
				detalleVenta.setId(rs.getInt("id_fila"));
				detalleVenta.setIdVenta(rs.getInt("id_venta"));
				detalleVenta.setIdArticulo(rs.getInt("id_articulo"));
				detalleVenta.setCantidad(rs.getInt("cantidad"));
				detalleVenta.setSubtotal(rs.getFloat("subtotal"));

				// se agrega el nombre del articulo al objeto para que en el filtro funcione con
				// busqueda por nombre de articulo
				detalleVenta.setNombreArticulo(prodMod.getProducto(rs.getInt("id_articulo")).getNombre());

				detallesVenta.add(detalleVenta);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return detallesVenta;
	}

	// obtiene los articulos vendidos por id de venta
	public float getTotalVenta(int idVenta) {
		float totalVenta = 0;
		try {
			Statement stmt = conn.ObtConexion().createStatement();

			String query = "select (select (vnt.cantidad * art.precio) from articulos art where art.codigo = vnt.id_articulo) as subtotal from detalle_ventas vnt where vnt.id_venta = "
					+ idVenta + ";";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				totalVenta = totalVenta + rs.getFloat("subtotal");

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			conn.closeConnection();
		}
		return totalVenta;
	}

	public boolean guardarVenta(Venta newVenta) {
		boolean isSave = false;
		try {
			String sql = "INSERT INTO ventas (id,cliente, id_empleado) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setInt(1, newVenta.getId());
			statement.setString(2, newVenta.getCliente());
			statement.setFloat(3, newVenta.getIdEmpleado());

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

	public boolean guardarDetalleVenta(DetalleVenta newDetalleVenta) {
		boolean isSave = false;
		prodMod = new ProductosModel();// se inicializa para bajar el stock del producto vendido
		try {
			String sql = "INSERT INTO detalle_ventas (id_venta,id_articulo, cantidad) VALUES (?, ?, ?)";
			PreparedStatement statement = conn.ObtConexion().prepareStatement(sql);

			// Establecer los valores del objeto en la sentencia SQL
			statement.setInt(1, newDetalleVenta.getIdVenta());
			statement.setInt(2, newDetalleVenta.getIdArticulo());
			statement.setFloat(3, newDetalleVenta.getCantidad());

			// Ejecutar la sentencia SQL
			statement.executeUpdate();
			isSave = true;

			// se actualiza el stock del producto vendido
			if (!prodMod.venderProducto(newDetalleVenta.getIdArticulo(), newDetalleVenta.getCantidad())) {
				JOptionPane.showMessageDialog(null, "No fue posible disminuir el stock del producto", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			conn.closeConnection();
		}
		return isSave;
	}

}
