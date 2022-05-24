package Negocio.Departamentos;

import java.util.List;

import Negocio.Empleados.TransferEmpleado;


public interface SADepartamentos {

    Integer altaDepartamento(TransferDepartamento transferDepartamento);
    Integer bajaDepartamento(Integer id);
    Integer modificarDepartamento(TransferDepartamento transferDepartamento);
    TransferDepartamento consultarDepartamento(Integer id);
    List<TransferDepartamento> listarDepartamentos();
    Double calcularNominasDepartamento(Integer id);
}