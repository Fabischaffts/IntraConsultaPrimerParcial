package ar.unlam.intraconsulta;

public class Alumno {

	private Integer dni;
	private String apellido;
	private String nombre;

	public Alumno(Integer dni, String apellido, String nombre) {
	this.dni=dni;
	this.apellido=apellido;
	this.nombre=nombre;
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
