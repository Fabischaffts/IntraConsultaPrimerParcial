package ar.unlam.intraconsulta;

import java.time.LocalDate;
import java.util.ArrayList;

public class CicloLectivo {
	private Integer cicloLectivoId;
	private String nombre;
	private LocalDate fechaInicioCicloLectivo;
	private LocalDate fechaFinalizacionCiclolectivo;
	private LocalDate fechaInicioInscripcion;
	private LocalDate fechafinalizacion;
	private ArrayList<LocalDate> fechaList;

	public CicloLectivo(Integer cicloLectivoId, String nombre, LocalDate fechaInicioCiclolectivo,
			LocalDate fechaFinalizacionCiclolectivo) {

		this.cicloLectivoId = cicloLectivoId;
		this.fechaInicioCicloLectivo = fechaInicioCiclolectivo;
		this.fechaFinalizacionCiclolectivo = fechaFinalizacionCiclolectivo;
		fechaList = new ArrayList<>();
		this.fechaInicioInscripcion = fechaInicioCiclolectivo;
		this.fechafinalizacion = fechaFinalizacionCiclolectivo;
	}

	public Integer getCicloLectivoId() {
		return cicloLectivoId;
	}

	public void setCicloLectivoId(Integer cicloLectivoId) {
		this.cicloLectivoId = cicloLectivoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaInicioCicloLectivo() {
		return fechaInicioCicloLectivo;
	}

	public void setFechaInicioCicloLectivo(LocalDate fechaInicioCicloLectivo) {
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
	}

	public LocalDate getFechaFinalizacionCiclolectivo() {
		return fechaFinalizacionCiclolectivo;
	}

	public void setFechaFinalizacionCiclolectivo(LocalDate fechaFinalizacionCiclolectivo) {
		this.fechaFinalizacionCiclolectivo = fechaFinalizacionCiclolectivo;
	}

	public LocalDate getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}

	public void setFechaInicioInscripcion(LocalDate fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}

	public LocalDate getFechafinalizacion() {
		return fechafinalizacion;
	}

	public void setFechafinalizacion(LocalDate fechafinalizacion) {
		this.fechafinalizacion = fechafinalizacion;
	}

	public boolean superponerFecha(LocalDate fechaInicioCicloLectivo, LocalDate fechaFinalizacionCiclolectivo) {
		// TODO Auto-generated method stub
		if ((fechaList.contains(fechaInicioCicloLectivo)) & !(fechaList.contains(fechaFinalizacionCiclolectivo))) {
			return false;
		} else {
			fechaList.add(fechaInicioCicloLectivo);
			fechaList.add(fechaFinalizacionCiclolectivo);
		}
		return true;
	}

	public boolean fechaInscripcion(LocalDate fechaInicioInscripcion, LocalDate fechafinalizacion) {
		if (fechaInicioInscripcion.isAfter(getFechaInicioCicloLectivo())
				&& fechafinalizacion.isBefore(getFechafinalizacion())) {
			return true;
		}
		return false;
	}

}
