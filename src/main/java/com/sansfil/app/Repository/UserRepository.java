package com.sansfil.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sansfil.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);
	
	public Optional<User> findByUsername(String userName);
	
	public Optional<User> findByEmail(String email);
}
