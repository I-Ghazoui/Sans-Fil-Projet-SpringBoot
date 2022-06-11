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
	
	public boolean ajouterSalle(Salle salle) {
		try {
			salleRepository.save(salle);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
