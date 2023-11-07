package ar.unlam.intraconsulta;

import java.time.DayOfWeek;
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
	private ArrayList<ComisionProfe> listaDeCursoProfesor;
	private ArrayList<Nota> nota;
	private ArrayList<ComisionAlumno> listaAlumnoComision;
	private ArrayList<Comision> alumnonoComisionExistente;
	//private private ComisionProfe comision;

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
		if (buscarMateria(materia.getMateriaId()) == null) {
			this.materias.add(materia);
			return true;
		}
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
		if (comision.esIgual(comision) == false)
			return this.comision.add(comision);
		return false;
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

				return true;
			}
		}
		return asignarProfesorAComision(profesor);
	}

	public boolean asignarProfesorAComision(Profesor profesor) {

		Comision nuevaComision = new Comision(1);
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

	public boolean verificacionCorrelatividad(Integer idCorrelativa, Integer idMateria) {
		for (Materia materia : correlativas) {
			if (materia.getIdCorrelativa().equals(idCorrelativa) && (materia.getMateriaId().equals(idMateria))) {
				return true;
			}
		}
		return false;
	}

	// eliminar Correlatividad
	public Materia existeCorrelatividad(Integer idCorrelativa, Integer MateriaId) {
		for (Materia materia : correlativas) {
			if (correlativas.contains(idCorrelativa) && (correlativas.contains(MateriaId)))
				return materia;
			;
		}
		return null;

	}

	public boolean eliminarCorrelatividad(Integer idCorrelativa, Integer MateriaId) {

		if (existeCorrelatividad(idCorrelativa, MateriaId) != null) {
			correlativas.remove(idCorrelativa);
			return true;
		} else {
			return false;
		}
	}

	// inscribirAlumno a comision
	// comision?universid
	public Universidad alumnoDadoDeAlta(Integer dni, Integer comisionId) {
		for (Alumno alumno : alumnos) {
			for (Comision comision : comision) {
				if (alumno.getDni().equals(dni) && (comision.getComisionId().equals(comisionId))) {
					return this.alumnoDadoDeAlta(dni, comisionId);
				}
			}
		}
		return null;
	}

	public boolean profesorDadoDeAltaYComision(Integer dni, Integer comisionId) {
		for (Profesor profesor : profesor) {
			for (Comision comision : comision) {
				if (profesor.getDni().equals(dni) && (comision.getComisionId().equals(comisionId))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean inscribirAlumnoAComision(Integer dni, Integer cantidadMaximaDeAlumnos, Integer valor, Integer codigo,

			LocalDate fechaInicioInscripcion, LocalDate fechafinalizacion, Integer comisionId, String turno,
			DayOfWeek dia) {
		Universidad uni = alumnoDadoDeAlta(dni, comisionId);
		CicloLectivo cl = new CicloLectivo(comisionId, nombre, fechafinalizacion, fechafinalizacion);
		Aula aula = new Aula(cantidadMaximaDeAlumnos);
		if (this.listaAlumnoComision == null) {
			this.listaAlumnoComision = new ArrayList<>();
		}
		for (ComisionAlumno alumnoExistente : listaAlumnoComision) {
			// Crear una instancia de ComisionAlumno para el alumno actual
			ComisionAlumno alumnoAgregado = new ComisionAlumno(dni);
			if (uni.alumnoDadoDeAlta(dni, comisionId).equals(alumnoDadoDeAlta(dni, comisionId)))
				if (alumnoAgregado.correlativaAprobada() == true)
					if (cl.fechaInscripcion(fechaInicioInscripcion, fechafinalizacion) == true)
						if (aula.alumnosPermitidos(cantidadMaximaDeAlumnos)
								.equals(aula.alumnosPermitidos(cantidadMaximaDeAlumnos)))
							if (alumnoAgregado.mismoDiaTurno(dia, turno) == false)
								if (alumnoAgregado.MateriaAprobada(valor) == false)

									listaAlumnoComision.add(alumnoAgregado);

			return true;
		}
		return false;
	}
	public Comision buscarComision(Integer idComision) {
		for (Comision comision : comision) {
			if(comision.getComisionId().equals(idComision))
				return this.comision.get(0);
			
		}
		return null;	
		
	}

	public boolean agregarProfesorAComision(Integer dni, Integer idComision) {
		Comision comision = buscarComision(idComision);
		Profesor profesor= buscarProfesorPorDni(dni);
		//verficar si el profe o la comision ya estan asignados
		if (profesor != null && comision != null) {
			for (ComisionProfe cursoProfeExistente : listaDeCursoProfesor) {
				if(cursoProfeExistente.getProfesor().getDni().equals(dni))
					if(cursoProfeExistente.getComision().equals(idComision))
				return false;
			}
		}

		int	cantidadAlumnos = comision.cantidadAlumnosInscripto();
		int profesoresNecesarios = (cantidadAlumnos / 20 +1);

		if (comisionProfe(comision)< profesoresNecesarios) {
			for (int i = 0; i < profesoresNecesarios; i++) {

				Profesor nuevoProfesor = new Profesor(dni, nombre, nombre);
				ComisionProfe.add(nuevoProfesor);
			}
		}

		return true;
	}

	private int comisionProfe(Comision comision) {
		for (Profesor profesor : profesor) {
		return this.profesor.size()	;
		}
		return 0;
	}

	public boolean agregarAulaAComision(Integer dni, Integer codigo, Integer cantidadMaximaAlumnos) {
		Universidad uni = new Universidad(nombre);
		Aula aula = new Aula(cantidadMaximaAlumnos);
		boolean profesorDadoDeAlta = uni.profesorDadoDeAltaYComision(dni, codigo);
		if (!agregarProfesorAComision(dni, codigo)) {
			return false;
		}
		Comision.add(aula);

		return true;
	}

	public boolean registrarNota(Integer dni, Integer idComision, Integer idAlumno) {

		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(idAlumno)) {
				for (Nota nota : nota) {
					Nota no = new Nota();
					ComisionAlumno co = new ComisionAlumno(dni);
					Comision notaAgregada = new Comision(idComision);
					comision = new ArrayList<Comision>();					
					if (no.rangoNota() == true)
						if (co.correlativaAprobada() == true)
							if (nota.notasValidas(nota) == true)
								if (nota.unRecuperatorio() == true)
									if (nota.primerParcialAprobada() && nota.segundoParcialAprobado() == true)

										return Comision.add(notaAgregada);
				}
			}
		}
		return false;
	}



	public ArrayList<Materia> obtenerMateriasAprobadasParaUnAlumno(Integer idAlumno) {

		ArrayList<Materia> aprobadas = new ArrayList<>();
		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(idAlumno)) {
				for (Materia materia : alumno.getMaterias()) {
					if (estaAprobado(idAlumno, materia.getMateriaId())) {
						aprobadas.add(materia);
					}
				}

				break;
			}
		}

		return aprobadas;
	}

	public ArrayList<Nota> obetenerNota(Integer idAlumno, Integer idMateria) {
		// Annahme: Hier wird die Liste der Noten gespeichert

		ArrayList<Nota> valor = new ArrayList<>();

		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(idAlumno)) {
				for (Nota nota : nota) {

					if (nota.getIdAlumno().equals(idAlumno) && nota.getIdMateria().equals(idMateria)) {
						valor.add(nota);
					}
				}
			}

		}

		return valor;
	}

	// aca se guarda la Lista de materias
	public ArrayList<Materia> obtenerMateriasQueFaltanCursarParaUnAlumno(Integer idAlumno) {
		ArrayList<Materia> faltante = new ArrayList<>();
		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(idAlumno)) {

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
		ArrayList<Nota> valor = new ArrayList<>();
		int sumatoriaNota = 0, contadorNotas = 0;
		double promedio = 0.0;
		for (Alumno alumno : alumnos) {
			if (alumno.getDni().equals(idAlumno)) {
				for (Nota nota : nota) {
					if (nota.getIdAlumno().equals(idAlumno)) {
						valor.add(nota);

						sumatoriaNota += nota.getValor();
						contadorNotas++;
					}
				}
			}
		}
		promedio = sumatoriaNota / contadorNotas;
		return promedio;

	}
}
