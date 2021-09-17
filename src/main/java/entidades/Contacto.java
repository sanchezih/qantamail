package entidades;

public class Contacto {
	private int idContacto;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "ID: " + this.idContacto + "\nNombre: " + this.nombre + "\nApellido: " + this.apellido
				+ "\nEmail primario: " + this.email + "\nDireccion: " + this.direccion + "\ntelefono: " + this.telefono;
	}
}
