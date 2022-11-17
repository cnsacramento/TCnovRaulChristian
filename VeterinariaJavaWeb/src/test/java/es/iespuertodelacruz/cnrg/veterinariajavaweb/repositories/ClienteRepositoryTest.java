package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

class ClienteRepositoryTest {

    static ClienteRepository clienteRepository;
    static final String DNI = "123456789";
    static final String NOMBRE = "nombre";
    static final String APELLIDOS = "apellido1 apellido2";
    static final String DIRECCION = "C/Mi direccion";
    static final String CORREO = "micorreo@gmail.com";
    static final String TELEFONO = "xxx-xx-xx-xx";

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        EntityManagerFactory entityManagerFactory =
                             EntityManagerFactorySingleton.getInstance().getEmf();
        clienteRepository = new ClienteRepository(entityManagerFactory);
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @Test
    void testClienteRepository() {
        fail("Not yet implemented");
    }

    @Test
    @Order(1)
    void testSave() {
        Cliente cliente = new Cliente();
        cliente.setDni(DNI);
        cliente.setNombre(NOMBRE);
        cliente.setApellidos(APELLIDOS);
        cliente.setDireccion(DIRECCION);
        cliente.setCorreo(CORREO);
        cliente.setTelefono(TELEFONO);

        assertNotNull(clienteRepository.save(cliente), "El cliente no ha sido insertado correctamente");
    }

    @Test
    @Order(2)
    void testFindById() {
        assertNotNull(clienteRepository.findById(DNI), "El cliente no debería ser nulo si existe");
    }

    @Test
    @Order(3)
    void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    @Order(5)
    void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    @Order(4)
    void testFindAll() {
        assertNotNull(clienteRepository.findAll(), "Si existen clientes no debería dar nulo");
    }

}
