package com.sansfil.app.Service;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.PersonneSalleRepository;
import com.sansfil.app.model.PersonneSalle;

@Service
public class PersonneSalleService {

	@Autowired
	PersonneSalleRepository personneSalleRepository;
	
	public Iterable<PersonneSalle> getAllVisites(){
		return personneSalleRepository.findAll();
	}
	
	public void deleteAllPersonneSallesByUserRfid(String rfid) {
		for(PersonneSalle ps: personneSalleRepository.findByPersonneRfid(rfid)) {
			personneSalleRepository.delete(ps);
		}
	}
	
	public Iterable<PersonneSalle> getAllVisitesOrderedByDateEntree(){
		return personneSalleRepository.findByOrderByDateEntreeDesc();
	}
	
	public Iterable<PersonneSalle> getAllVisitesOrderedByDateEntreeAndDate(LocalDateTime startDate, LocalDateTime endDate){
		return personneSalleRepository.findByDateEntreeBetweenOrderByDateEntreeDesc(startDate, endDate);
	}
	
	public Iterable<PersonneSalle> getAllVisitesBySalleId(Integer salleId){
		return personneSalleRepository.findBySalleIdOrderByDateEntreeDesc(salleId);
	}
	
	public Iterable<PersonneSalle> getAllVisitesBySalleIdAndDate(Integer salleId, LocalDateTime startDate, LocalDateTime endDate){		
		return personneSalleRepository.findBySalleIdAndDateEntreeBetweenOrderByDateEntreeDesc(salleId, startDate, endDate);
	}
	
	public Iterable<PersonneSalle> getAllVisitesByPersonneId(String personneId){
		return personneSalleRepository.findByPersonneRfidOrderByDateEntreeDesc(personneId);
	}
	
	public Iterable<PersonneSalle> getAllVisitesByPersonneIdAndDate(String personneId, LocalDateTime startDate, LocalDateTime endDate){
		return personneSalleRepository.findByPersonneRfidAndDateEntreeBetweenOrderByDateEntreeDesc(personneId, startDate, endDate);
	}
	
}
