package com.fr.adaming.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.IJardinConverter;
import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.ISessionService2;
import com.fr.adaming.service.IJardinService;
import com.fr.adaming.service.IPlanteUtilisateurService;

import lombok.extern.slf4j.Slf4j;

//TODO Testes sur toute la partie sécuité

/**
 * Controller pour la récuperation de données associés à une session
 * 
 * @author Gregoire
 *
 */
@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@RequestMapping(path = "/session")
@Slf4j
public class SessionController2 {

	@Autowired
	private ISessionService2 service;

	@Autowired
	private IJardinService jService;
	
	@Autowired
	private IPlanteUtilisateurService uService;

	@Autowired
	private IJardinConverter jconvert;
	
	@GetMapping(path = "/plants")
	public ResponseEntity<List<Integer>> getUserPlants() {
		log.info("Controller Session : méthode getUserGardens appelée");

		List<Integer> plantIdList = new ArrayList<Integer>();
		int localId = service.getUserIdentifier();
		
		log.debug("Session getUserGardens: ConnectedUser id = " + localId);
		
		HttpStatus status = HttpStatus.OK;

		if (localId == 0) {
			log.warn("token invalide ou session null: Http status 400");
			status = HttpStatus.BAD_REQUEST;
		} else {

			plantIdList = uService.readByUtilisateurId(localId).getBody();

		}

		return ResponseEntity.status(status).body(plantIdList);

	} 

	@GetMapping(path = "/gardens")
	public ResponseEntity<List<Integer>> getUserGardens() {
		log.info("Controller Session : méthode getUserGardens appelée");

		List<Integer> gardenIdList = new ArrayList<Integer>();
		int localId = service.getUserIdentifier();
		
		log.debug("Session getUserGardens: ConnectedUser id = " + localId);
		
		HttpStatus status = HttpStatus.OK;

		if (localId == 0) {
			log.warn("token invalide ou session null: Http status 400");
			status = HttpStatus.BAD_REQUEST;
		} else {

			gardenIdList = jconvert.convertJardinListToId(jService.readByUtilisateur(localId).getBody());

		}

		return ResponseEntity.status(status).body(gardenIdList);

	}

	@GetMapping(path = "/user")
	public ResponseEntity<ConnectedUserDto> getUser() {
		log.info("Controller Session : méthode getUser appelée");
		ConnectedUserDto user = service.getUser();
		HttpStatus status = HttpStatus.OK;

		if (user == null) {
			log.info("Utilisateur non trouvé : Http statut 400");
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(user);

	}

	/**
	 * Utilisée par l'auth guard du front, permet de récupérer le role de
	 * l'utilisateur (admin/utilisateur/none)
	 * 
	 * @param token Le token associé à la session
	 * @return ResponseEntity contenant un role
	 */
	@GetMapping(path = "/role")
	public ResponseEntity<Role> getUserRole() {
		log.info("Controller Session : méthode GetUserRole appelée");
		Role role = service.getUserRole();
		HttpStatus status = HttpStatus.OK;

		if (role == Role.None) {
			log.info("Utilisateur sans rôle : Http Statut 400");
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(role);

	}

	/**
	 * Pour récuperer l'id de l'utilisateur associé à la session
	 * 
	 * @param token Le token
	 * @return ResponseEntity contenant un int
	 */
	@GetMapping(path = "/identifier")
	public ResponseEntity<Integer> getUserIdentifier() {
		log.info("Controller Session : méthode GetUserIdentifier appelée");
		int id = service.getUserIdentifier();
		HttpStatus status = HttpStatus.OK;

		if (id == 0) {
			log.info("Id utilisateur = 0 : Http status 400");
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(id);

	}

	/**
	 * Pour récuperer l'email associé à la session
	 * 
	 * @param token Le token de la session
	 * @return ResponseEntity contenant l'email en format String.
	 */
	@GetMapping(path = "/email")
	public ResponseEntity<String> getUserEmail() {
		log.info("Controller Session : méthode GetUserMail appelée");
		String email = service.getUserEmail();

		HttpStatus status = HttpStatus.OK;

		if (email == null) {
			log.info(" Email utilisateur null : Http Statut 400");
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(email);

	}

	/**
	 * Pour récuperer le pseudo associé à la session.
	 * 
	 * @param token Le token de la session
	 * @return ResponseEntity contenant le pseudo en format string.
	 */
	@GetMapping(path = "/pseudo")
	public ResponseEntity<String> getUserPseudo() {
		log.info("Controller Session : méthode GetUserPseudo appelée");
		String pseudo = service.getUserPseudo();

		HttpStatus status = HttpStatus.OK;

		if (pseudo == null) {
			log.info("Pseudo utilisateur null : Http Statut 400 ");
			status = HttpStatus.BAD_REQUEST;
		}

		return ResponseEntity.status(status).body(pseudo);

	}

}