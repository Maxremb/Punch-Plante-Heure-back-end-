package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.ConnectedUserDto;

/**
 * Converteur pour pouvoir extraire les données de session de ConnectedUser
 * @author Gregoire
 *
 */
public interface IConnectedUserConverter {
	
	public ConnectedUserDto convertUsertoDto(String token);

}
