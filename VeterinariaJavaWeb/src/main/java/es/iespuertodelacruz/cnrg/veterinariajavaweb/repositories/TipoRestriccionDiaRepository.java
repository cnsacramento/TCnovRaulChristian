package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;

public class TipoRestriccionDiaRepository implements ICrud<TipoRestriccionDia, String> {

	private EntityManagerFactory entityManagerFactory;

	/**
	 * Constructor con un parametro de la clase TipoRestriccionDiaRepository
	 * 
	 * @param entityManagerFactory encargada de abrir la conexion con la DDBB
	 **/
	public TipoRestriccionDiaRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public TipoRestriccionDia save(TipoRestriccionDia tipoRestriccionDia) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(tipoRestriccionDia);
			entityManager.getTransaction().commit();
		} catch (RollbackException ex) {
			ex.printStackTrace();
			tipoRestriccionDia = null;
		}finally {
			entityManager.close();
		}

		return tipoRestriccionDia;
	}

	@Override
	public TipoRestriccionDia findById(String id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		TipoRestriccionDia tipoRestriccionDia = entityManager.find(TipoRestriccionDia.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tipoRestriccionDia;
	}

	@Override
	public boolean update(TipoRestriccionDia tipoRestriccionDia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TipoRestriccionDia> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
