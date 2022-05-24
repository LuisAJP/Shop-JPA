package Presentacion.Comando.Departamentos;

import Negocio.Departamentos.SADepartamentos;
import Negocio.Departamentos.TransferDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Presentacion.Comando.Comando.Comando;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.RespuestaComando;


public class AltaDepartamento implements Comando {
	@Override
	public RespuestaComando ejecutar(Object datos) {
		SADepartamentos saDepartamentos = FactoriaSA.getInstance().getSADepartamentos();

		Integer result = saDepartamentos.altaDepartamento((TransferDepartamento) datos);

		RespuestaComando respuestaComando = new RespuestaComando();
		if (result != null) {
			respuestaComando.setEvento(IDEventos.EVENTO_ALTA_DEPARTAMENTO);
			respuestaComando.setDatos(result);
		}
		else {
			respuestaComando.setEvento(IDEventos.EVENTO_ERROR);
		}

		return respuestaComando;
	}
}