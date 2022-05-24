
package Negocio.FactoriaSA;

import Negocio.Departamentos.SADepartamentos;
import Negocio.Empleados.SAEmpleados;

public abstract class FactoriaSA {
	private static class SingletonHelper {
		
		private static final FactoriaSA INSTANCE = new FactoriaSAImp();
	}


	public static FactoriaSA getInstance() {
		return SingletonHelper.INSTANCE;
	}


	public abstract SADepartamentos getSADepartamentos();

	
	public abstract SAEmpleados getSAEmpleados();

}