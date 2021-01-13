package com.fdvalls.abmbasico.servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fdvalls.abmbasico.baseDeDatos.AlumnoDAO;
import com.fdvalls.abmbasico.baseDeDatos.MaestroDAO;
import com.fdvalls.abmbasico.modelo.Alumno;
import com.fdvalls.abmbasico.modelo.Maestro;

public class ServicioDeAlumnos {

	private final AlumnoDAO alumnoDAO = new AlumnoDAO();
	private final MaestroDAO maestroDAO = new MaestroDAO();

	// si alumnoDAO ya tiene los alumnos, aca me arranca en null, segun DEBUGGER
	public ArrayList<Alumno> obtenerTodosLosAlumnos() throws SQLException {
		AlumnoDAO alumnoDAO = new AlumnoDAO();
		ArrayList<Alumno> misAlumnos = alumnoDAO.obtenerTodosLosAlumnos();
		return misAlumnos;
	}

	public void crearAlumno(String dniMaestro, String nombre, String documento, String genero, int edad, String mail,
			String fecha) throws SQLException {
		Maestro maestro = maestroDAO.obtenerMaestroPorDNI(dniMaestro);
		Alumno a = new Alumno(null, maestro, nombre, documento, genero, edad, mail, fecha);
		alumnoDAO.crearAlumno(a);

	}

	public boolean borrarAlumno(String dni) throws SQLException {
		boolean sePudo = false;
		AlumnoDAO alumnoDAO = new AlumnoDAO();
		Alumno a = alumnoDAO.obtenerAlumnoPorDNI(dni);
		if (a != null) {
			alumnoDAO.borrarAlumno(a);
			sePudo = true;
		}
		return sePudo;
	}

	public boolean modificarAlumno(String dniAlumno, String nombre, String genero, int edad, String mail, String fecha)
			throws SQLException {
		boolean sePudo = false;
		AlumnoDAO alumnoDAO = new AlumnoDAO();
		Alumno a = alumnoDAO.obtenerAlumnoPorDNI(dniAlumno);
		if (a != null) {
			alumnoDAO.modificarAlumno(nombre, dniAlumno, genero, edad, mail, fecha);
			sePudo = true;
		}
		return sePudo;
	}
}
