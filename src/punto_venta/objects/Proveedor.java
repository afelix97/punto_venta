package punto_venta.objects;

public class Proveedor {
	private int id;
	private String rfc;
	private String nombre;
	private String telefono;
	private String direccion;
	private String razonSocial;
	private String fechaAlta;

	public Proveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proveedor(int id, String rfc, String nombre, String telefono, String direccion, String razonSocial,
			String fechaAlta) {
		super();
		this.id = id;
		this.rfc = rfc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.razonSocial = razonSocial;
		this.fechaAlta = fechaAlta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", rfc=" + rfc + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion="
				+ direccion + ", razonSocial=" + razonSocial + ", fechaAlta=" + fechaAlta + "]";
	}

}
