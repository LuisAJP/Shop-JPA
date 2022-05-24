package Negocio.Empleados;

public abstract class TransferEmpleado {

	private Integer id;
	private String dni;
	private String nombre;
	private String apellidos;
	private Boolean activo;
	private Integer idDepartamento;
	
	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public String getDNI() {
		return dni;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public Integer getIDDepartamento() {
		return idDepartamento;
	}

	public void setIDDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return id.toString() + ": " + nombre + " " + apellidos + " \nDNI: " + dni;
	}
}