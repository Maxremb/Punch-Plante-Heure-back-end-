package com.fr.adaming.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.DepartementDto;

/**
 * Interface IConverter pour département
 * @author Isaline MILLET
 * @since 0.0.1
 * @param <D> objet DepartementDto
 * @param <E> entite departement
 */
public interface IConverterDepartement<E, D> {
	
	/**
	 * Methode permettant de convertir un objet Dto en l'objet Entite
	 * @param dto objet Dto a convertir
	 * @return resultat de la conversion (objet Entite ou objet null)
	 */
	public E convertDtoToEntity(D dto);
	
	/**
	 * Methode permettant de convertir un objet Entite en l'objet Dto considere
	 * @param entity objet Entite a convertir
	 * @return resultat de la conversion (objet Dto ou objet null)
	 */
	public D convertEntityToDto(E dep);
	
	/**
	 * Methode permettant de convertir une liste d'objets Dto en une liste d'objets Entite consideres
	 * @param listeDto objet liste d'objets Dto a convertir
	 * @return resultat de la conversion (liste d'objets Entite, pouvant etre vide)
	 */
	public List<E> convertListDtoToEntity(List<D> listeDto);
	
	/**
	 * Methode permettant de convertir une liste d'objets Entite en une liste d'objets Dto consideres
	 * @param listeEntity objet liste d'objets Entite a convertir
	 * @return resultat de la conversion (liste d'objets Dto, pouvant etre vide)
	 */
	public List<D> convertListEntityToDto(List<E> listeDep);

}
