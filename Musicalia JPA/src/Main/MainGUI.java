package Main;

import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Ventana.Ventana;

public class MainGUI {

    private Ventana panel;

    public MainGUI() {
        panel = new Ventana();
        panel.setVisible(true);
    }

    public void actualizar(IDEventos evento, Object datos) {
        panel.actualizar(evento, datos);
    }

}
