package com.fr.adaming.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'objet à communiquer pour la connexion d'un utilisateur/admin
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Getter @Setter @EqualsAndHashCode
public class ConnexionDto {
	
	boolean isUser ;
	
	String token;
	
	AdminUpdateDto bodyAdmin;
	
	UtilisateurUpdateDto bodyUtil;

}
