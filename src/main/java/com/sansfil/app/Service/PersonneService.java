package com.sansfil.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.PersonneRepository;
import com.sansfil.app.model.Personne;

@Service
public class PersonneService {
	
	@Autowired
	private PersonneRepository personneRepository;
	
	public Iterable<Personne> getAllPersonnes(){
		return personneRepository.findAll();
	}

}
