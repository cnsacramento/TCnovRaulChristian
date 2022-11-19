package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecialidadVeterinario;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EspecialidadVeterinarioRepository implements ICrud<EspecialidadVeterinario, Integer> {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructor que recibe el gestor de persistencia de datos
     * @param entityManagerFactory EntityManagerFactory encargada de abrir la conexion con la DDBB
     */
    public EspecialidadVeterinarioRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Guarda en la DDBB la Especialidad del Veterinario
     * @param dao Especialidad del veterinario que se desea guardar
     * @return Nulo en caso de no haberse guardado en caso contrario devuelve el objeto guardado
     */
    @Override
    public EspecialidadVeterinario save(EspecialidadVeterinario dao) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();
            return dao;
        } catch(Exception ex) {
            System.out.println(ex.getClass());
        } finally {
            entityManager.close();
        }

        return null;

    }

    /**
     * Busca la especialidad del veterinario segun el ID de la misma
     * @param id Identificador unico de la especialidad del veterinario
     * @return True si se encuentra la especialidad y False si no existe
     */
    @Override
    public EspecialidadVeterinario findById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        EspecialidadVeterinario especialidadVeterinario =
                entityManager.find(EspecialidadVeterinario.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return especialidadVeterinario;
    }

    /**
     * Actualiza la informacion de una especialidad de veterinario existente
     * @param dao Especialidad del veterinario que se desea actualizar
     * @return True si se han realizado los cambios y False si no se han podido realizar
     */
    @Override
    public boolean update(EspecialidadVeterinario dao) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            EspecialidadVeterinario especialidadVeterinarioParaModificar =
                    entityManager.find(EspecialidadVeterinario.class, dao.getId());
            especialidadVeterinarioParaModificar.setNombre(dao.getNombre());
            especialidadVeterinarioParaModificar.setVeterinarios(dao.getVeterinarios());
            entityManager.persist(especialidadVeterinarioParaModificar);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            System.out.println(ex.getClass());
        } finally {
            entityManager.close();
        }

        return false;

    }

    /**
     * Elimina la especialidad del veterinario segun su ID
     * @param id Identificador unico de la especialidad del veterinario
     * @return TRUE si se ha conseguido eliminar en caso contrario FALSE
     */
    @Override
    public boolean delete(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            EspecialidadVeterinario especialidadVeterinarioParaEliminar =
                    entityManager.find(EspecialidadVeterinario.class, id);
            entityManager.remove(especialidadVeterinarioParaEliminar);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            System.out.println(ex.getClass());
        } finally {
            entityManager.close();
        }

        return false;
    }

    /**
     * Busca todas las especialidades de veterinarios guardadas
     * @return Nulo si no se encuentrar especialidades
     * y si existen devuelve una List<EspecialidadVeterinario> con los tipos  de intervenciones
     */
    @Override
    public List<EspecialidadVeterinario> findAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<EspecialidadVeterinario> especialidadesVeterinarioList
                                      = entityManager.createNamedQuery(
                                              "EspecialidadVeterinario.findAll",
                EspecialidadVeterinario.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return especialidadesVeterinarioList;
    }
}
