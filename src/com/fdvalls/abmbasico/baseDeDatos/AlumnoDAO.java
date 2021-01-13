package com.fdvalls.abmbasico.baseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fdvalls.abmbasico.modelo.Alumno;
import com.fdvalls.abmbasico.modelo.Maestro;

// Alumno DATA ACCESS OBJECT (objeto que se conecta a la base de datos)
public class AlumnoDAO {

	public ArrayList<Alumno> obtenerTodosLosAlumnos() throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<>();
		Statement instruccion = Conexion.getInstance().createStatement();
		// Crear una consulta Query
		String sql = "select a.idAlumno, a.id_Maestro, a.nombre, a.documento, a.genero, a.edad, a.mail, a.fechaIngreso,"
				+ " m.idMaestro, m.nombre, m.documento, m.edad, m.mail" + " from alumnos as a"
				+ " inner join maestro as m on a.id_Maestro = m.idMaestro";
		ResultSet result = instruccion.executeQuery(sql);
		while (result.next()) {
			Maestro maestro = new Maestro(result.getInt(9), result.getString(10), result.getString(11),
					result.getInt(12), result.getString(13));

			Alumno alumno = new Alumno(result.getInt(1), maestro, result.getString(3), result.getString(4),
					result.getString(5), result.getInt(6), result.getString(7), result.getString(8));
			alumnos.add(alumno);
		}
		instruccion.close();
		return alumnos;
	}

	public void crearAlumno(Alumno a) throws SQLException {
		Statement statement = Conexion.getInstance().createStatement();
		String sql = "insert into alumnos values (default, '%d', '%s', '%s', '%s', %d,'%s','%s')";
		sql = String.format(sql, a.getMaestro().getIdMaestro(), a.getNombre(), a.getDocumento(), a.getGenero(),
				a.getEdad(), a.getMail(), a.getFecha());
		statement.executeUpdate(sql);
		statement.close();
	}

	public void borrarAlumno(Alumno a) throws SQLException {
		Statement statement = Conexion.getInstance().createStatement();
		String sql = "delete from alumnos where documento = " + a.getDocumento();
		statement.executeUpdate(sql);
		statement.close();
	}

	public Alumno obtenerAlumnoPorDNI(String dni) throws SQLException {
		Alumno a = null;
		Statement instruccion = Conexion.getInstance().createStatement();
		// Crear una consulta Query
		String sql = "select a.idAlumno, a.id_Maestro, a.nombre, a.documento, a.genero, a.edad, a.mail, a.fechaIngreso,"
				+ " m.idMaestro, m.nombre, m.documento, m.edad, m.mail" + " from alumnos as a"
				+ " inner join maestro as m on a.id_Maestro = m.idMaestro " + " where a.documento = " + dni;
		ResultSet result = instruccion.executeQuery(sql);
		while (result.next()) {
			Maestro maestro = new Maestro(result.getInt(9), result.getString(10), result.getString(11),
					result.getInt(12), result.getString(13));
			a = new Alumno(result.getInt(1), maestro, result.getString(3), result.getString(4), result.getString(5),
					result.getInt(6), result.getString(7), result.getString(8));
		}
		instruccion.close();
		return a;
	}

	public void modificarAlumno(String nombre, String dniAlumno, String genero, int edad, String mail, String fecha)
			throws SQLException {
		Statement instruccion = Conexion.getInstance().createStatement();
		String sql1 = "update alumnos set nombre = '" + nombre + "' where documento = " + dniAlumno;
		String sql2 = "update alumnos set genero = '" + genero + "' where documento = " + dniAlumno;
		String sql3 = "update alumnos set edad = '" + edad + "' where documento = " + dniAlumno;
		String sql4 = "update alumnos set mail = '" + mail + "' where documento = " + dniAlumno;
		String sql6 = "update alumnos set fechaIngreso = '" + fecha + "' where documento = " + dniAlumno;
		instruccion.executeUpdate(sql1);
		instruccion.executeUpdate(sql2);
		instruccion.executeUpdate(sql3);
		instruccion.executeUpdate(sql4);
		instruccion.executeUpdate(sql6);
		instruccion.close();
	}

	public void mostrarDatosAlumno() throws SQLException {
		Statement instruccion = Conexion.getInstance().createStatement();
		String sql = "select nombre, documento, genero, edad, mail, fechaIngreso from alumnos";
		ResultSet result = instruccion.executeQuery(sql);
		while (result.next()) {
			System.out.println("Nombre: " + result.getString(1));
			System.out.println("Documento: " + result.getString(2));
			System.out.println("Genero: " + result.getString(3));
			System.out.println("Edad: " + result.getString(4));
			System.out.println("Mail: " + result.getString(5));
			System.out.println("Fecha de Ingreso: " + result.getString(6) + "\n");
		}
		instruccion.close();
	}

}
