package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;

class MascotaRepositoryTest {
	
	static MascotaRepository mascotaRepository;
	static ClienteRepository clienteRepository;
	static EspeciMascotaRespository EspecieMascotaRepository;
	static final int id = 1;
	static final String nombre = "name";
	static final String fecha_nacimiento = "01-01-2022";
	static final int peso = 12;
	static final String dni_cliente = "43383649z" ;
	static final int id_especie = 1;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		mascotaRepository = new MascotaRepository(entityManagerFactory);
        clienteRepository = new ClienteRepository(entityManagerFactory);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
    @Order(1)
	void testSave() {
		
		Cliente cliente = new Cliente();
		Mascota mascota = new Mascota();
        EspecieMascota especieMascota = new EspecieMascota();

        //cliente
		cliente.setDni(dni_cliente);
        cliente.setNombre("nombre");
        cliente.setApellidos("apellidos");
        cliente.setDireccion("direccion");
        cliente.setCorreo("correo");
        cliente.setTelefono("telefono");
        
        
        //especieMascotas
		especieMascota.setNombre("nombre");
        especieMascota.setPeligrosa((byte)1); 

		//mascotas
		mascota.setCliente(cliente);
		mascota.setEspecieMascota(especieMascota);
		mascota.setFechaNacimiento(null);
		fail("Not yet implemented");
	}

	@Test
    @Order(2)
	void testFindById() {
        assertNotNull(mascotaRepository.findById(id), "El cliente no debería ser nulo si existe");
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
