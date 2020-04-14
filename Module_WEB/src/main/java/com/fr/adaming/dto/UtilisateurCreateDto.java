package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;

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
public class UtilisateurCreateDto extends AdminCreateDto {

	@NotBlank
	private String lastName;
	
	@NotBlank
	private String firstName;
	
	private String desc;
	 
	private Integer phone;
	
	private String reput;
	
	private Boolean news;

	private Boolean active = true;
	
	private String picture;
}
