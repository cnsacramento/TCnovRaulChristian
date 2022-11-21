package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Veterinario;

public class VeterinarioRepository implements ICrud<Veterinario, String> {
	
	EntityManagerFactory entityManagerFactory;
	
	public VeterinarioRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public Veterinario save(Veterinario veterinario) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(veterinario);
			entityManager.getTransaction().commit();
		} catch (RollbackException ex) {
			ex.printStackTrace();
			veterinario = null;
		}finally {
			entityManager.close();
		}

		return veterinario;
	}

	@Override
	public Veterinario findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Veterinario dao) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Veterinario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
