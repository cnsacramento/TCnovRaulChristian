package es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories;

import java.util.ArrayList;

public interface ICrud<T,E> {
	   
	   T save(T dao);
	   
	   T findById(E id);
	   
	   boolean update(T dao);
	   
	   boolean delete(E id);
	 
	   ArrayList<T> findAll();
}
