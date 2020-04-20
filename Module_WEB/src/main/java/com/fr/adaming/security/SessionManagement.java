package com.fr.adaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;
import com.fr.adaming.enums.Role;
import com.fr.adaming.security.interfaces.ISessionManagement;
import com.fr.adaming.session.ConnectedUser2;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe pour la gestion de sessions utilisateurs dans le front. Remplace TokenManagement.
 * @
 * @author Gregoire
 *
 */
@Component
@Slf4j
public class SessionManagement implements ISessionManagement{
	
	@Autowired
	private ConnectedUser2 user;

	public void makeNewSession(UtilisateurUpdateDto util) {
		log.info("Bean Tokenmanagement : méthode makeNewSession user appelée");
		
		user.setIdentifier(util.getIdentifier());
		user.setPseudo(util.getPseudo());
		user.setMail(util.getMail());
		user.setRole(Role.Utilisateur);
		
		log.info("Session user OK");
		
	}
	
	public void makeNewSession(AdminUpdateDto admin) {
		log.info("Bean Tokenmanagement : méthode makeNewAdmin admin appelée");
		
		user.setIdentifier(admin.getIdentifier());
		user.setPseudo(admin.getPseudo());
		user.setMail(admin.getMail());
		user.setRole(Role.Admin);
		
		log.info("Session admin OK");
		
	}
	
}
