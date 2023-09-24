package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Alumno {

	private Integer dni;
	private String apellido;
	private String nombre;
	private ArrayList<Materia> materias;

	public Alumno(Integer dni, String apellido, String nombre) {
	this.dni=dni;
	this.apellido=apellido;
	this.nombre=nombre;
	}

	public ArrayList<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(ArrayList<Materia> materias) {
		this.materias = materias;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
	
}
