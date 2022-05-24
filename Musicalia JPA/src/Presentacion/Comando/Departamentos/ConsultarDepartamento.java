
package Presentacion.Comando.Departamentos;

import Negocio.Departamentos.SADepartamentos;
import Negocio.Departamentos.TransferDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Comando.Comando.Comando;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.RespuestaComando;

public class ConsultarDepartamento implements Comando {
	
	@Override
	public RespuestaComando ejecutar(Object datos) {
		SADepartamentos saDepartamentos = FactoriaSA.getInstance().getSADepartamentos();

		TransferDepartamento result = saDepartamentos.consultarDepartamento((Integer) datos);

		RespuestaComando respuestaComando = new RespuestaComando();
		if (result != null) {
			respuestaComando.setEvento(IDEventos.EVENTO_CONSULTAR_DEPARTAMENTO);
			respuestaComando.setDatos(result);
		}
		else {
			respuestaComando.setEvento(IDEventos.EVENTO_ERROR);
		}
		return respuestaComando;
	}
}