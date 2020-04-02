package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fr.adaming.enums.Sol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet à communiquer au front pour la création de Jardin
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Getter @Setter @NoArgsConstructor
public class JardinCreateDto {

	private Enum<Sol> ground;

	@NotBlank
	@NotNull
	private String name;

	private Float length;

	private Float width;
	
	@NotNull
	private DepartementDto dept;  // Ne devrais pas être nul 
	
	@NotNull
	private UtilisateurCreateDto user; // Ne devrais pas être nul
	
}
