package com.fr.adaming.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Classe permettant la mise en place de page d'objet. Elle étend la classe
 * PageImpl.
 * 
 * @since 0.0.1-SNAPSHOT
 *
 * @param <T> Type d'objet à mettre sous forme de page
 */
public class RestPageImpl<T> extends PageImpl<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public RestPageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
			@JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements,
			@JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
			@JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort,
			@JsonProperty("first") boolean first, @JsonProperty("empty") boolean empty,
			@JsonProperty("numberOfElements") int numberOfElements) {

		super(content, PageRequest.of(number, size), totalElements);
	}

	/**
	 * Constructeur faisant appel à la super class permettant d'attribuer le
	 * contenu, la page et le nombre total de page souhaités
	 * 
	 * @param content  Contenue de la page
	 * @param pageable Numéro de la page souhaitée
	 * @param total    Nombre de total de page acceptée par requête
	 */
	public RestPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	/**
	 * Constructeur permettant d'attribuer simplement le contenu sous forme de liste
	 * 
	 * @param content Contenue souhaitée
	 */
	public RestPageImpl(List<T> content) {
		super(content);
	}

	/**
	 * Constructeur sans paramètre
	 */
	public RestPageImpl() {
		super(new ArrayList<>());
	}
}
