package Negocio.Empleados;



public interface SAEmpleados {
	
    Integer altaEmpleado(TransferEmpleado transferEmpleado);
    Integer bajaEmpleado(Integer id);
    Double calcularNomina(Integer id);
    
}