package Negocio.Empleados;

public class TransferEmpleadoTiempoCompleto extends TransferEmpleado {
	
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
}