package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

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
	static EspecieMascotaRepository especieMascotaRepository;
	private final int id = 1;
	private final String nombre = "name";
	private final String fecha_nacimiento = "01-01-2022";
	private final Double peso = 12.2;
	private final String dni_cliente = "43383649z" ;
	private final int id_especie = 1;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		mascotaRepository = new MascotaRepository(entityManagerFactory);
        clienteRepository = new ClienteRepository(entityManagerFactory);
        especieMascotaRepository = new EspecieMascotaRepository(entityManagerFactory);
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
        clienteRepository.save(cliente);

        
        //especieMascotas
		especieMascota.setNombre("nombre");
        especieMascota.setPeligrosa((byte)1);
        especieMascotaRepository.save(especieMascota);
        
		//mascotas
		mascota.setCliente(cliente);
		mascota.setEspecieMascota(especieMascota);
		mascota.setFechaNacimiento(new Timestamp(new Date().getTime()));
		mascota.setNombre(nombre);
		System.out.println(peso);
		mascota.setPeso(peso);
        assertNotNull(mascotaRepository.save(mascota), "La mascota se debería guardar");

	}

	@Test
    @Order(2)
	void testFindById() {
        assertNotNull(mascotaRepository.findById(id), "La mascota no debería ser nula si existe");
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
