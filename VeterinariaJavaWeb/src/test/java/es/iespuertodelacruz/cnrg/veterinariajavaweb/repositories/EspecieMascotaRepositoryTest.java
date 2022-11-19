package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EspecieMascotaRepositoryTest {

	static EspecieMascotaRepository especieMascotaRepository;

	// ESPECIE_MASCOTA

	private static final String NombreEspecie1 = "especie1";
	private static final byte Peligrosa1 = (byte) 1;

	private static final String NombreEspecie2 = "especie2";
	private static final byte Peligrosa2 = (byte) 0;

	private static EspecieMascota especie1;
	private static EspecieMascota especie2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		especieMascotaRepository = new EspecieMascotaRepository(entityManagerFactory);

		// ESPECIE 1
		especie1 = new EspecieMascota();
		especie1.setNombre(NombreEspecie1);
		especie1.setPeligrosa(Peligrosa1);

		// ESPECIE 2
		especie2 = new EspecieMascota();
		especie2.setNombre(NombreEspecie2);
		especie2.setPeligrosa(Peligrosa2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (especieMascotaRepository.findById(especie1.getId()) != null) {
			especieMascotaRepository.delete(especie1.getId());
		}
		if (especieMascotaRepository.findById(especie2.getId()) != null) {
			especieMascotaRepository.delete(especie2.getId());
		}
	}

	@Test
	@Order(1)
	void testSave() {
		assertNotNull(especieMascotaRepository.save(especie1), "La especie se debería guardar");

		Exception ex = assertThrows(Exception.class, () -> {
			especieMascotaRepository.save(especie1);
		});

		assertNotNull(ex, "Si la especie ya existía debería lanzar Excepcion");
	}

	@Test
	@Order(2)
	void testFindById() {
        assertNotNull(especieMascotaRepository.findById(especie1.getId()), "La mascota no debería ser nula si existe");
	}

	@Test
	@Order(3)
	void testUpdate() {
		especie2.setId(especie1.getId());
		especieMascotaRepository.update(especie2);
		
		especie2 = especieMascotaRepository.findById(especie1.getId());
		assertNotEquals(especie1.getNombre(), especie2.getNombre(), "Los nombres deberian ser distintos");
		assertNotEquals(especie1.getPeligrosa(), especie2.getPeligrosa(), "Las especie deberian ser distintas" );
		
	}

	@Test
	@Order(5)
	void testDelete() {
		assertTrue(especieMascotaRepository.delete(especie1.getId()), "Si la mascota existe se debería borrar");
		assertFalse(especieMascotaRepository.delete(especie1.getId()), "Si la mascota no existe no se debería borrar");
	}

	@Test
	@Order(4)
	void testFindAll() {
		assertTrue(especieMascotaRepository.findAll().size() > 0, "El tamanio del array debe ser mayor que cero");
	}

}
