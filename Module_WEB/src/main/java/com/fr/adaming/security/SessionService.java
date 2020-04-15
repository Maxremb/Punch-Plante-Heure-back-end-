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
		log.info("Bean Session service : méthode getUserRole apprlée");
		if (token != null) {
			log.info("GetRole OK");
			return user.getRole(token);
		} else {
			log.info("GetRole non réalisée : token null");
			return Role.None;
		}
	}

	public int getUserIdentifier(String token) {
		log.info("Bean Session service : méthode getUserIdentifier");
		if (token != null) {
			log.info("GetIdetnifier OK");
			return user.getIdentifier(token);
		} else {

			log.info("GetIdentifier non réalisée : token null");
			return 0;
		}
	}

	public String getUserEmail(String token) {
		log.info("Bean Session service : méthode getUserEmail");
		if (token != null) {
			log.info("GetMail OK");
			return user.getMail(token);
		} else {

			log.info("GetMail non réalisée : token null");
			return null;
		}
	}

	public String getUserPseudo(String token) {
		log.info("Bean Session service : méthode getUserPseudo");
		if (token != null) {
			log.info("GetPseudo OK");
			return user.getPseudo(token);
		} else {
			log.info("GetPseudo non réalisée : token null");
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
		log.info("Bean Session service : méthode getUser appelée");

		log.debug("user: " + user);

		if (token != null && user.testToken(token)) {
			log.info("Conversion user vers dto OK");
			return converter.convertUsertoDto(token);
		} else {
			log.info("Conversion non réalisée : token null");
			return null;
		}
	}

}
