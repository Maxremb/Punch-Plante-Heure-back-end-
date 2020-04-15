package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.ConnectedUserDto;
import com.fr.adaming.enums.Role;

/**
 * Une couche entre l'objet connected user et le controller.
 * @author Gregoire
 *
 */
public interface ISessionService {
	
	public Role getUserRole(String token);

	public int getUserIdentifier(String token);

	public String getUserEmail(String token);

	public String getUserPseudo(String token) ;

	/** Retourne l'objet connectedUserDto si le token est bon
	 * @param token Le token envoyé au controller
	 * @return Un objet connectedUser ou null
	 */
	public ConnectedUserDto getUser(String token);

}
