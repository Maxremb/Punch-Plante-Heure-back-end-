package com.fr.adaming.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fr.adaming.enums.Sol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet à communiquer au front pour la modification de Jardin
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Getter @Setter @NoArgsConstructor
public class JardinUpdateDto {

	@NotNull
	@Min(value = 1)
	private Integer id;

	private Enum<Sol> sol;

	@NotBlank
	@NotNull
	private String nom;

	private Float longueur;

	private Float largeur;
	
	@NotNull
	private DepartementDto departement;  // Ne devrais pas être nul 
	
	@NotNull
	private UtilisateurCreateDto utilisateur; // Ne devrais pas être nul
	
}
