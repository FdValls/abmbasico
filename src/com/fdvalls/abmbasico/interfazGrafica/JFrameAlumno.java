package com.fdvalls.abmbasico.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JFrameAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDniMaestro = new JTextField();
	private JTextField textFieldNombre = new JTextField();
	private JTextField textFieldDocumento = new JTextField();
	private JTextField textFieldGenero = new JTextField();
	private JTextField textFieldEdad = new JTextField();
	private JTextField textFieldMail = new JTextField();
	private JTextField textFieldFechaDeIngreso = new JTextField();
	private JLabel etiquetaMaestro = new JLabel("DNI del profesor");
	private JLabel etiquetaNombre = new JLabel("Nombre");
	private JLabel etiquetaDocumento = new JLabel("Documento");
	private JLabel etiquetaGenero = new JLabel("Genero");
	private JLabel etiquetaEdad = new JLabel("Edad");
	private JLabel etiquetaMail = new JLabel("Mail");
	private JLabel etiquetaFechaIngreso = new JLabel("Fecha de ingreso");
	private JButton botonGuardar = new JButton("Guardar");
	private Ventana ventana;
	private JFrameOpciones jFrameOpciones;
	
	public JFrameAlumno(Ventana ventana, JFrameOpciones jFrameOpciones) {
		textFieldDniMaestro.setBounds(114, 47, 86, 20);
		textFieldDniMaestro.setColumns(10);
		this.ventana = ventana;
		this.jFrameOpciones = jFrameOpciones;
		inicializarBotones();
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

		textFieldFechaDeIngreso.setBounds(96, 202, 86, 20);
		contentPane.add(textFieldFechaDeIngreso);
		textFieldFechaDeIngreso.setColumns(10);

		etiquetaFechaIngreso.setBounds(29, 241, 94, 14);
		contentPane.add(etiquetaFechaIngreso);

		textFieldMail.setBounds(133, 238, 86, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		etiquetaMaestro.setBounds(29, 50, 86, 14);

		contentPane.add(etiquetaMaestro);

		contentPane.add(textFieldDniMaestro);
	}

	private void inicializarBotones() {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String dniMaestro = textFieldDniMaestro.getText();
				if (dniMaestro == null || dniMaestro.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El dni del maestro no puede estar vacío, campo obligatorio");
					error = true;
				}
				String nombre = textFieldNombre.getText();
				if (nombre == null || nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío, campo obligatorio");
					error = true;
				}
				String documento = textFieldDocumento.getText();
				if (documento == null || documento.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El documetno no puede estar vacío, campo obligatorio");
					error = true;
				}
				String genero = textFieldGenero.getText();
				if (genero == null || genero.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El genero no puede estar vacío, campo obligatorio");
					error = true;

				}
				Integer edad = Integer.parseInt(textFieldEdad.getText());
				String sEdad = String.valueOf(edad);
				if (edad == null || sEdad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La edad no puede estar vacío, campo obligatorio");
					error = true;
				}
				String mail = textFieldMail.getText();
				if (mail == null || mail.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El mail no puede estar vacío, campo obligatorio");
					error = true;
				}
				String fechaIngreso = textFieldMail.getText();
				if (fechaIngreso == null || fechaIngreso.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La fecha no puede estar vacío, campo obligatorio");
					error = true;
				}
				if (!error) {
					try {
						ventana.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fechaIngreso);
						jFrameOpciones.reiniciarListaAlumno();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}

			}

		});

	}
}
