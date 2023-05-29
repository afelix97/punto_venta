package punto_venta.objects;

public class User {
	private int id;
	private String user;
	private String pass;
	private String fechaAlta;

	public User(int id, String user, String pass, String fechaAlta) {
		super();
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.fechaAlta = fechaAlta;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", pass=" + pass + ", fechaAlta=" + fechaAlta + "]";
	}

}
