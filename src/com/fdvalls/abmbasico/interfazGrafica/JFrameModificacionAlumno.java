package com.fdvalls.abmbasico.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.fdvalls.abmbasico.modelo.Alumno;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;

public class JFrameModificacionAlumno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrameOpciones jFrameOpciones;
	private List listaAlumnos = new List();
	private Ventana ventana;
	private Alumno alumno;
	private Label etiquetaNombre = new Label("Nombre");
	private Label etiquetaDocumento = new Label("Documento");
	private Label etiquetaGenero = new Label("Genero");
	private Label etiquetaEdad = new Label("Edad");
	private Label etiquetaMail = new Label("Mail");
	private Label etiquetaFechaDeIngreso = new Label("Fecha de ingreso");
	private Button botonGuardar = new Button("GUARDAR");
	private TextField textFieldNombre = new TextField();
	private TextField textFieldDocumento = new TextField();
	private TextField textFieldGenero = new TextField();
	private TextField textFieldEdad = new TextField();
	private TextField textFieldMail = new TextField();
	private TextField textFieldFechaDeIngreso = new TextField();

	public JFrameModificacionAlumno(Ventana ventana, JFrameOpciones jFrameOpciones, Alumno alumno) throws SQLException {
		this.ventana = ventana;
		this.alumno = alumno;
		this.jFrameOpciones = jFrameOpciones;
		inicializarBotones();
		this.mostrarListaAlumnos();
		jFrameOpciones.reiniciarListaAlumno();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listaAlumnos.setBounds(10, 10, 691, 181);
		contentPane.add(listaAlumnos);

		botonGuardar.setBounds(511, 266, 123, 87);
		contentPane.add(botonGuardar);

		etiquetaNombre.setBounds(10, 234, 62, 22);
		contentPane.add(etiquetaNombre);

		etiquetaDocumento.setBounds(10, 262, 62, 22);
		contentPane.add(etiquetaDocumento);

		etiquetaGenero.setBounds(10, 289, 62, 22);
		contentPane.add(etiquetaGenero);

		etiquetaEdad.setBounds(10, 317, 62, 22);
		contentPane.add(etiquetaEdad);

		etiquetaMail.setBounds(10, 345, 62, 22);
		contentPane.add(etiquetaMail);

		etiquetaFechaDeIngreso.setBounds(10, 373, 105, 22);
		contentPane.add(etiquetaFechaDeIngreso);

		textFieldNombre.setBounds(136, 234, 89, 22);
		contentPane.add(textFieldNombre);

		textFieldDocumento.setBounds(136, 262, 89, 22);
		contentPane.add(textFieldDocumento);

		textFieldGenero.setBounds(135, 287, 89, 22);
		contentPane.add(textFieldGenero);

		textFieldEdad.setBounds(135, 313, 89, 22);
		contentPane.add(textFieldEdad);

		textFieldMail.setBounds(134, 340, 89, 22);
		contentPane.add(textFieldMail);

		textFieldFechaDeIngreso.setBounds(134, 370, 89, 22);
		contentPane.add(textFieldFechaDeIngreso);

	}

	private void inicializarBotones() {
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alumno alumnoSeleccionado = alumno;
				String dniMaestro = alumno.getMaestro().getDocumento();
				boolean error = false;
				String nombre = textFieldNombre.getText();
				if (nombre == null || nombre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El nombre del alumno no puede estar vacío, campo obligatorio");
					error = true;
				}
				String documento = textFieldDocumento.getText();
				if (documento == null || documento.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"El documento del alumno no puede estar vacío, campo obligatorio");
					error = true;
				}
				String genero = textFieldGenero.getText();
				if (genero == null || genero.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El genero del alumno no puede estar vacío, campo obligatorio");
					error = true;
				}
				Integer edad = Integer.parseInt(textFieldEdad.getText());
				String sEdad = String.valueOf(edad);
				if (sEdad.isEmpty() || edad == null) {
					JOptionPane.showMessageDialog(null, "La edad del alumno no puede estar vacío, campo obligatorio");
					error = true;
				}
				String mail = textFieldMail.getText();
				if (mail == null || mail.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El mail del alumno no puede estar vacío, campo obligatorio");
					error = true;
				}
				String fecha = String.valueOf(textFieldFechaDeIngreso.getText());
				if (fecha == null || fecha.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"La fecha de ingreso del alumno no puede estar vacío, campo obligatorio");
					error = true;
				}
				if (!error) {
					try {
						ventana.borrarAlumno(alumnoSeleccionado.getDocumento());
						ventana.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
						jFrameOpciones.reiniciarListaAlumno();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				setVisible(false);

			}

		});
	}

	private void mostrarListaAlumnos() throws SQLException {
		for (Alumno alumno : ventana.obtenerTodosLosAlumnos()) {
			// nombre, documento, genero, edad, mail, fechaIngreso
			String alumnoAMostrar = "Nombre: %s, Documento: %s, Genero: %s, Edad: %d, Mail: %s, FechaDeIngreso: %s, (Sensei: %s)";
			alumnoAMostrar = String.format(alumnoAMostrar, alumno.getNombre(), alumno.getDocumento(),
					alumno.getGenero(), alumno.getEdad(), alumno.getMail(), alumno.getFecha(),
					alumno.getMaestro().getNombre());
			listaAlumnos.add(alumnoAMostrar.toString());
		}
	}

}
