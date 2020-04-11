package com.fr.adaming.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fr.adaming.enums.Role;

import lombok.Setter;

/**
 * Stoque les données pour une session d'un utilisateur
 * @author Gregoire
 *
 */
@SessionScope
@Component
@Setter
public class ConnectedUser {
	
	private int identifier;
	private String mail;
	private String pseudo;
	
	
	private String token;
	private Role role = Role.None;
	


	public int getIdentifier(String token) {
		
		return this.token.equals(token) ? identifier : 0;
	}

	public String getMail(String token) {
		return this.token.equals(token) ? mail : null;
	}

	public String getPseudo(String token) {
		return this.token.equals(token) ? pseudo : null;
	}

	public Role getRole(String token) {
		return this.token.equals(token) ? role : Role.None;
	}
	
	

}
