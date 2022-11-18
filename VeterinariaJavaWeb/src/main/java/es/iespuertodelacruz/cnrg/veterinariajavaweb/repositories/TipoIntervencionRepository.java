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
        return false;
    }

    @Override
    public boolean delete(Integer id) {
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
