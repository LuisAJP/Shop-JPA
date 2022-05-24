package Negocio.Empleados;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@DiscriminatorValue(value = "TiempoParcial")
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleados.ONEmpleadoTiempoParcial.findByHoras", query = "select obj from ONEmpleadoTiempoParcial obj where obj.horas = :horas"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleadoTiempoParcial.findBySalarioPorHora", query = "select obj from ONEmpleadoTiempoParcial obj where obj.salarioPorHora = :salarioPorHora") })
public class ONEmpleadoTiempoParcial extends ONEmpleado implements
		Serializable {
	
	private static final long serialVersionUID = 0;

	
	public ONEmpleadoTiempoParcial() {
	}

	private Integer horas;
	
	private Double salarioPorHora;

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Double getSalarioPorHora() {
		return salarioPorHora;
	}

	public void setSalarioPorHora(Double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}

    @Override
    public Double calcularNomina() {
        Double nomina = horas * salarioPorHora;
        return nomina;
    }

    @Override
    public TransferEmpleado toTransfer() {
        TransferEmpleadoTiempoParcial empleado = new TransferEmpleadoTiempoParcial();
        empleado.setID(getID());
        empleado.setActivo(getActivo());
        empleado.setNombre(getNombre());
        empleado.setApellidos(getApellidos());
        empleado.setDNI(getDNI());
        empleado.setIDDepartamento(getDepartamento().getID());
        empleado.setHoras(horas);
        empleado.setSalarioPorHora(salarioPorHora);

        return empleado;
    }
}