package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'objet à communiquer au front pour la création d'un Utilisateur
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Getter @Setter @EqualsAndHashCode(callSuper = true)
public class UtilisateurUpdateDto extends UtilisateurCreateDto {

	@NotNull @Positive
	private Integer identifier; 
	
}
