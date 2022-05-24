package Presentacion.Comando.Comando;

import Presentacion.Controlador.RespuestaComando;


public interface Comando {
	
    RespuestaComando ejecutar(Object datos);
}