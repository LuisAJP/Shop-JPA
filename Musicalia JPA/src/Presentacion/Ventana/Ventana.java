package Presentacion.Ventana;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JTabbedPane;
import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.ApplicationController;
import Presentacion.Departamentos.DepartamentosGUI;
import Presentacion.Empleados.EmpleadosGUI;
import java.awt.SystemColor;

public class Ventana extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private EmpleadosGUI empleadosGUI;
	private DepartamentosGUI departamentosGUI;

	public Ventana() {
		getContentPane().setBackground(SystemColor.activeCaption);
		setBackground(SystemColor.activeCaption);
		initGUI();
		setLocationRelativeTo(null);
	}

	private void initGUI() {
		setTitle("Proyecto Individual");

		tabbedPane = new JTabbedPane();
		departamentosGUI = new DepartamentosGUI();
		empleadosGUI = new EmpleadosGUI();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		tabbedPane.setMinimumSize(new Dimension(700, 590));
		tabbedPane.setPreferredSize(new Dimension(700, 590));

		tabbedPane.addTab("Empleados", empleadosGUI.getPanelEmpleados());
		tabbedPane.addTab("Departamentos", departamentosGUI.getPanelDepartamentos());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		setMinimumSize(new Dimension(700, 620));
		setPreferredSize(new Dimension(720, 620));

		pack();
	}


	public void actualizar(IDEventos evento, Object datos) {
		switch (evento) {
		case EVENTO_MAIN:
			ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_LISTAR_DEPARTAMENTOS, null);
			break;
		case EVENTO_ALTA_EMPLEADO:
		case EVENTO_BAJA_EMPLEADO:
		case EVENTO_CALCULAR_NOMINA_EMPLEADO:
			empleadosGUI.actualizar(evento, datos);
			break;

		case EVENTO_ALTA_DEPARTAMENTO:
		case EVENTO_BAJA_DEPARTAMENTO:
		case EVENTO_MODIFICAR_DEPARTAMENTO:
		case EVENTO_CONSULTAR_DEPARTAMENTO:
		case EVENTO_CALCULAR_NOMINA_DEPARTAMENTO:
		case EVENTO_LISTAR_DEPARTAMENTOS:
			departamentosGUI.actualizar(evento, datos);
			break;
		default:
			break;
		}
	}

}