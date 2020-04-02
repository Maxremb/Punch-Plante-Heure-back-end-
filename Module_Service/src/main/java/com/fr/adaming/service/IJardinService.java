package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;

/**
 * Interface de Jardin Service définissant les méthodes propres à Jardin Service
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
public interface IJardinService {

	/**
	 * Methode permettant la recherche de jardins par nom
	 * 
	 * @param nom du jardin au format String
	 * @return un ServiceReponse constitué d'un string "success" et d'une liste de
	 *         jardin (peut être vide), si cela fonctionne sinon un autre string et
	 *         un objet null
	 * @author Clara Cadet
	 */
	public ServiceResponse<List<Jardin>> readByNom(String nom);

	/**
	 * Methode permettant la recherche de jardins par un identifiant utilisateur
	 * 
	 * @param id de l'utilisateur des jardins à rechercher
	 * @return un ServiceReponse constitué d'un string "success" et d'une liste de
	 *         jardin (peut être vide), si cela fonctionne sinon un autre string et
	 *         un objet null
	 * @author Clara Cadet
	 */
	public ServiceResponse<List<Jardin>> readByUtilisateur(Integer id);

	/**
	 * Methode permettant la recherche de jardins par un numéro de département
	 * 
	 * @param numDep Numéro de département unique des jardins qu'on souhaite
	 *               cherché.
	 * @return un ServiceReponse constitué d'un string "success" et d'une liste de
	 *         jardin (peut être vide), si cela fonctionne sinon un autre string et
	 *         un objet null
	 * @author Clara Cadet
	 */
	public ServiceResponse<List<Jardin>> readByDepartement(Integer numDep);

}
