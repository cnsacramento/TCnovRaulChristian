package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoIntervencion;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;

class TipoIntervencionTest {

    private static TipoIntervencionRepository tipoIntervencionRepository;
    private TipoIntervencion tipoIntervencion;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        EntityManagerFactory entityManagerFactory =
                EntityManagerFactorySingleton.getInstance().getEmf();
        tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
    }

    @BeforeEach
    void setUpBeforeEach() {
        tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo("tipoIntervencion");
    }

    @AfterEach
    void setUpAfterEach() {
        tipoIntervencionRepository.delete(tipoIntervencion.getId());
    }

    @Test
    void testSave() {

        TipoIntervencion tipoIntervencionGuardada = tipoIntervencionRepository.save(tipoIntervencion);

        assertNotNull(tipoIntervencionGuardada,
                "Si se ha guardado correctamente no debería devolver null");

        tipoIntervencionRepository.findById(tipoIntervencionGuardada.getId());
        assertEquals(tipoIntervencion.getTipo(), tipoIntervencionGuardada.getTipo(),
                "El tipo guardado debería ser el mismo del tipo creado");
    }

    @Test
    void testSaveTipoNuloDevuelveNulo() {

        tipoIntervencion.setTipo(null);

        assertNull(tipoIntervencionRepository.save(tipoIntervencion),
                "No se debería poder guardar un tipo nulo");
    }

    @Test
    void testSaveIdCreadoManualDevuelveNulo() {

        tipoIntervencion.setId(10);
        tipoIntervencion.setTipo("nuevo");

        assertNull(tipoIntervencionRepository.save(tipoIntervencion),
                "No se debería poder guardar un tipo con un id manual");
    }

    @Test
    void testFindById() {

        TipoIntervencion tipoIntervencionEncontrada =
                tipoIntervencionRepository.save(tipoIntervencion);

        assertNotNull(tipoIntervencionEncontrada,
                "Si se ha guardado correctamente no debería devolver null");

        assertNotNull(tipoIntervencionRepository.findById(tipoIntervencionEncontrada.getId()),
                "Si existe en la DDBB no debería devolver null");
        assertEquals(tipoIntervencion.getTipo(), tipoIntervencionEncontrada.getTipo(),
                "El tipo guardado debería ser el mismo del tipo encontrado");
    }

    @Test
    void testFindByIdNoExistenteDevuelveNull() {

        assertNull(tipoIntervencionRepository.findById(-1),
                "Si el ID no existe debería devolver null");
    }

    @Test
    void testUpdate() {

        TipoIntervencion tipoIntervencionGuardada = tipoIntervencionRepository.save(tipoIntervencion);
        assertNotNull(tipoIntervencionGuardada,
                "Si se ha guardado correctamente no debería devolver null");

        tipoIntervencionGuardada.setTipo("Nuevo tipo");
        assertTrue(tipoIntervencionRepository.update(tipoIntervencionGuardada),
                "Si se ha modificado correctamente no debería devolver null");

    }

    @Test
    void testDelete() {
        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo("Medicinas");

        TipoIntervencion tipoIntervencionGuardada;
        assertNotNull(tipoIntervencionGuardada = tipoIntervencionRepository.save(tipoIntervencion),
                "Si se ha guardado correctamente no debería devolver null");

        assertTrue(tipoIntervencionRepository.delete(tipoIntervencionGuardada.getId()),
                "Si se borra correctamente debería devolver true");

    }

    @Test
    void testFindAll() {

        assertNotNull(tipoIntervencionRepository.save(tipoIntervencion),
                "Si se ha guardado correctamente no debería devolver null");

        assertNotNull(tipoIntervencionRepository.findAll(),
                "Si hay tipos de intervenciones no debería devolver null");
    }

}
