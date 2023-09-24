package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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
		Comision comision1 = new Comision(1);
		comision1.setMaterias(new ArrayList<>());
		comision1.getMaterias().add(new Materia(1, "Pb2"));
		comision1.setCicloLectivos(new ArrayList<>());
		comision1.getCicloLectivos().add(new CicloLectivo(2023, "Ciclo 2023", null, null));
		comision1.setTurno(new ArrayList<>());
		comision1.getTurno().add(new Turno("noche"));

		Comision comision2 = new Comision(1);
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

		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Materia correlativa = new Materia(1, "pb2");
		uni.agregarCorelatividad(correlativa);
		assertTrue(uni.agregarCorrelatividad(1, 1));
	}

	@Test
	// esta mal
	public void queSePuedaEliminarLaCorrelatividadYverficarSiExiste() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Materia correlativa = new Materia(1, "pb2");
		assertFalse(uni.eliminarCorrelatividad(1));
		// entfernt
	}

	@Test
	public void queEstendadoDeAltaAlumnoYComision() {
		Universidad uni = new Universidad("Unalm");
		Comision comision = new Comision(1);
		Alumno alumno = new Alumno(1, "Bauti", "Leon");
		uni.alumnoDadoDeAlta(1, 1);

		assertFalse(uni.alumnoDadoDeAlta(2, 1));
	}
	@Test
	public void queTengatodasLasCorrelativasAprobados() {
		Correlativa correlativa = new Correlativa(null);
		Nota nota = new Nota(3);		
		correlativa.correlativaAprobada();
		assertTrue(correlativa.correlativaAprobada());
	}
	@Test
	public void queSePuedaValidarLaFechaDeInscripcion() {
	    LocalDate fechaInicioCiclolectivo = LocalDate.of(2023, 3, 5);
	    LocalDate fechaFinalizacionCiclolectivo = LocalDate.of(2023, 7, 1);
	    CicloLectivo cicloLectivo = new CicloLectivo(1, "2do2023", fechaInicioCiclolectivo, fechaFinalizacionCiclolectivo);
	    
	    LocalDate fechaInicioInscripcion = LocalDate.of(2023, 3, 10); // Ändern Sie diese Daten nach Bedarf
	    LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 6, 20); // Ändern Sie diese Daten nach Bedarf
	    
	    // Rufen Sie die Methode fechaInscripcion einmal auf und speichern Sie das Ergebnis
	    boolean inscripcionValida = cicloLectivo.fechaInscripcion(fechaInicioInscripcion, fechaFinalizacionInscripcion);
	    
	    // Verwenden Sie das Ergebnis in Ihrer Assertionsaussage
	    assertTrue(inscripcionValida);
	}
	@Test
	
	public void queNoSePuedaExecderLaCantidadDeAlumnoEnAula() {
		Aula aula = new Aula(30);		
		//aula.alumnosPermitidos(20);
		assertTrue(aula.alumnosPermitidos(20));
	}
	@Test
	public void queNoSeaLaMismoDiaYmismoTurno() {

	    // Erstellen von Comision-Objekten
	    Comision comision1 = new Comision(1);
	    Comision comision2 = new Comision(1);

	    // Setzen des Tages (Dia) für comision1 und comision2
	    comision1.setDia(DayOfWeek.MONDAY);
	    comision2.setDia(DayOfWeek.TUESDAY); // Ändern Sie den Tag für comision2

	    // Setzen des Turnos für comision1 und comision2
	    comision1.setTurno(new ArrayList<>());
	    comision1.getTurno().add(new Turno("noche"));
	    comision2.setTurno(new ArrayList<>());
	    comision2.getTurno().add(new Turno("mañana")); // Ändern Sie den Turno für comision2

	    // Überprüfen, ob die getDia-Methode den erwarteten Wert zurückgibt
	    assertEquals(DayOfWeek.MONDAY, comision1.getDia());
	    assertEquals(DayOfWeek.TUESDAY, comision2.getDia());

	    // Überprüfen, ob die esIgual-Methode wie erwartet funktioniert
	    assertTrue(comision1.esIgual(comision1)); // comision1 ist gleich sich selbst
	    assertFalse(comision1.esIgual(comision2)); // comision1 sollte nicht gleich comision2 sein
	}
	    
	   /* @Test
	    public void testInscribirAlumnoAComision() {
	        // Erstellen einer Beispiel-Comision und einer Schülerkollektion
	        Comision comision = new Comision(1);
	        Collection<Comision> comisiones = new ArrayList<>();
	        comisiones.add(comision);

	        // Erstellen von Instanzen der erforderlichen Klassen (möglicherweise müssen Sie Mocks verwenden)
	        Correlativa cor = new Correlativa(null);
	        Universidad uni = new Universidad("Beispiel-Universität");
	        CicloLectivo cl = new CicloLectivo(2023, "Ciclo 2023", null, null);
	        Aula aula = new Aula(123); // Beispiel-DNI
	        Materia ma = new Materia(101, "Beispiel-Materia");

	        // Hier können Sie ggf. Mocks oder Stubs für Ihre Klassen verwenden, um das Verhalten zu steuern

	        // Erstellen Ihrer Klasse, die die inscribirAlumnoAComision-Methode enthält
	        Universidad universidad = new Universidad("unlam");
	        LocalDate fecha = LocalDate.of(2023, 3, 10);
	        // Testfall: Schüler anmelden (möglicherweise müssen Sie Ihre Methodenaufrufe anpassen)
	        boolean result = universidad.inscribirAlumnoAComision(123, 2023,fecha,comisiones);

	        // Überprüfen Sie, ob der Schüler erfolgreich hinzugefügt wurde (abhängig von Ihrer Implementierung)
	      assertTrue(result);

	        // Überprüfen Sie, ob der Schüler tatsächlich zur Comision hinzugefügt wurde
	        assertFalse(comision.getAlumnos().contains(123)); // 123 sollte in der Schülerliste von comision sein
	    }*/
	
	@Test
	public void queSePuedaRegistrarUnaProfesorAUnaComisionCada20Alumnos() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Comision co = new Comision (1);
		ComisionProfe cp = new ComisionProfe();
		Profesor profesor = new Profesor(1, "Leon", "Bauti");
	
		uni.agregarProfesor(1, 1);
		// tiene que ser true
		assertFalse(uni.agregarProfesor(1,1));
		
}
	
	@Test
	public void queSePuedaRegistrarUnAulaAUnaComision() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Aula aula = new Aula(22);
		Comision co = new Comision (1);
		ComisionProfe cp = new ComisionProfe();
		Profesor profesor = new Profesor(1, "Leon", "Bauti");
	
		uni.agregarProfesor(1, 1);
		// tiene que ser true
		assertFalse(uni.agregarProfesor(1,1));
	}

	
	/*  @Test
	    public void testAgregarProfesorNoDadoDeAlta() {
			String nombre = "Unlam";
			Universidad uni = new Universidad(nombre);
			Comision co = new Comision (1);
			Profesor profesor = new Profesor(1, "Leon", "Bauti");
			Profesor profesor2 = new Profesor(1, "Leon", "Bauti");
			Profesor profesor3 = new Profesor(1, "Leon", "Bauti");

	        // Configurar el escenario de prueba con un profesor no dado de alta en la universidad
	        Integer dni = 1;
	        Integer codigo = 1001;
	        Alumno.add(new Alumno());
	        comision.setAlumnos(alumnos);

	        // Ejecutar el método bajo prueba
	        boolean resultado = uni.agregarProfesor(dni, codigo);

	        // Verificar que el profesor no se haya agregado debido a que no está dado de alta
	        assertFalse(resultado);
	        assertEquals(0, ComisionProfe.size()); // No debe haber profesores en la ComisionProfe
	    }*/
}
