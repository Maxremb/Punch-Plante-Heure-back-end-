package com.fr.adaming.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet plante model simplifier à communiquer au
 * front
 * 
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteModelReducedDto {

	private String commun;
	private String scientifique;
	int identifiant;
	private String picture;

}
