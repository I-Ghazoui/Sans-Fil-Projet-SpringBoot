package com.sansfil.app.Repository;


import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.PersonneSalle;

@Repository
public interface PersonneSalleRepository extends CrudRepository<PersonneSalle, Integer> {

	public Iterable<PersonneSalle> findByOrderByDateEntreeDesc();
	
	public Iterable<PersonneSalle> findByDateEntreeBetweenOrderByDateEntreeDesc(LocalDateTime startDate, LocalDateTime endDate);
	
	public Iterable<PersonneSalle> findBySalleIdOrderByDateEntreeDesc(Integer SalleId);
	
	public Iterable<PersonneSalle> findBySalleIdAndDateEntreeBetweenOrderByDateEntreeDesc(Integer SalleId, LocalDateTime startDate, LocalDateTime endDate);
	
	public Iterable<PersonneSalle> findByPersonneRfidOrderByDateEntreeDesc(String personneId);
	
	public Iterable<PersonneSalle> findByPersonneRfidAndDateEntreeBetweenOrderByDateEntreeDesc(String personneId, LocalDateTime startDate, LocalDateTime endDate);

	
	
	public Iterable<PersonneSalle> findByPersonneRfid(String rfid);
}
