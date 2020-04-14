package com.fr.adaming.controller.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.AdminCreateDto;
import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.ConnexionDto;
import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.dto.UtilisateurCreateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Utilisateur;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.SessionService;
import com.fr.adaming.security.TokenManagement;
import com.fr.adaming.service.IAdminService;
import com.fr.adaming.service.IUtilisateurService;
import com.fr.adaming.session.ConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller pour la récuperation de données associés à une session
 * @author Gregoire
 *
 */
@RestController
@CrossOrigin(exposedHeaders = "Set-Cookie", allowCredentials = "true", origins = "http://localhost:4200")
@RequestMapping(path = "/session")
@Slf4j
public class SessionController {
	
	
	@Autowired
	private IAdminService adminService;

	@Autowired
	private IUtilisateurService userService;

	@Autowired
	private IConverter<UtilisateurCreateDto, UtilisateurUpdateDto, Utilisateur> utilConv;

	@Autowired
	private IConverter<AdminCreateDto, AdminUpdateDto, Admin> adminConv;
	
	@Autowired
	private TokenManagement tokenManagement;
	
	// *********//
	
	@Autowired
	private SessionService service;
	
	@Autowired
	private ConnectedUser user;
	
	@GetMapping(path = "/hello")
	public ResponseEntity<String> getHello(HttpServletResponse response){
		Cookie cookie = new Cookie("Bob", "123");
		
		response.addCookie(cookie);
		log.debug("getHello appelé");
		
		return ResponseEntity.status(200).body("hello");
		
	}
	
	/** Utilisée par l'auth guard du front, permet de récupérer le role de l'utilisateur (admin/utilisateur/none) 
	 * @param token Le token associé à la session
	 * @return ResponseEntity contenant un role
	 */
	@PostMapping(path = "/role")
	public ResponseEntity<Role> getUserRole(@RequestBody String token){
		
		log.debug("SessionController: connectedUser=" + user);
		log.debug("SessionController: token=" + token);
		
//		Role role = service.getUserRole(token);
		Role role = user.getRole(token);
		HttpStatus status = HttpStatus.OK;
		
		if(role == Role.None) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		
		return ResponseEntity.status(status).body(role);
		
	}
	
	/** Pour récuperer l'id de l'utilisateur associé à la session
	 * @param token Le token
	 * @return ResponseEntity contenant un int
	 */
	@PostMapping(path = "/identifier")
	public ResponseEntity<Integer> getUserIdentifier(String token){
		
		int id = service.getUserIdentifier(token);
		HttpStatus status = HttpStatus.OK;
		
		if(id == 0) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(id);
		
	}
	
	/** Pour récuperer l'email associé à la session
	 * @param token Le token de la session
	 * @return ResponseEntity contenant l'email en format String.
	 */
	@PostMapping(path = "/email")
	public ResponseEntity<String> getUserEmail(String token){
		
		String email = service.getUserEmail(token);
		
		HttpStatus status = HttpStatus.OK;
		
		if(email == null) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(email);
		
	}
	
	/** Pour récuperer le pseudo associé à la session.
	 * @param token Le token de la session
	 * @return ResponseEntity contenant le pseudo en format string.
	 */
	@PostMapping(path = "/pseudo")
	public ResponseEntity<String> getUserPseudo(String token){
		
		String pseudo = service.getUserPseudo(token);
		
		HttpStatus status = HttpStatus.OK;
		
		if(pseudo == null) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(pseudo);
		
	}
	
	// TODO Copié de adminController pour debug
	
	@PostMapping(path = "/mailAndPwd")
	public ResponseEntity<ConnexionDto> existsByMailandPwd(@RequestBody String[] tableau, HttpServletResponse response) {
		ConnexionDto connexionDto = new ConnexionDto();
		try {

			System.out.println(response.getHeader("Set-Cookie"));
			Cookie cookie = new Cookie("Bobby", "1234");
			response.addCookie(cookie);
			
			String mail = tableau[0];
			String pwd = tableau[1];
			if (userService.existsByEmailAndMdp(mail, pwd).getBody() != null) {
				ServiceResponse<Utilisateur> serviceResponse = userService.existsByEmailAndMdp(mail, pwd);

				UtilisateurUpdateDto returnedUtil = utilConv.convertEntityToUpdateDto(serviceResponse.getBody());
				String token = tokenManagement.makeNewSession(returnedUtil); // Generation de tokens pour la sécurité du front

				connexionDto.setUser(true);
				connexionDto.setBodyAdmin(null);
				connexionDto.setBodyUtil(returnedUtil);
				connexionDto.setToken(token);

				return ResponseEntity.status(HttpStatus.OK).body(connexionDto);

			} else if (adminService.existsByEmailAndMdp(mail, pwd).getBody() != null
					&& userService.existsByEmailAndMdp(mail, pwd).getBody() == null) {
				ServiceResponse<Admin> serviceResponse = adminService.existsByEmailAndMdp(mail, pwd);

				AdminUpdateDto returnedAdmin = adminConv.convertEntityToUpdateDto(serviceResponse.getBody());
				String token = tokenManagement.makeNewSession(returnedAdmin);

				connexionDto.setUser(false);
				connexionDto.setBodyAdmin(returnedAdmin);
				connexionDto.setBodyUtil(null);
				connexionDto.setToken(token);

				return ResponseEntity.status(HttpStatus.OK).body(connexionDto);
			} else {

				connexionDto.setUser(false);
				connexionDto.setBodyAdmin(null);
				connexionDto.setBodyUtil(null);
				connexionDto.setToken(null);

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(connexionDto);
			}

		} catch (NullPointerException e) {
			log.info("Null Pointer Exception" + e.getMessage());
			connexionDto.setUser(false);
			connexionDto.setBodyAdmin(null);
			connexionDto.setBodyUtil(null);
			connexionDto.setToken(null);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(connexionDto);
		}

	}

}
