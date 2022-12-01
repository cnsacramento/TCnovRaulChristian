package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Intervencion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.Arrays;
import java.util.List;

public class IntervencionRepository implements ICrud<Intervencion, Integer> {

    private final EntityManagerFactory entityManagerFactory;

    public IntervencionRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Guarda en la DDBB la intervencion
     * @param dao Intervencion que se desea guardar
     * @return Nulo en caso de no haberse guardado, en caso contrario devuelve el objeto guardado
     */
    @Override
    public Intervencion save(Intervencion dao) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            dao.getReservas().get(0).setIntervencion(dao);
            entityManager.persist(dao.getReservas().get(0));
            
            dao.getVeterinarios().forEach( veterinario -> {
            	veterinario.getIntervencions().add(dao);
            	entityManager.merge(veterinario);
            });
            entityManager.getTransaction().commit();
            return dao;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }

        return null;
    }

    /**
     * Busca una intervencion segun el ID de la misma
     * @param id Identificador unico de la intervencion
     * @return True si se encuentra la intervencion y False si no existe
     */
    @Override
    public Intervencion findById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Intervencion intervencion = entityManager.find(Intervencion.class, id);
        entityManager.getTransaction().commit();

        return intervencion;
    }

    /**
     * Actualiza la informacion de una intervencion existente en DDBB
     * @param dao Intervencion que se desea actualizar
     * @return True si se han realizado los cambios y False si no se han podido realizar
     */
    @Override
    public boolean update(Intervencion dao) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Intervencion intervencion = entityManager.find(Intervencion.class, dao.getId());
            intervencion.setAsunto(dao.getAsunto());
            intervencion.setDescripcion(dao.getDescripcion());
            intervencion.setTipoIntervencion(dao.getTipoIntervencion());
            intervencion.setMascota(dao.getMascota());
            intervencion.setFactura(dao.getFactura());
            intervencion.setVeterinarios(dao.getVeterinarios());          
            entityManager.getTransaction().commit();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            entityManager.close();
        }

        return false;
    }

    /**
     * Elimina una intervencion segun su ID
     * @param id Identificador unico de la intervencion
     * @return TRUE si se ha conseguido eliminar, en caso contrario FALSE
     */
    @Override
    public boolean delete(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

    /**
     * Busca todas las intervencion guardadas en DDBB
     * @return Nulo si no se encuentran ninguna intervencion
     * y si existen devuelve una List<Intervencion> con todas
     * las intervenciones
     */
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
