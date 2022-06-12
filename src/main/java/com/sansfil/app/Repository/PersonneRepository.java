package com.sansfil.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.Personne;
import com.sansfil.app.model.Salle;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, String> {
	
	public Iterable<Personne> findByNomContainingOrPrenomContainingOrEmailContaining(String nom, String prenom, String email);

}
