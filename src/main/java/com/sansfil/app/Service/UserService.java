package com.sansfil.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sansfil.app.Repository.UserRepository;
import com.sansfil.app.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void saveUser(User user) {
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}
	
	public void updateUserNoEncodingPassword(User user) {
		userRepository.save(user);
	}
	
	public Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User findUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	public Optional<User> findUserById(Integer id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public Optional<User> findUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
}
