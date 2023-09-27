package ar.unlam.intraconsulta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Nota {
 
	private Integer valor;
	private ArrayList <Nota> primerParcial;
	private ArrayList <Nota> segundoParcial;
	private ArrayList <Nota> recPrimerParcial;
	private ArrayList <Nota> recSegundoParcial;
	private ArrayList <Nota> Final;
	private ArrayList <Materia> idMateria;
	private Set<Alumno> recuperatoriosRendidos;
	private ArrayList <Alumno> idAlumno;
	  

	public Nota() {
	    this.valor = 0; // O cualquier otro valor por defecto que desees
	    recuperatoriosRendidos = new HashSet<>();
	}
	
	
	public Nota(Integer valor) {
		this.valor = valor;
	}
	


	public ArrayList<Alumno> getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(ArrayList<Alumno> idAlumno) {
		this.idAlumno = idAlumno;
	}


	public ArrayList<Materia> getIdMateria() {
		return idMateria;
	}


	public void setIdMateria(ArrayList<Materia> idMateria) {
		this.idMateria = idMateria;
	}


	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public boolean rangoNota() {
		if (valor > 0 && valor <= 10)
			return true;
		
		return false;
	}
	public boolean notasValidas(Nota nota){
		ArrayList<Nota> tiposDeNotasValidos = this.primerParcial;
		ArrayList<Nota> tiposDeNotasValidos2 = this.segundoParcial;
		ArrayList<Nota> tiposDeNotasValidos3 = this.segundoParcial;
		ArrayList<Nota> tiposDeNotasValidos4 = this.segundoParcial;
		ArrayList<Nota> tiposDeNotasValidos5 = this.Final;
		return true;	
	}
	public boolean unRecuperatorio() {
		  recuperatoriosRendidos = new HashSet<>();
		  if (recuperatoriosRendidos.contains(primerParcial) && recuperatoriosRendidos.contains(segundoParcial))
			return false;
		  return true;
			}
	public boolean primerParcialAprobada() {
        for (Nota nota : primerParcial) {
            if (nota.getValor() >= 4) {
                return true;	
}
        }
            	return Nota.add(primerParcial);
	}
	private static boolean add(ArrayList<Nota> primerParcial2) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean segundoParcialAprobado() {
        for (Nota nota : segundoParcial) {
        	  if (nota.getValor() >= 4) {
                  return true;	
  }
          }
              	return Nota.add(primerParcial);
  	}
  	private static boolean add1(ArrayList<Nota> segundoParcial) {
  		// TODO Auto-generated method stub
  		return false;
  	}
}
	




