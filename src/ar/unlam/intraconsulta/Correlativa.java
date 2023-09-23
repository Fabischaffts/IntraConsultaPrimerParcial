package ar.unlam.intraconsulta;

import java.util.ArrayList;

public class Correlativa {
	
	private ArrayList<Nota> nota;

	public Correlativa(Nota nota) {
		this.nota =new ArrayList<>();
	}

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
	
}