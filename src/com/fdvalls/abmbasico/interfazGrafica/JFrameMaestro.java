package com.fdvalls.abmbasico.interfazGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fdvalls.abmbasico.modelo.Maestro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JFrameMaestro extends JFrame {

	private static final long serialVersionUID = 1L;
	private Ventana ventana;
	private JFrameOpciones jFrameOpciones;
	private JPanel contentPane;
	private JTextField textFieldNombre = new JTextField();
	private JTextField textFieldDocumento = new JTextField();
	private JTextField textFieldEdad = new JTextField();
	private JTextField textFieldMail = new JTextField();
	private JLabel etiquetaNombre = new JLabel("Nombre");
	private JLabel etiquetaDocumento = new JLabel("Documento");
	private JLabel etiquetaEdad = new JLabel("Edad");
	private JLabel etiquetaMail = new JLabel("Mail");
	private JButton botonGuardar = new JButton("Guardar");

	public JFrameMaestro(Ventana ventana, JFrameOpciones jFrameOpciones, Maestro maestro) throws SQLException {
		this.ventana = ventana;
		this.jFrameOpciones = jFrameOpciones;
		inicializarDatos(maestro);
		inicializarBotones(maestro);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		etiquetaNombre.setBounds(10, 35, 46, 14);
		contentPane.add(etiquetaNombre);

		textFieldNombre.setBounds(75, 29, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		etiquetaDocumento.setBounds(10, 60, 68, 14);
		contentPane.add(etiquetaDocumento);

		textFieldDocumento.setBounds(75, 57, 86, 20);
		contentPane.add(textFieldDocumento);
		textFieldDocumento.setColumns(10);

		etiquetaEdad.setBounds(10, 85, 46, 14);
		contentPane.add(etiquetaEdad);

		etiquetaMail.setBounds(10, 110, 46, 14);
		contentPane.add(etiquetaMail);

		textFieldEdad.setBounds(75, 82, 86, 20);
		contentPane.add(textFieldEdad);
		textFieldEdad.setColumns(10);

		textFieldMail.setBounds(75, 107, 86, 20);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);

		botonGuardar.setBounds(10, 157, 151, 121);
		contentPane.add(botonGuardar);
	}

	private void inicializarDatos(Maestro maestro) {
		if (maestro != null) {
			this.textFieldNombre.setText(maestro.getNombre());
			this.textFieldDocumento.setText(maestro.getDocumento());
			this.textFieldDocumento.setEnabled(false);
			this.textFieldEdad.setText(String.valueOf(maestro.getEdad()));
			this.textFieldMail.setText(maestro.getMail());
		}
	}

	private void inicializarBotones(Maestro maestro) {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String nombre = textFieldNombre.getText();
				if (nombre == null || nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"El nombre del maestro no puede estar vacío, campo obligatorio");
					error = true;
				}
				String documento = textFieldDocumento.getText();
				if (documento == null || documento.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"El documento del maestro no puede estar vacío, campo obligatorio");
					error = true;
				}
				Integer edad = Integer.parseInt(textFieldEdad.getText());
				String sEdad = String.valueOf(edad);
				if (edad == null || sEdad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La edad del maestro no puede estar vacío, campo obligatorio");
					error = true;
				}
				String mail = textFieldMail.getText();
				if (mail == null || mail.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El mail del maestro no puede estar vacío, campo obligatorio");
					error = true;
				}

				if (!error) {
					try {
						if (maestro != null) {
							ventana.modificarMaestro(nombre, documento, edad, mail);
						} else {
							ventana.crearMaestro(null, nombre, documento, edad, mail);
						}
						jFrameOpciones.reiniciarListaMaestros();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				setVisible(false); 
			}

		});
	}
}
