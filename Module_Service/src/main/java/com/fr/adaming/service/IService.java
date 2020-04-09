package com.fr.adaming.service;

import org.springframework.data.domain.Page;

import com.fr.adaming.dto.ServiceResponse;

//TODO adapter les methodes au demandes du projet

/**
 * <p>IService représente les méthodes CRUD générales des classes Services</p>
 * 
 * @author Grégoire Brebner
 *
 * @param E l'entité
 * @since 0.0.1
 */
public interface IService<E> {

	/**
	 * <p>Cette methode create permet la création d'une entite</p>
	 * 
	 * @param entity L'entité à créer
	 * @return Un objet serviceResponse contenant une entité ou null
	 */
	public ServiceResponse<E> create(E entity);

	/**
	 * <p>Methode permettant l'affichage de la liste de toutes les entites</p>
	 * @param p numéro de la page à afficher
	 * @return serviceResponse contenant une page d'entités avec 20 élements maximum
	 */
	public ServiceResponse<Page<E>> readAll(int p);

	/**
	 * <p>Methode permettant l'affichage d'une entite par son ID</p>
	 * 
	 * @param id Id de l'entite
	 * @return Servuce response contenant une entite ou null
	 */
	public ServiceResponse<E> readById(Integer id);

	/**
	 * <p>Methode permettant la connaissance de l'existence d'une entite par son
	 * ID</p>
	 * 
	 * @param id Id de l'entite
	 * @return VRAI(entite existente) ou FAUX(entite non-existente)
	 */
	public boolean existsById(Integer id);

	/**
	 * <p>Methode permettant la suppression d'une entite par son ID</p>
	 * 
	 * @param id Id de l'entite à supprimer
	 * @return VRAI (supression effectue) ou FAUX (supression non-effectue)
	 */
	public boolean deleteById(Integer id);

	/**
	 * <p>Methode permettant la modification d'une entite </p>
	 * 
	 * @param entite Entite à modifier
	 * @return ServiceResponse contenant L'entité ou null (en cas d'erreur)
	 */
	public ServiceResponse<E> update(E entite); //TODO decider si on veut update en boolean

}
