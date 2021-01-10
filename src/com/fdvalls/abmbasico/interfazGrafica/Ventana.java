package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fdvalls.abmbasico.modelo.Alumno;
import com.fdvalls.abmbasico.modelo.Maestro;
import com.fdvalls.abmbasico.servicios.ServicioDeAlumnos;
import com.fdvalls.abmbasico.servicios.ServicioDeMaestros;

public class Ventana extends InterfazGrafica {

	private final ServicioDeMaestros servicioDeMaestros = new ServicioDeMaestros();
	private final ServicioDeAlumnos servicioDeAlumnos = new ServicioDeAlumnos();

	public void dibujarInterfaz() throws SQLException {
		ArrayList<Maestro> maestros = this.servicioDeMaestros.obtenerTodosLosMaestros();
		JFrameOpciones ventanaOpciones = new JFrameOpciones(this, maestros);
		ventanaOpciones.setVisible(true);
	}

	public void crearAlumno(String dniMaestro, String nombre, String documento, String genero, int edad, String mail,
			String fecha) throws SQLException {
		servicioDeAlumnos.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
	}

	public void crearMaestro(Integer idMaestro, String nombre, String documento, int edad, String mail)
			throws SQLException {
		servicioDeMaestros.crearMaestro(idMaestro, nombre, documento, edad, mail);
	}

	public void borrarAlumno(String dni) throws SQLException {
		servicioDeAlumnos.borrarAlumno(dni);
	}

	public void borrarMaestro(int id) throws SQLException {
		servicioDeMaestros.borrarMaestro(id);
	}

	public void modificarAlumno(String dniAlumno, String nombre, String genero, int edad, String mail, String fecha)
			throws SQLException {
		servicioDeAlumnos.modificarAlumno(dniAlumno, nombre, genero, edad, mail, fecha);
	}

	public void modificarMaestro(String nombre, String dni, int edad, String mail) throws SQLException {
		servicioDeMaestros.modificarMaestro(nombre, dni, edad, mail);
	}

	public ArrayList<Alumno> obtenerTodosLosAlumnos() throws SQLException {
		return servicioDeAlumnos.obtenerTodosLosAlumnos();
	}

	public ArrayList<Maestro> obtenerTodosLosMaestros() throws SQLException {
		return servicioDeMaestros.obtenerTodosLosMaestros();
	}
}