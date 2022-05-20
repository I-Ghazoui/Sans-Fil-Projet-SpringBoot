package com.sansfil.app.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sansfil.app.Service.SalleService;
import com.sansfil.app.Service.UserService;
import com.sansfil.app.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SalleService salleService;

	@GetMapping({"/", "/index"})
	public String loginPage(Model model,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {	//User is connected
			model.addAttribute("listSalles", salleService.getAllSalles());	//Get all salles
			Integer userId = (Integer) session.getAttribute("userId");
			userService.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));	//Get User infos
			return "accueil";
		}else {		//User is not connected
			return "index";
		}
	}
	
	@PostMapping("/")
	public String loginPage(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = userService.findUserByUsernameAndPassword(username, password);
		if(user != null) {
			session.setAttribute("userId", user.getId());
			return loginPage(model, request);
		}else {
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String seDeconnecter(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}
}
