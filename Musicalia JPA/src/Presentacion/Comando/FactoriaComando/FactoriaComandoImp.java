
package Presentacion.Comando.FactoriaComando;

import Presentacion.Comando.Comando.Comando;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Comando.Departamentos.*;
import Presentacion.Comando.Empleados.*;


public class FactoriaComandoImp extends FactoriaComando {

	protected FactoriaComandoImp() {}

	@Override
	public Comando nuevoComando(IDEventos evento) {
		Comando comando = null;

		switch (evento) {
		case EVENTO_MAIN:
			break;
		case EVENTO_ALTA_EMPLEADO:
			comando = new AltaEmpleado();
			break;
		case EVENTO_BAJA_EMPLEADO:
			comando = new BajaEmpleado();
			break;
		case EVENTO_CALCULAR_NOMINA_EMPLEADO:
			comando = new CalculoNomina();
			break;
		case EVENTO_ALTA_DEPARTAMENTO:
			comando = new AltaDepartamento();
			break;
		case EVENTO_BAJA_DEPARTAMENTO:
			comando = new BajaDepartamento();
			break;
		case EVENTO_MODIFICAR_DEPARTAMENTO:
			comando = new ModificarDepartamento();
			break;
		case EVENTO_CALCULAR_NOMINA_DEPARTAMENTO:
			comando = new CalculoNominaDepartamento();
		break;
		case EVENTO_CONSULTAR_DEPARTAMENTO:
			comando = new ConsultarDepartamento();
			break;
		case EVENTO_LISTAR_DEPARTAMENTOS:
			comando = new ListarDepartamentos();
			break;
		default:
			break;
		}

		return comando;
	}

}