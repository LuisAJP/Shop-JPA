
package Negocio.FactoriaSA;

import Negocio.Departamentos.SADepartamentos;
import Negocio.Departamentos.SADepartamentosImp;
import Negocio.Empleados.SAEmpleados;
import Negocio.Empleados.SAEmpleadosImp;


public class FactoriaSAImp extends FactoriaSA {

    
    protected FactoriaSAImp() {}

    @Override
    public SADepartamentos getSADepartamentos() {
        SADepartamentos saDepartamentos = new SADepartamentosImp();

        return saDepartamentos;
    }

    
    @Override
    public SAEmpleados getSAEmpleados() {
        SAEmpleados saEmpleados = new SAEmpleadosImp();

        return saEmpleados;
    }

}