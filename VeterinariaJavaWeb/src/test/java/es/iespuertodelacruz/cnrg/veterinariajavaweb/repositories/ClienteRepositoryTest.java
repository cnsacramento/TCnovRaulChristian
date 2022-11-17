package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    void testSave() {
        fail("Not yet implemented");
    }

    @Test
    void testFindById() {
        assertNotNull(clienteRepository.findById(DNI), "El cliente no debería ser nulo si existe");
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
