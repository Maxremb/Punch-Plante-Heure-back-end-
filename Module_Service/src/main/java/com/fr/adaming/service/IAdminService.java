package com.fr.adaming.service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Admin;

/**
 * Interface de Admin Service définissant les méthodes propres à Admin Service
 * Est utilisable pour les entités admin et utilisateur
 * @author Maxime Rembert
 * @since 0.0.1-SNAPSHOT
 *
 */
public interface IAdminService {

	/**
	 * Methode permettant la recherche d'un admin/utilisateur par pseudonyme
	 * @param pseudonyme de l'admin/utilisateur recherché
	 * @return un admin/utilisateur correspondant à l'admin recherché
	 */
	public ServiceResponse<Admin> readByPseudonyme (String pseudonyme);
	
	/**
	 * Methode permettant la recherche d'un admin/utilisateur par email
	 * @param email de l'admin/utilisateur recherché
	 * @return un admin/utilisateur correspondant à l'admin/utilisateur recherché
	 */
	public ServiceResponse<Admin> readByEmail (String email);
	
	
	/**
	 * Méthode permettant de vérifier l'existence d'un admin/utilisateur par email
	 * @param email de l'admin/utilisateur recherché
	 * @return un admin/utilisateur correspondant à l'admin/utilisateur recherché
	 */
	public Boolean existsByEmail ( String email);
	
	/**
	 * Méthode permettant de vérifier l'existence d'un admin/utilisateur par email
	 * @param pseudonyme de l'admin/utilisateur recherché
	 * @return un admin/utilisateur correspondant à l'admin/utilisateur recherché
	 */
	public Boolean existsByPseudonyme ( String pseudonyme);
	
	/**
	 * Méthode permettant de vérifier l'existence d'un utilisateur avec email et pwd donner
	 * @param email de l'utilisateur recherché
	 * @param mdp de l'utilisateur recherché
	 * @return boolean : TRUE si utiliseur existe / FALSE sinon
	 */
	public ServiceResponse<Admin> existsByEmailAndMdp (String email, String mdp);
	
}
