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
	public boolean update(TipoRestriccionDia tipoRestriccionDiaOriginal) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = true;
		
		try {
			entityManager.getTransaction().begin();			
			
			TipoRestriccionDia tipoRestriccionDia = entityManager.find(TipoRestriccionDia.class, tipoRestriccionDiaOriginal.getTipo());
			tipoRestriccionDia.setReservas(tipoRestriccionDiaOriginal.getReservas());
			tipoRestriccionDia.setIntervaloTiempo(tipoRestriccionDiaOriginal.getIntervaloTiempo());
			tipoRestriccionDia.setHoraCierre(tipoRestriccionDiaOriginal.getHoraCierre());
			tipoRestriccionDia.setHoraApertura(tipoRestriccionDiaOriginal.getHoraApertura());
            
			entityManager.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			resultado = false;
		}finally {
			entityManager.close();
		}
		
		return resultado;
	}

	@Override
	public boolean delete(String id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = false;
		
		try {
			entityManager.getTransaction().begin();
			TipoRestriccionDia tipoRestriccionDia = entityManager.find(TipoRestriccionDia.class, id);
			entityManager.remove(tipoRestriccionDia);
			entityManager.getTransaction().commit();
			resultado = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			entityManager.close();
		}
		
		entityManager.close();
		
		return resultado;
	}

	@Override
	public List<TipoRestriccionDia> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<TipoRestriccionDia> lista = entityManager.createNamedQuery("TipoRestriccionDia.findAll", TipoRestriccionDia.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}

}
