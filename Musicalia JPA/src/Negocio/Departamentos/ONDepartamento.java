/**
 * 
 */
package Negocio.Departamentos;

import Negocio.Empleados.ONEmpleado;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Departamentos.ONDepartamento.findById", query = "select obj from ONDepartamento obj where obj.id = :id"),
		@NamedQuery(name = "Negocio.Departamentos.ONDepartamento.findByNombre", query = "select obj from ONDepartamento obj where obj.nombre = :nombre"),
		@NamedQuery(name = "Negocio.Departamentos.ONDepartamento.findByActivo", query = "select obj from ONDepartamento obj where obj.activo = :activo"),
		@NamedQuery(name = "Negocio.Departamentos.ONDepartamento.findByVersion", query = "select obj from ONDepartamento obj where obj.version = :version"),
		@NamedQuery(name = "Negocio.Departamentos.ONDepartamento.findByEmpleados", query = "select obj from ONDepartamento obj where obj.empleados = :empleados") })
public class ONDepartamento implements Serializable {

	private static final long serialVersionUID = 0;

	public ONDepartamento() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;

	private Boolean activo;

	@OneToMany(mappedBy = "departamento")
	@OrderBy("nombre ASC")
	private List<ONEmpleado> empleados;

	@OneToMany(mappedBy = "departamento", cascade={CascadeType.ALL})
	@OrderBy
	
	@Version
	private int version;

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<ONEmpleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<ONEmpleado> empleados) {
		this.empleados = empleados;
	}

	public TransferDepartamento toTransfer() {
		TransferDepartamento departamento = new TransferDepartamento();
		departamento.setID(id);
		departamento.setNombre(nombre);
		departamento.setActivo(activo);

		return departamento;
	}

}