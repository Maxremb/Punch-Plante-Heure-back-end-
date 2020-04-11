package com.fr.adaming.security;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.session.ConnectedUser;

/**
 * Classe pour la gestion de sessions utilisateurs dans le front
 * @author Gregoire
 *
 */
@Component
public class TokenManagement {
	
	@Autowired
	private ConnectedUser user;

	/**
	 * Configure la session d'un utilisateur en enregistrant les données nécessaires et en génerant un token.
	 * @param util Un UtilisateurUpdateDto
	 * @return Un token sous format String
	 */
	public String makeNewSession(UtilisateurUpdateDto util) {
		
		user.setIdentifier(util.getIdentifier());
		user.setPseudo(util.getPseudo());
		user.setMail(util.getMail());
		user.setRole(Role.Utilisateur);
		
		String token = generateToken();
		user.setToken(token);
		
		return token;
		
	}
	
	/**
	 * Configure la session d'un admin en enregistrant les données nécessaires et en génerant un token.
	 * @param util Un AdminUpdateDto
	 * @return Un token sous format String
	 */
	public String makeNewSession(AdminUpdateDto admin) {
		
		user.setIdentifier(admin.getIdentifier());
		user.setPseudo(admin.getPseudo());
		user.setMail(admin.getMail());
		user.setRole(Role.Admin);
		
		String token = generateToken();
		user.setToken(token);
		
		return token;
		
	}
	
	/**
	 * Méthode provisoire pour la déconnexion
	 */
	public void disconnectSession() {
		user.setIdentifier(0);
		user.setPseudo(null);
		user.setMail(null);
		user.setRole(Role.None);
		user.setToken(null);
	}
	
	/**
	 * Génere un token de 20 caractères 
	 * @return Un String aléatoire, 20 caractères de long.
	 */
	public String generateToken() {
		
		Random random = new SecureRandom();
		
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-!£&~#'@<>,.?!";
		int tokenSize = 20;
		StringBuilder token = new StringBuilder(tokenSize);
		
		
		for (int i=0; i<tokenSize; i++) {
			int n = random.nextInt(alphabet.length());
			token.append(alphabet.charAt(n));
		}
		
		return new String(token);
	}
	
}
