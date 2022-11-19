package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Factura;

class FacturaRepositoryTest {
	
	private static FacturaRepository facturaRepository;

	private static Factura factura1;
	private static Factura factura2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		facturaRepository = new FacturaRepository(entityManagerFactory);
		
		//FACTURA 1
		factura1 = new Factura();
		factura1.setFecha(new Timestamp(new Date().getTime()));
		factura1.setDetalles("Este es el detalle de la factura");
		//FACTURA 2
		factura2 = new Factura();
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
