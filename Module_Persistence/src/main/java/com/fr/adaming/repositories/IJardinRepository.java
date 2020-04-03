package com.fr.adaming.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Jardin;

// TODO verifier si il faut passe les query en sql natif

/**
 * Interface repository responsable de la communication avec la base de données pour l'entité Jardin 
 * @author Clara Cadet
 * @since 0.0.1-SNAPSHOT
 */
@Repository
public interface IJardinRepository extends JpaRepository<Jardin, Integer>{

	/**
	 * Permet la recherche par un nom exact d'un jardin
	 * @param nom du jardin à rechercher
	 * @return une liste de jardins ayant exactement ce nom
	 */
	public Page<Jardin> findByNom(Pageable pageable, String nom);
	
	/**
	 * Permet la recherche de jardins par l'identifiant unique d'un utilisateur propriétaires
	 * @param idUtilisateur
	 * @return une liste de jardin possédés par l'utilisateur ayant cet identifiant
	 */
	//@Query(value = "from Jardin where utilisateur_id= :idUtilisateur")
	@Query(value = "select * from jardin where utilisateur_id= :idUtilisateur", nativeQuery = true)
	public Page<Jardin> trouveParUtilisateur(Pageable pageable, @Param(value = "idUtilisateur") Integer idUtilisateur);
	
	/**
	 * Permet la recherche de jardins par le numéro de département unique où il(s) se trouve(nt)
	 * @param idDepartement correspondant au numéro unique de département
	 * @return une liste de jardin localisé dans le département ayant ce numéro
	 */
	@Query(value = "select * from jardin where departement_id= :idDepartement", nativeQuery = true)
	public Page<Jardin> trouveParDepartement(Pageable pageable, @Param(value = "idDepartement") Integer idDepartement);
	
	
}
