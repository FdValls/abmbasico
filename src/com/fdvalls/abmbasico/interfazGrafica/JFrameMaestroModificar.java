package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;

import com.fdvalls.abmbasico.modelo.Maestro;

public class JFrameMaestroModificar extends JFrameMaestro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JFrameMaestroModificar(Ventana ventana, JFrameOpciones jFrameOpciones, Maestro maestro) throws SQLException {
		super(ventana, jFrameOpciones);
		this.inicializarDatos(maestro);
	}

	private void inicializarDatos(Maestro maestro) throws SQLException {

		if (maestro != null) {
			this.textFieldNombre.setText(maestro.getNombre());
			this.textFieldDocumento.setText(maestro.getDocumento());
			this.textFieldDocumento.setEnabled(false);
			this.textFieldEdad.setText(String.valueOf(maestro.getEdad()));
			this.textFieldMail.setText(maestro.getMail());
		}
	}

	protected void ejecutarAccionBotonGuardar(String nombre, String dni, int edad, String mail) {
		try {
			this.ventana.modificarMaestro(nombre, dni, edad, mail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
