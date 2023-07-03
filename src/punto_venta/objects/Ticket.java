package punto_venta.objects;

public class Ticket {
	private String articulo;
	private String cantidad;
	private String preciounidad;
	private String subtotal;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Ticket(String articulo, String cantidad, String preciounidad, String subtotal) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.preciounidad = preciounidad;
		this.subtotal = subtotal;
	}


	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPreciounidad() {
		return preciounidad;
	}

	public void setPreciounidad(String preciounidad) {
		this.preciounidad = preciounidad;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "Ticket [articulo=" + articulo + ", cantidad=" + cantidad + ", preciounidad=" + preciounidad
				+ ", subtotal=" + subtotal + "]";
	}

}
