package com.fr.adaming.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.ISessionService;

/**
 * Controller pour la récuperation de données associés à une session
 * @author Gregoire
 *
 */
@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@RequestMapping(path = "/session")
public class SessionController {
	
	@Autowired
	private ISessionService service;
	
	@PostMapping(path = "/user")
	public ResponseEntity<ConnectedUserDto> getUser(@RequestBody String token){
		
		ConnectedUserDto user = service.getUser(token);
		HttpStatus status = HttpStatus.OK;
		
		if(user == null) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		
		return ResponseEntity.status(status).body(user);
		
	}
	
	/** Utilisée par l'auth guard du front, permet de récupérer le role de l'utilisateur (admin/utilisateur/none) 
	 * @param token Le token associé à la session
	 * @return ResponseEntity contenant un role
	 */
	@PostMapping(path = "/role")
	public ResponseEntity<Role> getUserRole(@RequestBody String token){
		
		Role role = service.getUserRole(token);
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
	public ResponseEntity<Integer> getUserIdentifier(@RequestBody String token){
		
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
	public ResponseEntity<String> getUserEmail(@RequestBody String token){
		
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
	public ResponseEntity<String> getUserPseudo(@RequestBody String token){
		
		String pseudo = service.getUserPseudo(token);
		
		HttpStatus status = HttpStatus.OK;
		
		if(pseudo == null) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(pseudo);
		
	}

}
