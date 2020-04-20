package com.fr.adaming.security;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.ISessionManagement;
import com.fr.adaming.session.ConnectedUser;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe pour la gestion de sessions utilisateurs dans le front
 * @author Gregoire
 *
 */
@Component
@Deprecated
@Slf4j
public class TokenManagement{ //implements ISessionManagement{
	
	@Autowired
	private ConnectedUser user;

	/**
	 * Configure la session d'un utilisateur en enregistrant les données nécessaires et en génerant un token.
	 * @param util Un UtilisateurUpdateDto
	 * @return Un token sous format String
	 */
	public String makeNewSession(UtilisateurUpdateDto util) {
		log.info("Bean Tokenmanagement : méthode makeNewSession user appelée");
		
		user.setIdentifier(util.getIdentifier());
		user.setPseudo(util.getPseudo());
		user.setMail(util.getMail());
		user.setRole(Role.Utilisateur);
		
		String token = generateToken();
		user.setToken(token);
		
		log.info("Session user OK");
		return token;
		
	}
	
	/**
	 * Configure la session d'un admin en enregistrant les données nécessaires et en génerant un token.
	 * @param util Un AdminUpdateDto
	 * @return Un token sous format String
	 */
	public String makeNewSession(AdminUpdateDto admin) {
		log.info("Bean Tokenmanagement : méthode makeNewAdmin admin appelée");
		
		user.setIdentifier(admin.getIdentifier());
		user.setPseudo(admin.getPseudo());
		user.setMail(admin.getMail());
		user.setRole(Role.Admin);
		
		String token = generateToken();
		user.setToken(token);
		
		log.info("Session admin OK");
		return token;
		
	}
	
	/**
	 * Génere un token de 20 caractères 
	 * @return Un String aléatoire, 20 caractères de long.
	 */
	public String generateToken() {
		log.info("Bean Tokenmanagement : méthode generateToken");
		
		Random random = new SecureRandom();
		
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-!£€&~#'@<>,.?!$%^*() {}[]=|`¬¦_";
		int tokenSize = 50;
		StringBuilder token = new StringBuilder(tokenSize);
		
		
		for (int i=0; i<tokenSize; i++) {
			int n = random.nextInt(alphabet.length());
			token.append(alphabet.charAt(n));
		}
		log.info("Token généré !");
		return new String(token);
	}
	
}
