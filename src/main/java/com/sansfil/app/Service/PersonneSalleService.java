package com.sansfil.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.PersonneSalleRepository;
import com.sansfil.app.model.PersonneSalle;

@Service
public class PersonneSalleService {

	@Autowired
	PersonneSalleRepository personneSalleRepository;
	
	public Iterable<PersonneSalle> getAllPostes(){
		return personneSalleRepository.findAll();
	}
}
