package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;

class TipoRestriccionDiaTest {
	
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

		// ESPECIE 1
		tipo1 = new TipoRestriccionDia();
		tipo1.setTipo(Tipo1);
		tipo1.setHoraApertura(Apertura1);
		tipo1.setHoraCierre(Cierre1);
		tipo1.setIntervaloTiempo(Intervalo1);

		// ESPECIE 2
		tipo2 = new TipoRestriccionDia();
		tipo2.setTipo(Tipo2);
		tipo2.setHoraApertura(Apertura2);
		tipo2.setHoraCierre(Cierre2);
		tipo2.setIntervaloTiempo(Intervalo2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

}
