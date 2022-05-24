package Presentacion.Controlador;

import javax.swing.JOptionPane;
import Main.MainGUI;

public class DispatcherImp extends Dispatcher {

	private MainGUI mainGUI;
	
    protected DispatcherImp() {
        mainGUI = new MainGUI();
    }
    
    @Override
    public void despachar(RespuestaComando respuesta) {
        switch (respuesta.getEvento()) {
        case EVENTO_ERROR:
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            break;
        case EVENTO_ALTA_EMPLEADO:
            mainGUI.actualizar(respuesta.getEvento(), respuesta.getDatos());
            JOptionPane.showMessageDialog(null, "Empleado creado",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            break;
        case EVENTO_BAJA_EMPLEADO:
            mainGUI.actualizar(respuesta.getEvento(), respuesta.getDatos());
            JOptionPane.showMessageDialog(null, "Empleado dado de baja",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            break;
        case EVENTO_ALTA_DEPARTAMENTO:
            mainGUI.actualizar(respuesta.getEvento(), respuesta.getDatos());
            JOptionPane.showMessageDialog(null, "Departamento creado",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            break;
        case EVENTO_BAJA_DEPARTAMENTO:
            mainGUI.actualizar(respuesta.getEvento(), respuesta.getDatos());
            JOptionPane.showMessageDialog(null, "Departamento dado de baja",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            break;
        case EVENTO_MODIFICAR_DEPARTAMENTO:
            mainGUI.actualizar(respuesta.getEvento(), respuesta.getDatos());
            JOptionPane.showMessageDialog(null, "Departamento modificado",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            break;
        case EVENTO_MAIN:
        case EVENTO_CALCULAR_NOMINA_EMPLEADO:
        case EVENTO_CONSULTAR_DEPARTAMENTO:
        case EVENTO_CALCULAR_NOMINA_DEPARTAMENTO:
        case EVENTO_LISTAR_DEPARTAMENTOS:
            mainGUI.actualizar(respuesta.getEvento(), respuesta.getDatos());
            break;
        }
    }

}