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
	private ArrayList<Integer> idList;
	private ArrayList<LocalDate> fechaList;
	
	public CicloLectivo(Integer cicloLectivoId, String nombre, LocalDate fechaInicioCiclolectivo, LocalDate fechaFinalizacionCiclolectivo ) {
		
		this.cicloLectivoId = cicloLectivoId;
		idList = new ArrayList<>();
		fechaList = new ArrayList<>();
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

	public ArrayList<Integer> getIdList() {
		return idList;
	}

	public void setIdList(ArrayList<Integer> idList) {
		this.idList = idList;
	}
	public boolean CicloLectivoIdRegistrado(Integer cicloLectivoId) {
		if(idList.contains(cicloLectivoId)) {
			return false;
	}else {
		idList.add(cicloLectivoId);
		
	}
		return true;
	}
	public boolean superponerFecha(LocalDate fechaInicioCicloLectivo,LocalDate fechaFinalizacionCiclolectivo) {
		// TODO Auto-generated method stub
		if ((fechaList.contains(fechaInicioCicloLectivo))&!(fechaList.contains(fechaFinalizacionCiclolectivo)) ){
			return false;
		}else {
			fechaList.add(fechaInicioCicloLectivo);
			fechaList.add(fechaFinalizacionCiclolectivo);
		}
		return true;
	}
	

}
