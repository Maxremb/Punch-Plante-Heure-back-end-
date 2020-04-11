package com.fr.adaming.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.enums.Role;

@SessionScope
@Component
public class ConnectedUser {
	
	private int identifier;
	private String mail;
	private String pseudo;
	
	
	private String token;
	private Role role;
	
	public ConnectedUser(UtilisateurUpdateDto util, String token) {
		
		this.identifier = util.getIdentifier();
		this.mail = util.getMail();
		this.pseudo = util.getPseudo();
		this.role = Role.Utilisateur;
		this.token = token;
		
	}
	
	public ConnectedUser(AdminUpdateDto admin, String token) {
		
		this.identifier = admin.getIdentifier();
		this.mail = admin.getMail();
		this.pseudo = admin.getPseudo();
		this.role = Role.Admin;
		this.token = token;
		
	}

	public int getIdentifier(String token) {
		
		return this.token == token ? identifier : 0;
	}

	public String getMail(String token) {
		return this.token == token ? mail : null;
	}

	public String getPseudo(String token) {
		return this.token == token ? pseudo : null;
	}

	public Role getRole(String token) {
		return role;
	}
	
	

}
