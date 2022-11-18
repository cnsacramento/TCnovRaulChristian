package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;

public class EspecieMascotaRepository implements ICrud<EspecieMascota, Integer>{

	private final EntityManagerFactory entityManagerFactory;
	
	/**
	 * Constructor con un parametro de la clase EspecieMascotaRepository 
	 * @param entityManagerFactory encargado de abrir la conexion DDBB
	 */
	public EspecieMascotaRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	/**
	 * Metodo utilizado para guardar las especies de las mascotas en la DDBB
	 * @param dao EspecieMascota a guardar en la base de datos
	 */
	@Override
	public EspecieMascota save(EspecieMascota especieMascota) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(especieMascota);
			entityManager.getTransaction().commit();
		}catch(RollbackException ex) {
			ex.printStackTrace();
			especieMascota = null;
		}
		
		entityManager.close();
		return especieMascota;
	}

	@Override
	public EspecieMascota findById(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		EspecieMascota especieMascota = entityManager.find(EspecieMascota.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return especieMascota;
	}

	@Override
	public boolean update(EspecieMascota dao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EspecieMascota> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
