package com.sansfil.app.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sansfil.app.Service.PersonneSalleService;
import com.sansfil.app.Service.PersonneService;
import com.sansfil.app.Service.SalleService;
import com.sansfil.app.Service.UserService;
import com.sansfil.app.model.User;
import com.sansfil.app.security.CustomUserDetails;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SalleService salleService;
	
	@Autowired
	private PersonneSalleService personneSalleService;
	
	@Autowired
	private PersonneService pesonneService;
	
	@GetMapping({"/", "/index", "/login"})
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/salles")
	public String listesDesSalles(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listSalles", salleService.getAllSalles());	//Get all salles
		return "salles";
	}
	
	@PostMapping("/salles")
	public String listesDesSallesAvecAction(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, HttpServletRequest request) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		if(request.getParameter("chercher") != null) {
			String nom = request.getParameter("nom");
			String etage = request.getParameter("etage");

			if(!nom.isBlank() && !etage.isBlank() && !etage.equals("all")) {
				model.addAttribute("listSalles", salleService.getSallesByNomAndLocation(nom, etage));
			}else if(!nom.isBlank()) {
				model.addAttribute("listSalles", salleService.getSallesByNom(nom));
			}else if(!etage.isBlank() && !etage.equals("all")) {
				model.addAttribute("listSalles", salleService.getSallesByLocation(etage));
			}else {
				model.addAttribute("listSalles", salleService.getAllSalles());	//Get all salles
			}
			
		}else if(request.getParameter("ajouter") != null){
			String nom = request.getParameter("nom");
			String etage = request.getParameter("etage");
			
			if(salleService.ajouterSalle(nom, etage)) {
				model.addAttribute("statusAjoutSalleSuccess", "La salle " + nom + " a été bien ajouté.");
			}else {
				model.addAttribute("statusAjoutSalleErreur", "Une erreur est survenur, réessayer.");
			}
			model.addAttribute("listSalles", salleService.getAllSalles());	//Get all salles
		}
		return "salles";
	}
	
	@GetMapping({"/personnes"})
	public String getAllUsers(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listPersonnes", pesonneService.getAllPersonnes());	//Get all salles
		return "users";
	}
	
	@PostMapping({"/personnes"})
	public String getAllUsersAvecAction(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, HttpServletRequest request) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		if(request.getParameter("chercher") != null) {
			String nom = request.getParameter("nom");

			if(!nom.isBlank()) {
				model.addAttribute("listPersonnes", pesonneService.getAllPersonnesContainingNom(nom));
			}else {
				model.addAttribute("listPersonnes", pesonneService.getAllPersonnes());	//Get all salles
			}
		}else if(request.getParameter("ajouter") != null){
			String rfid = request.getParameter("rfid");
			String email = request.getParameter("email");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			
			if(pesonneService.ajouterPersonne(rfid, email, nom, prenom)) {
				model.addAttribute("statusAjoutSalleSuccess", "L'utilisateur " + nom + " " + prenom + " a été bien ajouté.");
			}else {
				model.addAttribute("statusAjoutSalleErreur", "Une erreur est survenur, réessayer.");
			}
			model.addAttribute("listPersonnes", pesonneService.getAllPersonnes());	//Get all salles
		}
		return "users";
	}
	
	@GetMapping({"/parametres"})
	public String editProfile(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		return "parametres";
	}
	
	@GetMapping({"/visite/salle/{idSalle}"})
	public String afficherLesVisitesDUneSalle(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, @PathVariable("idSalle") Integer idSalle) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listDesVisites", personneSalleService.getAllVisitesBySalleId(idSalle));	//Get all visites
		return "visite";
	}
	
	@GetMapping({"/visites"})
	public String afficherLesVisites(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listDesVisites", personneSalleService.getAllVisitesOrderedByDateEbtree());	//Get all visites
		return "visite";
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
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
	
	//Get page with all users
	@GetMapping({"/personnes"})
	public String getAllUsers(Model model,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {	//User is connected
			model.addAttribute("listPersonnes", pesonneService.getAllPersonnes());	//Get all salles
			Integer userId = (Integer) session.getAttribute("userId");
			userService.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));	//Get User infos
			return "users";
		}else {		//User is not connected
			return "index";
		}
	}
	
	//Get page with all users
		@GetMapping({"/parametres"})
		public String editProfile(Model model,  HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session.getAttribute("userId") != null) {	//User is connected
				model.addAttribute("listPostes", personneSalleService.getAllPostes());	//Get all salles
				Integer userId = (Integer) session.getAttribute("userId");
				userService.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));	//Get User infos
				return "editProfile";
			}else {		//User is not connected
				return "index";
			}
		}
		
		
		
		
		
		
		
		
		//------------  Partie des salles
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
		
		@PostMapping({"/", "/index"})
		public String listeDesSallesPage(Model model,  HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session.getAttribute("userId") != null) {	//User is connected
				String nomSalle = request.getParameter("nomSalle");
				String locationSalle = request.getParameter("locationSalle");
				Salle salle = new Salle();
				salle.setImage(null);
				salle.setNom(nomSalle);
				salle.setLocation(locationSalle);
				
				if(salleService.ajouterSalle(salle)) {
					model.addAttribute("statusAjoutSalle", "La salle " + nomSalle + " a été bien ajouté.");
				}else {
					model.addAttribute("statusAjoutSalle", "Une erreur est survenur, réessayer.");
				}
				
				model.addAttribute("listSalles", salleService.getAllSalles());	//Get all salles
				Integer userId = (Integer) session.getAttribute("userId");
				userService.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));	//Get User infos
				return "accueil";
			}else {		//User is not connected
				return "index";
			}
		}
		*/
}
