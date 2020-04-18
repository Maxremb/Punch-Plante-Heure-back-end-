package com.fr.adaming.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;

/**
 * Interface de Jardin Service définissant les méthodes propres à Jardin Service
 * @author Clara Cadet / Isaline MILLET / Gregoire BREBNER
 * @since 0.0.1-SNAPSHOT
 */
public interface IJardinService {

	/**
	 * Methode permettant la recherche de jardins par nom
	 * 
	 * @param nom du jardin au format String
	 * @param page la page recherchée
	 * @return un ServiceReponse constitué d'un message, d'un booleen erreur et d'une page de
	 *         jardin (peut être vide ou NULL) 
	 */
	public ServiceResponse<Page<Jardin>> readByNom(int page, String nom);

	/**
	 * Methode permettant la recherche de jardins par un identifiant utilisateur
	 * 
	 * @param id de l'utilisateur des jardins à rechercher
	 * @param page la page recherchée
	 * @return un ServiceReponse constitué d'un message, d'un boolean erreur et d'une page de
	 *         jardin (peut être vide ou NULL)
	 */
	public ServiceResponse<Page<Jardin>> readByUtilisateur(int page, Integer id);
	
	/**
	 * Methode permettant la recherche de jardins par un identifiant utilisateur
	 * 
	 * @param id de l'utilisateur des jardins à rechercher
	 * @return un ServiceReponse constitué d'un message, d'un boolean erreur et d'une liste de
	 *         jardin (peut être vide ou NULL)
	 */
	public ServiceResponse<List<Jardin>> readByUtilisateur(Integer id);

	/**
	 * Methode permettant la recherche de jardins par un numéro de département
	 * 
	 * @param numDep Numéro de département unique des jardins qu'on souhaite
	 *               cherché.               
	 * @param page la page recherchée
	 * @return un ServiceReponse constitué d'un message, d'un boolean erreur et d'une page de
	 *         jardin (peut être vide ou NULL)
	 */
	public ServiceResponse<Page<Jardin>> readByDepartement(int page, Integer numDep);
	
	/**
	 * Methdoe permettant le calcul de la reserve max du jardin
	 * Necessite de spécifier la longueur, largeur et profonder de sol
	 * @param jardin correspond au jardin dont on veux calculer sa reserve max
	 * @return le même jardin avec un paramètre en plus
	 */
	public Jardin calculReserveEauMax (Jardin jardin);

}
