package com.fdvalls.abmbasico.modelo;

public class Maestro {

	private Integer idMaestro;
	private String nombre;
	private String documento;
	private int edad;
	private String mail;

	public Maestro(Integer idMaestro, String nombre, String documento, int edad, String mail) {
		this.idMaestro = idMaestro;
		this.nombre = nombre;
		this.documento = documento;
		this.edad = edad;
		this.mail = mail;

	}

	@Override
	public String toString() {
		return "Maestro [idMaestro=" + idMaestro + ", nombre=" + nombre + ", documento=" + documento + ", edad=" + edad
				+ ", mail=" + mail + "]";
	}

	public Integer getIdMaestro() {
		return idMaestro;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public int getEdad() {
		return edad;
	}

	public String getMail() {
		return mail;
	}

}
