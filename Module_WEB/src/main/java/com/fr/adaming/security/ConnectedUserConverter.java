package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.security.interfaces.IConnectedUserConverter;
import com.fr.adaming.session.IConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gregoire
 * @deprecated en faveur de ConnectedUserConverter2
 */
@Component
@Slf4j
@Deprecated
public class ConnectedUserConverter implements IConnectedUserConverter {

	@Autowired
	private IConnectedUser user;

	public ConnectedUserDto convertUsertoDto(String token) {
		log.info("Bean ConnectedUser : méthode convertUsertoDto appelée");
		if (token != null) {
			log.info("Conversion user OK");
			ConnectedUserDto dto = new ConnectedUserDto();
			dto.setId(user.getIdentifier(token));
			dto.setMail(user.getMail(token));
			dto.setPseudo(user.getPseudo(token));
			dto.setRole(user.getRole(token));

			return dto;
		} else {
			log.info("Conversion non réalisée : token null");
			return null;
		}

	}

}
