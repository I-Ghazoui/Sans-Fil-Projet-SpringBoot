package com.sansfil.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.Salle;

@Repository
public interface SalleRepository extends CrudRepository<Salle, Integer>{
	
	public Iterable<Salle> findByNomContainingAndLocation(String nom, String location);
	
	public Iterable<Salle> findByNomContaining(String nom);
	
	public Iterable<Salle> findByLocation(String location);

}
