package ar.unlam.intraconsulta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Universidad {

	// private Alumno [] alumnos;
	private ArrayList<Alumno> alumnos;
	private String nombre;
	private ArrayList<Materia> materias;
	private ArrayList<InscripcionMateria> inscripcionesMateria;
	private ArrayList<CicloLectivo> cicloLectivo;
	private ArrayList<Comision> comision;
	private ArrayList<Profesor> profesor;
	private ArrayList<Materia> correlativas;
	private Collection<? extends ComisionProfe> profesorComision;
	private ArrayList<ComisionProfe> comisionProfe;
	private ArrayList<Nota> nota;
	
	
	public Universidad(String nombre) {
		this.nombre = nombre;
		this.alumnos = new ArrayList<Alumno>();
		this.materias = new ArrayList<>();
		this.inscripcionesMateria = new ArrayList<>();
		this.cicloLectivo = new ArrayList<>();
		this.comision = new ArrayList<>();
		this.profesor = new ArrayList<>();
		this.correlativas = new ArrayList<>();
		this.profesorComision = new ArrayList<>();
	}

//agregar Alumno
	public Boolean registrar(Alumno alumno) {

		if (buscarAlumnoPorDni(alumno.getDni()) == null)

			return this.alumnos.add(alumno);

		return false;
	}

	public Alumno buscarAlumnoPorDni(Integer dni) {

		for (int i = 0; i < alumnos.size(); i++) {
			if (this.alumnos.get(i).getDni().equals(dni))
				return this.alumnos.get(i);
		}

		return null;
	}

	public Boolean existeAlumno(Integer dni) {
		for (int i = 0; i < alumnos.size(); i++) {
			if (this.alumnos.get(i).getDni().equals(dni))
				return true;
		}
		return false;
	}

//agregar Materia
	public Boolean registraMateria(Materia materia) {
		if (buscarMateria(materia.getMateriaId()) == null)

			return this.materias.add(materia);

		return false;
	}

	public Materia buscarMateria(Integer materiaId) {

		for (int i = 0; i < this.materias.size(); i++) {
			if (this.materias.get(i).getMateriaId().equals(materiaId))
				;
			return this.materias.get(i);
		}
		return null;
	}

	public boolean inscribirAlumnoAUnaMateria(Integer dni, Integer codigo) {

		Alumno alumno = this.buscarAlumnoPorDni(dni);
		Materia materia = this.buscarMateriaPorCodigo(codigo);

		if (alumno == null || materia == null) {
			return false;
		}

		ArrayList<Materia> correlativas = materia.getCorrelativas();
		for (int i = 0; i < correlativas.size(); i++) {
			Materia correlativa = correlativas.get(i);
			if (!estaAprobado(dni, correlativa.getMateriaId()))
				return false;
		}

		InscripcionMateria inscripcionMateria = new InscripcionMateria(alumno, materia);
		return this.inscripcionesMateria.add(inscripcionMateria);

	}

	private Materia buscarMateriaPorCodigo(Integer codigo) {
		for (int i = 0; i < this.materias.size(); i++) {
			if (this.materias.get(i).getMateriaId().equals(codigo))
				return this.materias.get(i);
		}
		return null;
	}

	public InscripcionMateria getInscripcion(Integer dni, Integer codigo) {

		Alumno alumno = this.buscarAlumnoPorDni(dni);
		Materia materia = this.buscarMateriaPorCodigo(codigo);

		for (int i = 0; i < this.inscripcionesMateria.size(); i++) {
			InscripcionMateria ins = this.inscripcionesMateria.get(i);
			Alumno alumnoIns = ins.getAlumno();
			Materia materiaIns = ins.getMateria();
			if (alumnoIns.equals(alumno) && materiaIns.equals(materia)) {
				return ins;
			}
		}
		return null;

	}

	public boolean calificar(Integer dni, Integer codigo, Nota nota) {

		Alumno alumno = this.buscarAlumnoPorDni(dni);
		Materia materia = this.buscarMateriaPorCodigo(codigo);

		if (alumno == null || materia == null) {
			return false;
		}

		InscripcionMateria inscripcionMateria = getInscripcion(dni, codigo);

		if (inscripcionMateria == null) {
			return false;
		}

		inscripcionMateria.setNota(nota);

		return true;

	}

	public Nota getNota(Integer dni, Integer codigo) {
		InscripcionMateria ins = getInscripcion(dni, codigo);
		return ins.getNota();
	}

	public boolean estaAprobado(Integer dni, Integer codigo) {
		Nota nota = getNota(dni, codigo);

		if (nota == null) {
			return false;
		}

		Integer valor = nota.getValor();
		return valor >= 4;
	}

	// agregar CicloLectivo
	public boolean registrarCicloLectivo(CicloLectivo cicloLectivo) {

		return this.cicloLectivo.add(cicloLectivo);

	}

	// agregar Comision
	public boolean rigistrarComision(Comision comision) {

		return this.comision.add(comision);

	}

	// agregar Profesor
	public boolean registrarProfesor(Profesor profesor) {

		if (buscarProfesorPorDni(profesor.getDni()) == null)

			return this.profesor.add(profesor);
		return false;
	}

	public Profesor buscarProfesorPorDni(Integer dni) {

		for (int i = 0; i < profesor.size(); i++) {
			if (this.profesor.get(i).getDni().equals(dni))
				return this.profesor.get(i);
		}

		return null;
	}

//Agregar un Profesor a una comision que no sea el mismo
	public boolean mismoProfesor(Profesor profesor) {
		for (Comision comision : comision) {
			if (comision.getProfesores().contains(profesor)) {
				// Der Professor wurde gefunden, geben Sie true zurück
				return true;
			}
		}

		// Der Professor wurde nicht gefunden, versuchen Sie, ihn zur Comision
		// hinzuzufügen
		return asignarProfesorAComision(profesor);
	}

	public boolean asignarProfesorAComision(Profesor profesor) {
		// Fügen Sie den Professor zur Comision hinzu, falls er nicht gefunden wurde
		Comision nuevaComision = new Comision(1); // Sie müssen hier die Logik zum Hinzufügen zur Comision implementieren
		return nuevaComision.getProfesores().add(profesor);
	}

	// agregar Correlatividad
	public boolean agregarCorelatividad(Materia correlativa) {
		if (!correlativas.contains(correlativa)) {
			correlativas.add(correlativa);
			return true;
		}
		return false;
	}

	// agregar Correlativa y si existe id-correlativa y - materia
	public boolean agregarCorrelatividad(Integer idCorrelativa, Integer idMateria) {
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

	// eliminar Correlatividad
	public Materia existeCorrelatividad(Integer idCorrelativa, Integer materiaId) {
		for (Materia materia : correlativas) {
			if (correlativas.contains(idCorrelativa) && (correlativas.contains(materiaId)))
				return materia;
			;
		}
		return null;

	}

	public boolean eliminarCorrelatividad(Integer idCorrelativaAELiminar) {
		Materia correlatividad = existeCorrelatividad(idCorrelativaAELiminar, idCorrelativaAELiminar);
		  if (correlativas.contains(idCorrelativaAELiminar)) {
		        correlativas.remove(idCorrelativaAELiminar);
		        return true; // Correlatividad eliminada exitosamente
		    } else {
		        return false; // La correlatividad no existe
		    }
   }
	//inscribirAlumno a comision
	//comision?universid
	public boolean alumnoDadoDeAlta(Integer dni, Integer comisionId) {
		for (Alumno alumno : alumnos) {
		for (Comision comision : comision) {
			if (alumno.getDni().equals(dni)&&(comision.getComisionId().equals(comisionId))) {
				return true;
			}
		}	
		}
		return false;
	}
	public boolean profesorDadoDeAltaYComision(Integer dni, Integer comisionId) {
		for (Profesor profesor : profesor) {
		for (Comision comision : comision) {
			if (profesor.getDni().equals(dni)&&(comision.getComisionId().equals(comisionId))) {
				return true;
			}
		}	
		}
		return false;
	}
	public boolean inscribirAlumnoAComision(Integer dni, Integer codigo,LocalDate fecha,Collection<? extends Comision> alumno) {
		Correlativa cor = new Correlativa(null);
		Universidad uni =new  Universidad(nombre);
		CicloLectivo cl = new CicloLectivo(codigo, nombre, null, null);
		Comision co = new Comision(codigo);
		Aula aula = new Aula(dni);
		Materia ma = new Materia(codigo, nombre);
		if(uni.alumnoDadoDeAlta(dni,codigo)== false) 
			if(cor.correlativaAprobada()==true)
				if(cl.fechaInscripcion(fecha,fecha)==true)
					if(aula.alumnosPermitidos(codigo)==true)
						if(co.mismoDiaTurno(co)== true)
							if(ma.MateriaAprobada()==false)
								return this.comision.addAll(alumno);
								
		
		return false;
	}
	public boolean agregarProfesor(Integer dni, Integer codigo) {
	    Universidad uni = new Universidad(nombre); 
	    boolean profesorDadoDeAlta = uni.profesorDadoDeAltaYComision(dni, codigo);

	    if (!profesorDadoDeAlta) {

	        return false;
	    }

	    int cantidadAlumnos = alumnos.size();
	    int cantidadProfesoresAgregados = profesor.size();
	    int profesoresNecesarios = (cantidadAlumnos / 20) - cantidadProfesoresAgregados;

	    if (profesoresNecesarios > 0) {
	        for (int i = 0; i < profesoresNecesarios; i++) {
	          
	        	Profesor nuevoProfesor = new Profesor(dni, nombre, nombre);
	            ComisionProfe.add(nuevoProfesor);
	        }
	    }

	    return true;
	}
	public boolean agregarAulaAComision(Integer dni,Integer codigo, Integer cantidadMaximaAlumnos) {
	    Universidad uni = new Universidad(nombre); 
	    Aula aula = new Aula(cantidadMaximaAlumnos);
	    boolean profesorDadoDeAlta = uni.profesorDadoDeAltaYComision(dni, codigo);
	    if(!agregarProfesor(dni,codigo)) {
	    	return false;
	    }
	            Comision.add(aula);
	        
	    return true;
	}
	public boolean registrarNota(Integer idComision, Integer idAlumno, Nota nota) {
		Nota no = new Nota();
		Correlativa co = new Correlativa(nota);
		ArrayList<Nota> comision = new ArrayList<>();
		if (no.rangoNota() == true)
			if (co.correlativaAprobada()== true)//tengo que agregar que si no es true no se puede asignar una nota <= 7 
				if (nota.notasValidas(nota) == true)
					if (nota.unRecuperatorio()== true)
						if(nota.primerParcialAprobada()&&nota.segundoParcialAprobado()==true)
							
			
			return Comision.add(nota);
		return false;
	}

	
	public ArrayList<Materia> obtenerMateriasAprobadasParaUnAlumno(Integer idAlumno){
		
		ArrayList<Materia> aprobadas = new ArrayList<>();
		   // Durchlaufe die Schüler, um den Schüler mit der angegebenen ID zu finden
        for (Alumno alumno : alumnos) {
            if (alumno.getDni().equals(idAlumno)) {
                // Durchlaufe die Materien des Schülers
                for (Materia materia : alumno.getMaterias()) {
                    // Überprüfe, ob der Schüler die Materie bestanden hat
                    if (estaAprobado(idAlumno, materia.getMateriaId())) {
                        aprobadas.add(materia);
                    }
                }
                // Da der Schüler gefunden wurde, kannst du die Schleife verlassen
                break;
            }
        }

        return aprobadas;
    }
	public ArrayList<Nota> obetenerNota(Integer idAlumno, Integer idMateria){
	    // Annahme: Hier wird die Liste der Noten gespeichert

	    // Andere Attribute und Methoden der Klasse

	   // ArrayList<Nota> obtenerNota(Integer idAlumno1, Integer idMateria1) {
	        ArrayList<Nota> valor = new ArrayList<>();
	        
	        for (Alumno alumno : alumnos) {
	            if (alumno.getDni().equals(idAlumno)) {
	                for (Nota nota : nota) {
	                    // Überprüfe, ob die Note dem Schüler und der Materie entspricht
	                    if (nota.getIdAlumno().equals(idAlumno) && nota.getIdMateria().equals(idMateria)) {
	                        valor.add(nota);
	                    }
	                }
	            }
	            // Beachte, dass das break hier entfernt wurde, um die Schleife für alle Schüler zu durchlaufen
	        }
	        
	        return valor;
	    }

	public ArrayList<Materia> obtenerMateriasQueFaltanCursarParaUnAlumno(Integer idAlumno){
		ArrayList<Materia> faltante = new ArrayList<>();
		  for (Alumno alumno : alumnos) {
	            if (alumno.getDni().equals(idAlumno)) {
		            // Erstelle eine Set zur Verfolgung der bestandenen Materien
		            Set<Integer> materiasAprobadas = new HashSet<>();

		            // Durchlaufe die Materien, die der Schüler bereits bestanden hat
		            for (Materia materia : alumno.getMaterias()) {
		                if (estaAprobado(idAlumno, materia.getMateriaId())) {
		                    materiasAprobadas.add(materia.getMateriaId());
		                }
		            }

		            // Durchlaufe alle Materien, um die fehlenden Materien zu finden
		            for (Materia materia : materias) {
		                if (!materiasAprobadas.contains(materia.getMateriaId())) {
		                    faltante.add(materia);
		                }
		            }  
		        }
		       break;
		    }
		return faltante;	
		}
	
	public double calcularPromedio(Integer idAlumno) {
		int sumatoriaNota = 0, contadorNotas = 0;
		double promedio = 0.0;
		  for (Alumno alumno : alumnos) {
	            if (alumno.getDni().equals(idAlumno)) {
	            	for(Nota nota : nota) {
	            		sumatoriaNota += nota.getValor();
	            		contadorNotas++;	            								
					}
	            }
		  }
	            promedio = sumatoriaNota / contadorNotas;
	            return promedio;	            
	}	
}

//se hizo el commit????


