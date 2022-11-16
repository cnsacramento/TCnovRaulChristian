package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerFactorySingleton {

	
	private static EntityManagerFactory entityManagerFactory;

    private EntityManagerFactorySingleton() {
    	
    	entityManagerFactory = Persistence.createEntityManagerFactory("VeterinariaJavaWeb");
    }

    public static EntityManagerFactory getEmf() {
    	
    	 if(entityManagerFactory == null) {
         	new EntityManagerFactorySingleton();
         }
    	 
    	 return entityManagerFactory;
    }
}
