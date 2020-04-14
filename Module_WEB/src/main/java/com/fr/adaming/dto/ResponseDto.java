package com.fr.adaming.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentative de l'objet Response Dto à communiquer au front lors de
 * la réponse de chaque méthode du controller
 * 
 * @since 0.0.1-SNAPSHOT
 *
 * @param <T> Type d'objet à renvoyer
 */
@Getter
@Setter
@EqualsAndHashCode
public class ResponseDto<T> {

	boolean error;
	String message;
	T body;

}
