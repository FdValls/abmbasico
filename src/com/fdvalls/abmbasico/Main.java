package com.fdvalls.abmbasico;

import java.sql.SQLException;

import com.fdvalls.abmbasico.interfazGrafica.Consola;
import com.fdvalls.abmbasico.interfazGrafica.InterfazGrafica;
import com.fdvalls.abmbasico.interfazGrafica.Ventana;

public class Main {

	private final InterfazGrafica interfazGrafica = new Ventana();

	public static void main(String[] args) throws SQLException {
		Main app = new Main();
		app.iniciar();
	}

	public void iniciar() throws SQLException {
		interfazGrafica.dibujarInterfaz();
	}
}
