package Presentacion.Controlador;

import Presentacion.Comando.Comando.Comando;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Comando.FactoriaComando.FactoriaComando;

public class ApplicationControllerImp extends ApplicationController {

	protected ApplicationControllerImp() {}

	@Override
	public RespuestaComando handleRequest(IDEventos evento, Object datos) {
		Comando comando = FactoriaComando.getInstance().nuevoComando(evento);

		RespuestaComando respuestaComando = null;
		if (comando != null) {
			respuestaComando = comando.ejecutar(datos);
		}

		if (respuestaComando == null) {
			respuestaComando = new RespuestaComando();
			respuestaComando.setEvento(evento);
		}

		Dispatcher.getInstance().despachar(respuestaComando);

		return respuestaComando;
	}

}