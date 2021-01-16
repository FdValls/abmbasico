package com.fdvalls.abmbasico.servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fdvalls.abmbasico.baseDeDatos.MaestroDAO;
import com.fdvalls.abmbasico.modelo.Maestro;

public class ServicioDeMaestros {
	
	MaestroDAO maestroDAO;
	Maestro maestro;

	public ArrayList<Maestro> obtenerTodosLosMaestros() throws SQLException {
		MaestroDAO maestroDAO = new MaestroDAO();
		ArrayList<Maestro> misMaestros = maestroDAO.obtenerTodosLosMaestros();
		return misMaestros;
	}

	public void crearMaestro(Integer idMaestro, String nombre, String documento, int edad, String mail) throws SQLException {
		MaestroDAO maestroDAO = new MaestroDAO();
		Maestro m = new Maestro(idMaestro, nombre, documento, edad, mail);
		maestroDAO.crearMaestro(m);

	}
	
	
	public boolean borrarMaestro(int id) throws SQLException {
		boolean sePudo = false;
		MaestroDAO maestroDAO = new MaestroDAO();
		Maestro m = maestroDAO.obtenerMaestroPorId(id);
		if (m != null) {
			maestroDAO.borrarMaestro(id);
			sePudo = true;
		}
		return sePudo;
	}
	
	public boolean modificarMaestro(String nombre, String dni, int edad, String mail) throws SQLException {
		boolean sePudo = false;
		MaestroDAO maestroDAO = new MaestroDAO();
		Maestro m = maestroDAO.obtenerMaestroPorDNI(dni);
		if (m != null) {
			maestroDAO.modificarMaestro(nombre, dni, edad, mail);
			sePudo = true;
		}
		return sePudo;
	}
	
	public Maestro obtenerMaestroPorDni(String dni) throws SQLException {
		MaestroDAO maestroDAO = new MaestroDAO();
		Maestro m = maestroDAO.obtenerMaestroPorDNI(dni);
		return m;
	}

}
