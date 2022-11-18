package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoIntervencion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TipoIntervencionRepository implements ICrud<TipoIntervencion, Integer> {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructor que recibe el gestor de persistencia de datos
     * @param entityManagerFactory EntityManagerFactory encargada de abrir la conexion con la DDBB
     */
    public TipoIntervencionRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Guarda en la DDBB el TipoIntervencion
     * @param dao Tipo de intervencion que se desea guardar
     * @return Nulo en caso de no haberse guardado sino devuelve el objeto guardado
     */
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

    /**
     * Busca el tipo de intervencion segun el id de la misma
     * @param id Identificador unico del tipo de intervencion
     * @return True si se encuentra y False si no existe
     */
    @Override
    public TipoIntervencion findById(Integer id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TipoIntervencion tipoIntervencion = entityManager.find(TipoIntervencion.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return tipoIntervencion;
    }

    /**
     * Actualiza la informacion de un tipo intervencion existente
     * @param dao Tipo de intervencion que se quiere actualizar
     * @return True si se han realizado los cambio y False si no se han podido realizar
     */
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

    /**
     * Elimina un tipo de intervencion segun su ID
     * @param id Identificador del tipo de intervencion
     * @return True si se ha conseguido eliminar y False si no se ha podido eliminar
     */
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

    /**
     * Busca todos los tipos de intervenciones existentes
     * @return Nulo si no existen tipos de intervenciones
     * y si existen devuelve una List con los tipos  de intervenciones
     */
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
