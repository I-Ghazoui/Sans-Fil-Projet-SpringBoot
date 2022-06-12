package com.sansfil.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.PersonneRepository;
import com.sansfil.app.model.Personne;
import com.sansfil.app.model.Salle;

@Service
public class PersonneService {
	
	@Autowired
	private PersonneRepository personneRepository;
	
	public Iterable<Personne> getAllPersonnes(){
		return personneRepository.findAll();
	}
	
	public Iterable<Personne> getAllPersonnesContainingNom(String nom){
		return personneRepository.findByNomContainingOrPrenomContainingOrEmailContaining(nom, nom, nom);
	}
	
	public boolean ajouterPersonne(String rfid, String email, String nom, String prenom) {
		Personne personne = new Personne();
		personne.setRfid(rfid);
		personne.setEmail(email);
		personne.setNom(nom);
		personne.setPrenom(prenom);
		try {
			personneRepository.save(personne);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
