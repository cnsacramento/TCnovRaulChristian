package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
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
		System.out.println("chill");
		entityManager.getTransaction().begin();
		
		try {
			System.out.println("llege");
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
	public boolean update(EspecieMascota especieMascotaOriginal) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = true;
		
		try {
			entityManager.getTransaction().begin();			
			
			EspecieMascota especieMascota = entityManager.find(EspecieMascota.class, especieMascotaOriginal.getId());
			especieMascota.setId(especieMascotaOriginal.getId());
			especieMascota.setNombre(especieMascotaOriginal.getNombre());
			especieMascota.setPeligrosa(especieMascotaOriginal.getPeligrosa());
            
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
	public boolean delete(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = false;
		
		try {
			entityManager.getTransaction().begin();
			EspecieMascota especieMascota = entityManager.find(EspecieMascota.class, id);
			entityManager.remove(especieMascota);
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
	public List<EspecieMascota> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<EspecieMascota> lista = entityManager.createNamedQuery("EspecieMascota.findAll", EspecieMascota.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}

}
