package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;

import com.fdvalls.abmbasico.modelo.Alumno;

public class JFrameAlumnoCrear extends JFrameAlumno {

	public JFrameAlumnoCrear(Ventana ventana, JFrameOpciones jFrameOpciones) throws SQLException {
		super(ventana, jFrameOpciones);
	}

	@Override
	protected void ejecutarAccionBotonGuardar(String dniMaestro, String nombre, String documento, String genero,
			int edad, String mail, String fecha) {
		try {
			ventana.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
		} catch (SQLException e) {
			//TODO Hacer algo
		}
	}

}
