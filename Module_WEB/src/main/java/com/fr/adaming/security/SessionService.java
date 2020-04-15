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
public class SessionService implements ISessionService {

	@Autowired
	private IConnectedUser user;

	@Autowired
	private IConnectedUserConverter converter;

	public Role getUserRole(String token) {
		if (token != null) {
			return user.getRole(token);
		} else {
			return Role.None;
		}
	}

	public int getUserIdentifier(String token) {
		if (token != null) {
			return user.getIdentifier(token);
		} else {
			return 0;
		}
	}

	public String getUserEmail(String token) {
		if (token != null) {
			return user.getMail(token);
		} else {
			return null;
		}
	}

	public String getUserPseudo(String token) {
		if (token != null) {
			return user.getPseudo(token);
		} else {
			return null;
		}
	}

	/**
	 * Retourne l'objet connectedUserDto si le token est bon
	 * 
	 * @param token Le token envoyé au controller
	 * @return Un objet connectedUser ou null
	 */
	public ConnectedUserDto getUser(String token) {

		log.debug("user: " + user);

		if (token != null && user.testToken(token)) {
			return converter.convertUsertoDto(token);
		} else {
			return null;
		}
	}

}
