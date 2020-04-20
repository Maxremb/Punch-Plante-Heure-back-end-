package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.security.interfaces.IConnectedUserConverter2;
import com.fr.adaming.session.ConnectedUser2;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConnectedUserConverter2 implements IConnectedUserConverter2 {

	@Autowired
	private ConnectedUser2 user;

	public ConnectedUserDto convertUsertoDto() {
		log.info("Bean ConnectedUser : méthode convertUsertoDto appelée");
			log.info("Conversion user OK");
			ConnectedUserDto dto = new ConnectedUserDto();
			dto.setId(user.getIdentifier());
			dto.setMail(user.getMail());
			dto.setPseudo(user.getPseudo());
			dto.setRole(user.getRole());

			return dto;

	}

}
