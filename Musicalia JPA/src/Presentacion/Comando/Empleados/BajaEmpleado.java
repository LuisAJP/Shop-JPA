
package Presentacion.Comando.Empleados;

import Negocio.Empleados.SAEmpleados;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Comando.Comando.Comando;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.RespuestaComando;

public class BajaEmpleado implements Comando {
	
	@Override
	public RespuestaComando ejecutar(Object datos) {
		SAEmpleados saEmpleados = FactoriaSA.getInstance().getSAEmpleados();

		Integer result = saEmpleados.bajaEmpleado((Integer) datos);

		RespuestaComando respuestaComando = new RespuestaComando();
		if (result != null) {
			respuestaComando.setEvento(IDEventos.EVENTO_BAJA_EMPLEADO);
			respuestaComando.setDatos(result);
		}
		else {
			respuestaComando.setEvento(IDEventos.EVENTO_ERROR);
		}

		return respuestaComando;
	}
}