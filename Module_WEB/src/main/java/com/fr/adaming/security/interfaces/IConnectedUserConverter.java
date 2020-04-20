package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.ConnectedUserDto;

/**
 * Converteur pour pouvoir extraire les données de session de ConnectedUser. 
 * @deprecated En faveur de IConnectedUserConverter2
 * @author Gregoire
 *
 */
@Deprecated
public interface IConnectedUserConverter {
	
	public ConnectedUserDto convertUsertoDto(String token);

}
