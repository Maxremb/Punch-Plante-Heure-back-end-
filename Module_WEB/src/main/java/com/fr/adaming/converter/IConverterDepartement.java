package com.fr.adaming.converter;

import java.util.List;

import org.springframework.data.domain.Page;


/**
 * Interface IConverter pour département
 * @author Isaline MILLET
 * @since 0.0.1
 * @param <D> objet DepartementDto
 * @param <E> entite Departement
 */
public interface IConverterDepartement<E, D> {
	
	/**
	 * Methode permettant de convertir un objet Dto en l'objet Entite
	 * @param dto DepartementDto a convertir
	 * @return resultat de la conversion (objet Departement ou objet null)
	 */
	public E convertDtoToEntity(D dto);
	
	/**
	 * Methode permettant de convertir un objet Entite en l'objet Dto considere
	 * @param dep objet Departement a convertir
	 * @return resultat de la conversion (objet DepartementDto ou objet null)
	 */
	public D convertEntityToDto(E dep);
	
	/**
	 * Methode permettant de convertir une liste d'objets Dto en une liste d'objets Entite consideres
	 * @param listeDto objet liste d'objets DepartementDto a convertir
	 * @return resultat de la conversion (liste d'objets Departement, pouvant etre vide)
	 */
	public List<E> convertListDtoToEntity(List<D> listeDto);
	
	/**
	 * Methode permettant de convertir une liste d'objets Entite en une liste d'objets Dto consideres
	 * @param listeDep objet liste d'objets Departement a convertir
	 * @return resultat de la conversion (liste d'objets DepartementDto, pouvant etre vide)
	 */
	public List<D> convertListEntityToDto(List<E> listeDep);
	
	/**
	 * Methode permettant de convertir une page d'objets Dto en une page d'objets Entite consideres
	 * @param pageDto objet page d'objets DepartementDto a convertir
	 * @return resultat de la conversion (page d'objets Departement)
	 */
	public Page<E> convertPageDtoToEntity(Page<D> pageDto);
	
	/**
	 * Methode permettant de convertir une page d'objets Entite en une page d'objets Dto consideres
	 * @param pageDep objet page d'objets Departement a convertir
	 * @return resultat de la conversion (page d'objets DepartementDto)
	 */
	public Page<D> convertPageEntityToDto(Page<E> pageDep);

}
