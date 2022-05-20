package com.sansfil.app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sansfil.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);
}
