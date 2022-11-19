package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Factura;

public class FacturaRepository implements ICrud<Factura, Integer>{

private final EntityManagerFactory entityManagerFactory;
	
	/**
	 * Constructor con un parametro de la clase FacturaRepository 
	 * @param entityManagerFactory encargado de abrir la conexion DDBB
	 */
	public FacturaRepository(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	/**
	 * Metodo utilizado para guardar las Facturas en la DDBB
	 * @param dao Factura a guardar en la base de datos
	 */
	@Override
	public Factura save(Factura factura) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(factura);
			entityManager.getTransaction().commit();
		}catch(RollbackException ex) {
			ex.printStackTrace();
			factura = null;
		}
		
		entityManager.close();
		return factura;
	}

	@Override
	public Factura findById(Integer id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Factura factura = entityManager.find(Factura.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return factura;
	}

	@Override
	public boolean update(Factura facturaOriginal)  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean resultado = true;
		
		try {
			entityManager.getTransaction().begin();			
			
			Factura factura = entityManager.find(Factura.class, facturaOriginal.getId());
			factura.setFecha(facturaOriginal.getFecha());
			factura.setCoste(facturaOriginal.getCoste());
			factura.setDetalles(facturaOriginal.getDetalles());
            
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
			Factura factura = entityManager.find(Factura.class, id);
			entityManager.remove(factura);
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
	public List<Factura> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Factura> lista = entityManager.createNamedQuery("Factura.findAll", Factura.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lista;
	}
}
