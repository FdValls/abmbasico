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
		JFrameOpciones ventanaOpciones = new JFrameOpciones(this);
		ventanaOpciones.setVisible(true);
	}

	public void crearAlumno(String dniMaestro, String nombre, String documento, String genero, int edad, String mail,
			String fecha) throws SQLException {
		servicioDeAlumnos.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
	}

	public void crearMaestro(Integer idMaestro, String nombre, String documento, int edad, String mail)
			throws SQLException {
		servicioDeMaestros.crearMaestro(null, nombre, documento, edad, mail);
	}

	public void borrarAlumno(String dni) throws SQLException {
		servicioDeAlumnos.borrarAlumno(dni);
	}

	public void borrarMaestro(int id) throws SQLException {
		servicioDeMaestros.borrarMaestro(id);
	}
	//alumno.getDocumento(), nombre, genero, edad, mail, fechaIngreso
	public void modificarAlumno(String dniAlumno, Integer idMaestro, String nombre, String genero, int edad, String mail, String fecha)
			throws SQLException {
		servicioDeAlumnos.modificarAlumno(dniAlumno, idMaestro, nombre, genero, edad, mail, fecha);
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
	
	public Maestro obtenerMaestroPorDni(String dni) throws SQLException {
		Maestro m = servicioDeMaestros.obtenerMaestroPorDni(dni);
		return m;
	}
}