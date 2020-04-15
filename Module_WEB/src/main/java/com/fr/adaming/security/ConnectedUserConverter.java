package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.security.interfaces.IConnectedUserConverter;
import com.fr.adaming.session.IConnectedUser;

@Component
public class ConnectedUserConverter implements IConnectedUserConverter {

	@Autowired
	private IConnectedUser user;

	public ConnectedUserDto convertUsertoDto(String token) {

		if (token != null) {

			ConnectedUserDto dto = new ConnectedUserDto();
			dto.setId(user.getIdentifier(token));
			dto.setMail(user.getMail(token));
			dto.setPseudo(user.getPseudo(token));
			dto.setRole(user.getRole(token));

			return dto;
		} else {
			return null;
		}

	}

}
