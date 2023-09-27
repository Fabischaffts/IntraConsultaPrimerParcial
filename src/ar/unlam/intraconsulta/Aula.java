package ar.unlam.intraconsulta;

public class Aula {

	private Integer aulaId;
	private Integer cantidadDeAlumnos;
	private Integer cantidadMaximaDealumnos;

	public Aula(Integer cantidadMaximaDealumnos) {
		this.cantidadMaximaDealumnos = cantidadMaximaDealumnos;
		this.aulaId = aulaId;
		this.cantidadDeAlumnos = cantidadDeAlumnos;
		//define  las aulas disponibles

	}

	public Integer getAulaId() {
		return aulaId;
	}

	public void setAulaId(Integer aulaId) {
		this.aulaId = aulaId;
	}

	public Integer getCantidadDeAulas() {
		return cantidadDeAlumnos;
	}

	public void setCantidadDeAulas(Integer cantidadDeAulas) {
		this.cantidadDeAlumnos = cantidadDeAulas;
	}

	public Integer getCantidadDeAlumnos() {
		return cantidadDeAlumnos;
	}

	public void setCantidadDeAlumnos(Integer cantidadDeAlumnos) {
		this.cantidadDeAlumnos = cantidadDeAlumnos;
	}

	public Integer getCantidadMaximaDealumnos() {
		return cantidadMaximaDealumnos;
	}

	public void setCantidadMaximaDealumnos(Integer cantidadMaximaDealumnos) {
		this.cantidadMaximaDealumnos = cantidadMaximaDealumnos;
	}

	public Aula alumnosPermitidos(Integer cantidadDeAlumnos) {
		if (cantidadMaximaDealumnos >= cantidadDeAlumnos)
			return this.alumnosPermitidos(cantidadDeAlumnos);

		return null;
	}

}
