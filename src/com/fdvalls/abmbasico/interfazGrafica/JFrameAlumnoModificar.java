package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;

import com.fdvalls.abmbasico.modelo.Alumno;
import com.fdvalls.abmbasico.modelo.Maestro;

public class JFrameAlumnoModificar extends JFrameAlumno {

	private Alumno alumno;
	
	public JFrameAlumnoModificar(Ventana ventana, JFrameOpciones jFrameOpciones, Alumno alumno) throws SQLException {
		super(ventana, jFrameOpciones);
		this.alumno = alumno;
		this.inicializarDatos(alumno);
	}

	private void inicializarDatos(Alumno alumno) throws SQLException {
		
		if (alumno != null) {
			for (Maestro m : maestros) {
				choiseProfesores.add(String.valueOf(m.getNombre()));
			}
			this.textFieldNombre.setText(alumno.getNombre());
			this.textFieldDocumento.setText(alumno.getDocumento());
			this.textFieldDocumento.setEnabled(false);
			this.textFieldGenero.setText(alumno.getGenero());
			this.textFieldEdad.setText(String.valueOf(alumno.getEdad()));
			this.textFieldMail.setText(alumno.getMail());
			this.textFieldFechaDeIngreso.setText(alumno.getFecha());
		}
	}

	@Override
	protected void ejecutarAccionBotonGuardar(String dniMaestro, String nombre, String documento, String genero,
			int edad, String mail, String fecha) {
		try {
			this.ventana.modificarAlumno(documento, null, nombre, genero, edad, mail, fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
