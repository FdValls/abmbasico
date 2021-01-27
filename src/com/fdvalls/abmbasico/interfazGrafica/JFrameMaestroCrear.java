package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;

public class JFrameMaestroCrear extends JFrameMaestro {

	private static final long serialVersionUID = 1L;

	public JFrameMaestroCrear(Ventana ventana, JFrameOpciones jFrameOpciones) throws SQLException {
		super(ventana, jFrameOpciones);
	}

	protected void ejecutarAccionBotonGuardar(String nombre, String dni, int edad, String mail) {
		try {
			ventana.crearMaestro(null, nombre, dni, edad, mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
