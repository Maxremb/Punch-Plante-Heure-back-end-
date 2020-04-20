package com.fr.adaming.security.interfaces;

import com.fr.adaming.dto.ConnectedUserDto;

/**
 * Converteur pour pouvoir extraire les données de session de ConnectedUser2
 * @author Gregoire
 *
 */
public interface IConnectedUserConverter2 {
	
	/** Converti entre ConnectedUser et ConnectedUserDto
	 * @return Un objet ConnectedUSerDto
	 */
	public ConnectedUserDto convertUsertoDto();

}
