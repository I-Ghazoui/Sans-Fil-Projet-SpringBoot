package com.sansfil.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.PersonneSalle;

@Repository
public interface PersonneSalleRepository extends CrudRepository<PersonneSalle, Integer> {

	public Iterable<PersonneSalle> findByOrderByDateEntreeDesc();
	
	public Iterable<PersonneSalle> findBySalleIdOrderByDateEntreeDesc(Integer SalleId);
}
