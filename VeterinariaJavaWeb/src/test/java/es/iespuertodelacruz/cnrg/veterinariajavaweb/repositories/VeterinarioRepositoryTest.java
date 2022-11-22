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
	static CuentaVeterinarioRepository cuentaVeterinarioRepository;
	static EspecialidadVeterinarioRepository especialidadVeterinarioRepository;
	
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
		cuentaVeterinarioRepository = new CuentaVeterinarioRepository(entityManagerFactory);
		especialidadVeterinarioRepository = new EspecialidadVeterinarioRepository(entityManagerFactory);
		
		
		cuenta1 = new CuentaVeterinario();
		cuenta1.setCorreo("correo1@gmail.com");
		cuenta1.setContrasenia("1q2w3e4r");
		cuentaVeterinarioRepository.save(cuenta1);
		
		cuenta2 = new CuentaVeterinario();
		cuenta2.setCorreo("correo2@gmail.com");
		cuenta2.setContrasenia("1q2w3e4r");
		cuentaVeterinarioRepository.save(cuenta2);
		
		especialidad1 = new EspecialidadVeterinario();
		especialidad1.setNombre("ESPECIALIDAD1");
		especialidadVeterinarioRepository.save(especialidad1);
		
		especialidad2 = new EspecialidadVeterinario();
		especialidad2.setNombre("ESPECIALIDAD2");
		especialidadVeterinarioRepository.save(especialidad2);
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
		assertNotNull(veterinarioRepository.save(veterinario1), "El veterinario se debería guardar");
	}

	@Test
	@Order(2)
	void testFindById() {
        assertNotNull(veterinarioRepository.findById(veterinario1.getDni()), "El veterinario no debería ser nulo si existe");
	}

	@Test
	@Order(3)
	void testUpdate() {
		veterinario2.setDni(veterinario1.getDni());
		veterinarioRepository.update(veterinario2);
		
		veterinario2 = veterinarioRepository.findById(veterinario1.getDni());
		assertNotEquals(veterinario1.getNombre(), veterinario2.getNombre(), "Los nombres deberian ser distintas");
		assertNotEquals(veterinario1.getApellidos(), veterinario2.getApellidos(), "Los apellidos deberian ser distintas");
		assertNotEquals(veterinario1.getTelefono(), veterinario2.getTelefono(), "Los telefonos deberian ser distintos");
		assertNotEquals(veterinario1.getCuentaVeterinario(), veterinario2.getCuentaVeterinario(), "Las cuentas deberian ser distintos");
		assertNotEquals(veterinario1.getEspecialidadVeterinario(), veterinario2.getEspecialidadVeterinario(), "Las espcialidades deberian ser distintos");

		
	}

	@Test
	@Order(5)
	void testDelete() {
		assertTrue(veterinarioRepository.delete(veterinario1.getDni()), "Si ningun equipoDeIntervencion lo tiene asigado se debería borrar");
		assertFalse(veterinarioRepository.delete(veterinario1.getDni()), "Si algun equipoDeIntervencion lo tiene asigado no se debería borrar y si no existe no se debería borrar");
	}

	@Test
	@Order(4)
	void testFindAll() {
		assertTrue(veterinarioRepository.findAll().size() > 0, "El tamanio del array debe ser mayor que cero");
	}

}
