package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import static org.junit.jupiter.api.Assertions.*;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecialidadVeterinario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

class EspecialidadVeterinarioTest {

    private static EspecialidadVeterinarioRepository especialidadVeterinarioRepository;
    private static List<EspecialidadVeterinario> especialidadVeterinarioList;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEmf();
        especialidadVeterinarioRepository = new EspecialidadVeterinarioRepository(entityManagerFactory);

        especialidadVeterinarioList = new ArrayList<>();

        // Especialidad 1

        EspecialidadVeterinario especialidad1 = new EspecialidadVeterinario();
        especialidad1.setNombre("especialidad1");
        especialidadVeterinarioList.add(especialidad1);

        // Especialidad 2

        EspecialidadVeterinario especialidad2 = new EspecialidadVeterinario();
        especialidad2.setNombre("especialidad2");
        especialidadVeterinarioList.add(especialidad2);

        // Especialidad 3

        EspecialidadVeterinario especialidad3 = new EspecialidadVeterinario();
        especialidad3.setNombre("especialidad3");
        especialidadVeterinarioList.add(especialidad3);

        // Especialidad 4

        EspecialidadVeterinario especialidad4 = new EspecialidadVeterinario();
        especialidad4.setNombre("especialidad4");
        especialidadVeterinarioList.add(especialidad4);

        // Especialidad 5

        EspecialidadVeterinario especialidad5 = new EspecialidadVeterinario();
        especialidad5.setNombre("especialidad5");
        especialidadVeterinarioList.add(especialidad5);

        // Especialidad 6

        EspecialidadVeterinario especialidad6 = new EspecialidadVeterinario();
        especialidad6.setNombre("especialidad6");
        especialidadVeterinarioList.add(especialidad6);

        // Especialidad 7

        EspecialidadVeterinario especialidad7 = new EspecialidadVeterinario();
        especialidad7.setNombre(null);
        especialidadVeterinarioList.add(especialidad7);
    }


    @AfterAll
    static void tearDownAfterClass() throws Exception {

        especialidadVeterinarioList.forEach(especialidad ->
                especialidadVeterinarioRepository.delete(especialidad.getId()));
    }


    @Test
    void testSave() {

        EspecialidadVeterinario especialidadVeterinarioGuardada;
        assertNotNull(
                especialidadVeterinarioGuardada =
                        especialidadVeterinarioRepository.save(especialidadVeterinarioList.get(0)),
                "Si la especialidad se guarda no debería devolver null");

        especialidadVeterinarioRepository.findById(especialidadVeterinarioGuardada.getId());
        assertEquals(especialidadVeterinarioList.get(0).getNombre(), especialidadVeterinarioGuardada.getNombre(),
                "Si se ha guardado correctamente deberían tener los mismo nombres"
        );
    }

    @Test
    void testSaveNombreNuloDevuelveNulo() {
        assertNull(especialidadVeterinarioRepository.save(especialidadVeterinarioList.get(6)),
                "Si se guarda un nombre nulo debería devolver nulo");
    }

    @Test
    void testFindById() {

        EspecialidadVeterinario especialidadVeterinarioGuardada;
        assertNotNull(especialidadVeterinarioGuardada
                        = especialidadVeterinarioRepository.save(especialidadVeterinarioList.get(1)),
                "Si la especialidad se guarda no debería devolver null");

        assertNotNull(especialidadVeterinarioRepository.findById(especialidadVeterinarioGuardada.getId()),
                "Si la especialidad existe no debería ser nula");
    }

    @Test
    void testFindByIdNoExisteIdDevuelveNulo() {

        assertNull(especialidadVeterinarioRepository.findById(-1));
    }

    @Test
    void testUpdate() {


        assertNotNull(especialidadVeterinarioRepository.save(especialidadVeterinarioList.get(2)));

        EspecialidadVeterinario especialidadVeterinarioParaModificar
                = especialidadVeterinarioList.get(2);
        especialidadVeterinarioParaModificar.setNombre("nuevonombre");

        assertTrue(especialidadVeterinarioRepository.update(especialidadVeterinarioParaModificar));

        EspecialidadVeterinario especialidadGuardada =
                especialidadVeterinarioRepository.findById(especialidadVeterinarioParaModificar.getId());
        assertNotNull(especialidadGuardada);

        assertNotEquals(especialidadVeterinarioList.get(3).getNombre(), especialidadGuardada.getNombre());

    }

    @Test
    void testUpdateNoExisteEspecialidadDevuelveFalse() {

        assertFalse(especialidadVeterinarioRepository.update(especialidadVeterinarioList.get(5)));
    }

    @Test
    void testDelete() {

        EspecialidadVeterinario especialidadVeterinarioGuardada;
        assertNotNull(especialidadVeterinarioGuardada
                        = especialidadVeterinarioRepository.save(especialidadVeterinarioList.get(3)),
                "Si la especialidad se guarda no debería devolver null");

        assertTrue(especialidadVeterinarioRepository.delete(especialidadVeterinarioGuardada.getId()),
                "Si la especialidad se borra correctamente debería devolver FALSE");
    }

    @Test
    void testDeleteNoExisteEspecialidadDevuelveFalse() {

        assertFalse(especialidadVeterinarioRepository.delete(-1));
    }

    @Test
    void testFindAll() {

        assertNotNull(especialidadVeterinarioRepository.save(especialidadVeterinarioList.get(4)));
        assertNotNull(especialidadVeterinarioRepository.findAll(),
                "Si existen especialidades no debería ser nulo");
    }

}
