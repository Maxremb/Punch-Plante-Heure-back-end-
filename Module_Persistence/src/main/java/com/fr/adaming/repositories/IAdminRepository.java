package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.fr.adaming.entity.Admin;
import com.fr.adaming.entity.Utilisateur;

/**
 * Cette interface manage la couche repository de l'entité Admin.
 * @author Maxime Rembert
 * @since 0.01-SNAPSHOT
 *
 */
public interface IAdminRepository extends JpaRepositoryImplementation<Admin, Integer>{
	

	
	/**
	 * Permet la recherche d'un utilisateur par pseudonyme
	 * @param pseudonyme de l'utlisateur recherché
	 * @return l'utilisateur en question
	 */
	public Admin findByPseudonyme(String pseudonyme);
	
	/**
	 * Permet la recherche d'un utilisateur par email
	 * @param email de l'utlisateur recherché
	 * @return l'utilisateur en question
	 */
	public Admin	 findByEmail(String email);
	
	/**
	 * Permet la recherche d'un utilisateur par email et mot de passe
	 * @param email de l'utlisateur recherché
	 * @param mdp de l'utlisateur recherché
	 * @return un utilisateur
	 */
	public Admin findByEmailAndMdp (String email, String mdp);
	

}
