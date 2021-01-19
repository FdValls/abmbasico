package com.fdvalls.abmbasico.interfazGrafica;

import javax.swing.JFrame;
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
	private ArrayList<Maestro> maestros;
	private Ventana ventana;

	public JFrameOpciones(Ventana ventana) throws SQLException {
		this.ventana = ventana;
		this.maestros = new ArrayList<>();
		this.reiniciarListaMaestros();
		this.alumnos = new ArrayList<>();
		this.reiniciarListaAlumno();
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
				JFrameAlumno ventanaCrearAlumno;
				try {
					ventanaCrearAlumno = new JFrameAlumno(ventana, JFrameOpciones.this, null);
					ventanaCrearAlumno.setVisible(true);
					reiniciarListaAlumno();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		botonCrearMaestro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JFrameMaestro ventanaCrearMaestro = null;
				try {
					ventanaCrearMaestro = new JFrameMaestro(ventana, JFrameOpciones.this, null);
					reiniciarListaMaestros();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ventanaCrearMaestro.setVisible(true);
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
			public void actionPerformed(ActionEvent actionEvent) {
				int indiceMaestroSeleccionado = listaMaestros.getSelectedIndex();
				if (indiceMaestroSeleccionado >= 0) {
					try {
						Maestro maestroSeleccionado = maestros.get(indiceMaestroSeleccionado);
						ventana.borrarMaestro(maestroSeleccionado.getIdMaestro());
						reiniciarListaMaestros();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});

		botonModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int indiceAlumnoSeleccionado = listaAlumnos.getSelectedIndex();
				Alumno alumnoSeleccionado = alumnos.get(indiceAlumnoSeleccionado);
				JFrameAlumno ventanaAlumno;
				try {
					ventanaAlumno = new JFrameAlumno(ventana, JFrameOpciones.this, alumnoSeleccionado);
					ventanaAlumno.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});

		botonModificarMaestro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int indiceMaestroSeleccionado = listaMaestros.getSelectedIndex();
				try {
					Maestro maestroSeleccionado = maestros.get(indiceMaestroSeleccionado);
					JFrameMaestro ventanaMaestro;
					ventanaMaestro = new JFrameMaestro(ventana, JFrameOpciones.this, maestroSeleccionado);
					ventanaMaestro.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void reiniciarListaAlumno() throws SQLException {
		listaAlumnos.removeAll();
		alumnos = ventana.obtenerTodosLosAlumnos();
		for (Alumno alumno : alumnos) {
			String alumnoAMostrar = "%s (Sensei: %s)";
			alumnoAMostrar = String.format(alumnoAMostrar, alumno.getNombre(), alumno.getMaestro().getNombre());
			listaAlumnos.add(alumnoAMostrar);
		}
	}

	public void reiniciarListaMaestros() throws SQLException {
		listaMaestros.removeAll();
		maestros = ventana.obtenerTodosLosMaestros();
		for (Maestro maestro : this.maestros) {
			String maestroAMostrar = "%s (Id: %d)";
			maestroAMostrar = String.format(maestroAMostrar, maestro.getNombre(), maestro.getIdMaestro());
			listaMaestros.add(maestroAMostrar);
		}
	}

}
