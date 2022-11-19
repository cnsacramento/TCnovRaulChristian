package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Intervencion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class IntervencionRepository implements ICrud<Intervencion, Integer> {

    private final EntityManagerFactory entityManagerFactory;

    public IntervencionRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Intervencion save(Intervencion dao) {
        return null;
    }

    @Override
    public Intervencion findById(Integer id) {
        return null;
    }

    @Override
    public boolean update(Intervencion dao) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Intervencion> findAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Intervencion> intervencionesList =
                entityManager.createNamedQuery("Intervencion.findAll", Intervencion.class)
                                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return intervencionesList;
    }
}
