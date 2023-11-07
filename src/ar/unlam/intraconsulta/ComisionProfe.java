package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class ComisionProfe {
	private ArrayList<ComisionProfe> listaDeCursoProfesor;
	private Profesor profesor;
	private Comision comision;
	
	
	  public ComisionProfe(	Profesor profesor, Comision comision) {
	        this.profesor = profesor;
	        this.comision = comision;
	    }



	public Profesor getProfesor() {
		return profesor;
	}



	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}



	public Comision getComision() {
		return comision;
	}



	public void setComision(Comision comision) {
		this.comision = comision;
	}



	public ArrayList<ComisionProfe> getListaDeCursoProfesor() {
		return listaDeCursoProfesor;
	}

	public void setListaDeCursoProfesor(ArrayList<ComisionProfe> listaDeCursoProfesor) {
		this.listaDeCursoProfesor = listaDeCursoProfesor;
	}



	public static int size() {
		// TODO Auto-generated method stub
		return 0;
	}



	public static void add(Profesor nuevoProfesor) {
		// TODO Auto-generated method stub
		
	}





	

}
