package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Materia {

	private Integer idMateria;
	private String nombre;
	private ArrayList<Materia> correlativas;
	private Integer idCorrelativa;
	private ArrayList<Nota>nota;
	private ArrayList<Alumno> alumnos;//no tendria que estar en materia

	public Materia(Integer materiaId, String nombre) {
		this.idMateria = materiaId;
		this.nombre = nombre;
		correlativas = new ArrayList<>();
		this.idCorrelativa = idCorrelativa;
	}

	public Integer getIdCorrelativa() {
		return idCorrelativa;
	}

	public void setIdCorrelativa(Integer idCorrelativa) {
		this.idCorrelativa = idCorrelativa;
	}

	public Integer getMateriaId() {
		return idMateria;
	}

	public void setMateriaId(Integer materiaId) {
		this.idMateria = materiaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Materia> getCorrelativas() {
		return correlativas;
	}

	public void setCorrelativas(ArrayList<Materia> correlativas) {
		this.correlativas = correlativas;
	}

	// agregar Correlativa y si existe id-correlativa y - materia
	public boolean agregarCorrelativa(Materia correlativa) {
		if (!correlativas.contains(correlativa)) {
			correlativas.add(correlativa);
			return true;
		}
		return false;
}
	 public boolean MateriaAprobada() {
	        for (Nota nota : nota) {
	            if (nota.getValor() < 4) {
	                return false;	
}
	        }
	            	return true;
	 }
	
}