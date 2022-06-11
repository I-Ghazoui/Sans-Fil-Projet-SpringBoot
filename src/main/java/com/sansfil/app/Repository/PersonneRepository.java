package com.sansfil.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, String> {

}
