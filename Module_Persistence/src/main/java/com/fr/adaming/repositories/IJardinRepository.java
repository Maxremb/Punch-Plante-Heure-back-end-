package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	 * @param pageable caractéristiques de la pagination
	 * @param nom du jardin à rechercher
	 * @return une page de jardins ayant exactement ce nom
	 */
	public Page<Jardin> findByNom(Pageable pageable, String nom);
	
	/**
	 * Permet la recherche de jardins par l'identifiant unique d'un utilisateur propriétaires
	 * @param pageable caractéristiques de la pagination
	 * @param idUtilisateur L'id de l'utilisateur
	 * @return une page de jardin possédés par l'utilisateur ayant cet identifiant
	 */
	@Query(value = "select * from jardin where utilisateur_id= :idUtilisateur", nativeQuery = true)
	public Page<Jardin> trouveParUtilisateur(Pageable pageable, @Param(value = "idUtilisateur") Integer idUtilisateur);
	
	/**
	 * Permet la recherche de jardins par l'identifiant unique d'un utilisateur propriétaires
	 * @param idUtilisateur L'id de l'utilisateur
	 * @return une Liste de jardin possédés par l'utilisateur ayant cet identifiant
	 */
	@Query(value = "select * from jardin where utilisateur_id= :idUtilisateur", nativeQuery = true)
	public List<Jardin> trouveParUtilisateur(@Param(value = "idUtilisateur") Integer idUtilisateur);
	
	/**
	 * Permet la recherche de jardins par le numéro de département unique où il(s) se trouve(nt)
	 * @param pageable caractéristiques de la pagination
	 * @param idDepartement correspondant au numéro unique de département
	 * @return une page de jardin localisé dans le département ayant ce numéro
	 */
	@Query(value = "select * from jardin where departement_numero_dep = :idDepartement", nativeQuery = true)
	public Page<Jardin> trouveParDepartement(Pageable pageable, @Param(value = "idDepartement") Integer idDepartement);
	
	/**
	 * Permet la recherche de jardins par le numéro de département unique où il(s) se trouve(nt)
	 * @param idDepartement correspondant au numéro unique de département
	 * @return une liste de jardin localisé dans le département ayant ce numéro
	 */
	@Query(value = "select * from jardin where departement_numero_dep = :idDepartement", nativeQuery = true)
	public List<Jardin> trouveListJardinParDepartement(@Param(value = "idDepartement") Integer idDepartement);
		
	
}
