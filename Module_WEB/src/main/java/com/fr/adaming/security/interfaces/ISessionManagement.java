package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;

/**
 * Classe pour la gestion de sessions utilisteur du front.
 * @author Gregoire
 *
 */
public interface ISessionManagement {
	
	/**
	 * Configure la session d'un utilisateur en enregistrant les données nécessaires dans connectedUser2.
	 * @param util Un AdminUpdateDto
	 * @return Un token sous format String
	 */
	public void makeNewSession(UtilisateurUpdateDto util) ;
	
	/**
	 * Configure la session d'un admin en enregistrant les données nécessaires dans connectedUser2.
	 * @param util Un AdminUpdateDto
	 * @return Un token sous format String
	 */
	public void makeNewSession(AdminUpdateDto admin);

}
