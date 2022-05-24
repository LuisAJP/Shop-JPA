package Presentacion.Controlador;

import Presentacion.Comando.Comando.IDEventos;


public class RespuestaComando {
	
	private IDEventos evento;

	private Object datos;

	public IDEventos getEvento() {
		return evento;
	}


	public void setEvento(IDEventos evento) {
		this.evento = evento;
	}

	
	public Object getDatos() {
		return datos;
	}

	public void setDatos(Object datos) {
		this.datos = datos;
	}

}