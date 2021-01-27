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

public abstract class JFrameMaestro extends JFrame {

	protected static final long serialVersionUID = 1L;
	protected Ventana ventana;
	protected JFrameOpciones jFrameOpciones;
	protected JPanel contentPane;
	protected JTextField textFieldNombre = new JTextField();
	protected JTextField textFieldDocumento = new JTextField();
	protected JTextField textFieldEdad = new JTextField();
	protected JTextField textFieldMail = new JTextField();
	protected JLabel etiquetaNombre = new JLabel("Nombre");
	protected JLabel etiquetaDocumento = new JLabel("Documento");
	protected JLabel etiquetaEdad = new JLabel("Edad");
	protected JLabel etiquetaMail = new JLabel("Mail");
	protected JButton botonGuardar = new JButton("Guardar");
	private Maestro maestro;

	public JFrameMaestro(Ventana ventana, JFrameOpciones jFrameOpciones) throws SQLException {
		this.ventana = ventana;
		this.jFrameOpciones = jFrameOpciones;
		inicializarDatos(maestro);
		inicializarBotones();
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

	private void inicializarBotones() {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String nombre = textFieldNombre.getText();
				if (nombre == null || nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"El nombre del maestro no puede estar vac�o, campo obligatorio");
					error = true;
				}
				String documento = textFieldDocumento.getText();
				if (documento == null || documento.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"El documento del maestro no puede estar vac�o, campo obligatorio");
					error = true;
				}
				Integer edad = Integer.parseInt(textFieldEdad.getText());
				String sEdad = String.valueOf(edad);
				if (edad == null || sEdad.isEmpty()) {
					JOptionPane.showMessageDialog(null, "La edad del maestro no puede estar vac�o, campo obligatorio");
					error = true;
				}
				String mail = textFieldMail.getText();
				if (mail == null || mail.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El mail del maestro no puede estar vac�o, campo obligatorio");
					error = true;
				}

				if (!error) {
					try {
						ejecutarAccionBotonGuardar(nombre, documento, edad, mail);
						jFrameOpciones.reiniciarListaMaestros();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
//					try {
//						if (maestro != null) {
//							ventana.modificarMaestro(nombre, documento, edad, mail);
//						} else {
//							ventana.crearMaestro(null, nombre, documento, edad, mail);
//						}
//						jFrameOpciones.reiniciarListaMaestros();
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
				}
				setVisible(false);
			}

		});
	}

	protected abstract void ejecutarAccionBotonGuardar(String nombre, String dni, int edad, String mail);
}
