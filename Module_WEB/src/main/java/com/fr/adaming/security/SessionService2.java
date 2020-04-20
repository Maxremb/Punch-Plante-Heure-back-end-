package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.IConnectedUserConverter2;
import com.fr.adaming.security.interfaces.ISessionService2;
import com.fr.adaming.session.ConnectedUser2;

import lombok.extern.slf4j.Slf4j;

/**
 * Une couche entre l'objet connected user et le controller
 * 
 * @author Gregoire
 *
 */
@Component
@Slf4j
public class SessionService2 implements ISessionService2 {

	@Autowired
	private ConnectedUser2 user;

	@Autowired
	private IConnectedUserConverter2 converter;

	public Role getUserRole() {
		log.info("Bean Session service : méthode getUserRole apprlée");
		log.info("GetRole OK");
		return user.getRole();
	}

	public int getUserIdentifier() {
		log.info("Bean Session service : méthode getUserIdentifier");
		log.info("GetIdetnifier OK");
		return user.getIdentifier();
	}

	public String getUserEmail() {
		log.info("Bean Session service : méthode getUserEmail");
		log.info("GetMail OK");
		return user.getMail();
	}

	public String getUserPseudo() {
		log.info("Bean Session service : méthode getUserPseudo");
		log.info("GetPseudo OK");
		return user.getPseudo();
	}

	/**
	 * Retourne l'objet connectedUserDto si le token est bon
	 * 
	 * @param token Le token envoyé au controller
	 * @return Un objet connectedUser ou null
	 */
	public ConnectedUserDto getUser() {
		log.info("Bean Session service : méthode getUser appelée");

		log.debug("user: " + user);

		log.info("Conversion user vers dto OK");
		return converter.convertUsertoDto();
	}

}
