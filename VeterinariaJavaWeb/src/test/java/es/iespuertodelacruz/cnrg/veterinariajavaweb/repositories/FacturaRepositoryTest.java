package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Factura;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
		factura1.setDetalles("Este es el detalle de la factura1");
		factura1.setCoste(54.99);
		//FACTURA 2
		factura2 = new Factura();
		factura2.setFecha(new Timestamp(new Date().getTime()+100));
		factura2.setDetalles("Este es el otro detalle de la factura2");
		factura2.setCoste(23.99);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (facturaRepository.findById(factura1.getId()) != null) {
			facturaRepository.delete(factura1.getId());
		}
	}

	@Test
	@Order(1)
	void testSave() {
		assertNotNull(facturaRepository.save(factura1), "La factura se debería guardar");

		Exception ex = assertThrows(Exception.class, () -> {
			facturaRepository.save(factura1);
		});

		assertNotNull(ex, "Si la factura ya existía debería lanzar Excepcion");
	}

	@Test
	@Order(2)
	void testFindById() {
        assertNotNull(facturaRepository.findById(factura1.getId()), "La factura no debería ser nula si existe");
	}

	@Test
	@Order(3)
	void testUpdate() {
		
		assertFalse(facturaRepository.update(factura2), "El objeto no existe y por ello no lo debe guardar");
		factura2.setId(factura1.getId());
		assertTrue(facturaRepository.update(factura2), "El objeto existe y por ello lo debe guardar");		
		factura2 = facturaRepository.findById(factura1.getId());
		assertNotEquals(factura1.getCoste(), factura2.getCoste(), "Los costes deberian ser distintos");
		assertNotEquals(factura1.getDetalles(), factura2.getDetalles(), "Los detalles deberian ser distintas" );
		assertNotEquals(factura1.getFecha(), factura2.getFecha(), "Las fechas deberian ser distintas" );
	
	}

	@Test
	@Order(5)
	void testDelete() {
		assertTrue(facturaRepository.delete(factura1.getId()), "Si la mascota existe se debería borrar");
		assertFalse(facturaRepository.delete(factura1.getId()), "Si la mascota no existe no se debería borrar");
	}

	@Test
	@Order(4)
	void testFindAll() {
		assertTrue(facturaRepository.findAll().size() > 0, "El tamanio del array debe ser mayor que cero");
	}

}
