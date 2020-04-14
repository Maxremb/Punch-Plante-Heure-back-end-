package com.fr.adaming.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'objet retention à communiquer au front lors de la
 * modification
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RetentionUpdateDto extends RetentionCreateDto {

	private Integer identifier;

}
