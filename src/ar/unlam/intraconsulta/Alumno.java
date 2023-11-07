package ar.unlam.intraconsulta;

import java.time.LocalDate;
import java.util.ArrayList;

public class Alumno {

	private Integer dni;
	private String apellido;
	private String nombre;
	private LocalDate fechaNacimiento;
	private LocalDate fechaDeIngreso;
	private ArrayList<Materia> materias;

	public Alumno(Integer dni, String apellido, String nombre) {
	this.dni=dni;
	this.apellido=apellido;
	this.nombre=nombre;
	this.materias = new ArrayList<>();
	}
public void nombre(String nombre) {
	return;
}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
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
