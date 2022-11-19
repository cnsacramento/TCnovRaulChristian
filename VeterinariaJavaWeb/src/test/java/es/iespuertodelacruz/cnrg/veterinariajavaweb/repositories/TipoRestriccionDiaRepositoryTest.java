package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TipoRestriccionDiaRepositoryTest {
	
	static TipoRestriccionDiaRepository tipoRestriccionDiaRepository;

	// ESPECIE_MASCOTA

	
	private static final String Tipo1 = "especie1";
	private static final Time Apertura1 = new Time(new Date().getTime());
	private static final Time Cierre1 =  new Time(new Date().getTime()+10);
	private static final int Intervalo1 = 1;

	private static final String Tipo2 = "especie2";
	private static final Time Apertura2 = new Time(new Date().getTime());
	private static final Time Cierre2 =  new Time(new Date().getTime()+10);
	private static final int Intervalo2 = 2;


	private static TipoRestriccionDia tipo1;
	private static TipoRestriccionDia tipo2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(entityManagerFactory);

		// TIPO 1
		tipo1 = new TipoRestriccionDia();
		tipo1.setTipo(Tipo1);
		tipo1.setHoraApertura(Apertura1);
		tipo1.setHoraCierre(Cierre1);
		tipo1.setIntervaloTiempo(Intervalo1);

		// TIPO 2
		tipo2 = new TipoRestriccionDia();
		tipo2.setTipo(Tipo2);
		tipo2.setHoraApertura(Apertura2);
		tipo2.setHoraCierre(Cierre2);
		tipo2.setIntervaloTiempo(Intervalo2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
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
		assertNotNull(tipoRestriccionDiaRepository.save(tipo1), "La especie se debería guardar");

		Exception ex = assertThrows(Exception.class, () -> {
			tipoRestriccionDiaRepository.save(tipo1);
		});

		assertNotNull(ex, "Si la especie ya existía debería lanzar Excepcion");
	}

	@Test
	@Order(2)
	void testFindById() {
        assertNotNull(tipoRestriccionDiaRepository.findById(tipo1.getTipo()), "La mascota no debería ser nula si existe");
	}

	@Test
	@Order(3)
	void testUpdate() {
		tipo2.setTipo(tipo1.getTipo());
		tipoRestriccionDiaRepository.update(tipo2);
		
		tipo2 = tipoRestriccionDiaRepository.findById(tipo1.getTipo());
		System.out.println(tipo1.getTipo()+" "+ tipo2.getTipo());
		assertNotEquals(tipo1.getHoraApertura(), tipo2.getHoraApertura(), "Las horas de apertura deberian ser distintas");
		assertNotEquals(tipo1.getHoraCierre(), tipo2.getHoraCierre(), "Los horas de cierre deberian ser distintas");
		assertNotEquals(tipo1.getIntervaloTiempo(), tipo2.getIntervaloTiempo(), "Los intervalos deberian ser distintos");
		
	}

	@Test
	@Order(5)
	void testDelete() {
		assertTrue(tipoRestriccionDiaRepository.delete(tipo1.getTipo()), "Si ningun dia lo tiene asigado se debería borrar");
		assertFalse(tipoRestriccionDiaRepository.delete(tipo1.getTipo()), "Si algun dia lo tiene asigado no se debería borrar");
	}

	@Test
	@Order(4)
	void testFindAll() {
		assertTrue(tipoRestriccionDiaRepository.findAll().size() > 0, "El tamanio del array debe ser mayor que cero");
	}

}
