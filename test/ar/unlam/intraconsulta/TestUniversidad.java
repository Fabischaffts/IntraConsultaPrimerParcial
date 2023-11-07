package ar.unlam.intraconsulta;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class TestUniversidad {

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

	public void queSePuedaEliminarLaCorrelatividadYverficarSiExiste() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		assertFalse(uni.eliminarCorrelatividad(1, 1));
		assertNull(uni.existeCorrelatividad(1, 1));
	}

	@Test
	public void queEstendadoDeAltaAlumnoYComision() {
		Universidad uni = new Universidad("Unalm");
		Comision comision = new Comision(1);
		Alumno alumno = new Alumno(1, "Bauti", "Leon");
		uni.alumnoDadoDeAlta(1, 1);

		assertNull(uni.alumnoDadoDeAlta(2, 1));
	}

	@Test
	public void queTengatodasLasCorrelativasAprobados() {
		ComisionAlumno correlativa = new ComisionAlumno(1);
		Nota nota = new Nota(3);
		correlativa.correlativaAprobada();
		assertTrue(correlativa.correlativaAprobada());
	}

	@Test
	public void queSePuedaValidarLaFechaDeInscripcion() {
		LocalDate fechaInicioCiclolectivo = LocalDate.of(2023, 3, 5);
		LocalDate fechaFinalizacionCiclolectivo = LocalDate.of(2023, 7, 1);
		CicloLectivo cicloLectivo = new CicloLectivo(1, "2do2023", fechaInicioCiclolectivo,
				fechaFinalizacionCiclolectivo);

		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 3, 10);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 6, 20);

		boolean inscripcionValida = cicloLectivo.fechaInscripcion(fechaInicioInscripcion, fechaFinalizacionInscripcion);

		assertTrue(inscripcionValida);
	}

	@Test

	public void queNoSePuedaExecderLaCantidadDeAlumnoEnAula() {
		Aula aula = new Aula(30);
		Integer cantidadDeAlumnos = 35;

		Aula resultado = aula.alumnosPermitidos(cantidadDeAlumnos);

		assertNull(resultado);
	}

	@Test
	public void queNoSeaLaMismoDiaYmismoTurno() {

		Comision comision1 = new Comision(1);
		Comision comision2 = new Comision(1);

		comision1.setDia(DayOfWeek.MONDAY);
		comision2.setDia(DayOfWeek.TUESDAY);

		comision1.setTurno(new ArrayList<>());
		comision1.getTurno().add(new Turno("noche"));
		comision2.setTurno(new ArrayList<>());
		comision2.getTurno().add(new Turno("mañana"));

		assertEquals(DayOfWeek.MONDAY, comision1.getDia());
		assertEquals(DayOfWeek.TUESDAY, comision2.getDia());

		assertTrue(comision1.esIgual(comision1));
		assertFalse(comision1.esIgual(comision2));
	}

	@Test
	public void testInscribirAlumnoAComision() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		ArrayList<ComisionAlumno> listaAlumnoComision = new ArrayList<>();

		listaAlumnoComision = new ArrayList<>();
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 3, 5);
		LocalDate fechafinalizacion = LocalDate.of(2023, 7, 1);
		boolean resultado = uni.inscribirAlumnoAComision(1, 1, 1, 1, fechaInicioInscripcion, fechafinalizacion, 1,
				"noche", DayOfWeek.MONDAY);

		assertFalse(resultado);

		assertFalse(listaAlumnoComision.contains(new ComisionAlumno(1)));
	}

	@Test
	public void queSePuedaRegistrarUnaProfesorAUnaComisionCada20Alumnos() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Comision co = new Comision(1);
		Profesor profesor = new Profesor(1, "Leon", "Bauti");
		ComisionProfe cp = new ComisionProfe(profesor, co);
		uni.agregarProfesorAComision(1, 1);

		assertFalse(uni.agregarProfesorAComision(1, 1));

	}

	@Test
	public void queSePuedaRegistrarUnAulaAUnaComision() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Aula aula = new Aula(22);
		Comision co = new Comision(1);

		Profesor profesor = new Profesor(1, "Leon", "Bauti");
		ComisionProfe cp = new ComisionProfe(profesor, co);
		uni.agregarProfesorAComision(1, 1);

		assertFalse(uni.agregarProfesorAComision(1, 1));
	}

	@Test
	public void QueSePuedaRegistrarLaNota() {
		Universidad uni = new Universidad("Universidad de Ejemplo");

		Alumno alumno1 = new Alumno(1, "Bauti", "Leon");
		Comision comision1 = new Comision(1);

		uni.registrar(alumno1);
		uni.rigistrarComision(comision1);

		// assertTrue(uni.registrarNota(1, 1, 1));
	}

	@Test
	public void QueSePuedeObtenerMateriasAprobadasParaUnAlumno() {
		Universidad uni = new Universidad("Unlam");
		Alumno alumno = new Alumno(1, "Bauti", "Leon");
		uni.registrar(alumno);

		Materia materia1 = new Materia(1, "Pb1");
		Materia materia2 = new Materia(2, "Pb2");
		Materia materia3 = new Materia(3, "Pb3");

		uni.registraMateria(materia1);
		uni.registraMateria(materia2);
		uni.registraMateria(materia3);
		ArrayList<Materia> materiasAprobadas = uni.obtenerMateriasAprobadasParaUnAlumno(1);

		assertEquals(0, materiasAprobadas.size());
	}

	public void QueSePuedaObtenerLaNotaDeUnAlumno() {
		Universidad uni = new Universidad("Universidad de Ejemplo");

		Alumno alumno1 = new Alumno(1, "Bauti", "Leon");
		Alumno alumno2 = new Alumno(2, "leon", "Gomez");

		uni.registrar(alumno1);
		uni.registrar(alumno2);
		uni.getNota(1, 1);
		uni.getNota(2, 2);
		uni.getNota(3, 3);

		Integer idAlumno = 1;
		Integer idMateria = 1;

		ArrayList<Nota> notas = uni.obetenerNota(idAlumno, idMateria);

		assertNotNull(notas);
		assertEquals(1, notas.size());

		Nota notaObtenida = notas.get(0);
		assertEquals(idAlumno, notaObtenida.getIdAlumno());
		assertEquals(idMateria, notaObtenida.getIdMateria());
		assertEquals(Integer.valueOf(7), notaObtenida.getValor());
	}

	@Test
	public void QueSePuedeObtenerLasMateriasQueFaltanCursarParaUnAlumno() {
		Universidad uni = new Universidad("Universidad de Ejemplo");

		Alumno alumno1 = new Alumno(1, "Buti", "Leon");
		Alumno alumno2 = new Alumno(2, "Leon", "gomez");

		// Agregar alumnos, materias y notas a la universidad
		uni.registrar(alumno1);
		uni.registrar(alumno2);
		uni.registraMateria(new Materia(1, "Pb1"));
		uni.registraMateria(new Materia(2, "Pb2"));

		ArrayList<Materia> materiasFaltantes = uni.obtenerMateriasQueFaltanCursarParaUnAlumno(1);

		assertNotNull(materiasFaltantes);

		assertEquals(1, materiasFaltantes.size());

		Materia materiaFaltante = materiasFaltantes.get(0);
		assertEquals("Pb1", materiaFaltante.getNombre());
	}

	@Test
	public void queSePuedaCalcularPromedio() {
		Universidad uni = new Universidad("Universidad de Ejemplo");

		Alumno alumno1 = new Alumno(1, "Bauti", "Leon");
		Alumno alumno2 = new Alumno(2, "Leon", "Gomez");

		ArrayList<Nota> notas = uni.obetenerNota(1, 1);

		uni.registrar(alumno1);
		uni.registrar(alumno2);

	}

}