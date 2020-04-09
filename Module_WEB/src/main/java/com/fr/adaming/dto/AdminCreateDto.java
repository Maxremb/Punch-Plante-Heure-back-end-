package com.fr.adaming.dto;

import org.hibernate.validator.constraints.UniqueElements;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'objet à communiquer au front pour la création d'un Admin
 * 
 * @author Isaline MILLET
 * @since 0.0.1
 */
@Getter @Setter @EqualsAndHashCode
public class AdminCreateDto {
	
	@NotNull
	private String pwd;
	
	@NotNull
	private String pseudo;
	
	@NotNull
	private String mail;

}
