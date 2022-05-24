package Negocio.Departamentos;

import java.util.List;
import Negocio.Empleados.TransferEmpleado;


public class TransferDepartamento {
	
	private Integer id;
	private String nombre;
	private Boolean activo;
	private List<TransferEmpleado> empleados;
	
	
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

	public List<TransferEmpleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<TransferEmpleado> empleados) {
		this.empleados = empleados;
	}

    @Override
    public String toString() {
        return id.toString() + ": " + nombre;
    }
}