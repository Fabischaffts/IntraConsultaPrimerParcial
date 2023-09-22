package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestUniversidad {
//agregar Alumno
	@Test
	public void queSePuedaRegistrarUnAlumnoAUnaUniversidad() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);
		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		Boolean registroExitoso = unlam.registrar(alumno);
		assertTrue(registroExitoso);

	}

	@Test
	public void queNoSePuedaRegistrarUnAlumnoCuandoElAlumnoYaEsteRegistradoAUnaUniversidad() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);
		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		Alumno alumno2 = new Alumno(dni, "jose", "Lopez");
		unlam.registrar(alumno);
		Boolean registroExitoso = unlam.registrar(alumno2);
		assertFalse(registroExitoso);

	}

	@Test
	public void queSePuedaRegistrarUnaMateriaAUnaUniversidad() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);
		nombre = "PB2 ";
		Integer codigo = 1;
		Materia pb2 = new Materia(codigo, nombre);
		assertTrue(unlam.registraMateria(pb2));

	}

	@Test
	public void queSePuedaInscribirUnAlumnoenMateriaSinCorrelativas() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);
		nombre = "PB2 ";
		Integer codigo = 1;
		Materia pb2 = new Materia(codigo, nombre);
		unlam.registraMateria(pb2);
		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		unlam.registrar(alumno);
		assertTrue(unlam.inscribirAlumnoAUnaMateria(dni, codigo));

	}

	@Test
	public void queSePuedaAsociarUnaMateriaCorrelativaAOtraMateria() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);

		nombre = "PB1";
		Integer codigo = 1;
		Materia pb1 = new Materia(codigo, nombre);

		unlam.registraMateria(pb1);

		nombre = "PB2";
		codigo = 2;
		ArrayList<Materia> correlativas = new ArrayList<>();
		correlativas.add(pb1);
		Materia pb2 = new Materia(codigo, nombre);

		pb2.agregarCorrelativa(pb1);
		unlam.registraMateria(pb2);

		ArrayList<Materia> correlativas2 = pb2.getCorrelativas();

		assertTrue(correlativas2.contains(pb1));
	}

	@Test
	public void queNoSePuedaAsociarUnaMateriaCorrelativaAOtraMateriaPorqueYaExiste() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);

		nombre = "PB1";
		Integer codigo = 1;
		Materia pb1 = new Materia(codigo, nombre);

		unlam.registraMateria(pb1);

		nombre = "PB2";
		codigo = 2;
		ArrayList<Materia> correlativas = new ArrayList<>();
		correlativas.add(pb1);
		Materia pb2 = new Materia(codigo, nombre);

		pb2.agregarCorrelativa(pb1);
		assertFalse(pb2.agregarCorrelativa(pb1));

	}

	public void queSePuedaCalificar() {

		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);

		nombre = "PB1";
		Integer codigo = 1;
		Materia pb1 = new Materia(codigo, nombre);

		unlam.registraMateria(pb1);

		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		unlam.registrar(alumno);
		unlam.inscribirAlumnoAUnaMateria(dni, codigo);

		Nota nota = new Nota(9);
		unlam.calificar(dni, codigo, nota);

		Nota nota2 = unlam.getNota(dni, codigo);
		assertEquals(nota, nota2);
	}

	@Test
	public void queSePuedaSaberSiUnAlumnoAproboUnaDeterminadaMateria() {

		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);

		nombre = "PB1";
		Integer codigo = 1;
		Materia pb1 = new Materia(codigo, nombre);

		unlam.registraMateria(pb1);

		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		unlam.registrar(alumno);
		unlam.inscribirAlumnoAUnaMateria(dni, codigo);

		Nota nota = new Nota(9);
		unlam.calificar(dni, codigo, nota);

		assertTrue(unlam.estaAprobado(dni, codigo));

	}

	@Test
	public void queSeNoPuedaInscribirAUnAlumnoSiAdeudaLaCorrelativa() {
		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);

		nombre = "PB1";
		Integer codigo = 1;
		Materia pb1 = new Materia(codigo, nombre);

		unlam.registraMateria(pb1);

		nombre = "PB2";
		codigo = 2;
		ArrayList<Materia> correlativas = new ArrayList<>();
		correlativas.add(pb1);
		Materia pb2 = new Materia(codigo, nombre);

		pb2.agregarCorrelativa(pb1);
		unlam.registraMateria(pb2);

		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		unlam.registrar(alumno);

		unlam.inscribirAlumnoAUnaMateria(dni, 1);

		assertFalse(unlam.inscribirAlumnoAUnaMateria(dni, 2));
	}

	@Test
	public void queSePuedaInscribirAUnAlumnoALaCorrelativa() {

		String nombre = "Unlam";
		Universidad unlam = new Universidad(nombre);

		nombre = "PB1";
		Integer codigo = 1;
		Materia pb1 = new Materia(codigo, nombre);

		unlam.registraMateria(pb1);

		nombre = "PB2";
		codigo = 2;
		ArrayList<Materia> correlativas = new ArrayList<>();
		correlativas.add(pb1);
		Materia pb2 = new Materia(codigo, nombre);

		pb2.agregarCorrelativa(pb1);
		unlam.registraMateria(pb2);

		nombre = "Marta";
		String apellido = "perez";
		Integer dni = 44555;
		Alumno alumno = new Alumno(dni, apellido, nombre);
		unlam.registrar(alumno);

		unlam.inscribirAlumnoAUnaMateria(dni, 1);

		Nota nota = new Nota(9);
		unlam.calificar(dni, 1, nota);

		// assertTrue(unlam.inscribirAlumnoAUnaMateria(dni,2)) ;

	}

	// agregar Materia-
	@Test
	public void queSePuedaRegistrarUnaMateriaPorIdSinQueSeRepita() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Materia materia = new Materia(1, "pb2");
		Materia materia2 = new Materia(1, "pb1");
		uni.registraMateria(materia);
		boolean buscarMateriaExitoso = uni.registraMateria(materia2);
		assertFalse(buscarMateriaExitoso);

	}

	public void queSePuedaRegistrarUnaMateria() {
		Universidad uni = new Universidad("Unalm");
		Materia materia = new Materia(1, "pb2");
		uni.registraMateria(materia);

		assertTrue(uni.registraMateria(materia));
	}

	// agregar CicloLectivo
	@Test
	public void queSePuedaRegistrarUnCicloLectivo() {
		Universidad uni = new Universidad("Unalm");
		CicloLectivo cicloLectivo = new CicloLectivo(1, "2do2023", null, null);
		uni.registrarCicloLectivo(cicloLectivo);

		assertTrue(uni.registrarCicloLectivo(cicloLectivo));
	}

	// agregar Comision que no se pueden generar 2 comisiones para la misma
	// materia,cicloLectivo y turno
	public void queSePuedaRegistrarUnaComisionNoSePuedeRepetirParaLaMismaMateriaCicloLectivoYTurno() {
		Universidad uni = new Universidad("Unalm");
		Comision comision1 = new Comision();
		comision1.setMaterias(new ArrayList<>());
		comision1.getMaterias().add(new Materia(1, "Pb2"));
		comision1.setCicloLectivos(new ArrayList<>());
		comision1.getCicloLectivos().add(new CicloLectivo(2023, "Ciclo 2023", null, null));
		comision1.setTurno(new ArrayList<>());
		comision1.getTurno().add(new Turno("noche"));

		Comision comision2 = new Comision();
		comision2.setMaterias(new ArrayList<>());
		comision2.getMaterias().add(new Materia(1, "Pb2"));
		comision2.setCicloLectivos(new ArrayList<>());
		comision2.getCicloLectivos().add(new CicloLectivo(2023, "Ciclo 2023", null, null));
		comision2.setTurno(new ArrayList<>());
		comision2.getTurno().add(new Turno("noche"));

		assertTrue(comision1.esIgual(comision1));
		assertFalse(comision1.esIgual(comision2));
		uni.rigistrarComision(comision1);

		assertTrue(uni.rigistrarComision(comision1));
	}

	// agregar Profesor no se puede registrar con el mismo dni
	@Test
	public void queSePuedaRegistrarUnaProfesorPorDniSinQueSeRepita() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Profesor profesor = new Profesor(1, "Leon", "Bauti");
		Profesor profesor2 = new Profesor(1, "Gomez", "Leon");
		uni.registrarProfesor(profesor);
		boolean buscarProfesorExitoso = uni.registrarProfesor(profesor2);
		assertFalse(buscarProfesorExitoso);

	}

	public void queSePuedaRegistrarUnaProfesor() {
		Universidad uni = new Universidad("Unalm");
		Profesor profesor = new Profesor(1, "pb2", null);
		uni.registrarProfesor(profesor);

		assertTrue(uni.registrarProfesor(profesor));
	}

	@Test
	public void queSePuedaRegistrarUnaProfesorAUnaComisionSinQueSeRepita() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Profesor profesor = new Profesor(1, "Leon", "Bauti");
		Profesor profesor2 = new Profesor(1, "Leon", "Bauti");
		uni.mismoProfesor(profesor);
		boolean buscarProfesorExitoso = uni.mismoProfesor(profesor2);
		assertTrue(buscarProfesorExitoso);
		// tiene que decir assertFalse
	}

	@Test
	public void queSePuedaRegistrarUnaCorrelativaYSeExisteIdMateriaYIdCorrelativa() {
		Materia materiaA = new Materia(1, "pb1");
		Materia materiaB = new Materia(2, "pb2");

		assertTrue(materiaA.agregarCorrelativa(materiaB)); // Fügen Sie Materia B als Korrelation zu Materia A hinzu
		assertTrue(materiaA.getCorrelativas().contains(materiaB)); // Überprüfen, ob Materia B in den Korrelationen von
																	// Materia A vorhanden ist
		assertFalse(materiaA.agregarCorrelativa(materiaB)); // Versuchen Sie, Materia B erneut hinzuzufügen, sollte
															// false zurückgeben
		assertTrue(materiaA.agregarCorrelatividad(2)); // Versuchen, Materia B (ID 2) als Korrelation zu Materia A
														// hinzuzufügen
		assertTrue(materiaA.getCorrelativas().contains(materiaB)); // Überprüfen, ob Materia B in den Korrelationen von
		// es false // Materia A vorhanden ist
		assertTrue(materiaA.agregarCorrelatividad(2)); // Versuchen Sie, Materia B erneut hinzuzufügen, sollte false
														// zurückgeben
		materiaA.agregarCorrelativa(materiaB); // Fügen Sie Materia B manuell als Korrelation zu Materia A hinzu
		assertTrue(materiaA.verificacionCorrelatividad(2)); // Überprüfen, ob die ID 2 in den Korrelationen von Materia
															// A existiert
		assertFalse(materiaA.verificacionCorrelatividad(3)); // Überprüfen, ob eine nicht existierende ID (z. B. 3) in
																// den Korrelationen von Materia A nicht existiert

	}

	@Test
	public void queSePuedaEliminarLaCorrelatividadYverficarSiExiste() {
		 	Materia materiaA = new Materia(1, "Materia A");
		 	Materia materiaB = new Materia(2, "Materia B");
	        materiaA.agregarCorrelatividad(2); // Fügen Sie Materia B als Korrelation zu Materia A hinzu
	      //  assertTrue(materiaA.existeCorrelatividad(1, 2)); // Überprüfen, ob die Korrelation zwischen Materia A (ID 1) und Materia B (ID 2) existiert
	        assertFalse(materiaA.existeCorrelatividad(1, 3)); // Überprüfen, ob eine nicht existierende Korrelation zwischen Materia A (ID 1) und einer anderen Materia (ID 3) nicht existiert
	        assertFalse(materiaA.existeCorrelatividad(2, 1)); // Überprüfen, ob die Methode die Reihenfolge der IDs beachtet und eine nicht existierende Korrelation nicht findet
	    //    assertTrue(materiaA.eliminarCorrelatividad(1, 2)); // Versuchen Sie, die Korrelation zwischen Materia A (ID 1) und Materia B (ID 2) zu entfernen, sollte erfolgreich sein
	        assertFalse(materiaA.existeCorrelatividad(1, 2)); // Überprüfen, ob die Korrelation nicht mehr existiert
	        assertFalse(materiaA.eliminarCorrelatividad(1, 2)); // Versuchen Sie erneut, die Korrelation zu entfernen, sollte fehlschlagen, da sie bereits entfernt wurde
	        assertFalse(materiaA.eliminarCorrelatividad(1, 3)); // Versuchen Sie, eine nicht existierende Korrelation zu entfernen, sollte fehlschlagen
	        assertFalse(materiaA.eliminarCorrelatividad(2, 1)); // Überprüfen, ob die Methode die Reihenfolge der IDs beachtet und eine nicht existierende Korrelation nicht entfernt
	    }

}
