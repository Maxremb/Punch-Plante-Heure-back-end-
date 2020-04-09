package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Utilisateur;

/**
 * Cette interface manage la couche repository de l'entité Utilisateur
 * 
 * @author Maxime Rembert
 * @since 0.0.1-SNPASHOT
 *
 */
@Repository
public interface IUtilisateurRepository extends JpaRepositoryImplementation<Utilisateur, Integer> {
	
	/**
	 * Permet la recherche d'un utilisateur par nom et prenom
	 * 
	 * @param nom    de l'utlisateur recherché
	 * @param prenom de l'utilisateur recherché
	 * @return l'utilisateur en question
	 */
	public Utilisateur findByNomAndPrenom(String nom, String prenom);

	/**
	 * @param pseudonyme de l'utilisateur recherché
	 * @return un booléen true si l'utilisateur est actif, false sinon
	 */
	public boolean isActif(String pseudonyme);
	
	

}
