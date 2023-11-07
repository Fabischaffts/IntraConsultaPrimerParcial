package ar.unlam.intraconsulta;

import java.time.LocalDate;
import java.util.ArrayList;

public class Profesor {
	private Integer dni;
	private String apellido;
	private String nombre;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	private ArrayList<Profesor>ListaProfesores;
	private Comision comision;

	public Profesor(Integer dni, String apellido, String nombre) {
	this.dni=dni;
	this.apellido=apellido;
	this.nombre=nombre;
	}

	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
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
