package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Reserva;

public class ReservaRepository implements ICrud<Reserva, Integer> {

	private final EntityManagerFactory entityManagerFactory;

	/**
	 * Constructor con un parametro de la clase ReservaRepository
	 * 
	 * @param entityManagerFactory encargado de abrir la conexion DDBB
	 */
	public ReservaRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public Reserva save(Reserva reserva) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(reserva);
			entityManager.getTransaction().commit();
		} catch (RollbackException ex) {
			ex.printStackTrace();
			reserva = null;
		}

		entityManager.close();
		return reserva;
	}

	@Override
	public Reserva findById(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Reserva reserva = entityManager.find(Reserva.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return reserva;
	}

	@Override
	public boolean update(Reserva reservaOriginal) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = true;

		try {
			entityManager.getTransaction().begin();

			Reserva reserva = entityManager.find(Reserva.class, reservaOriginal.getId());
			reserva.setId(reservaOriginal.getId());
			reserva.setFechaInicio(reservaOriginal.getFechaInicio());
			reserva.setFechaFin(reservaOriginal.getFechaFin());
			reserva.setIntervencion(null);//CAMBIAR CUANDO SE CREE INTERVENCION
			reserva.setTipoRestriccionDia(reservaOriginal.getTipoRestriccionDia());

			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			resultado = false;
		} finally {
			entityManager.close();
		}

		return resultado;
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = false;

		try {
			entityManager.getTransaction().begin();
			Reserva reserva = entityManager.find(Reserva.class, id);
			entityManager.remove(reserva);
			entityManager.getTransaction().commit();
			resultado = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			entityManager.close();
		}

		entityManager.close();

		return resultado;
	}

	@Override
	public List<Reserva> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Reserva> lista = entityManager.createNamedQuery("Reserva.findAll", Reserva.class)
				.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Reserva> encontrarCitasDeUnDia(Timestamp fechaApertura, Timestamp fechaCierre) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String query = "SELECT * FROM reserva"
				+ " WHERE fecha_inicio"
				+ " BETWEEN :fechaApertura AND :fechaCierre"
				+ " ORDER BY fecha_inicio ASC";
				
		List<Reserva> lista = entityManager.createNativeQuery(query, Reserva.class)
				.setParameter("fechaApertura", fechaApertura)
				.setParameter("fechaCierre", fechaCierre)
				.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}

}
