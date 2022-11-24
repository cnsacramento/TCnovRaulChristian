package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
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
	public boolean update(Veterinario veterinarioOriginal) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = true;
		
		try {
			entityManager.getTransaction().begin();			
			
			Veterinario veterinario = entityManager.find(Veterinario.class, veterinarioOriginal.getDni());
			veterinario.setDni(veterinarioOriginal.getDni());
			veterinario.setNombre(veterinarioOriginal.getNombre());
			veterinario.setApellidos(veterinarioOriginal.getApellidos());
			veterinario.setTelefono(veterinarioOriginal.getTelefono());
			veterinario.setCuentaVeterinario(veterinarioOriginal.getCuentaVeterinario());
			veterinario.setEspecialidadVeterinario(veterinarioOriginal.getEspecialidadVeterinario());
            
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
	public boolean delete(String dni) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = false;
		
		try {
			entityManager.getTransaction().begin();
			Veterinario veterinario = entityManager.find(Veterinario.class, dni);
			entityManager.remove(veterinario);
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
	public List<Veterinario> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Veterinario> lista = entityManager.createNamedQuery("Veterinario.findAll", Veterinario.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}
	
	
	public List<Veterinario> findByName(String name){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		String query = "SELECT veterinario.* FROM veterinario WHERE nombre Like '" + name+"%'";
		List<Veterinario> lista = entityManager.createNativeQuery(query, Veterinario.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}

}
