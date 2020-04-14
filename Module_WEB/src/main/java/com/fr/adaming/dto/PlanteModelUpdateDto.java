package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet plante model à communiquer au front lors de
 * la modification
 * 
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlanteModelUpdateDto extends PlanteModelCreateDto {

	@Positive(message = "PlanteModelUpdateDto: Un identifiant ne peut pas etre 0 ou negatif")
	int identifiant;

}
