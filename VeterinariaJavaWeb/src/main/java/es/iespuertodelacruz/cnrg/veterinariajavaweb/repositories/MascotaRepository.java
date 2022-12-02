package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Reserva;

public class MascotaRepository implements ICrud<Mascota, Integer>{
	
	private EntityManagerFactory entityManagerFactory;
	
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
	public Mascota save(Mascota mascota) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(mascota);
			entityManager.getTransaction().commit();
		}catch(RollbackException ex) {
			ex.printStackTrace();
			mascota = null;
		}
		
		entityManager.close();
		return mascota;
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
	public boolean update(Mascota mascotaOriginal) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = true;
		
		try {
			entityManager.getTransaction().begin();
			
			Mascota mascota = entityManager.find(Mascota.class, mascotaOriginal.getId());
			mascota.setId(mascotaOriginal.getId());
			mascota.setNombre(mascotaOriginal.getNombre());
			mascota.setCliente(mascotaOriginal.getCliente());
			mascota.setEspecieMascota(mascotaOriginal.getEspecieMascota());
			mascota.setFechaNacimiento(mascotaOriginal.getFechaNacimiento());
			mascota.setIntervencions(mascotaOriginal.getIntervencions());
			mascota.setPeso(mascotaOriginal.getPeso());
			
			entityManager.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			resultado = false;
		}finally {
			entityManager.close();
		}
		
		return resultado;
	}

	/**
	 * Metodo utilizado para borrar una mascota en la DDBB
	 * @param id de la mascota a borrar en la base de datos
	 */
	@Override
	public boolean delete(Integer id) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = false;
		
		try {
			entityManager.getTransaction().begin();
			Mascota mascota = entityManager.find(Mascota.class, id);
			entityManager.remove(mascota);
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

	/**
	 * Metodo utilizado para buscar las mascotas en la DDBB
	 */
	@Override
	public List<Mascota> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Mascota> lista = entityManager.createNamedQuery("Mascota.findAll", Mascota.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}
	
	public List<Mascota> findByCliente(String dni) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		String query = "SELECT * FROM mascota" + " WHERE dni_cliente" + " = :dni";

		List<Mascota> lista = entityManager.createNativeQuery(query, Mascota.class).setParameter("dni", dni).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}
	

}
