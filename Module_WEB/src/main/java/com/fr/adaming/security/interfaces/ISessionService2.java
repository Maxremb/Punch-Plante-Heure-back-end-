package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.enums.Role;

/**
 * Une couche entre l'objet connected user et le controller.
 * @author Gregoire
 *
 */
public interface ISessionService2 {
	
	public Role getUserRole();

	public int getUserIdentifier();

	public String getUserEmail();

	public String getUserPseudo() ;

	/** Retourne l'objet connectedUserDto si le token est bon
	 * @param token Le token envoyé au controller
	 * @return Un objet connectedUser ou null
	 */
	public ConnectedUserDto getUser();

}
