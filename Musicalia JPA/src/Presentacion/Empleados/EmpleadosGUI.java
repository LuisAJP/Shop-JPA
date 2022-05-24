/**
 * 
 */
package Presentacion.Empleados;

import java.util.Locale;

import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.ApplicationController;

public class EmpleadosGUI {
	
	private PanelEmpleados panelEmpleados;

	public EmpleadosGUI() {
		this.panelEmpleados = new PanelEmpleados();
	}
	
	public void actualizar(IDEventos evento, Object datos) {
		switch (evento) {
		case EVENTO_ALTA_EMPLEADO:
			// Actualizar id del empleado
			panelEmpleados.getTextFieldID().setText(datos.toString());
			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_CALCULAR_NOMINA_EMPLEADO, datos);
			break;
		case EVENTO_BAJA_EMPLEADO:
			panelEmpleados.getTextFieldID().setText(null);
			panelEmpleados.getTextFieldDNI().setText(null);
			panelEmpleados.getTextFieldNombre().setText(null);
			panelEmpleados.getTextFieldApellidos().setText(null);
			panelEmpleados.getTextFieldDepartamento().setText(null);

			panelEmpleados.getTextFieldImpuesto().setText(null);
			panelEmpleados.getTextFieldSalario().setText(null);
			panelEmpleados.setPanelEnabled(panelEmpleados.getPanelTiempoCompleto(), false);

			panelEmpleados.getTextFieldHoras().setText(null);
			panelEmpleados.getTextFieldSalarioPorHora().setText(null);
			panelEmpleados.setPanelEnabled(panelEmpleados.getPanelTiempoParcial(), false);

			panelEmpleados.getTipoEmpleadoButtonGroup().clearSelection();

			panelEmpleados.getNominaLabel().setText(" ");

			break;
		case EVENTO_CALCULAR_NOMINA_EMPLEADO:
			panelEmpleados.getNominaLabel().setText(String.format(Locale.getDefault(), "%.2f€", (Double)datos));
			break;
		default:
			break;
		}
	}

	public PanelEmpleados getPanelEmpleados() {
		return panelEmpleados;
	}

	public void setPanelEmpleados(PanelEmpleados panelEmpleados) {
		this.panelEmpleados = panelEmpleados;
	}
}