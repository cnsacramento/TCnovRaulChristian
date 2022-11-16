package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerFactorySingleton {

	private static EntityManagerFactorySingleton instance;
	private EntityManagerFactory entityManagerFactory;

    private EntityManagerFactorySingleton() {
    	
    	entityManagerFactory = Persistence.createEntityManagerFactory("VeterinariaJavaWeb");
    }

    public static EntityManagerFactorySingleton getInstance() {
    	
    	 if(instance == null) {
         	instance = new EntityManagerFactorySingleton();
         }
    	 
    	 return instance;
    }
    
    public EntityManagerFactory getEmf() {
    	return entityManagerFactory;
    }
}
