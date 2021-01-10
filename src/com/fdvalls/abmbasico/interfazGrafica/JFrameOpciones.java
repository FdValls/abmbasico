package com.fdvalls.abmbasico.interfazGrafica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fdvalls.abmbasico.modelo.Alumno;
import com.fdvalls.abmbasico.modelo.Maestro;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JFrameOpciones extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private List listaMaestros = new List();
	private List listaAlumnos = new List();
	private JButton botonCrearAlumno = new JButton("Crear Alumno");
	private JButton botonCrearMaestro = new JButton("Crear Maestro");
	private JButton botonBorrarAlumno = new JButton("Borrar un alumno");
	private JButton botonBorrarMaestro = new JButton("Borrar Maestro");
	private JButton botonModificarAlumno = new JButton("Modificar Alumno");
	private JButton botonModificarMaestro = new JButton("Modificar Maestro");

	
	private ArrayList<Alumno> alumnos;
	private Ventana ventana;
	private ArrayList<Maestro> maestros;//cambio de pos

	public JFrameOpciones(Ventana ventana, ArrayList<Maestro> maestros) throws SQLException {
		this.ventana = ventana;
		this.maestros = maestros;
		this.inicializarListaMaestros();
		this.alumnos = new ArrayList<>();
		this.reiniciarListaAlumno(); // Copiar para maestros!
		this.inicializarBotones();

		/*
		 * CODIGO PARA IGNORAR (corresponde al dibujado del frame, no darle importancia)
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listaMaestros.setBounds(10, 10, 210, 400);
		contentPane.add(listaMaestros);

		listaAlumnos.setBounds(400, 10, 210, 400);
		contentPane.add(listaAlumnos);

		botonCrearAlumno.setBounds(226, 10, 168, 23);
		contentPane.add(botonCrearAlumno);

		botonCrearMaestro.setBounds(226, 44, 168, 23);
		contentPane.add(botonCrearMaestro);

		botonBorrarAlumno.setBounds(226, 78, 168, 23);
		contentPane.add(botonBorrarAlumno);

		botonBorrarMaestro.setBounds(226, 112, 168, 23);
		contentPane.add(botonBorrarMaestro);

		botonModificarAlumno.setBounds(226, 146, 168, 23);
		contentPane.add(botonModificarAlumno);

		botonModificarMaestro.setBounds(226, 180, 168, 23);
		contentPane.add(botonModificarMaestro);
		/* FIN CODIGO PARA IGNORAR */

	}

	private void inicializarBotones() {
		botonCrearAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JFrameAlumno ventanaCrearAlumno = new JFrameAlumno(ventana);
				ventanaCrearAlumno.setVisible(true);
//				String dniMaestro = JOptionPane.showInputDialog(null, "Ingrese DNI del maestro");
//				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre");
//				String documento = JOptionPane.showInputDialog(null, "Ingrese su numero de DNI");
//				String genero = JOptionPane.showInputDialog(null, "Ingrese genero");
//				String edadStr = JOptionPane.showInputDialog(null, "Ingrese su edad");
//				Integer edad = edadStr != null ? Integer.valueOf(edadStr) : null;
//				String mail = JOptionPane.showInputDialog(null, "Ingrese su mail");
//				String fecha = JOptionPane.showInputDialog(null, "Ingrese fecha de inicio");
//				try {
//					ventana.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
//					reiniciarListaAlumno();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}
		});

		botonCrearMaestro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre");
				String documento = JOptionPane.showInputDialog(null, "Ingrese su numero de DNI");
				int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su edad"));
				String mail = JOptionPane.showInputDialog(null, "Ingrese su mail");
				try {
					ventana.crearMaestro(null, nombre, documento, edad, mail);
					listaMaestros.clear();
					for (Maestro maestro : ventana.obtenerTodosLosMaestros()) {
						String maestroAMostrar = "%s (Id: %d)";
						maestroAMostrar = String.format(maestroAMostrar, maestro.getNombre(), maestro.getIdMaestro());
						listaMaestros.add(maestroAMostrar);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		botonBorrarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int indiceAlumnoSeleccionado = listaAlumnos.getSelectedIndex();
				if (indiceAlumnoSeleccionado >= 0) {
					try {
						Alumno alumnoSeleccionado = alumnos.get(indiceAlumnoSeleccionado);
						ventana.borrarAlumno(alumnoSeleccionado.getDocumento());
						reiniciarListaAlumno();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

		botonBorrarMaestro.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent actionEvent) {
				int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su Id"));
				try {
					ventana.borrarMaestro(id);
					listaMaestros.clear();
					for (Maestro maestro : ventana.obtenerTodosLosMaestros()) {
						String maestroAMostrar = "%s (Id: %d)";
						maestroAMostrar = String.format(maestroAMostrar, maestro.getNombre(), maestro.getIdMaestro());
						listaMaestros.add(maestroAMostrar);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		botonModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String dniAlumno = JOptionPane.showInputDialog(null, "Ingrese DNI del alumno");
				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre");
				String genero = JOptionPane.showInputDialog(null, "Ingrese genero");
				int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su edad"));
				String mail = JOptionPane.showInputDialog(null, "Ingrese su mail");
				String fecha = JOptionPane.showInputDialog(null, "Ingrese fecha de inicio");
				try {
					ventana.modificarAlumno(dniAlumno, nombre, genero, edad, mail, fecha);
					reiniciarListaAlumno();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		botonModificarMaestro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre");
				String dni = JOptionPane.showInputDialog(null, "Ingrese su numero de DNI");
				int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su edad"));
				String mail = JOptionPane.showInputDialog(null, "Ingrese su mail");
				try {
					ventana.modificarMaestro(nombre, dni, edad, mail);
					listaMaestros.removeAll();
					for (Maestro maestro : ventana.obtenerTodosLosMaestros()) {
						String maestroAMostrar = "%s (Id: %d)";
						maestroAMostrar = String.format(maestroAMostrar, maestro.getNombre(), maestro.getIdMaestro());
						listaMaestros.add(maestroAMostrar);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void inicializarListaMaestros() {
		for (Maestro maestro : this.maestros) {
			String maestroAMostrar = "%s (Id: %d)";
			maestroAMostrar = String.format(maestroAMostrar, maestro.getNombre(), maestro.getIdMaestro());
			listaMaestros.add(maestroAMostrar);
		}

	}

	private void reiniciarListaAlumno() throws SQLException {
		listaAlumnos.removeAll();
		alumnos = ventana.obtenerTodosLosAlumnos();
		for (Alumno alumno : alumnos) {
			String alumnoAMostrar = "%s (Sensei: %s)";
			alumnoAMostrar = String.format(alumnoAMostrar, alumno.getNombre(), alumno.getMaestro().getNombre());
			listaAlumnos.add(alumnoAMostrar);
		}
	}

}
