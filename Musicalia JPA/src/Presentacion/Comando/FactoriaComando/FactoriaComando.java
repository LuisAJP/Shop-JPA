
package Presentacion.Comando.FactoriaComando;

import Presentacion.Comando.Comando.Comando;
import Presentacion.Comando.Comando.IDEventos;

public abstract class FactoriaComando {

	private static class SingletonHelper {
	
		private static final FactoriaComando INSTANCE = new FactoriaComandoImp();
	}

	public static FactoriaComando getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public abstract Comando nuevoComando(IDEventos evento);
}