package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteRepositoryTest {

    private static ClienteRepository clienteRepository;
    private Cliente cliente;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        EntityManagerFactory entityManagerFactory =
                             EntityManagerFactorySingleton.getInstance().getEmf();
        clienteRepository = new ClienteRepository(entityManagerFactory);
    }

    @BeforeEach
    void setUpBeforeEach() {
        cliente = new Cliente();
        cliente.setDni("111111111");
        cliente.setNombre("cliente1");
        cliente.setApellidos("apellido1 apellido1");
        cliente.setDireccion("Cliente 1 direccion");
        cliente.setCorreo("primercliente@gmail.com");
        cliente.setTelefono("111-11-11-11");
    }

    @AfterEach
    void setUpAfterAll() {
        clienteRepository.delete(cliente.getDni());
    }

    @Test
    void testSave() {

        assertNotNull(clienteRepository.save(cliente),
                "El cliente no ha sido insertado correctamente");

        Cliente clienteGuardado;
        assertNotNull(clienteGuardado = clienteRepository.findById(cliente.getDni()),
                "El cliente debería estar guardado");

        assertEquals(cliente.getDni(), clienteGuardado.getDni(),
                "El cliente debería tener el mismo DNI si se ha guardado");
        assertEquals(cliente.getNombre(), clienteGuardado.getNombre(),
                "El cliente debería tener el mismo NOMBRE si se ha guardado");
        assertEquals(cliente.getApellidos(), clienteGuardado.getApellidos(),
                "El cliente debería tener los mismos APELLIDOS si se ha guardado");
        assertEquals(cliente.getDireccion(), clienteGuardado.getDireccion(),
                "El cliente debería tener la misma DIRECCION si se ha guardado");
        assertEquals(cliente.getTelefono(), clienteGuardado.getTelefono(),
                "El cliente debería tener el mismo TELEFONO si se ha guardado");

    }

    @Test
    void testSaveNuevoClienteConDNIexistenteDevuelveNulo() {

        assertNotNull(clienteRepository.save(cliente),
                "El cliente no ha sido insertado correctamente");

        Cliente nuevoClienteConDNIexistente = new Cliente();
        nuevoClienteConDNIexistente.setDni(cliente.getDni());
        nuevoClienteConDNIexistente.setNombre("Cliente2Retido");
        nuevoClienteConDNIexistente.setApellidos("Apellido2repetido Apellido2repetido");
        nuevoClienteConDNIexistente.setDireccion("Cliente 2 direccion repetida");
        nuevoClienteConDNIexistente.setCorreo("cliente2repetido@gmail.com");
        nuevoClienteConDNIexistente.setTelefono("222-22-22-22");

        assertNull(clienteRepository.save(nuevoClienteConDNIexistente));
    }
    
    @Test
    void testSaveClienteConDniNuloDevuelveNulo() {

        cliente.setDni(null);
        assertNull(clienteRepository.save(cliente),
                "Un cliente con DNI nulo no se debería guardar");
    }

    @Test
    void testFindById() {

        assertNotNull(clienteRepository.save(cliente),
                "El cliente no se ha guardado en DDBB");
        assertNotNull(clienteRepository.findById(cliente.getDni()),
                "El cliente no debería ser nulo si existe");
    }

    @Test
    void testUpdate() {

        assertNotNull(clienteRepository.save(cliente),
                "El cliente no se ha podido guardar");

        Cliente clienteParaActualizar = clienteRepository.findById(cliente.getDni());
        clienteParaActualizar.setNombre("cliente5Nuevo");
        clienteParaActualizar.setApellidos("apellido5Nuevo apellido5Nuevo");
        clienteParaActualizar.setDireccion("Cliente 5 nueva direccion");
        clienteParaActualizar.setCorreo("quintoclienteNuevo@gmail.com");
        clienteParaActualizar.setTelefono("555-55-66-55");

        assertTrue(clienteRepository.update(clienteParaActualizar),
                "Si el cliente existe debería haberse modificado");

        Cliente clienteActualizado = clienteRepository.findById(clienteParaActualizar.getDni());
        assertNotEquals(cliente.getNombre(), clienteActualizado.getNombre(),
                "El cliente debería tener distinto NOMBRE si se ha guardado");
        assertNotEquals(cliente.getApellidos(), clienteActualizado.getApellidos(),
                "El cliente debería tener distintos APELLIDOS si se ha guardado");
        assertNotEquals(cliente.getDireccion(), clienteActualizado.getDireccion(),
                "El cliente debería tener distinta DIRECCION si se ha guardado");
        assertNotEquals(cliente.getTelefono(), clienteActualizado.getTelefono(),
                "El cliente debería tener distinto TELEFONO si se ha guardado");

    }

    @Test
    void testFindAll() {
        assertNotNull(clienteRepository.save(cliente),
                "El cliente no se ha podido guardar");
        assertNotNull(clienteRepository.findAll(), "Si existen clientes no debería dar nulo");
    }

    @Test
    void testDelete() {
        assertNotNull(clienteRepository.save(cliente),
                "El cliente no se ha podido guardar");
        assertTrue(clienteRepository.delete(cliente.getDni()),
                "Si el cliente existe debería y no tiene mascotas debería poder borrarse");
    }

}
