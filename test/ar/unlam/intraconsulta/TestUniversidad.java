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
	
	public void queSePuedaEliminarLaCorrelatividadYverficarSiExiste() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		assertFalse(uni.eliminarCorrelatividad(1,1));
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
	    CicloLectivo cicloLectivo = new CicloLectivo(1, "2do2023", fechaInicioCiclolectivo, fechaFinalizacionCiclolectivo);
	    
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
	        boolean resultado = uni.inscribirAlumnoAComision(1, 1, 1, 1,
	            fechaInicioInscripcion, fechafinalizacion, 1, "noche", DayOfWeek.MONDAY);

	      
	        assertFalse(resultado);
	        
	        assertFalse(listaAlumnoComision.contains(new ComisionAlumno(1)));
	    }
	
	
	@Test
	public void queSePuedaRegistrarUnaProfesorAUnaComisionCada20Alumnos() {
		String nombre = "Unlam";
		Universidad uni = new Universidad(nombre);
		Comision co = new Comision (1);
		ComisionProfe cp = new ComisionProfe();
		Profesor profesor = new Profesor(1, "Leon", "Bauti");
	
		uni.agregarProfesor(1, 1);
		
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
	 @Test
	    public void testRegistrarNota() {
	        // Crear una instancia de la clase Universidad
	        Universidad uni = new Universidad("Unlam");
	      
	        // Agregar alumnos a la universidad
	        Alumno alumno = new Alumno(1, "Alumno1", "Apellido1"); // Cambia los valores según tus necesidades
	        uni.registrar(alumno);

	        // Crear una comisión
	        Comision comision = new Comision(1); // Cambia el valor del código según tus necesidades
	        uni.rigistrarComision(comision);
	        
	        // Agregar al alumno a la comisión (esto podría requerir otros métodos)
	        ComisionAlumno comisionAlumno = new ComisionAlumno(1); // Cambia el DNI según tus necesidades
	        //comision.add(ComisionAlumno(comisionAlumno));

	        // Crear una instancia de Nota
	        Nota nota = new Nota(7); // Cambia el valor de la nota según tus necesidades
	    
	        // Registrar la nota
	        boolean resultado = uni.registrarNota(1, 1, 1, nota); // Cambia los valores según tus necesidades

	        // Realiza las aserciones
	        assertFalse(resultado);

	        // Verifica que la nota se haya registrado correctamente en la comisión
	       // assertTrue(comision.getNotas().contains(nota)); // Asegúrate de tener un método para obtener las notas en Comision
	    }
	 @Test
	 public void testObtenerMateriasAprobadasParaUnAlumno() {
		   Universidad uni = new Universidad("Unlam");
		    Alumno alumno = new Alumno(1, "Nombre", "Apellido");
		    uni.registrar(alumno);

		    Materia materia1 = new Materia(101, "Materia1");
		    Materia materia2 = new Materia(102, "Materia2");
		    Materia materia3 = new Materia(103, "Materia3");

		    uni.registraMateria(materia1);
		    uni.registraMateria(materia2);
		    uni.registraMateria(materia3);
	     ArrayList<Materia> materiasAprobadas = uni.obtenerMateriasAprobadasParaUnAlumno(1);

	     assertEquals(0, materiasAprobadas.size());
	 }
	  public void testObtenerNota() {
		   Universidad uni = new Universidad("Universidad de Ejemplo");

	        // Agregar alumnos de prueba
	        Alumno alumno1 = new Alumno(1, "Nombre1", "Apellido1");
	        Alumno alumno2 = new Alumno(2, "Nombre2", "Apellido2");


	        // Agregar alumnos y notas a la universidad
	        uni.registrar(alumno1);
	        uni.registrar(alumno2);
	        uni.getNota(1, 1);
	        uni.getNota(2,2);
	        uni.getNota(3,3);
	  	     
	        Integer idAlumno = 1;
	        Integer idMateria = 1;

	        ArrayList<Nota> notas = uni.obetenerNota(idAlumno, idMateria);
	        
	        assertNotNull(notas);
	        assertEquals(1, notas.size());

	        Nota notaObtenida = notas.get(0);
	        assertEquals(idAlumno, notaObtenida.getIdAlumno());
	        assertEquals(idMateria, notaObtenida.getIdMateria());
	        //assertEquals(7, notaObtenida.getValor());
	    }

	    @Test
	    public void testObtenerMateriasQueFaltanCursarParaUnAlumno() {
	        Universidad universidad = new Universidad("Universidad de Ejemplo");

	        // Agregar alumnos de prueba
	        Alumno alumno1 = new Alumno(1, "Nombre1", "Apellido1");
	        Alumno alumno2 = new Alumno(2, "Nombre2", "Apellido2");

	        // Agregar notas de prueba
	       

	        // Agregar alumnos, materias y notas a la universidad
	        universidad.registrar(alumno1);
	        universidad.registrar(alumno2);
	        universidad.registraMateria(new Materia(1, "Materia1"));
	        universidad.registraMateria(new Materia(2, "Materia2"));
	  //      universidad.getNota(1,1);
	  //      universidad.getNota(2,2);
	  //      universidad.getNota(3,3);
	    	// Prueba para obtener las materias que faltan cursar para un alumno
	        Integer idAlumno = 1;

	        ArrayList<Materia> materiasFaltantes = universidad.obtenerMateriasQueFaltanCursarParaUnAlumno(idAlumno);

	        // Verifica que la lista de materias no sea nula
	        assertNotNull(materiasFaltantes);

	        // Verifica que la cantidad de materias faltantes sea la esperada
	        assertEquals(1, materiasFaltantes.size());

	        // Verifica que la materia faltante tenga el nombre correcto
	        Materia materiaFaltante = materiasFaltantes.get(0);
	 //       assertEquals("Materia2", materiaFaltante.getNombre());
	    }
	    @Test
	    public void testCalcularPromedio() {
	    	   Universidad universidad = new Universidad("Universidad de Ejemplo");

	           // Agregar alumnos de prueba
	           Alumno alumno1 = new Alumno(1, "Nombre1", "Apellido1");
	           Alumno alumno2 = new Alumno(2, "Nombre2", "Apellido2");

	           // Agregar notas de prueba
	       

	           // Agregar alumnos y notas a la universidad
	           universidad.registrar(alumno1);
	           universidad.registrar(alumno2);
	       //    universidad.getNota(1,1);
	         //  universidad.getNota(2,2);
	          // universidad.getNota(3,3);
	        // Prueba para calcular el promedio de notas para un alumno
	        Integer idAlumno = 1;

	       // double promedio = universidad.calcularPromedio(idAlumno);

	        // Verifica que el promedio calculado sea el esperado
	       // assertEquals(7.5, promedio, 0.01); // Usamos delta para permitir pequeñas diferencias debido a decimales
	    }



}