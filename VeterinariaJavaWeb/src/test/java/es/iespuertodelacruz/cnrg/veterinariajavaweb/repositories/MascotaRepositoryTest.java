package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MascotaRepositoryTest {
	
	static MascotaRepository mascotaRepository;
	static ClienteRepository clienteRepository;
	static EspecieMascotaRepository especieMascotaRepository;
	
	
	//CLIENTE
	private static final String DNI1 = "12345678Z";
	private static final String NombreCliente1 = "cliente1";
	private static final String Apellidos1 = "ape1 ape2";
	private static final String Direccion1 = "Direccion1";
	private static final String Correo1 = "correo1@gmail.com";
	private static final String Telefono1 = "123456789";
	

	private static final String DNI2 = "12345678Z";
	private static final String NombreCliente2 = "cliente1";
	private static final String Apellidos2 = "ape1 ape2";
	private static final String Direccion2 = "Direccion1";
	private static final String Correo2 = "correo1@gmail.com";
	private static final String Telefono2 = "123456789";
	//ESPECIE_MASCOTA
	
	private static final String NombreEspecie1 = "especie1";
	private static final byte Peligrosa1 = (byte)1;
	
	private static final String NombreEspecie2 = "especie2";
	private static final byte Peligrosa2 = (byte)0;
	
	//MASCOTA 1
	private static final String Nombre1 = "nombre";
	private static final Double Peso1 = 12.23;
	
	//MASCOTA 2
	private static final String Nombre2 = "nombre";
	private static final Double Peso2 = 15.22;
	
	//OBJETOS
	
	private static Cliente cliente1;
	private static Cliente cliente2;
	private static EspecieMascota especie1;
	private static EspecieMascota especie2;
	private static Mascota mascota1;
	private static Mascota mascota2;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
		mascotaRepository = new MascotaRepository(entityManagerFactory);
        clienteRepository = new ClienteRepository(entityManagerFactory);
        especieMascotaRepository = new EspecieMascotaRepository(entityManagerFactory);
        //CLIENTE 1
        cliente1 = new Cliente();
        cliente1.setDni(DNI1);
        cliente1.setNombre(NombreCliente1);
        cliente1.setApellidos(Apellidos1);
        cliente1.setDireccion(Direccion1);
        cliente1.setCorreo(Correo1);
        cliente1.setTelefono(Telefono1);
        clienteRepository.save(cliente1);
        
        //CLIENTE 2
        cliente2 = new Cliente();
        cliente2.setDni(DNI2);
        cliente2.setNombre(NombreCliente2);
        cliente2.setApellidos(Apellidos2);
        cliente2.setDireccion(Direccion2);
        cliente2.setCorreo(Correo2);
        cliente2.setTelefono(Telefono2);
        clienteRepository.save(cliente2);
        
        //ESPECIE 1
        especie1 = new EspecieMascota();
        especie1.setNombre(NombreEspecie1);
        especie1.setPeligrosa(Peligrosa1);
        especieMascotaRepository.save(especie1);

        //ESPECIE 2
        especie2 = new EspecieMascota();
        especie2.setNombre(NombreEspecie2);
        especie2.setPeligrosa(Peligrosa2);
        especieMascotaRepository.save(especie2);
        
        //MASCOTA 1
        mascota1 = new Mascota();
        mascota1.setCliente(cliente1);
		mascota1.setEspecieMascota(especie1);
		mascota1.setFechaNacimiento(new Timestamp(new Date().getTime()));
		mascota1.setNombre(Nombre1);
		mascota1.setPeso(Peso1);
		
        //MASCOTA 2
		mascota2 = new Mascota();
		mascota2.setCliente(cliente2);
		mascota2.setEspecieMascota(especie2);
		mascota2.setFechaNacimiento(new Timestamp(new Date().getTime()));
		mascota2.setNombre(Nombre2);
		mascota2.setPeso(Peso2);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
    @Order(1)
	void testSave() {
        assertNotNull(mascotaRepository.save(mascota1), "La mascota se debería guardar");
	}

	@Test
    @Order(2)
	void testFindById() {
        assertNotNull(mascotaRepository.findById(mascota1.getId()), "La mascota no debería ser nula si existe");
	}

	@Test
    @Order(3)
	void testUpdate() {
		
	}

	@Test
    @Order(4)
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	@Order(5)
	void testFindAll() {
		fail("Not yet implemented");
	}

}
