package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;

public class MascotaRepository implements ICrud<Mascota, Integer>{
	
	private final EntityManagerFactory entityManagerFactory;
	
	/**
	 * Constructor con un parametro de la clase MascotaRepository 
	 * @param entityManagerFactory encargado de abrir la conexion DDBB
	 */
	public MascotaRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	/**
	 * Metodo utilizado para guardar mascotas en la DDBB
	 * @param dao mascota a guardar en la base de datos
	 */
	@Override
	public Mascota save(Mascota dao) {
		
		return null;
	}

	/**
	 * Metodo utilizado para buscar una mascota en la DDBB
	 * @param id de la mascota a buscar en la base de datos
	 */
	@Override
	public Mascota findById(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Mascota mascota = entityManager.find(Mascota.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return mascota;
	}

	/**
	 * Metodo utilizado para actualizar mascotas en la DDBB
	 * @param dao mascota a actualizar en la base de datos
	 */
	@Override
	public boolean update(Mascota dao) {
		
		return false;
	}

	/**
	 * Metodo utilizado para borrar una mascota en la DDBB
	 * @param id de la mascota a borrar en la base de datos
	 */
	@Override
	public boolean delete(Integer id) {
		
		return false;
	}

	/**
	 * Metodo utilizado para buscar las mascotas en la DDBB
	 */
	@Override
	public ArrayList<Mascota> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Mascota> lista = entityManager.createNamedQuery("Mascota.findAll", Mascota.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}
	
	

}
