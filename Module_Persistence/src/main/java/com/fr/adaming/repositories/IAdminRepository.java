package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Admin;

/**
 * Cette interface manage la couche repository de l'entité Admin.
 * @author Maxime Rembert
 * @since 0.01-SNAPSHOT
 *
 */
@Repository
public interface IAdminRepository extends JpaRepositoryImplementation<Admin, Integer>{
		
	/**
	 * Permet la recherche d'un utilisateur par pseudonyme
	 * @param pseudonyme de l'utliisateur recherché
	 * @return l'utilisateur en question
	 */
	public Admin findByPseudonyme(String pseudonyme);
	
	/**
	 * Permet la recherche d'un utilisateur par email
	 * @param email de l'utilisateur recherché
	 * @return l'utilisateur en question
	 */
	public Admin findByEmail(String email);
	
	/**
	 * Permet la recherche d'un utilisateur par email et mot de passe
	 * @param email de l'utilisateur recherché
	 * @param mdp de l'utilisateur recherché
	 * @return un utilisateur
	 */
	public Admin findByEmailAndMdp (String email, String mdp);
	
	/**
	 * Permet de vérifier si un utilisateur existe deja dans la DB avec ce pseudo
	 * @param pseudonyme de l'utilisateur voulu
	 * @return booléen true si le pseudo est déjà existant, false sinon
	 */
	public Boolean existsByPseudonyme (String pseudonyme);
	
	/**
	 * Permet de vérifier si un utilisateur existe deja dans la DB avec cet email
	 * @param email de l'utilisateur voulu
	 * @return booléen true si le mail est déjà existant, false sinon
	 */
	public Boolean existsByEmail (String email);
		

}
