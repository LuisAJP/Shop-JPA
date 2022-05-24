
package Presentacion.Departamentos;


import Presentacion.Comando.Comando.IDEventos;
import Presentacion.Controlador.ApplicationController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import Negocio.Departamentos.TransferDepartamento;
import Negocio.Empleados.TransferEmpleado;

import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

public class PanelDepartamentos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelDepartamentos() {
		setBackground(SystemColor.activeCaption);
		setForeground(SystemColor.activeCaption);
		initGUI();
	}

	private JButton btnAlta;
	private JButton btnBaja;
	private JButton btnCargar;
	private JList<String> listDepartamentos;
	private JList<String> listEmpleados;
	private JTextField textFieldID;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton btnModificar;
	private JTextField textFieldNombre;
	private JLabel nominaDepartamentoLabel;

	private List<TransferDepartamento> departamentos;
	private JLabel lblNominaDepartamento;
	private JLabel lblDepartamento;

	public void actualizarDepartamentos( List<TransferDepartamento> departamentos) {
		this.departamentos = departamentos;
		listDepartamentos.setModel(new AbstractListModel<String>() {
			public int getSize() { return PanelDepartamentos.this.departamentos == null ? 0 : PanelDepartamentos.this.departamentos.size(); }
			public String getElementAt(int i) { return PanelDepartamentos.this.departamentos.get(i).toString(); }
		});
	}

	public void actualizarEmpleados(final List<TransferEmpleado> empleados) {
		listEmpleados.setModel(new AbstractListModel<String>() {
			public int getSize() { return empleados == null ? 0 : empleados.size(); }
			public String getElementAt(int i) { return empleados.get(i).toString(); }
		});
	}

	private void altaButtonActionPerformed(ActionEvent evt) {
		TransferDepartamento newDepartamento = new TransferDepartamento();
		newDepartamento.setNombre(textFieldNombre.getText());
		newDepartamento.setActivo(true);

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_ALTA_DEPARTAMENTO, newDepartamento);
	}

	private void bajaButtonActionPerformed(ActionEvent evt) {
		Integer id = getID();
		if (id == null) {
			return;
		}

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_BAJA_DEPARTAMENTO, id);
	}

	private void modificarButtonActionPerformed(ActionEvent evt) {
		Integer id = getID();
		if (id == null) {
			return;
		}

		TransferDepartamento departamento = new TransferDepartamento();
		departamento.setID(id);
		departamento.setNombre(textFieldNombre.getText());
		departamento.setActivo(true);

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_MODIFICAR_DEPARTAMENTO, departamento);
	}

	private void cargarButtonActionPerformed(ActionEvent evt) {
		Integer id = getID();
		if (id == null) {
			return;
		}

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_CONSULTAR_DEPARTAMENTO, id);
	}

	private void departamentosListValueChanged(ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting()) {
			return;
		}

		int index = listDepartamentos.getSelectedIndex();
		if (departamentos == null || index < 0 || index >= departamentos.size()) {
			return;
		}

		TransferDepartamento departamento = departamentos.get(index);

		ApplicationController.getInstance().handleRequest(IDEventos.EVENTO_CONSULTAR_DEPARTAMENTO, departamento.getID());
	}

	private Integer getID() {
		Integer id = null;
		try {
			id = Integer.valueOf(textFieldID.getText());
		}
		catch (NumberFormatException ignored) {
			JOptionPane.showMessageDialog(null, "ID de departamento inválido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		return id;
	}

	/**
	 * Inicialización elementos interfaz
	 */
	private void initGUI() {

		setMinimumSize(new java.awt.Dimension(700, 590));
		setPreferredSize(new Dimension(700, 590));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{406, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		javax.swing.JPanel panelData = new javax.swing.JPanel();
		panelData.setBackground(SystemColor.activeCaption);
		GridBagLayout gbl_panelData = new GridBagLayout();
		gbl_panelData.columnWidths = new int[]{132, 63, 132, 0};
		gbl_panelData.rowHeights = new int[]{23, 0, 20, 14, 138, 0, 0, 0};
		gbl_panelData.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelData.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelData.setLayout(gbl_panelData);

		lblDepartamento = new JLabel("DEPARTAMENTO");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 0;
		panelData.add(lblDepartamento, gbc_lblDepartamento);
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
		btnCargar = new javax.swing.JButton();

		btnCargar.setText("Cargar");
		btnCargar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cargarButtonActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_btnCargar = new GridBagConstraints();
		gbc_btnCargar.anchor = GridBagConstraints.WEST;
		gbc_btnCargar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCargar.gridx = 2;
		gbc_btnCargar.gridy = 1;
		panelData.add(btnCargar, gbc_btnCargar);
		javax.swing.JLabel lblNombre = new javax.swing.JLabel();

		lblNombre.setText("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		panelData.add(lblNombre, gbc_lblNombre);
		textFieldNombre = new javax.swing.JTextField();

		textFieldNombre.setColumns(16);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 2;
		panelData.add(textFieldNombre, gbc_textFieldNombre);
		javax.swing.JLabel lblEmpleados = new javax.swing.JLabel();

		lblEmpleados.setText("EMPLEADOS");
		GridBagConstraints gbc_lblEmpleados = new GridBagConstraints();
		gbc_lblEmpleados.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpleados.gridx = 1;
		gbc_lblEmpleados.gridy = 3;
		panelData.add(lblEmpleados, gbc_lblEmpleados);
		GridBagConstraints gbc_panelData = new GridBagConstraints();
		gbc_panelData.fill = GridBagConstraints.BOTH;
		gbc_panelData.insets = new Insets(0, 0, 5, 0);
		gbc_panelData.gridx = 0;
		gbc_panelData.gridy = 0;
		add(panelData, gbc_panelData);
		javax.swing.JPanel panelListaEmpleados = new javax.swing.JPanel();
		panelListaEmpleados.setBackground(SystemColor.activeCaption);
		jScrollPane2 = new javax.swing.JScrollPane();
		listEmpleados = new javax.swing.JList<>();

		panelListaEmpleados.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 3, 0));

		jScrollPane2.setViewportView(listEmpleados);

		panelListaEmpleados.add(jScrollPane2);
		GridBagConstraints gbc_panelListaEmpleados = new GridBagConstraints();
		gbc_panelListaEmpleados.insets = new Insets(0, 0, 5, 5);
		gbc_panelListaEmpleados.fill = GridBagConstraints.BOTH;
		gbc_panelListaEmpleados.gridx = 1;
		gbc_panelListaEmpleados.gridy = 4;
		panelData.add(panelListaEmpleados, gbc_panelListaEmpleados);

		lblNominaDepartamento = new JLabel("N\u00D3MINA DEPARTAMENTO");
		GridBagConstraints gbc_lblNominaDepartamento = new GridBagConstraints();
		gbc_lblNominaDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNominaDepartamento.gridx = 1;
		gbc_lblNominaDepartamento.gridy = 5;
		panelData.add(lblNominaDepartamento, gbc_lblNominaDepartamento);
		javax.swing.JPanel panelNomidaDepartamento = new javax.swing.JPanel();
		panelNomidaDepartamento.setBackground(SystemColor.activeCaption);
		nominaDepartamentoLabel = new javax.swing.JLabel();

		nominaDepartamentoLabel.setFont(new java.awt.Font("Lucida Grande", 0, 30)); // NOI18N
		nominaDepartamentoLabel.setText(" ");

		javax.swing.GroupLayout gl_panelNomidaDepartamento = new javax.swing.GroupLayout(panelNomidaDepartamento);
		panelNomidaDepartamento.setLayout(gl_panelNomidaDepartamento);
		gl_panelNomidaDepartamento.setHorizontalGroup(
				gl_panelNomidaDepartamento.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gl_panelNomidaDepartamento.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nominaDepartamentoLabel)
						.addGap(12, 12, 12))
				);
		gl_panelNomidaDepartamento.setVerticalGroup(
				gl_panelNomidaDepartamento.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(gl_panelNomidaDepartamento.createSequentialGroup()
						.addContainerGap()
						.addComponent(nominaDepartamentoLabel)
						.addContainerGap())
				);
		GridBagConstraints gbc_panelNomidaDepartamento = new GridBagConstraints();
		gbc_panelNomidaDepartamento.fill = GridBagConstraints.VERTICAL;
		gbc_panelNomidaDepartamento.gridwidth = 3;
		gbc_panelNomidaDepartamento.gridx = 0;
		gbc_panelNomidaDepartamento.gridy = 6;
		panelData.add(panelNomidaDepartamento, gbc_panelNomidaDepartamento);
		javax.swing.JPanel panelBotones = new javax.swing.JPanel();
		panelBotones.setBackground(SystemColor.activeCaption);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[]{51, 53, 75, 0};
		gbl_panelBotones.rowHeights = new int[]{23, 0};
		gbl_panelBotones.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBotones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelBotones.setLayout(gbl_panelBotones);
		btnAlta = new javax.swing.JButton();

		btnAlta.setForeground(Color.BLACK);
		btnAlta.setText("Alta");
		btnAlta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				altaButtonActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_btnAlta = new GridBagConstraints();
		gbc_btnAlta.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAlta.insets = new Insets(0, 0, 0, 5);
		gbc_btnAlta.gridx = 0;
		gbc_btnAlta.gridy = 0;
		panelBotones.add(btnAlta, gbc_btnAlta);
		btnModificar = new javax.swing.JButton();

		btnModificar.setText("Modificar");
		btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modificarButtonActionPerformed(evt);
			}
		});
		btnBaja = new javax.swing.JButton();

		btnBaja.setForeground(Color.BLACK);
		btnBaja.setText("Baja");
		btnBaja.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bajaButtonActionPerformed(evt);
			}
		});
		GridBagConstraints gbc_btnBaja = new GridBagConstraints();
		gbc_btnBaja.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBaja.insets = new Insets(0, 0, 0, 5);
		gbc_btnBaja.gridx = 1;
		gbc_btnBaja.gridy = 0;
		panelBotones.add(btnBaja, gbc_btnBaja);
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnModificar.gridx = 2;
		gbc_btnModificar.gridy = 0;
		panelBotones.add(btnModificar, gbc_btnModificar);
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 5, 0);
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 1;
		add(panelBotones, gbc_panelBotones);
		javax.swing.JPanel panelDepartamentos = new javax.swing.JPanel();
		panelDepartamentos.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_panelDepartamentos = new GridBagConstraints();
		gbc_panelDepartamentos.anchor = GridBagConstraints.NORTH;
		gbc_panelDepartamentos.gridx = 0;
		gbc_panelDepartamentos.gridy = 2;
		add(panelDepartamentos, gbc_panelDepartamentos);
		GridBagLayout gbl_panelDepartamentos = new GridBagLayout();
		gbl_panelDepartamentos.columnWidths = new int[]{273, 0};
		gbl_panelDepartamentos.rowHeights = new int[]{0, 100, 0};
		gbl_panelDepartamentos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDepartamentos.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelDepartamentos.setLayout(gbl_panelDepartamentos);
		jScrollPane1 = new javax.swing.JScrollPane();
		listDepartamentos = new javax.swing.JList<>();

		listDepartamentos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				departamentosListValueChanged(evt);
			}
		});

		JLabel lblDepartamentos = new JLabel("DEPARTAMENTOS");
		GridBagConstraints gbc_lblDepartamentos = new GridBagConstraints();
		gbc_lblDepartamentos.fill = GridBagConstraints.VERTICAL;
		gbc_lblDepartamentos.insets = new Insets(0, 0, 5, 0);
		gbc_lblDepartamentos.gridx = 0;
		gbc_lblDepartamentos.gridy = 0;
		panelDepartamentos.add(lblDepartamentos, gbc_lblDepartamentos);
		jScrollPane1.setViewportView(listDepartamentos);
		GridBagConstraints gbc_jScrollPane1 = new GridBagConstraints();
		gbc_jScrollPane1.fill = GridBagConstraints.BOTH;
		gbc_jScrollPane1.gridx = 0;
		gbc_jScrollPane1.gridy = 1;
		panelDepartamentos.add(jScrollPane1, gbc_jScrollPane1);
	}

	public JButton getAltaButton() {
		return btnAlta;
	}

	public void setAltaButton(JButton altaButton) {
		this.btnAlta = altaButton;
	}

	public JButton getBajaButton() {
		return btnBaja;
	}

	public void setBajaButton(JButton bajaButton) {
		this.btnBaja = bajaButton;
	}

	public JButton getCargarButton() {
		return btnCargar;
	}

	public void setCargarButton(JButton cargarButton) {
		this.btnCargar = cargarButton;
	}

	public JList<String> getDepartamentosList() {
		return listDepartamentos;
	}

	public void setDepartamentosList(JList<String> departamentosList) {
		this.listDepartamentos = departamentosList;
	}

	public JList<String> getEmpleadosList() {
		return listEmpleados;
	}

	public void setEmpleadosList(JList<String> empleadosList) {
		this.listEmpleados = empleadosList;
	}

	public JTextField getIdTextField() {
		return textFieldID;
	}

	public void setIdTextField(JTextField idTextField) {
		this.textFieldID = idTextField;
	}

	public JScrollPane getjScrollPane1() {
		return jScrollPane1;
	}

	public void setjScrollPane1(JScrollPane jScrollPane1) {
		this.jScrollPane1 = jScrollPane1;
	}

	public JScrollPane getjScrollPane2() {
		return jScrollPane2;
	}

	public void setjScrollPane2(JScrollPane jScrollPane2) {
		this.jScrollPane2 = jScrollPane2;
	}

	public JButton getModificarButton() {
		return btnModificar;
	}

	public void setModificarButton(JButton modificarButton) {
		this.btnModificar = modificarButton;
	}

	public JTextField getNombreTextField() {
		return textFieldNombre;
	}

	public void setNombreTextField(JTextField nombreTextField) {
		this.textFieldNombre = nombreTextField;
	}

	public JLabel getNominaDepartamentoLabel() {
		return nominaDepartamentoLabel;
	}

	public void setNominaDepartamentoLabel(JLabel nominaDepartamentoLabel) {
		this.nominaDepartamentoLabel = nominaDepartamentoLabel;
	}

	public List<TransferDepartamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<TransferDepartamento> departamentos) {
		this.departamentos = departamentos;
	}

	public JLabel getLblNominaDepartamento() {
		return lblNominaDepartamento;
	}

	public void setLblNominaDepartamento(JLabel lblNominaDepartamento) {
		this.lblNominaDepartamento = lblNominaDepartamento;
	}
}
