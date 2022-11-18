package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoIntervencion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TipoIntervencionRepository implements ICrud<TipoIntervencion, Integer> {

    private final EntityManagerFactory entityManagerFactory;

    public TipoIntervencionRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public TipoIntervencion save(TipoIntervencion dao) {

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
    public TipoIntervencion findById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TipoIntervencion tipoIntervencion = entityManager.find(TipoIntervencion.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return tipoIntervencion;
    }

    @Override
    public boolean update(TipoIntervencion dao) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TipoIntervencion tipoIntervencionActualizada = entityManager.find(TipoIntervencion.class, dao.getId());
            tipoIntervencionActualizada.setId(dao.getId());
            tipoIntervencionActualizada.setTipo(dao.getTipo());
            tipoIntervencionActualizada.setIntervencions(dao.getIntervencions());
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }

        return false;
    }

    @Override
    public boolean delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(id);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            entityManager.close();
        }

        return false;
    }

    @Override
    public List<TipoIntervencion> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<TipoIntervencion> tiposIntevencionesList =
                entityManager.createNamedQuery("TipoIntervencion.findAll", TipoIntervencion.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return tiposIntevencionesList;
    }
}
