package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Jardin;

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
	public List<Jardin> findByNom(String nom);
	
	/**
	 * Permet la recherche de jardins par l'identifiant unique d'un utilisateur propriétaires
	 * @param idUtilisateur
	 * @return une liste de jardin possédés par l'utilisateur ayant cet identifiant
	 */
	@Query(value = "from Jardin where utilisateur_id= :idUtilisateur")
	public List<Jardin> trouveParUtilisateur(@Param(value = "idUtilisateur") Integer idUtilisateur);
	
	/**
	 * Permet la recherche de jardins par le numéro de département unique où il(s) se trouve(nt)
	 * @param idDepartement correspondant au numéro unique de département
	 * @return une liste de jardin localisé dans le département ayant ce numéro
	 */
	@Query(value = "from Jardin where departement_id= :idDepartement")
	public List<Jardin> trouveParDepartement(@Param(value = "idDepartement") Integer idDepartement);
	
	
}
