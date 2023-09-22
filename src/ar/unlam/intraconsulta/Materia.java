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

	/*
	 * public boolean agregarCorrelativa(Materia correlativa) { if
	 * (correlativas.contains(correlativa)) return false;
	 * 
	 * return correlativas.add(correlativa); } /* public boolean
	 * agregarCorrelatividad(Materia correlativa, Materia idCorrelativa) {
	 * 
	 * if ((correlativa.contains(codigo))&&(codigoList.contains(idCorrelativa))) {
	 * correlativas.add(codigo); correlativas.add(idCorrelativa); }else { return
	 * false;
	 * 
	 * } return true; } public boolean verificacionCorrelatividad(Integer materiaId,
	 * Integer idCorrelativa) {
	 * 
	 * for(int i = 0; i < correlativas.size();i++) { if())
	 * 
	 * } } }
	 */
	// agregar Correlativa y si existe id-correlativa y - materia
	public boolean agregarCorrelativa(Materia correlativa) {
		if (!correlativas.contains(correlativa)) {
			correlativas.add(correlativa);
			return true;
		}
		return false;
	}

	public boolean agregarCorrelatividad(Integer idCorrelativa) {
		if (!correlativas.contains(idCorrelativa)) {
			correlativas.add(new Materia(idCorrelativa, nombre)); // Sie müssen den Namen entsprechend setzen
			return true;
		}
		return false;
	}

	public boolean verificacionCorrelatividad(Integer idCorrelativa) {
		for (Materia correlativa : correlativas) {
			if (correlativa.getMateriaId().equals(idCorrelativa)) {
				return true;
			}
		}
		return false;
	}

	public boolean existeCorrelatividad(Integer idCorrelatividad, Integer materiaId) {
		for (Materia materia : correlativas) {
			if (correlativas.contains(idCorrelativa) && (correlativas.contains(materiaId))) {
				return true;
			}
		}
		return false;

	}

	public boolean eliminarCorrelatividad(Integer materiaId, Integer idCorrelativaAELiminar) {
			 if (existeCorrelatividad(materiaId, idCorrelativa)) {
			  if (correlativas.contains(idCorrelativaAELiminar)) {
			        correlativas.remove(idCorrelativaAELiminar);
			        return true; // Correlatividad eliminada exitosamente
			    } else {
			        return false; // La correlatividad no existe
			    }
			}
	    return false;
	    }
}