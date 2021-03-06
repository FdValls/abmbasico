package com.fdvalls.abmbasico.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.fdvalls.abmbasico.modelo.Maestro;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public abstract class JFrameAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField textFieldDniMaestro = new JTextField();
	protected JTextField textFieldNombre = new JTextField();
	protected JTextField textFieldDocumento = new JTextField();
	protected JTextField textFieldGenero = new JTextField();
	protected JTextField textFieldEdad = new JTextField();
	protected JTextField textFieldMail = new JTextField();
	protected JTextField textFieldFechaDeIngreso = new JTextField();
	protected JLabel etiquetaMaestro = new JLabel("DNI del profesor");
	protected JLabel etiquetaNombre = new JLabel("Nombre");
	protected JLabel etiquetaDocumento = new JLabel("Documento");
	protected JLabel etiquetaGenero = new JLabel("Genero");
	protected JLabel etiquetaEdad = new JLabel("Edad");
	protected JLabel etiquetaMail = new JLabel("Mail");
	protected JLabel etiquetaFechaIngreso = new JLabel("Fecha de ingreso");
	protected Choice choiseProfesores = new Choice();
	protected JButton botonGuardar = new JButton("Guardar");
	protected Ventana ventana;
	protected JFrameOpciones jFrameOpciones;
	protected ArrayList<Maestro> maestros;
	protected Maestro mSeleccionado;

	public JFrameAlumno(Ventana ventana, JFrameOpciones jFrameOpciones) throws SQLException {
		textFieldDniMaestro.setBounds(114, 47, 86, 20);
		textFieldDniMaestro.setColumns(10);
		this.ventana = ventana;
		this.maestros = ventana.obtenerTodosLosMaestros();
		this.jFrameOpciones = jFrameOpciones;
		this.inicializarBotones();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNombre.setBounds(96, 80, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		etiquetaNombre.setBounds(29, 83, 46, 14);
		contentPane.add(etiquetaNombre);

		etiquetaDocumento.setBounds(29, 114, 77, 14);
		contentPane.add(etiquetaDocumento);

		textFieldDocumento.setBounds(96, 111, 86, 20);
		contentPane.add(textFieldDocumento);
		textFieldDocumento.setColumns(10);

		botonGuardar.setBounds(29, 294, 156, 86);
		contentPane.add(botonGuardar);

		textFieldGenero.setBounds(96, 139, 86, 20);
		contentPane.add(textFieldGenero);
		textFieldGenero.setColumns(10);

		etiquetaGenero.setBounds(29, 142, 57, 14);
		contentPane.add(etiquetaGenero);

		etiquetaEdad.setBounds(29, 173, 46, 14);
		contentPane.add(etiquetaEdad);

		textFieldEdad.setColumns(10);
		textFieldEdad.setBounds(96, 170, 86, 20);
		contentPane.add(textFieldEdad);
		etiquetaMail.setBounds(29, 205, 46, 14);

		contentPane.add(etiquetaMail);

		textFieldFechaDeIngreso.setBounds(125, 238, 96, 20);
		contentPane.add(textFieldFechaDeIngreso);
		textFieldFechaDeIngreso.setColumns(10);

		etiquetaFechaIngreso.setBounds(29, 241, 94, 14);
		contentPane.add(etiquetaFechaIngreso);

		textFieldMail.setBounds(96, 202, 188, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		etiquetaMaestro.setBounds(29, 50, 86, 14);

		contentPane.add(etiquetaMaestro);

		contentPane.add(textFieldDniMaestro);

		choiseProfesores.setBounds(115, 21, 215, 20);
		contentPane.add(choiseProfesores);

		choiseProfesores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				Maestro m = maestros.get(choiseProfesores.getSelectedIndex());
				mSeleccionado = maestros.get(choiseProfesores.getSelectedIndex());
				textFieldDniMaestro.setText(m.getDocumento());
				textFieldDniMaestro.setEnabled(false);
			}
		});
	}

	private void inicializarBotones() {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				try {
					String dniMaestro = textFieldDniMaestro.getText();
					if (dniMaestro == null || dniMaestro.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"El dni del maestro no puede estar vac�o, campo obligatorio");
						error = true;
					}
					Maestro m = ventana.obtenerMaestroPorDni(dniMaestro);
					Integer idMaestro = m.getIdMaestro();
					String nombre = textFieldNombre.getText();
					if (nombre == null || nombre.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El nombre no puede estar vac�o, campo obligatorio");
						error = true;
					}
					String documento = textFieldDocumento.getText();
					if (documento == null || documento.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El documento no puede estar vac�o, campo obligatorio");
						error = true;
					}
					String genero = textFieldGenero.getText();
					if (genero == null || genero.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El genero no puede estar vac�o, campo obligatorio");
						error = true;
					}
					Integer edad = Integer.parseInt(textFieldEdad.getText());
					String sEdad = String.valueOf(edad);
					if (edad == null || sEdad.isEmpty()) {
						JOptionPane.showMessageDialog(null, "La edad no puede estar vac�o, campo obligatorio");
						error = true;
					}
					String mail = textFieldMail.getText();
					if (mail == null || mail.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El mail no puede estar vac�o, campo obligatorio");
						error = true;
					}
					String fechaIngreso = textFieldFechaDeIngreso.getText();
					if (fechaIngreso == null || fechaIngreso.isEmpty()) {
						JOptionPane.showMessageDialog(null, "La fecha no puede estar vac�o, campo obligatorio");
						error = true;
					}
					if (!error) {
						try {
							ejecutarAccionBotonGuardar(documento, idMaestro, nombre, genero, edad, mail, fechaIngreso);
							jFrameOpciones.reiniciarListaAlumno();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					setVisible(false);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
	}

	protected abstract void ejecutarAccionBotonGuardar(String dniAlumno, Integer idMaestro, String nombre, String genero,
			int edad, String mail, String fecha);
	
}
