package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.CuentaVeterinario;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;

class CuentaVeterinarioRepositoryTest {

    private static CuentaVeterinarioRepository cuentaVeterinarioRepository;
    private static CuentaVeterinario cuentaVeterinario;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        EntityManagerFactory entityManagerFactory =
                EntityManagerFactorySingleton.getInstance().getEmf();
        cuentaVeterinarioRepository = new CuentaVeterinarioRepository(entityManagerFactory);


    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUpBeforeEach() {
        cuentaVeterinario = new CuentaVeterinario();
        cuentaVeterinario.setCorreo("CORREO");
        cuentaVeterinario.setContrasenia("PASSWORD");
    }

    @AfterEach
    void setUpAfterAll() {
        cuentaVeterinarioRepository.delete(cuentaVeterinario.getCorreo());
    }

    @Test
    void testSave() {

        assertNotNull(cuentaVeterinarioRepository.save(cuentaVeterinario),
                "Si se ha guardado correctamente la cuenta no debería dar null");

        CuentaVeterinario cuentaVeterinarioGuardada =
                cuentaVeterinarioRepository.findById(cuentaVeterinario.getCorreo());
        assertNotNull(cuentaVeterinarioRepository);

        assertEquals(cuentaVeterinario.getCorreo(), cuentaVeterinarioGuardada.getCorreo(),
                "Si se ha guardado correctamente los correos deberían ser los mismos");
        assertEquals(cuentaVeterinario.getContrasenia(), cuentaVeterinarioGuardada.getContrasenia(),
                "Si se ha guardado la cuenta correctamente deberían ser las mismas contraseñas");
    }

    @Test
    void testFindById() {

        CuentaVeterinario cuentaVeterinarioGuardada = cuentaVeterinarioRepository.save(cuentaVeterinario);
        assertNotNull(cuentaVeterinarioGuardada,
                "Si se ha guardado correctamente la cuenta no debería dar null");

        assertNotNull(cuentaVeterinarioRepository.findById(cuentaVeterinarioGuardada.getCorreo()),
                "Si el cuenta esta guardada no debería devolver null");

    }

    @Test
    void testFindByIdNoExistenteDevuelveNulo() {
        assertNull(cuentaVeterinarioRepository.findById(""),
                "Si el correo no esta guardado no debería devolver distinto de nulo");
    }

    @Test
    void testUpdateCuentaVeterinario() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdateStringCuentaVeterinario() {
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
