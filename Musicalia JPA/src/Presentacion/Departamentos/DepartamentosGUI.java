
package Presentacion.Departamentos;

import java.util.List;
import java.util.Locale;

import Negocio.Departamentos.TransferDepartamento;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.ApplicationController;

public class DepartamentosGUI {

	private PanelDepartamentos panelDepartamentos;

	public DepartamentosGUI() {

		this.panelDepartamentos = new PanelDepartamentos();
	}

	public void actualizar(IDEventos evento, Object datos) {

		switch (evento) {
		case EVENTO_ALTA_DEPARTAMENTO:
			// Actualizar id del departamento
			panelDepartamentos.getIdTextField().setText(datos.toString());
			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_LISTAR_DEPARTAMENTOS, null);
			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_CALCULAR_NOMINA_DEPARTAMENTO, datos);
			break;
		case EVENTO_BAJA_DEPARTAMENTO:
			panelDepartamentos.getIdTextField().setText(null);
			panelDepartamentos.getNombreTextField().setText(null);
			panelDepartamentos.getNominaDepartamentoLabel().setText(" ");
			panelDepartamentos.actualizarEmpleados(null);

			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_LISTAR_DEPARTAMENTOS, null);
			break;
		case EVENTO_MODIFICAR_DEPARTAMENTO:
			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_LISTAR_DEPARTAMENTOS, null);
			break;
		case EVENTO_CONSULTAR_DEPARTAMENTO:
			TransferDepartamento departamento = (TransferDepartamento) datos;

			panelDepartamentos.getIdTextField().setText(departamento.getID().toString());
			panelDepartamentos.getNombreTextField().setText(departamento.getNombre());
			panelDepartamentos.actualizarEmpleados(departamento.getEmpleados());

			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_CALCULAR_NOMINA_DEPARTAMENTO, departamento.getID());
			break;
		case EVENTO_CALCULAR_NOMINA_DEPARTAMENTO:
			panelDepartamentos.getNominaDepartamentoLabel().setText(String.format(Locale.getDefault(), "%.2f€", (Double)datos));
			break;
		case EVENTO_LISTAR_DEPARTAMENTOS:
			List<TransferDepartamento> departamentos = (List<TransferDepartamento>)datos;
			panelDepartamentos.actualizarDepartamentos(departamentos);
			break;
		default:
			break;
		}
	}

	public PanelDepartamentos getPanelDepartamentos() {
		return panelDepartamentos;
	}

	public void setPanelDepartamentos(PanelDepartamentos panelDepartamentos) {
		this.panelDepartamentos = panelDepartamentos;
	}
}