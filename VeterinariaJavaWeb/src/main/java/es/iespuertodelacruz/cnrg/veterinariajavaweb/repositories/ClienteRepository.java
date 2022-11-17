package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;

public class ClienteRepository implements ICrud<Cliente, String>{


    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructor que recibe el entityManagerFactory
     * @param entityManagerFactory encargado de abrir la conexion DDBB
     */
    public ClienteRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Metodo que guarda el cliente en la DDBB
     * @param cliente Cliente que se quiere guardar
     */
    @Override
    public Cliente save(Cliente cliente) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
            return cliente;
        } catch (RollbackException ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }


        return null;
    }

    /**
     * Metodo que busca el cliente en la DDBB
     * @param id indentificador del cliente
     */
    @Override
    public Cliente findById(String id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Cliente cliente = entityManager.find(Cliente.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();

        return cliente;
    }

    /**
     * Metodo que actualiza los datos del cliente en la DDBB
     * @param cliente Cliente que se quiere actualizar
     */
    @Override
    public boolean update(Cliente cliente) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Cliente clienteEncontrado = entityManager.find(Cliente.class, cliente.getDni());
            clienteEncontrado.setNombre(cliente.getNombre());
            clienteEncontrado.setApellidos(cliente.getApellidos());
            clienteEncontrado.setCorreo(cliente.getCorreo());
            clienteEncontrado.setDireccion(cliente.getDireccion());
            clienteEncontrado.setMascotas(cliente.getMascotas());
            clienteEncontrado.setTelefono(cliente.getTelefono());

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
     * Metodo que elimina el cliente en la DDBB
     * @param id identificador del cliente que se quiere borrar
     */
    @Override
    public boolean delete(String id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Cliente cliente = entityManager.find(Cliente.class, id);
            cliente.getMascotas().forEach(entityManager::remove);
            entityManager.remove(cliente);
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
     * Metodo que devuelve todos los clientes de la DDBB
     */
    @Override
    public List<Cliente> findAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Cliente> clientesList =
                entityManager.createNamedQuery("Cliente.findAll", Cliente.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return clientesList;
    }

}
