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
		Cliente clienteGuardado = cliente;
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (RollbackException ex) {
			clienteGuardado = null;
			ex.printStackTrace();
		}
		
		entityManager.close();
		
		return clienteGuardado;
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
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Metodo que elimina el cliente en la DDBB
	 * @param id identificador del cliente que se quiere borrar
	 */
	@Override
	public boolean delete(String id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Cliente clienteBorrado = null;
		
		entityManager.getTransaction().begin();
		Cliente cliente = entityManager.find(Cliente.class, id);
		
		if(cliente != null) {
			cliente.getMascotas().forEach(mascota -> entityManager.remove(mascota));
			entityManager.remove(cliente);
			clienteBorrado = entityManager.find(Cliente.class, id);
			entityManager.getTransaction().commit();
		}
		
		entityManager.close();
		
		return clienteBorrado == null;
	}

	/**
	 * Metodo que devuelve todos los clientes de la DDBB
	 */
	@Override
	public ArrayList<Cliente> findAll() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Cliente> clientesList = 
				entityManager.createNamedQuery("cliente.findAll", Cliente.class)
				.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return clientesList;
	}

}
