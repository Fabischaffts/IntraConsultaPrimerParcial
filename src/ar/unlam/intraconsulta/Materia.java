package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Materia {

	private Integer materiaId;
	private String nombre;
	private ArrayList<Materia> correlativas;
	private Integer idCorrelativa;

	public Materia(Integer materiaId, String nombre) {
		this.materiaId = materiaId;
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
		return materiaId;
	}

	public void setMateriaId(Integer materiaId) {
		this.materiaId = materiaId;
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
}