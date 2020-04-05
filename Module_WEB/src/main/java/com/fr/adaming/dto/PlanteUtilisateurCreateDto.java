package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fr.adaming.enums.EtatPlante;
import com.fr.adaming.enums.EtatSante;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data access Objet pour l'entite Plante Utilisateur, sans l'id, utiliser pour
 * la méthode create().
 * </p>
 * 
 * @author lucie
 * @since 0.0.1
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PlanteUtilisateurCreateDto {

	private EtatPlante plantStage;

	private EtatSante healthStage;

	private LocalDate semiDate;

	private LocalDate plantingDate;

	@NotNull
	private JardinUpdateDto garden;

	@NotNull
	private PlanteModelUpdateDto modelPlant;

}
