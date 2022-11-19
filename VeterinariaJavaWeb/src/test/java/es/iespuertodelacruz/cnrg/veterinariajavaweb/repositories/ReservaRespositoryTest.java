package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Reserva;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservaRespositoryTest {

	static ReservaRepository reservaRepository;
	static TipoRestriccionDiaRepository tipoRestriccionDiaRepository;

	// Reserva

	private static final Timestamp Inicio1 = new Timestamp(new Date().getTime());
	private static final Timestamp Fin1 =  new Timestamp(new Date().getTime()+1);
	private static final int Intervencion1 = 1;

	private static final Timestamp Inicio2 = new Timestamp(new Date().getTime());
	private static final Timestamp Fin2 =  new Timestamp(new Date().getTime()+1);
	private static final int Intervencion2 = 1;


	private static Reserva reserva1;
	private static Reserva reserva2;
	
	//Restriccion
	
	private static final String Tipo1 = "festivo";
	private static final Time Apertura1 = new Time(new Date().getTime());
	private static final Time Cierre1 =  new Time(new Date().getTime()+10);
	private static final int Intervalo1 = 1;
	
	private static final String Tipo2 = "cotidiano";
	private static final Time Apertura2 = new Time(new Date().getTime());
	private static final Time Cierre2 =  new Time(new Date().getTime()+10);
	private static final int Intervalo2 = 2;


	private static TipoRestriccionDia tipo1;
	private static TipoRestriccionDia tipo2;



	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		reservaRepository = new ReservaRepository(entityManagerFactory);
		tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(entityManagerFactory);
		//RESTRICCION
		
		tipo1 = new TipoRestriccionDia();
		tipo1.setTipo(Tipo1);
		tipo1.setHoraApertura(Apertura1);
		tipo1.setHoraCierre(Cierre1);
		tipo1.setIntervaloTiempo(Intervalo1);
		tipoRestriccionDiaRepository.save(tipo1);
		
		tipo2 = new TipoRestriccionDia();
		tipo2.setTipo(Tipo2);
		tipo2.setHoraApertura(Apertura2);
		tipo2.setHoraCierre(Cierre2);
		tipo2.setIntervaloTiempo(Intervalo2);
		tipoRestriccionDiaRepository.save(tipo2);

		// Reserva 1
		reserva1 = new Reserva();
		reserva1.setFechaInicio(Inicio1);
		reserva1.setFechaFin(Fin1);
		reserva1.setIntervencion(null); //FALTA IMPLEMENTAR
		reserva1.setTipoRestriccionDia(tipo1);

		// Reserva 2
		reserva2 = new Reserva();
		reserva2.setFechaInicio(Inicio2);
		reserva2.setFechaFin(Fin2);
		reserva2.setIntervencion(null); //FALTA IMPLEMENTAR
		reserva2.setTipoRestriccionDia(tipo2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (reservaRepository.findById(reserva1.getId()) != null) {
			reservaRepository.delete(reserva1.getId());
		}
		if (reservaRepository.findById(reserva2.getId()) != null) {
			reservaRepository.delete(reserva2.getId());
		}
		if (tipoRestriccionDiaRepository.findById(tipo1.getTipo()) != null) {
			tipoRestriccionDiaRepository.delete(tipo1.getTipo());
		}
		if (tipoRestriccionDiaRepository.findById(tipo2.getTipo()) != null) {
			tipoRestriccionDiaRepository.delete(tipo2.getTipo());
		}
	}

	@Test
	@Order(1)
	void testSave() {
		assertNotNull(reservaRepository.save(reserva1), "La reserva se debería guardar");

		Exception ex = assertThrows(Exception.class, () -> {
			reservaRepository.save(reserva1);
		});

		assertNotNull(ex, "Si la especie ya existía debería lanzar Excepcion");
	}

	@Test
	@Order(2)
	void testFindById() {
        assertNotNull(reservaRepository.findById(reserva1.getId()), "La reserva no debería ser nula si existe");
	}

	@Test
	@Order(3)
	void testUpdate() {
		reserva2.setId(reserva1.getId());
		reservaRepository.update(reserva2);
		
		reserva2 = reservaRepository.findById(reserva1.getId());
		assertNotEquals(reserva1.getFechaFin(), reserva2.getFechaFin(), "Las fechas de fin deberian ser distintas");
		assertNotEquals(reserva1.getFechaInicio(), reserva2.getFechaInicio(), "Los fechas de inicio deberian ser distintas");
//		assertNotEquals(reserva1.getIntervencion(), reserva2.getIntervencion(), "Los intervalos deberian ser distintos"); FALTA IMPLEMENTAR
		assertNotEquals(reserva1.getTipoRestriccionDia(), reserva2.getTipoRestriccionDia(), "Los tipos de Restriccion deberian ser distintos");
		
	}

	@Test
	@Order(5)
	void testDelete() {
		assertTrue(reservaRepository.delete(reserva1.getId()), "Si ninguna reserva lo tiene asigado se debería borrar");
		assertFalse(reservaRepository.delete(reserva1.getId()), "Si alguna reserva lo tiene asigado no se debería borrar");
	}

	@Test
	@Order(4)
	void testFindAll() {
		assertTrue(reservaRepository.findAll().size() > 0, "El tamanio del array debe ser mayor que cero");
	}

}
