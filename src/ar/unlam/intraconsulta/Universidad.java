package ar.unlam.intraconsulta;

import java.util.ArrayList;
import java.util.Iterator;

public class Universidad {

	// private Alumno [] alumnos;
	private ArrayList<Alumno> alumnos;
	private String nombre;
	private ArrayList<Materia> materias;
	private ArrayList<InscripcionMateria> inscripcionesMateria;
	private ArrayList<CicloLectivo> cicloLectivo;
	private ArrayList<Comision> comision;
	private ArrayList<Profesor> profesor;

	public Universidad(String nombre) {
		this.nombre = nombre;
		this.alumnos = new ArrayList<Alumno>();
		this.materias = new ArrayList<>();
		this.inscripcionesMateria = new ArrayList<>();
		this.cicloLectivo = new ArrayList<>();
		this.comision = new ArrayList<>();
		this.profesor = new ArrayList<>();
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
	    
	    // Der Professor wurde nicht gefunden, versuchen Sie, ihn zur Comision hinzuzufügen
	    return asignarProfesorAComision(profesor);
	}

	public boolean asignarProfesorAComision(Profesor profesor) {
	    // Fügen Sie den Professor zur Comision hinzu, falls er nicht gefunden wurde
	    Comision nuevaComision = new Comision(); // Sie müssen hier die Logik zum Hinzufügen zur Comision implementieren
	    return nuevaComision.getProfesores().add(profesor);
	}
}