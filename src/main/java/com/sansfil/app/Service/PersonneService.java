package com.sansfil.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.PersonneRepository;
import com.sansfil.app.model.Personne;

@Service
public class PersonneService {
	
	@Autowired
	private PersonneRepository personneRepository;
	
	@Autowired
	private PersonneSalleService personneSalleService;
	
	public Iterable<Personne> getAllPersonnes(){
		return personneRepository.findAll();
	}
	
	public void deletePersonne(String rfid) {
		personneSalleService.deleteAllPersonneSallesByUserRfid(rfid);
		personneRepository.deleteById(rfid);
	}
	
	public Iterable<Personne> getAllPersonnesContainingNom(String nom){
		return personneRepository.findByNomContainingOrPrenomContainingOrEmailContaining(nom, nom, nom);
	}
	
	public boolean ajouterPersonne(String rfid, String email, String nom, String prenom, String poste, String image) {
		Personne personne = new Personne();
		personne.setRfid(rfid);
		personne.setEmail(email);
		personne.setNom(nom);
		personne.setPrenom(prenom);
		personne.setPoste(poste);
		personne.setImage(image);
		try {
			personneRepository.save(personne);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
