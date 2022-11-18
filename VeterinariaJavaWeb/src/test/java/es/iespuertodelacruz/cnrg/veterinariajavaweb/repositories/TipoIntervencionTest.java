package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoIntervencion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;

class TipoIntervencionTest {

    static TipoIntervencionRepository tipoIntervencionRepository;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        EntityManagerFactory entityManagerFactory =
                EntityManagerFactorySingleton.getInstance().getEmf();
        tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
    }

    @Test
    void testSave() {

        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo("Cirugía");

        TipoIntervencion tipoIntervencionGuardada;
        assertNotNull(tipoIntervencionGuardada = tipoIntervencionRepository.save(tipoIntervencion),
                "Si se ha guardado correctamente no debería devolver null");

        tipoIntervencionRepository.findById(tipoIntervencionGuardada.getId());
        assertEquals(tipoIntervencion.getTipo(), tipoIntervencionGuardada.getTipo(),
                "El tipo guardado debería ser el mismo del tipo creado");
    }

    @Test
    void testSaveTipoNuloDevuelveNulo() {

        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo(null);

        assertNull(tipoIntervencionRepository.save(tipoIntervencion),
                "No se debería poder guardar un tipo nulo");
    }

    @Test
    void testSaveIdCreadoManualDevuelveNulo() {
        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setId(10);
        tipoIntervencion.setTipo("Estética");

        assertNull(tipoIntervencionRepository.save(tipoIntervencion),
                "No se debería poder guardar un tipo con un id manual");
    }

    @Test
    void testFindById() {
        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo("Consulta");

        TipoIntervencion tipoIntervencionEncontrada;
        assertNotNull(tipoIntervencionEncontrada = tipoIntervencionRepository.save(tipoIntervencion),
                "Si se ha guardado correctamente no debería devolver null");

        assertNotNull(tipoIntervencionRepository.findById(tipoIntervencionEncontrada.getId()),
                "Si existe en la DDBB no debería devolver null");
        assertEquals(tipoIntervencion.getTipo(), tipoIntervencionEncontrada.getTipo(),
                "El tipo guardado debería ser el mismo del tipo encontrado");
    }

    @Test
    void testFindByIdNoExistenteDevuelveNull() {

        assertNull(tipoIntervencionRepository.findById(0),
                "Si el ID no existe debería devolver null");
    }

    @Test
    void testUpdate() {
        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo("Exploración");

        TipoIntervencion tipoIntervencionGuardada = tipoIntervencionRepository.save(tipoIntervencion);
        assertNotNull(tipoIntervencionGuardada,
                "Si se ha guardado correctamente no debería devolver null");

        TipoIntervencion tipoIntervencionModificada = tipoIntervencionGuardada;
        tipoIntervencionModificada.setTipo("Nuevo tipo");
        assertTrue(tipoIntervencionRepository.update(tipoIntervencionModificada),
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
        TipoIntervencion tipoIntervencion = new TipoIntervencion();
        tipoIntervencion.setTipo("Revision");

        assertNotNull(tipoIntervencionRepository.save(tipoIntervencion),
                "Si se ha guardado correctamente no debería devolver null");

        assertNotNull(tipoIntervencionRepository.findAll(),
                "Si hay tipos de intervenciones no debería devolver null");
    }

}
