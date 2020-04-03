package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>Data Access Objet pour l'entite Plante Utilisateur avec l'identifiant pour les méthodes d'update et autres<br>
 * Hérite du Create Dto de cette même entite</p>
 * @author lucie
 * @since 0.0.1
 *
 */
@Getter
@Setter
public class PlanteUtilisateurUpdateDto extends PlanteUtilisateurCreateDto {
	
	@NotNull
	private int identifiant;

}
