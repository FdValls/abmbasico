package com.fdvalls.abmbasico.baseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fdvalls.abmbasico.modelo.Maestro;

// Maestro DATA ACCESS OBJECT
public class MaestroDAO {

	public ArrayList<Maestro> obtenerTodosLosMaestros() throws SQLException {
		ArrayList<Maestro> maestros = new ArrayList<>();

		Statement statement = Conexion.getInstance().createStatement();
		String sql = "select * from maestro";
		ResultSet resultset = statement.executeQuery(sql);

		while (resultset.next()) {
			Maestro maestro = new Maestro(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
					resultset.getInt(4), resultset.getString(5));

			maestros.add(maestro);
		}

		return maestros;
	}

	public void crearMaestro(Maestro m) throws SQLException {
		Statement statement = Conexion.getInstance().createStatement();
		String sql = "insert into maestro values (default,'%s', '%s', '%d', '%s')";
		sql = String.format(sql, m.getNombre(), m.getDocumento(), m.getEdad(), m.getMail());
		statement.executeUpdate(sql);
	}

	public void borrarMaestro(int id) throws SQLException {
		Statement statement = Conexion.getInstance().createStatement();
		String sql = "delete from alumnos where id_maestro = "+id;
		String sql1 = "delete from maestro where idMaestro = "+id;
		statement.executeUpdate(sql);
		statement.executeUpdate(sql1);
	}

	public Maestro obtenerMaestroPorDNI(String dni) throws SQLException {
		Maestro m = null;
		Statement instruccion = Conexion.getInstance().createStatement();
		// Crear una consulta Query
		String sql = "select idMaestro, nombre, documento, edad, mail from maestro where documento ="+dni;
		ResultSet result = instruccion.executeQuery(sql);
		while (result.next()) {
			m = new Maestro(
					result.getInt(1),
					result.getString(2), 
					result.getString(3), 
					result.getInt(4),
					result.getString(5));
		}
		instruccion.close();
		return m;
	}
	
	public void modificarMaestro(String nombre, String documento, int edad, String mail) throws SQLException {
		Statement instruccion = Conexion.getInstance().createStatement();
		String sql1 = "update maestro set nombre = '" +nombre+"' where documento = "+documento;
		String sql2 = "update maestro set edad = '" +edad+"' where documento = "+documento;
		String sql3 = "update maestro set mail = '" +mail+"' where documento = "+documento;
		instruccion.executeUpdate(sql1);
		instruccion.executeUpdate(sql2);
		instruccion.executeUpdate(sql3);
		instruccion.close();
	}
	
	public Maestro obtenerMaestroPorId(int numeroId) throws SQLException {
		Maestro m = null;
		Statement instruccion = Conexion.getInstance().createStatement();
		// Crear una consulta Query
		String sql = "select * from maestro where idMaestro = "+numeroId;
		ResultSet result = instruccion.executeQuery(sql);
		while (result.next()) {
			m = new Maestro(
					result.getInt(1),
					result.getString(2), 
					result.getString(3), 
					result.getInt(4),
					result.getString(5));
		}
		instruccion.close();
		return m;
	}

}
