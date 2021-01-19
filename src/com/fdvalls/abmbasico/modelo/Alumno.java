package com.fdvalls.abmbasico.modelo;

public class Alumno {

	private Integer idAlumno;
	private String nombre;
	private String documento;
	private String genero;
	private int edad;
	private String mail;
	private String fecha;
	private Maestro maestro;

	public Alumno(Integer idAlumno, Maestro maestro, String nombre, String documento, String genero, int edad,
			String mail, String fecha) {
		this.idAlumno = idAlumno; 
		this.maestro = maestro;
		this.nombre = nombre;
		this.documento = documento;
		this.genero = genero;
		this.edad = edad;
		this.mail = mail;
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getGenero() {
		return genero;
	}

	public int getEdad() {
		return edad;
	}

	public String getMail() {
		return mail;
	}

	public String getFecha() {
		return fecha;
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}
	
	public Maestro getMaestro() {
		return maestro;
	}

	@Override
	public String toString() {
		return "Alumno [idAlumno=" + idAlumno + ", nombre=" + nombre + ", documento=" + documento + ", genero=" + genero
				+ ", edad=" + edad + ", mail=" + mail + ", fecha=" + fecha + ", maestro=" + maestro + "]";
	}

}
