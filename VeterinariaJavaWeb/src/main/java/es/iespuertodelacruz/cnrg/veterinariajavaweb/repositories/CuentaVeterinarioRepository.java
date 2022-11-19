package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.CuentaVeterinario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CuentaVeterinarioRepository implements ICrud<CuentaVeterinario, String> {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructor que recibe el gestor de persistencia de datos
     * @param entityManagerFactory EntityManagerFactory encargada de abrir la conexion con la DDBB
     */
    public CuentaVeterinarioRepository(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Guarda en la DDBB la cuenta del Veterinario
     * @param dao Cuenta del veterinario que se desea guardar
     * @return Nulo en caso de no haberse guardado en caso contrario devuelve el objeto guardado
     */
    @Override
    public CuentaVeterinario save(CuentaVeterinario dao) {
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
     * Busca la cuenta del veterinario segun el CORREO de la misma
     * @param id Correo unico del veterinario
     * @return True si se encuentra la cuenta y False si no existe
     */
    @Override
    public CuentaVeterinario findById(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CuentaVeterinario cuentaVeterinario =
                entityManager.find(CuentaVeterinario.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return cuentaVeterinario;
    }

    /**
     * Actualiza la contrasenia de la cuenta de un veterinario existente
     * @param dao Cuenta del veterinario que se desea actualizar
     * @return True si se han realizado los cambios y False si no se han podido realizar
     */
    @Override
    public boolean update(CuentaVeterinario dao) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            CuentaVeterinario cuentaVeterinarioParaActualizar =
                    entityManager.find(CuentaVeterinario.class, dao.getCorreo());
            cuentaVeterinarioParaActualizar.setContrasenia(dao.getContrasenia());
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
     * Actualiza la informacion de una especialidad de veterinario existente
     * @param correoAntiguo Correo antiguo del veterinario
     * @param cuentaActualizada Datos de la cuenta del veterinario actualizados
     * @return True si se han realizado los cambios y False si no se han podido realizar
     */
    public boolean update(final String correoAntiguo, final CuentaVeterinario cuentaActualizada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            CuentaVeterinario cuentaVeterinarioParaActualizar =
                    entityManager.find(CuentaVeterinario.class, correoAntiguo);
            cuentaVeterinarioParaActualizar.setCorreo(cuentaActualizada.getCorreo());
            cuentaVeterinarioParaActualizar.setContrasenia(cuentaActualizada.getContrasenia());
            cuentaVeterinarioParaActualizar.setVeterinarios(cuentaActualizada.getVeterinarios());
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
     * Elimina la cuenta del veterinario segun su CORREO
     * @param id Correo unico del veterinario
     * @return TRUE si se ha conseguido eliminar en caso contrario FALSE
     */
    @Override
    public boolean delete(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            CuentaVeterinario cuentaVeterinarioParaEliminar =
                    entityManager.find(CuentaVeterinario.class, id);
            entityManager.remove(cuentaVeterinarioParaEliminar);
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
     * Busca todas las cuentas de los veterinarios guardadas
     * @return Nulo si no se encuentrar ninguna cuenta
     * y si existen devuelve una List<CuentaVeterinario> con las cuentas existentes
     */
    @Override
    public List<CuentaVeterinario> findAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<CuentaVeterinario> cuentasVeterinariosList
                = entityManager.createNamedQuery(
                        "CuentaVeterinario.findAll",
                        CuentaVeterinario.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return cuentasVeterinariosList;
    }
}
