package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.session.ConnectedUser;

@Component
public class ConnectedUserConverter {
	
	@Autowired
	ConnectedUser user;
	
	public ConnectedUserDto convertUsertoDto(String token) {
		
		ConnectedUserDto dto = new ConnectedUserDto();
		dto.setId(user.getIdentifier(token));
		dto.setMail(user.getMail(token));
		dto.setPseudo(user.getPseudo(token));
		dto.setRole(user.getRole(token));
		
		return dto;
		
	}

}
