package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.AdminUpdateDto;
import com.fr.adaming.dto.UtilisateurUpdateDto;

public interface ITokenManagement {
	
	public String makeNewSession(UtilisateurUpdateDto util) ;
	
	/**
	 * Configure la session d'un admin en enregistrant les données nécessaires et en génerant un token.
	 * @param util Un AdminUpdateDto
	 * @return Un token sous format String
	 */
	public String makeNewSession(AdminUpdateDto admin);
	
	/**
	 * Génere un token de 20 caractères 
	 * @return Un String aléatoire, 20 caractères de long.
	 */
	public String generateToken();

}
