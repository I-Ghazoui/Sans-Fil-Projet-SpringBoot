package com.sansfil.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.UserRepository;
import com.sansfil.app.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User findUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	public Optional<User> findUserById(Integer id) {
		return userRepository.findById(id);
	}
}
