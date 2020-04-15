package com.fr.adaming.session;

import com.fr.adaming.enums.Role;

/**
 * Stock les details d'une session utilisateur
 * @author Gregoire
 *
 */
public interface IConnectedUser {
	
	public int getIdentifier(String token) ;

	public String getMail(String token) ;

	public String getPseudo(String token) ;

	public Role getRole(String token) ;
	
	public boolean testToken(String token) ;

}
