/**
 * 
 */
package Presentacion.Empleados;

import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.ApplicationController;

import javax.swing.*;

import Negocio.Empleados.TransferEmpleado;
import Negocio.Empleados.TransferEmpleadoTiempoCompleto;
import Negocio.Empleados.TransferEmpleadoTiempoParcial;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelEmpleados extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form PanelEmpleados
	 */
	public PanelEmpleados() {
		setBackground(SystemColor.activeCaption);
		initGUI();
	}


	private JButton btnAlta;
	private JTextField textFieldApellidos;
	private JButton btnBaja;
	private JTextField textFieldDepartamento;
	private JTextField textFieldDNI;
	private JTextField textFieldHoras;
	private JTextField textFieldID;
	private JTextField textFieldImpuesto;
	private JTextField textFieldNombre;
	private JLabel nominaLabel;
	private JTextField textFieldSalarioPorHora;
	private JTextField textFieldSalario;
	private JPanel panelTiempoCompleto;
	private JRadioButton rdbtTiempoCompleto;
	private JPanel panelTiempoParcial;
	private JRadioButton rdbtnTiempoParcial;
	private ButtonGroup tipoEmpleadoButtonGroup;

	private List<TransferEmpleado> empleados;
	private JLabel lblEmpleado;

	private void altaButtonActionPerformed(ActionEvent evt) {
		TransferEmpleado newEmpleado = getTransfer();
		if (newEmpleado == null) {
			return;
		}

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_ALTA_EMPLEADO, newEmpleado);
	}

	private void bajaButtonActionPerformed(ActionEvent evt) {
		Integer id = getID();
		if (id == null) {
			return;
		}

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_BAJA_EMPLEADO, id);
	}

	private void tipoEmpleadoChanged(ActionEvent evt) {
		setPanelEnabled(panelTiempoCompleto, rdbtTiempoCompleto.isSelected());
		setPanelEnabled(panelTiempoParcial, rdbtnTiempoParcial.isSelected());
	}


	private TransferEmpleado getTransfer() {
		Integer idDepartamento = getIDDepartamento();
		if (idDepartamento == null) {
			return null;
		}

		TransferEmpleado empleado;

		if (rdbtTiempoCompleto.isSelected()) {
			Double impuesto = getImpuesto();
			if (impuesto == null) {
				return null;
			}
			Double salario = getSalario();
			if (salario == null) {
				return null;
			}

			TransferEmpleadoTiempoCompleto empleadoTC = new TransferEmpleadoTiempoCompleto();
			empleadoTC.setImpuesto(impuesto);
			empleadoTC.setSalario(salario);

			empleado = empleadoTC;
		}
		else if (rdbtnTiempoParcial.isSelected()) {
			Integer horas = getHoras();
			if (horas == null) {
				return null;
			}
			Double salarioPorHora = getSalarioPorHora();
			if (salarioPorHora == null) {
				return null;
			}

			TransferEmpleadoTiempoParcial empleadoTP = new TransferEmpleadoTiempoParcial();
			empleadoTP.setHoras(horas);
			empleadoTP.setSalarioPorHora(salarioPorHora);

			empleado = empleadoTP;
		}
		else {
			JOptionPane.showMessageDialog(null, "Selecciona tipo de empleado",
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		empleado.setDNI(textFieldDNI.getText());
		empleado.setNombre(textFieldNombre.getText());
		empleado.setApellidos(textFieldApellidos.getText());
		empleado.setIDDepartamento(idDepartamento);
		empleado.setActivo(true);

		return empleado;
	}

	private Integer getID() {
		Integer id = null;
		try {
			id = Integer.valueOf(textFieldID.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "ID de empleado inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return id;
	}

	private Double getImpuesto() {
		Double impuesto = null;
		try {
			impuesto = Double.valueOf(textFieldImpuesto.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "Impuesto inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return impuesto;
	}

	private Double getSalario() {
		Double salario = null;
		try {
			salario = Double.valueOf(textFieldSalario.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "Salario inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return salario;
	}

	private Integer getHoras() {
		Integer horas = null;
		try {
			horas = Integer.valueOf(textFieldHoras.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "Horas inválidas",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return horas;
	}

	private Double getSalarioPorHora() {
		Double salarioPorHora = null;
		try {
			salarioPorHora = Double.valueOf(textFieldSalarioPorHora.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "Salario por hora inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return salarioPorHora;
	}

	private Integer getIDDepartamento() {
		Integer idDepartamento = null;
		try {
			idDepartamento = Integer.valueOf(textFieldDepartamento.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "ID de departamento inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return idDepartamento;
	}


	void setPanelEnabled(Container container, Boolean isEnabled) {
		container.setEnabled(isEnabled);

		for (Component component: container.getComponents()) {
			component.setEnabled(isEnabled);
			if (component instanceof Container) {
				setPanelEnabled((Container)component, isEnabled);
			}
		}
	}


	/**
	 * Inicialización elementos interfaz
	 */
	private void initGUI() {
		tipoEmpleadoButtonGroup = new javax.swing.ButtonGroup();

		setMinimumSize(new java.awt.Dimension(670, 530));
		setPreferredSize(new java.awt.Dimension(670, 530));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{246, 0};
		gridBagLayout.rowHeights = new int[]{446, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		javax.swing.JPanel panelData = new javax.swing.JPanel();
		panelData.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panelData = new GridBagConstraints();
		gbc_panelData.insets = new Insets(0, 0, 5, 0);
		gbc_panelData.gridx = 0;
		gbc_panelData.gridy = 0;
		add(panelData, gbc_panelData);
		GridBagLayout gbl_panelData = new GridBagLayout();
		gbl_panelData.columnWidths = new int[]{73, 46, 65, 0};
		gbl_panelData.rowHeights = new int[]{23, 0, 20, 20, 20, 20, 23, 68, 0, 82, 0};
		gbl_panelData.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelData.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelData.setLayout(gbl_panelData);
		
		lblEmpleado = new JLabel("EMPLEADO");
		GridBagConstraints gbc_lblEmpleado = new GridBagConstraints();
		gbc_lblEmpleado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpleado.gridx = 1;
		gbc_lblEmpleado.gridy = 0;
		panelData.add(lblEmpleado, gbc_lblEmpleado);
		javax.swing.JLabel lblID = new javax.swing.JLabel();
		
				lblID.setText("ID:");
				GridBagConstraints gbc_lblID = new GridBagConstraints();
				gbc_lblID.anchor = GridBagConstraints.EAST;
				gbc_lblID.insets = new Insets(0, 0, 5, 5);
				gbc_lblID.gridx = 0;
				gbc_lblID.gridy = 1;
				panelData.add(lblID, gbc_lblID);
		textFieldID = new javax.swing.JTextField();
		
				textFieldID.setColumns(5);
				GridBagConstraints gbc_textFieldID = new GridBagConstraints();
				gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldID.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldID.gridx = 1;
				gbc_textFieldID.gridy = 1;
				panelData.add(textFieldID, gbc_textFieldID);
		javax.swing.JLabel lblDNI = new javax.swing.JLabel();

		lblDNI.setText("DNI:");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.EAST;
		gbc_lblDNI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDNI.gridx = 0;
		gbc_lblDNI.gridy = 2;
		panelData.add(lblDNI, gbc_lblDNI);
		textFieldDNI = new javax.swing.JTextField();

		textFieldDNI.setColumns(9);
		GridBagConstraints gbc_textFieldDNI = new GridBagConstraints();
		gbc_textFieldDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDNI.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDNI.gridx = 1;
		gbc_textFieldDNI.gridy = 2;
		panelData.add(textFieldDNI, gbc_textFieldDNI);
		javax.swing.JLabel lblNombre = new javax.swing.JLabel();

		lblNombre.setText("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		panelData.add(lblNombre, gbc_lblNombre);
		rdbtTiempoCompleto = new javax.swing.JRadioButton();
		rdbtTiempoCompleto.setBackground(SystemColor.activeCaption);

		tipoEmpleadoButtonGroup.add(rdbtTiempoCompleto);
		rdbtTiempoCompleto.setText("Tiempo Completo");
		rdbtTiempoCompleto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tipoEmpleadoChanged(evt);
			}
		});
		textFieldNombre = new javax.swing.JTextField();

		textFieldNombre.setColumns(16);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		panelData.add(textFieldNombre, gbc_textFieldNombre);
		javax.swing.JLabel lblApellidos = new javax.swing.JLabel();

		lblApellidos.setText("Apellidos:");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 4;
		panelData.add(lblApellidos, gbc_lblApellidos);
		textFieldApellidos = new javax.swing.JTextField();

		textFieldApellidos.setColumns(16);
		GridBagConstraints gbc_textFieldApellidos = new GridBagConstraints();
		gbc_textFieldApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidos.gridx = 1;
		gbc_textFieldApellidos.gridy = 4;
		panelData.add(textFieldApellidos, gbc_textFieldApellidos);
		javax.swing.JLabel lblDepartamento = new javax.swing.JLabel();

		lblDepartamento.setText("Departamento:");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 5;
		panelData.add(lblDepartamento, gbc_lblDepartamento);
		textFieldDepartamento = new javax.swing.JTextField();

		textFieldDepartamento.setColumns(16);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartamento.gridx = 1;
		gbc_textFieldDepartamento.gridy = 5;
		panelData.add(textFieldDepartamento, gbc_textFieldDepartamento);
		GridBagConstraints gbc_rdbtTiempoCompleto = new GridBagConstraints();
		gbc_rdbtTiempoCompleto.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbtTiempoCompleto.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtTiempoCompleto.gridx = 0;
		gbc_rdbtTiempoCompleto.gridy = 6;
		panelData.add(rdbtTiempoCompleto, gbc_rdbtTiempoCompleto);
		rdbtnTiempoParcial = new javax.swing.JRadioButton();
		rdbtnTiempoParcial.setBackground(SystemColor.activeCaption);

		tipoEmpleadoButtonGroup.add(rdbtnTiempoParcial);
		rdbtnTiempoParcial.setText("Tiempo Parcial");
		rdbtnTiempoParcial.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tipoEmpleadoChanged(evt);
			}
		});
		GridBagConstraints gbc_rdbtnTiempoParcial = new GridBagConstraints();
		gbc_rdbtnTiempoParcial.anchor = GridBagConstraints.NORTHWEST;
		gbc_rdbtnTiempoParcial.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTiempoParcial.gridx = 1;
		gbc_rdbtnTiempoParcial.gridy = 6;
		panelData.add(rdbtnTiempoParcial, gbc_rdbtnTiempoParcial);
		panelTiempoCompleto = new javax.swing.JPanel();
		panelTiempoCompleto.setBackground(SystemColor.activeCaption);
		javax.swing.JLabel lblImpuesto = new javax.swing.JLabel();
		lblImpuesto.setForeground(Color.BLACK);
		lblImpuesto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldImpuesto = new javax.swing.JTextField();
		javax.swing.JLabel lblSalario = new javax.swing.JLabel();
		textFieldSalario = new javax.swing.JTextField();
		panelTiempoCompleto.setEnabled(false);

		lblImpuesto.setText("Impuesto:");
		lblImpuesto.setEnabled(false);

		textFieldImpuesto.setColumns(8);
		textFieldImpuesto.setEnabled(false);

		lblSalario.setText("Salario:");
		lblSalario.setEnabled(false);

		textFieldSalario.setColumns(8);
		textFieldSalario.setEnabled(false);

		javax.swing.GroupLayout gl_panelTiempoCompleto = new javax.swing.GroupLayout(panelTiempoCompleto);
		panelTiempoCompleto.setLayout(gl_panelTiempoCompleto);
		gl_panelTiempoCompleto.setHorizontalGroup(
				gl_panelTiempoCompleto.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_panelTiempoCompleto.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelTiempoCompleto.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(gl_panelTiempoCompleto.createSequentialGroup()
										.addComponent(lblImpuesto)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelTiempoCompleto.createSequentialGroup()
										.addComponent(lblSalario)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_panelTiempoCompleto.setVerticalGroup(
				gl_panelTiempoCompleto.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_panelTiempoCompleto.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelTiempoCompleto.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblImpuesto)
								.addComponent(textFieldImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(gl_panelTiempoCompleto.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblSalario)
								.addComponent(textFieldSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		GridBagConstraints gbc_panelTiempoCompleto = new GridBagConstraints();
		gbc_panelTiempoCompleto.anchor = GridBagConstraints.NORTH;
		gbc_panelTiempoCompleto.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTiempoCompleto.insets = new Insets(0, 0, 5, 5);
		gbc_panelTiempoCompleto.gridx = 0;
		gbc_panelTiempoCompleto.gridy = 7;
		panelData.add(panelTiempoCompleto, gbc_panelTiempoCompleto);
		panelTiempoParcial = new javax.swing.JPanel();
		panelTiempoParcial.setBackground(SystemColor.activeCaption);
		javax.swing.JLabel lblHoras = new javax.swing.JLabel();
		textFieldHoras = new javax.swing.JTextField();
		javax.swing.JLabel lblSalarioPorHora = new javax.swing.JLabel();
		textFieldSalarioPorHora = new javax.swing.JTextField();
		panelTiempoParcial.setEnabled(false);

		lblHoras.setText("Horas:");
		lblHoras.setEnabled(false);

		textFieldHoras.setColumns(8);
		textFieldHoras.setEnabled(false);

		lblSalarioPorHora.setText("Salario por hora:");
		lblSalarioPorHora.setEnabled(false);

		textFieldSalarioPorHora.setColumns(8);
		textFieldSalarioPorHora.setEnabled(false);

		javax.swing.GroupLayout gl_panelTiempoParcial = new javax.swing.GroupLayout(panelTiempoParcial);
		panelTiempoParcial.setLayout(gl_panelTiempoParcial);
		gl_panelTiempoParcial.setHorizontalGroup(
				gl_panelTiempoParcial.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_panelTiempoParcial.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelTiempoParcial.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(gl_panelTiempoParcial.createSequentialGroup()
										.addComponent(lblHoras)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelTiempoParcial.createSequentialGroup()
										.addComponent(lblSalarioPorHora)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textFieldSalarioPorHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		gl_panelTiempoParcial.setVerticalGroup(
				gl_panelTiempoParcial.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_panelTiempoParcial.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelTiempoParcial.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblHoras)
								.addComponent(textFieldHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(gl_panelTiempoParcial.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblSalarioPorHora)
								.addComponent(textFieldSalarioPorHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		GridBagConstraints gbc_panelTiempoParcial = new GridBagConstraints();
		gbc_panelTiempoParcial.anchor = GridBagConstraints.NORTH;
		gbc_panelTiempoParcial.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTiempoParcial.insets = new Insets(0, 0, 5, 5);
		gbc_panelTiempoParcial.gridx = 1;
		gbc_panelTiempoParcial.gridy = 7;
		panelData.add(panelTiempoParcial, gbc_panelTiempoParcial);

		JLabel lblNmina = new JLabel("N\u00F3mina");
		GridBagConstraints gbc_lblNmina = new GridBagConstraints();
		gbc_lblNmina.insets = new Insets(0, 0, 5, 5);
		gbc_lblNmina.gridx = 1;
		gbc_lblNmina.gridy = 8;
		panelData.add(lblNmina, gbc_lblNmina);
		javax.swing.JPanel panelNomina = new javax.swing.JPanel();
		panelNomina.setBackground(SystemColor.activeCaption);
		nominaLabel = new javax.swing.JLabel();

		nominaLabel.setFont(new java.awt.Font("Lucida Grande", 0, 30)); // NOI18N
		nominaLabel.setText(" ");

		javax.swing.GroupLayout gl_panelNomina = new javax.swing.GroupLayout(panelNomina);
		panelNomina.setLayout(gl_panelNomina);
		gl_panelNomina.setHorizontalGroup(
				gl_panelNomina.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gl_panelNomina.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nominaLabel)
						.addGap(12, 12, 12))
				);
		gl_panelNomina.setVerticalGroup(
				gl_panelNomina.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_panelNomina.createSequentialGroup()
						.addContainerGap()
						.addComponent(nominaLabel)
						.addContainerGap())
				);
		GridBagConstraints gbc_panelNomina = new GridBagConstraints();
		gbc_panelNomina.anchor = GridBagConstraints.NORTH;
		gbc_panelNomina.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNomina.gridwidth = 3;
		gbc_panelNomina.gridx = 0;
		gbc_panelNomina.gridy = 9;
		panelData.add(panelNomina, gbc_panelNomina);
		javax.swing.JPanel panelBotones = new javax.swing.JPanel();
		panelBotones.setBackground(SystemColor.activeCaption);
		btnAlta = new javax.swing.JButton();
		btnBaja = new javax.swing.JButton();

		btnAlta.setForeground(Color.BLACK);
		btnAlta.setText("Alta");
		btnAlta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				altaButtonActionPerformed(evt);
			}
		});
		panelBotones.add(btnAlta);

		btnBaja.setForeground(Color.BLACK);
		btnBaja.setText("Baja");
		btnBaja.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bajaButtonActionPerformed(evt);
			}
		});
		panelBotones.add(btnBaja);
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.NORTH;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 1;
		add(panelBotones, gbc_panelBotones);
	}

	// GETTERS AND SETTERS
	
	public JButton getBtnAlta() {
		return btnAlta;
	}

	public void setBtnAlta(JButton btnAlta) {
		this.btnAlta = btnAlta;
	}

	public JTextField getTextFieldApellidos() {
		return textFieldApellidos;
	}

	public void setTextFieldApellidos(JTextField textFieldApellidos) {
		this.textFieldApellidos = textFieldApellidos;
	}

	public JButton getBtnBaja() {
		return btnBaja;
	}

	public void setBtnBaja(JButton btnBaja) {
		this.btnBaja = btnBaja;
	}

	public JTextField getTextFieldDepartamento() {
		return textFieldDepartamento;
	}

	public void setTextFieldDepartamento(JTextField textFieldDepartamento) {
		this.textFieldDepartamento = textFieldDepartamento;
	}

	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}

	public void setTextFieldDNI(JTextField textFieldDNI) {
		this.textFieldDNI = textFieldDNI;
	}

	public JTextField getTextFieldHoras() {
		return textFieldHoras;
	}

	public void setTextFieldHoras(JTextField textFieldHoras) {
		this.textFieldHoras = textFieldHoras;
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public void setTextFieldID(JTextField textFieldID) {
		this.textFieldID = textFieldID;
	}

	public JTextField getTextFieldImpuesto() {
		return textFieldImpuesto;
	}

	public void setTextFieldImpuesto(JTextField textFieldImpuesto) {
		this.textFieldImpuesto = textFieldImpuesto;
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public void setTextFieldNombre(JTextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public JLabel getNominaLabel() {
		return nominaLabel;
	}

	public void setNominaLabel(JLabel nominaLabel) {
		this.nominaLabel = nominaLabel;
	}

	public JTextField getTextFieldSalarioPorHora() {
		return textFieldSalarioPorHora;
	}

	public void setTextFieldSalarioPorHora(JTextField textFieldSalarioPorHora) {
		this.textFieldSalarioPorHora = textFieldSalarioPorHora;
	}

	public JTextField getTextFieldSalario() {
		return textFieldSalario;
	}

	public void setTextFieldSalario(JTextField textFieldSalario) {
		this.textFieldSalario = textFieldSalario;
	}

	public JPanel getPanelTiempoCompleto() {
		return panelTiempoCompleto;
	}

	public void setPanelTiempoCompleto(JPanel panelTiempoCompleto) {
		this.panelTiempoCompleto = panelTiempoCompleto;
	}

	public JRadioButton getRdbtTiempoCompleto() {
		return rdbtTiempoCompleto;
	}

	public void setRdbtTiempoCompleto(JRadioButton rdbtTiempoCompleto) {
		this.rdbtTiempoCompleto = rdbtTiempoCompleto;
	}

	public JPanel getPanelTiempoParcial() {
		return panelTiempoParcial;
	}

	public void setPanelTiempoParcial(JPanel panelTiempoParcial) {
		this.panelTiempoParcial = panelTiempoParcial;
	}

	public JRadioButton getRdbtnTiempoParcial() {
		return rdbtnTiempoParcial;
	}

	public void setRdbtnTiempoParcial(JRadioButton rdbtnTiempoParcial) {
		this.rdbtnTiempoParcial = rdbtnTiempoParcial;
	}

	public ButtonGroup getTipoEmpleadoButtonGroup() {
		return tipoEmpleadoButtonGroup;
	}

	public void setTipoEmpleadoButtonGroup(ButtonGroup tipoEmpleadoButtonGroup) {
		this.tipoEmpleadoButtonGroup = tipoEmpleadoButtonGroup;
	}

	public List<TransferEmpleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<TransferEmpleado> empleados) {
		this.empleados = empleados;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public void setLblEmpleado(JLabel lblEmpleado) {
		this.lblEmpleado = lblEmpleado;
	}
}
