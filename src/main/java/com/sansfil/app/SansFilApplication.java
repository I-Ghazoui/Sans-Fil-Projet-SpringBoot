package com.sansfil.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sansfil.app.Service.UserService;
import com.sansfil.app.model.User;

@SpringBootApplication
public class SansFilApplication implements CommandLineRunner{

	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SansFilApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User ilyasghazoui = new User(null, "Ilyas", "Ghazoui", "/assets/img/uploads/ilyas.jpg", "i.ghazoui", "ilyas.ghazoui@gmail.com", "ilyas");
		userService.saveUser(ilyasghazoui);
		User mouadchakiri = new User(null, "Mouad", "Chakiri", "/assets/img/uploads/mouad.jpg", "m.chakiri", "mouad.chakiri@gmail.com", "mouad");
		userService.saveUser(mouadchakiri);
		User khalidaitzi = new User(null, "Khalid", "Ait Zi", "/assets/img/uploads/khalid.jpg", "k.ait-zi", "khalid.ait-zi@gmail.com", "khalid");
		userService.saveUser(khalidaitzi);
	}

}
