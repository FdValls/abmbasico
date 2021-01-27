package com.fdvalls.abmbasico.interfazGrafica;

import java.sql.SQLException;
import java.util.Scanner;

import com.fdvalls.abmbasico.modelo.Alumno;
import com.fdvalls.abmbasico.modelo.Maestro;
import com.fdvalls.abmbasico.servicios.ServicioDeAlumnos;
import com.fdvalls.abmbasico.servicios.ServicioDeMaestros;

public class Consola extends InterfazGrafica {

	private final static Scanner sc = new Scanner(System.in);
	private final static ServicioDeAlumnos SERVICIO_DE_ALUMNOS = new ServicioDeAlumnos();
	private final static ServicioDeMaestros SERVICIO_DE_MAESTROS = new ServicioDeMaestros();
	
	public void dibujarInterfaz() throws SQLException {
		int opcion = -1;
		while (opcion != 100) {
			System.out.println("1. Mostrar todos los alumnos");
			System.out.println("2. Crear un alumno");
			System.out.println("3. Modificar un alumno");
			System.out.println("4. Borrar un alumno");
			System.out.println("5. Mostrar todos los maestros");
			System.out.println("6. Crear un maestro");
			System.out.println("7. Modificar un maestro");
			System.out.println("8. Borrar un maestro");
			System.out.println("100. Salir");

			opcion = Integer.parseInt(sc.nextLine());
			switch (opcion) {
			case 1:
				mostrarAlumnos();
				break;
			case 2:
				crearAlumno();
				break;
			case 3:
				modificarAlumno();
				break;
			case 4:
				borrarAlumno();
				break;
			case 5:
				mostrarMaestros();
				break;
			case 6:
				crearMaestro();
				break;
			case 7:
				modificarMaestro();
				break;
			case 8:
				borrarMaestro();
				break;
			case 100:
				System.out.println("Terminar");
				break;
			}
		}
	}

	private void mostrarAlumnos() throws SQLException {
		for (Alumno alumno : SERVICIO_DE_ALUMNOS.obtenerTodosLosAlumnos()) {
			System.out.println(alumno);
		}

	}

	private void crearAlumno() throws SQLException {
		System.out.println("Ingresar un dni del maestro");
		String dniMaestro = sc.nextLine();
		System.out.println("Ingresar un nombre");
		String nombre = sc.nextLine();
		System.out.println("Ingresar un documento");
		String documento = sc.nextLine();
		System.out.println("Ingresar un genero");
		String genero = sc.nextLine();
		System.out.println("Ingresar una edad");
		int edad = Integer.valueOf(sc.nextLine());
		System.out.println("Ingresar una mail");
		String mail = sc.nextLine();
		System.out.println("Ingresar una fecha nacimiento");
		String fecha = sc.nextLine();
		SERVICIO_DE_ALUMNOS.crearAlumno(dniMaestro, nombre, documento, genero, edad, mail, fecha);
	}

	private void modificarAlumno() throws SQLException {
		System.out.println("Ingresar un dni ");
		String dniAlumno = sc.nextLine();
		System.out.println("Ingresar un nombre");
		String nombre = sc.nextLine();
		System.out.println("Ingresar un genero");
		String genero = sc.nextLine();
		System.out.println("Ingresar una edad");
		int edad = Integer.valueOf(sc.nextLine());
		System.out.println("Ingresar una mail");
		String mail = sc.nextLine();
		System.out.println("Ingresar una fecha de inicio");
		String fecha = sc.nextLine();
		SERVICIO_DE_ALUMNOS.modificarAlumno(dniAlumno, null,nombre, genero, edad,
				mail, fecha);

	}

	private void borrarAlumno() throws SQLException {
		System.out.println("Ingresar el dni del alumno a borrar: ");
		String dni = sc.nextLine();
		SERVICIO_DE_ALUMNOS.borrarAlumno(dni);
	}

	private void mostrarMaestros() throws SQLException {
		for (Maestro m : SERVICIO_DE_MAESTROS.obtenerTodosLosMaestros()) {
			System.out.println(m);
		}
	}

	private void crearMaestro() throws SQLException {
		System.out.println("Ingresar un nombre");
		String nombre = sc.nextLine();
		System.out.println("Ingresar un documento");
		String documento = sc.nextLine();
		System.out.println("Ingresar una edad");
		int edad = Integer.valueOf(sc.nextLine());
		System.out.println("Ingresar una mail");
		String mail = sc.nextLine();
		SERVICIO_DE_MAESTROS.crearMaestro(null, nombre, documento, edad, mail);
	}

	private void modificarMaestro() throws SQLException {
		System.out.println("Ingresar un documento");
		String dni = sc.nextLine();
		System.out.println("Ingresar un nombre");
		String nombre = sc.nextLine();
		System.out.println("Ingresar una edad");
		int edad = Integer.valueOf(sc.nextLine());
		System.out.println("Ingresar una mail");
		String mail = sc.nextLine();
		SERVICIO_DE_MAESTROS.modificarMaestro(nombre, dni, edad, mail);
	}

	private void borrarMaestro() throws SQLException {
		System.out.println("Ingrese el ID del maestro para borrarlo.");
		int id = Integer.valueOf(sc.nextLine());
		SERVICIO_DE_MAESTROS.borrarMaestro(id);
	}

}
