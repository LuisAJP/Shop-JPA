
package Negocio.Empleados;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@DiscriminatorValue(value = "TiempoCompleto")
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleados.ONEmpleadoTiempoCompleto.findById", query = "select obj from ONEmpleadoTiempoCompleto obj where obj.id = :id"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleadoTiempoCompleto.findBySalario", query = "select obj from ONEmpleadoTiempoCompleto obj where obj.salario = :salario"),
		@NamedQuery(name = "Negocio.Empleados.ONEmpleadoTiempoCompleto.findByImpuesto", query = "select obj from ONEmpleadoTiempoCompleto obj where obj.impuesto = :impuesto") })
public class ONEmpleadoTiempoCompleto extends ONEmpleado implements
		Serializable {
	
	private static final long serialVersionUID = 0;

	
	public ONEmpleadoTiempoCompleto() {
	}

	
	private Double salario;
	
	private Double impuesto;

	public Double getSalario() {
		return salario;
	}

	
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	
	public Double getImpuesto() {
		return impuesto;
	}

	
	public void setImpuesto(Double impuesto) {
		this.impuesto = impuesto;
	}


	@Override
	public Double calcularNomina() {
		Double nomina = salario - (impuesto/100.0*salario);
		return nomina;
	}

	@Override
	public TransferEmpleado toTransfer() {
		TransferEmpleadoTiempoCompleto empleado = new TransferEmpleadoTiempoCompleto();
		empleado.setID(getID());
		empleado.setActivo(getActivo());
		empleado.setNombre(getNombre());
		empleado.setApellidos(getApellidos());
		empleado.setDNI(getDNI());
		empleado.setIDDepartamento(getDepartamento().getID());
		empleado.setSalario(salario);
		empleado.setImpuesto(impuesto);

		return empleado;
	}

}