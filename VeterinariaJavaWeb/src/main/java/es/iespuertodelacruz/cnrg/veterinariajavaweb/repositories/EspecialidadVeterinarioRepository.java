package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecialidadVeterinario;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EspecialidadVeterinarioRepository implements ICrud<EspecialidadVeterinario, Integer> {

    private final EntityManagerFactory entityManagerFactory;

    public EspecialidadVeterinarioRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public EspecialidadVeterinario save(EspecialidadVeterinario dao) {
        return null;
    }

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

    @Override
    public boolean update(EspecialidadVeterinario dao) {
        return false;
    }

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
