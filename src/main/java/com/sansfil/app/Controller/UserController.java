package com.sansfil.app.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sansfil.app.Service.SalleService;
import com.sansfil.app.Service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SalleService salleService;

	@GetMapping({"/", "/index"})
	public String loginPage(Model model) {
		HttpSession session = getSession();	//Get Session
		if(session.getAttribute("userId") != null) {	//User is connected
			model.addAttribute("listSalles", salleService.getAllSalles());	//Get all salles
			return "accueil";
		}else {		//User is not connected
			return "index";
		}
	}
	
	@PostMapping("/")
	public String loginPage(@RequestParam("username") String username, @RequestParam("password") String password) {
		HttpSession session = getSession();
		if(userService.findUserByUsernameAndPassword(username, password) != null) {
			session.setAttribute("userId", userService.findUserByUsernameAndPassword(username, password).getId());
			return "accueil";
		}else {
			return "index";
		}
	}
	
	
	
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
}
