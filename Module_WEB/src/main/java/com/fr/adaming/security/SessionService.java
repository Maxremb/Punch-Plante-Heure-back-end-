package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.enums.Role;
import com.fr.adaming.session.ConnectedUser;

@Component
public class SessionService {
	
	@Autowired
	private ConnectedUser user;
	
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

}
