package Negocio.Empleados;

import javax.persistence.*;
import java.io.Serializable;
import Negocio.Departamentos.ONDepartamento;


@Entity
// Lo probe con JOINED y SQLite daba problemas con la sintaxis que usa
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EMPLEADO_TYPE")
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findById", query = "select obj from ONEmpleado obj where obj.id = :id"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findByDni", query = "select obj from ONEmpleado obj where obj.dni = :dni"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findByNombre", query = "select obj from ONEmpleado obj where obj.nombre = :nombre"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findByapellidos", query = "select obj from ONEmpleado obj where obj.apellidos = :apellidos"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findByActivo", query = "select obj from ONEmpleado obj where obj.activo = :activo"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findByVersion", query = "select obj from ONEmpleado obj where obj.version = :version"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleado.findByDepartamento", query = "select obj from ONEmpleado obj where obj.departamento = :departamento") })
public abstract class ONEmpleado implements Serializable {
	
	private static final long serialVersionUID = 0;

	public ONEmpleado() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String dni;
	private String nombre;
	private String apellidos;
	private Boolean activo;
	@ManyToOne
	private ONDepartamento departamento;
	@Version
	private int version;
	public Integer getID() {
		return id;
	}

	public void setID(Integer ID) {
		this.id = ID;
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

	public ONDepartamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(ONDepartamento departamento) {
		this.departamento = departamento;
	}

	public abstract Double calcularNomina();

	public abstract TransferEmpleado toTransfer();
}