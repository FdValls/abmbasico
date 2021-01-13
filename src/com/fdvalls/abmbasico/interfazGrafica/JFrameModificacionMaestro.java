package com.fdvalls.abmbasico.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fdvalls.abmbasico.modelo.Maestro;

import java.awt.List;
import java.sql.SQLException;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameModificacionMaestro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrameOpciones jFrameOpciones;
	private List listaMaestros = new List();
	private Ventana ventana;
	private Maestro maestro;
	private TextField textFieldNombre = new TextField();
	private TextField textFieldDocumento = new TextField();
	private TextField textFieldEdad = new TextField();
	private TextField textFieldMail = new TextField();
	private Label etiquetaNombre = new Label("Cambiar Nombre");
	private Label etiquetaDocumento = new Label("Cambiar Documento");
	private Label etiquetaEdad = new Label("Cambiar Edad");
	private Label etiquetaMail = new Label("Cambiar Mail");

	private Button botonGuardar = new Button("GUARDAR");

	public JFrameModificacionMaestro(Ventana ventana, JFrameOpciones jFrameOpciones, Maestro maestro)
			throws SQLException {
		this.ventana = ventana;
		this.maestro = maestro;
		this.jFrameOpciones = jFrameOpciones;
		inicializarBotones();
		this.mostrarListaMaestros();
		jFrameOpciones.reiniciarListaMaestros();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listaMaestros.setBounds(10, 10, 681, 113);
		contentPane.add(listaMaestros);

		etiquetaNombre.setBounds(10, 148, 117, 22);
		contentPane.add(etiquetaNombre);

		textFieldNombre.setBounds(133, 148, 80, 22);
		contentPane.add(textFieldNombre);
		botonGuardar.setBounds(270, 329, 105, 77);

		contentPane.add(botonGuardar);
		etiquetaDocumento.setBounds(10, 176, 117, 22);

		contentPane.add(etiquetaDocumento);
		etiquetaEdad.setBounds(10, 204, 117, 22);

		contentPane.add(etiquetaEdad);
		etiquetaMail.setBounds(10, 232, 117, 22);

		contentPane.add(etiquetaMail);
		textFieldDocumento.setBounds(133, 176, 80, 22);

		contentPane.add(textFieldDocumento);
		textFieldEdad.setBounds(133, 204, 80, 22);

		contentPane.add(textFieldEdad);
		textFieldMail.setBounds(133, 232, 80, 22);

		contentPane.add(textFieldMail);
	}

	private void inicializarBotones() {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Maestro maestroSeleccionado = maestro;
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
				if (sEdad.isEmpty() || edad == null) {
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
						ventana.borrarMaestro(maestroSeleccionado.getIdMaestro());
						ventana.crearMaestro(null, nombre, documento, edad, mail);
						jFrameOpciones.reiniciarListaMaestros();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				setVisible(false);

			}

		});
	}

	private void mostrarListaMaestros() throws SQLException {
		for (Maestro maestro : this.ventana.obtenerTodosLosMaestros()) {
			String maestroAMostrar = "Nombre: %s, Documento: %s, Edad: %d, Mail: %s";
			maestroAMostrar = String.format(maestroAMostrar, maestro.getNombre(), maestro.getDocumento(),
					maestro.getEdad(), maestro.getMail());
			listaMaestros.add(maestroAMostrar);
		}
	}
}
