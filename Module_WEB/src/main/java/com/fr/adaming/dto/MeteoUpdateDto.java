package com.fr.adaming.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentative de l'objet météo à communiquer au front lors de la
 * modification
 * 
 * @author Jeanne-Marie MATHEVET
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MeteoUpdateDto extends MeteoCreateDto {

	@NotNull
	@Min(value = 1)
	private int identifier;
}
