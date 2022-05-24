package Negocio.Empleados;




public class TransferEmpleadoTiempoParcial extends TransferEmpleado {

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
}