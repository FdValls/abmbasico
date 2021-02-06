package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;


public class JFrameAlumnoCrear extends JFrameAlumno {

	private static final long serialVersionUID = 1L;

	public JFrameAlumnoCrear(Ventana ventana, JFrameOpciones jFrameOpciones) throws SQLException {
		super(ventana, jFrameOpciones);
	}

	protected void ejecutarAccionBotonGuardar(String dniAlumno, Integer idMaestro, String nombre, String genero,
		int edad, String mail, String fecha) {
		try {
			this.ventana.crearAlumno(dniAlumno, idMaestro, nombre, genero, edad, mail, fecha);
			jFrameOpciones.reiniciarListaAlumno();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
