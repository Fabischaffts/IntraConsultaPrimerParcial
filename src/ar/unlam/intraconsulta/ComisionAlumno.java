package ar.unlam.intraconsulta;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class ComisionAlumno {
	private ArrayList<Nota> nota;
	private DayOfWeek dia;;
	private ArrayList<Turno> turnos;
	private ArrayList<ComisionAlumno> listaAlumnoComison;
	private ArrayList <Comision> alumnoComisionExistene;
	

	public ComisionAlumno(Integer dni) {
		this.nota = new ArrayList<>();
		this.listaAlumnoComison = new ArrayList<>();
	}
	

	public ArrayList<ComisionAlumno> getAlumno() {
		return listaAlumnoComison;
	}


	/*public void setAlumno(ArrayList<Comision> alumno) {
		this.alumno = alumno;
	}*/


	public ArrayList<Nota> getNota() {
		return nota;
	}

	public void setNota(ArrayList<Nota> nota) {
		this.nota = nota;
	}

	public boolean correlativaAprobada() {
		for (Nota nota : nota) {
			if (nota.getValor() < 4) {
				return false;
			}
		}
		return true;
	}

	public boolean mismoDiaTurno(DayOfWeek dia, String turno) {
		for (Turno turnos : turnos) {
			boolean mismoDia = this.dia.equals(dia);
			boolean mismoTurno = this.turnos.equals(turno);

			return mismoDia && mismoDia;
		}
		return false;
	}

	public boolean MateriaAprobada(Integer valor) {
		for (Nota nota : nota) {
			if (nota.getValor() < 4) {
				return false;
			}
		}
		return true;
	}


	
		
	


	public static void add(ComisionAlumno alumnoAgregado) {
		// TODO Auto-generated method stub
		
	}


	








}
