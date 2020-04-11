package com.fr.adaming.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fr.adaming.enums.Role;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Stoque les données pour une session d'un utilisateur
 * @author Gregoire
 *
 */
@SessionScope
@Component
@Setter
@ToString
@Slf4j
public class ConnectedUser {
	
	private int identifier;
	private String mail;
	private String pseudo;
	
	
	private String token;
	private Role role = Role.None;
	


	public int getIdentifier(String token) {
		
		return token.equals(this.token) ? identifier : 0;
	}

	public String getMail(String token) {
		return token.equals(this.token) ? mail : null;
	}

	public String getPseudo(String token) {
		return token.equals(this.token) ? pseudo : null;
	}

	public Role getRole(String token) {
		log.debug("token input: " + token);
		log.debug("token in memory: " + this.token);
		log.debug("role: " + this.role);
		return token.equals(this.token) ? role : Role.None;
	}
	
	

}
