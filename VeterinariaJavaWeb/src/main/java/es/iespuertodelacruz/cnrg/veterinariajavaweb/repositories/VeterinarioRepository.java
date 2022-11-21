package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;
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
	public Veterinario findById(String dni) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Veterinario veterinario = entityManager.find(Veterinario.class, dni);
		entityManager.getTransaction().commit();
		entityManager.close();
		return veterinario;
	}

	@Override
	public boolean update(Veterinario veterinario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String dni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Veterinario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
