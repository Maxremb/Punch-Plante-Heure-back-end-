package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.IConnectedUserConverter;
import com.fr.adaming.security.interfaces.ISessionService;
import com.fr.adaming.session.IConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Une couche entre l'objet connected user et le controller
 * 
 * @author Gregoire
 *
 */
@Component
@Slf4j
public class SessionService implements ISessionService{

	@Autowired
	private IConnectedUser user;
	
	@Autowired
	private IConnectedUserConverter converter;

	public Role getUserRole(String token) {
		return user.getRole(token);
	}

	public int getUserIdentifier(String token) {
		return user.getIdentifier(token);
	}

	public String getUserEmail(String token) {
		return user.getMail(token);
	}

	public String getUserPseudo(String token) {
		return user.getPseudo(token);
	}

	/** Retourne l'objet connectedUserDto si le token est bon
	 * @param token Le token envoyé au controller
	 * @return Un objet connectedUser ou null
	 */
	public ConnectedUserDto getUser(String token) {
		
		log.debug("user: " + user);

		if (user.testToken(token)) {
			return converter.convertUsertoDto(token);
		} else {
			return null;
		}
	}

}
