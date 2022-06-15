package com.sansfil.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.SalleRepository;
import com.sansfil.app.model.Salle;

@Service
public class SalleService {

	@Autowired
	private SalleRepository salleRepository;
	
	public Iterable<Salle> getAllSalles(){
		return salleRepository.findAll();
	}
	
	public Iterable<Salle> getSallesByNom(String nom){
		return salleRepository.findByNomContaining(nom);
	}
	
	public Iterable<Salle> getSallesByLocation(String location){
		return salleRepository.findByLocation(location);
	}
	
	public Iterable<Salle> getSallesByNomAndLocation(String nom, String location){
		return salleRepository.findByNomContainingAndLocation(nom, location);
	}
	
	public boolean ajouterSalle(String nom, String etage, String image) {
		Salle salle = new Salle();
		salle.setNom(nom);
		salle.setLocation(etage);
		salle.setImage(image);
		try {
			salleRepository.save(salle);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
