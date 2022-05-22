package com.sansfil.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.Poste;

@Repository
public interface PosteRepository extends CrudRepository<Poste, Integer> {

}
