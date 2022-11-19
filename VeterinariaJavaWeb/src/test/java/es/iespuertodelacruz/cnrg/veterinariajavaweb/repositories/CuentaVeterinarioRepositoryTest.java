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

    @BeforeEach
    void setUpBeforeEach() {
        cuentaVeterinario = new CuentaVeterinario();
        cuentaVeterinario.setCorreo("CORREO");
        cuentaVeterinario.setContrasenia("PASSWORD");
    }

    @AfterEach
    void setUpAfterEach() {
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

        assertNotNull(cuentaVeterinarioRepository.save(cuentaVeterinario),
                "Si se ha guardado correctamente la cuenta no debería dar null");

        String contraseniaAntigua = cuentaVeterinario.getContrasenia();
        cuentaVeterinario.setContrasenia("NUEVA");

        assertTrue(cuentaVeterinarioRepository.update(cuentaVeterinario));

        CuentaVeterinario cuentaVeterinarioActualizada =
                cuentaVeterinarioRepository.findById(cuentaVeterinario.getCorreo());

        assertNotEquals(contraseniaAntigua,
                cuentaVeterinarioActualizada.getContrasenia());

    }

    @Test
    void testUpdateStringCuentaVeterinario() {

     /*   assertNotNull(cuentaVeterinarioRepository.save(cuentaVeterinario),
                "Si se ha guardado correctamente la cuenta no debería dar null");

        String correoAntiguo = cuentaVeterinario.getCorreo();
        cuentaVeterinario.setCorreo("NUEVO_CORREO");

        String contraseniaAntigua = cuentaVeterinario.getContrasenia();
        cuentaVeterinario.setContrasenia("NUEVA");

        assertTrue(cuentaVeterinarioRepository.update(correoAntiguo, cuentaVeterinario));

        CuentaVeterinario cuentaVeterinarioActualizada =
                cuentaVeterinarioRepository.findById(cuentaVeterinario.getCorreo());

        assertNotEquals(correoAntiguo,
                cuentaVeterinarioActualizada.getCorreo());

        assertNotEquals(contraseniaAntigua,
                cuentaVeterinarioActualizada.getContrasenia());

*/
    }

    @Test
    void testDelete() {
        CuentaVeterinario cuentaVeterinarioGuardada = cuentaVeterinarioRepository.save(cuentaVeterinario);
        assertNotNull(cuentaVeterinarioGuardada,
                "Si se ha guardado correctamente la cuenta no debería dar null");

        assertTrue(cuentaVeterinarioRepository.delete(cuentaVeterinarioGuardada.getCorreo()),
                "Si el correo esta guardado debería devolver TRUE");
    }

    @Test
    void testDeleteSiNoExisteDeberiaDevolverFalse() {
        assertFalse(cuentaVeterinarioRepository.delete(""),
                "Si no esta guardado en DDBB no debería devolver true");
    }

    @Test
    void testFindAll() {

        CuentaVeterinario cuentaVeterinarioGuardada = cuentaVeterinarioRepository.save(cuentaVeterinario);
        assertNotNull(cuentaVeterinarioGuardada,
                "Si se ha guardado correctamente la cuenta no debería dar null");

        assertNotNull(cuentaVeterinarioRepository.findAll(),
                "Si existen cuentas no debería devolver null");
    }

}
