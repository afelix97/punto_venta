package punto_venta.objects;

public class DetalleVenta {
	private int id;
	private int idVenta;
	private int idArticulo;
	private String nombreArticulo;
	private int cantidad;
	private float subtotal;

	public DetalleVenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "DetalleVenta [id=" + id + ", idVenta=" + idVenta + ", idArticulo=" + idArticulo + ", nombreArticulo="
				+ nombreArticulo + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
	}

}
