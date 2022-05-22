package com.sansfil.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.PosteRepository;
import com.sansfil.app.model.Poste;

@Service
public class PosteService {

	@Autowired
	PosteRepository posteRepository;
	
	public Iterable<Poste> getAllPostes(){
		return posteRepository.findAll();
	}
}
