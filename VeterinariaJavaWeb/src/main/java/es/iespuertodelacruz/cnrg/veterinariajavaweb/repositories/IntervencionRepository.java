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

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();
            return dao;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }

        return null;
    }

    @Override
    public Intervencion findById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Intervencion intervencion = entityManager.find(Intervencion.class, id);
        entityManager.getTransaction().commit();

        return intervencion;
    }

    @Override
    public boolean update(Intervencion dao) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();;
        try {
            entityManager.getTransaction().begin();
            Intervencion intervencion = entityManager.find(Intervencion.class, id);
            entityManager.remove(intervencion);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }

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
