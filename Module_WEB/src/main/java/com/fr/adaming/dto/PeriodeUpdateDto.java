package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'objet période à communiquer au front lors de la
 * modification
 * 
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@EqualsAndHashCode
public class PeriodeUpdateDto extends PeriodeCreateDto {

	@Positive(message = "l'identifiant de l'uttilisateur doit être positif")
	private int identity;

}
