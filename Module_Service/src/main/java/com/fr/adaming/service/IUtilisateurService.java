package com.fr.adaming.service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Utilisateur;

/**
 * Interface d'Utilisateur Service définissant les méthodes propres à
 * Utilisateur Service
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
public interface IUtilisateurService {

	/**
	 * 
	 * Methode permettant la recherche d'un utilisateur par nom et prenom
	 * 
	 * @param nom    de l'utilisateur recherché
	 * @param prenom de l'utilisateur recherché
	 * @return un objet service Response avec un body de type utilisateur
	 */
	public ServiceResponse<Utilisateur> readByNomAndPrenom(String nom, String prenom);

	/**
	 * Methode permettant de vérifier l'activation d'un utilisateur
	 * 
	 * @param pseudonyme de l'utilisateur en question
	 * @return un booléen true si l'utilisateur est actif, false sinon
	 */
	public Boolean isActif(String pseudonyme);
	
	/**
	 * Méthdoe permettant de désactiver un utilisateur
	 * @param id de l'utilisateur en question
	 * @return un booléen true sur l'utilisateur à bien été desactivé, false sinon
	 */
	public Boolean desactivateUser(Integer id);

}
