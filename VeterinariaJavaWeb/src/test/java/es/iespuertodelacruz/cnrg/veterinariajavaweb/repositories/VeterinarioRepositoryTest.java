package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.CuentaVeterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecialidadVeterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Veterinario;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VeterinarioRepositoryTest {
	
	static VeterinarioRepository veterinarioRepository;

	static Veterinario veterinario1;
	static Veterinario veterinario2;
	static CuentaVeterinario cuenta1;
	static CuentaVeterinario cuenta2;
	static EspecialidadVeterinario especialidad1;
	static EspecialidadVeterinario especialidad2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		veterinarioRepository = new VeterinarioRepository(entityManagerFactory);

		cuenta1 = new CuentaVeterinario();
		cuenta1.setCorreo("correo1@gmail.com");
		cuenta1.setContrasenia("1q2w3e4r");
		
		cuenta2 = new CuentaVeterinario();
		cuenta2.setCorreo("correo2@gmail.com");
		cuenta2.setContrasenia("1q2w3e4r");
		
		especialidad1 = new EspecialidadVeterinario();
		especialidad1.setNombre("ESPECIALIDAD1");
		
		especialidad2 = new EspecialidadVeterinario();
		especialidad2.setNombre("ESPECIALIDAD2");
		
		// TIPO 1
		veterinario1 = new Veterinario();
		veterinario1.setDni("12345678z");
		veterinario1.setNombre("nombre1");
		veterinario1.setApellidos("apellidos1");
		veterinario1.setTelefono("123456789");
		veterinario1.setCuentaVeterinario(cuenta1);
		veterinario1.setEspecialidadVeterinario(especialidad1);

		// TIPO 2
		veterinario2 = new Veterinario();
		veterinario2.setDni("87654321z");
		veterinario2.setNombre("nombre2");
		veterinario2.setApellidos("apellidos2");
		veterinario2.setTelefono("987654321");
		veterinario2.setCuentaVeterinario(cuenta2);
		veterinario2.setEspecialidadVeterinario(especialidad2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if (veterinarioRepository.findById(veterinario1.getDni()) != null) {
			veterinarioRepository.delete(veterinario1.getDni());
		}
		if (veterinarioRepository.findById(veterinario2.getDni()) != null) {
			veterinarioRepository.delete(veterinario2.getDni());
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
