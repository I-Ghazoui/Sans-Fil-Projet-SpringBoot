package com.sansfil.app.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sansfil.app.Service.PersonneSalleService;
import com.sansfil.app.Service.PersonneService;
import com.sansfil.app.Service.SalleService;
import com.sansfil.app.Service.UserService;
import com.sansfil.app.model.User;
import com.sansfil.app.security.CustomUserDetails;

@Controller
public class UserController {
	
	private static String uploadDirectory = "src\\main\\resources\\static\\assets\\img\\uploads\\";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SalleService salleService;
	
	@Autowired
	private PersonneSalleService personneSalleService;
	
	@Autowired
	private PersonneService pesonneService;
	
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
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
			Model model,@RequestParam(value="image", required=false) MultipartFile image, HttpServletRequest request ) {
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
			String imageName = "salle-image-" + image.getOriginalFilename();
			
			Path fileNameAndPath = Paths.get(uploadDirectory, imageName);
			
			try {
				Files.write(fileNameAndPath, image.getBytes());
				
				String imagePath = "/assets/img/uploads/" + imageName;
				
				if(salleService.ajouterSalle(nom, etage, imagePath)) {
					model.addAttribute("statusAjoutSalleSuccess", "La salle " + nom + " a été bien ajouté.");
				}else {
					model.addAttribute("statusAjoutSalleErreur", "Une erreur est survenur, réessayer.");
				}
			} catch (IOException e) {
				e.printStackTrace();
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
			Model model, @RequestParam(value="image", required=false) MultipartFile image, HttpServletRequest request) {
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
			String poste = request.getParameter("poste");
			String imageName = rfid + "-image-" + image.getOriginalFilename();
			
			Path fileNameAndPath = Paths.get(uploadDirectory, imageName);
			
			try {
				Files.write(fileNameAndPath, image.getBytes());
				
				String imagePath = "/assets/img/uploads/" + imageName;
				
				if(pesonneService.ajouterPersonne(rfid, email, nom, prenom, poste, imagePath)) {
					model.addAttribute("statusAjoutSalleSuccess", "L'utilisateur " + nom + " " + prenom + " a été bien ajouté.");
				}else {
					model.addAttribute("statusAjoutSalleErreur", "Une erreur est survenur, réessayer.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("statusAjoutSalleErreur", "Une erreur est survenur, réessayer.");
			}
			
			
			model.addAttribute("listPersonnes", pesonneService.getAllPersonnes());	//Get all salles
			
		}else if(request.getParameter("supprimer") != null) {
			
			String rfid = request.getParameter("rfid");
			
			pesonneService.deletePersonne(rfid);
			
			model.addAttribute("listPersonnes", pesonneService.getAllPersonnes());	//Get all salles
		}
		return "users";
	}
	
	
	//------------------------------------------------------------ GET et POST pour /parametres --------------------
	@GetMapping({"/parametres"})
	public String editProfile(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		return "parametres";
	}
	
	@PostMapping({"/parametres"})
	public String editProfileUpdating(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, @RequestParam(value="image", required=false) MultipartFile image, HttpServletRequest request) {
		User user = loggedUser.getUser();	//Get logged in user informations
		
		String oldPassword = request.getParameter("oldPassword");
		
		String username = request.getParameter("username");	
		String email = request.getParameter("email");
		
		if(username != null && userService.findUserByUsername(username).isPresent()) {
			
			model.addAttribute("statusUpdateUserErreur", "Nom d'utilisateur déja utilisé, réessayer.");
			
		}else if(email != null && userService.findUserByEmail(email).isPresent()) {
			
			model.addAttribute("statusUpdateUserErreur", "Email déja utilisé, réessayer.");
			
		}else if(getPasswordEncoder().matches(oldPassword, user.getPassword())) {
			
			String firstName = request.getParameter("firstName");	
			String lastName = request.getParameter("lastName");	
				
			String password = request.getParameter("password");	
			
			if(firstName != null && !firstName.isBlank()) {
				user.setFirstName(firstName);
			}
			if(lastName != null && !lastName.isBlank()) {
				user.setLastName(lastName);
			}
			if(username != null && !username.isBlank()) {
				user.setUsername(username);
			}
			if(email != null && !email.isBlank()) {
				user.setEmail(email);
			}
			if(password != null && !password.isBlank()) {
				user.setPassword(password);
			}
			if(image != null) {
				String imageName = user.getUsername() + "-image-" + image.getOriginalFilename();
				
				Path fileNameAndPath = Paths.get(uploadDirectory, imageName);
				
				try {
					Files.write(fileNameAndPath, image.getBytes());
					
					String imagePath = "/assets/img/uploads/" + imageName;
					
					user.setImage(imagePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(password != null && !password.isBlank()) {
				userService.saveUser(user);
			}else {
				userService.updateUserNoEncodingPassword(user);
			}
			
			model.addAttribute("statusUpdateUserSuccess", "Vous avez bien modifier vos coordonnées.");
		}else {
			model.addAttribute("statusUpdateUserErreur", "Mot de passe actuel incorrect, réessayer.");
		}
		
		model.addAttribute("user", user);
		return "parametres";
	}
	//---------------------------------------------------------------------------------------------------------------------------

	
	
	
	
	
	
	
	
	
	
	
	//------------------------------------------------------------ GET et POST pour /visite/salle/{idSalle} --------------------
	@GetMapping({"/visite/salle/{idSalle}"})
	public String afficherLesVisitesDUneSalle(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, @PathVariable("idSalle") Integer idSalle) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listDesVisites", personneSalleService.getAllVisitesBySalleId(idSalle));	//Get all visites
		return "visite";
	}
	
	//Find visites by dates
	@PostMapping({"/visite/salle/{idSalle}"})
	public String afficherLesVisitesDUneSalleAvecDate(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, @PathVariable("idSalle") Integer idSalle, HttpServletRequest request) throws ParseException{
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		
		String dateStr = request.getParameter("date");	//Date entrée
		
		if(dateStr != null && !dateStr.isBlank()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime startDate = LocalDateTime.parse(dateStr + " 00:00:00", formatter);
			LocalDateTime endDate = LocalDateTime.parse(dateStr + " 23:59:59", formatter);
			
			model.addAttribute("listDesVisites", 
					personneSalleService.getAllVisitesBySalleIdAndDate(idSalle, startDate, endDate));	//Get all visites
		}else {
			model.addAttribute("listDesVisites", 
					personneSalleService.getAllVisitesBySalleId(idSalle));	//Get all visites
		}
		return "visite";
	}
	//---------------------------------------------------------------------------------------------------------------------------

	
	
	
	
	
	//------------------------------------------------------------ GET et POST pour /visite/salle/{idSalle} --------------------
	@GetMapping({"/visite/personne/{idPersonne}"})
	public String afficherLesVisitesDUnePersonne(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, @PathVariable("idPersonne") String idPersonne) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listDesVisites", personneSalleService.getAllVisitesByPersonneId(idPersonne));	//Get all visites
		return "visite";
	}
	
	@PostMapping({"/visite/personne/{idPersonne}"})
	public String afficherLesVisitesDUnePersonneAvecDate(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, @PathVariable("idPersonne") String idPersonne, HttpServletRequest request) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		
		String dateStr = request.getParameter("date");	//Date entrée
		
		if(dateStr != null && !dateStr.isBlank()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime startDate = LocalDateTime.parse(dateStr + " 00:00:00", formatter);
			LocalDateTime endDate = LocalDateTime.parse(dateStr + " 23:59:59", formatter);
			
			model.addAttribute("listDesVisites", 
					personneSalleService.getAllVisitesByPersonneIdAndDate(idPersonne, startDate, endDate));	//Get all visites
		}else {
			model.addAttribute("listDesVisites", 
					personneSalleService.getAllVisitesByPersonneId(idPersonne));	//Get all visites
		}
		return "visite";
	}
	
	//---------------------------------------------------------------------------------------------------------------------------

	
	
	
	
	
	
	
	//------------------------------------------------------------ GET et POST pour /visites --------------------
	@GetMapping({"/visites"})
	public String afficherLesVisites(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		model.addAttribute("listDesVisites", personneSalleService.getAllVisitesOrderedByDateEntree());	//Get all visites
		return "visite";
	}
	
	@PostMapping({"/visites"})
	public String afficherLesVisitesAvecDate(@AuthenticationPrincipal CustomUserDetails loggedUser,
			Model model, HttpServletRequest request) {
		User user = loggedUser.getUser();	//Get logged in user informations
		model.addAttribute("user", user);
		
		String dateStr = request.getParameter("date");	//Date entrée
		
		if(dateStr != null && !dateStr.isBlank()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime startDate = LocalDateTime.parse(dateStr + " 00:00:00", formatter);
			LocalDateTime endDate = LocalDateTime.parse(dateStr + " 23:59:59", formatter);
			
			model.addAttribute("listDesVisites", 
					personneSalleService.getAllVisitesOrderedByDateEntreeAndDate(startDate, endDate));	//Get all visites
		}else {
			model.addAttribute("listDesVisites", 
					personneSalleService.getAllVisitesOrderedByDateEntree());	//Get all visites
		}
		return "visite";
	}
	//---------------------------------------------------------------------------------------------------------------------------

	
	
	
	
	
	
	
	
	
	
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
