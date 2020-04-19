package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fr.adaming.entity.PlanteUtilisateur;

/**
 * <p>Interface repository responsable de la communication avec la base de données pour l'entité Plante Utilisateur<br>
 * Hérite de la classe JpaRespository.</p>
 * @author Lucie Martinez
 * @since 0.0.1
 */
@Repository
public interface IPlanteUtilisateurRepository extends JpaRepository<PlanteUtilisateur, Integer> {
	
	/**
	 * Methode d'affichage d'une page de Plante Utilisateur à partir de l'id du Jardin
	 * @param idJardin identifiant de jardin
	 * @param pageable informations de pagination
	 * @return Page<PlanteUtilisateur> : retourne la page associée à l'id du Jardin.
	 */
	@Query(value = "select * from plante_utilisateur where jardin_id= :idJardin", nativeQuery = true)
	public Page<PlanteUtilisateur> findByJardin (@Param(value = "idJardin") Integer idJardin, Pageable pageable);
	
	/**
	 * Methode d'affichage d'une liste de Plante Utilisateur à partir de l'id du Jardin
	 * @param idJardin identifiant de jardin
	 * @param pageable informations de pagination
	 * @return List<PlanteUtilisateur> : retourne la liste associé à l'id du Jardin.
	 */
	@Query(value = "select * from plante_utilisateur where jardin_id= :idJardin", nativeQuery = true)
	public List<PlanteUtilisateur> findByJardin (@Param(value = "idJardin") Integer idJardin);
	
	/** Trouve les plantes par utilisateur
	 * @param idUtil Id de l'utilisateur
	 * @return une liste d'id de plantUtilisateur
	 * @author Gregoire
	 */
	@Query(value = "select pu.id from PlanteUtilisateur pu join pu.jardin j where j.utilisateur.id = :idUtil")
	public List<Integer> findByUtilisateurId(@Param(value = "idUtil") Integer idUtil);
	
	/**
	 * Permet la suppression des plantes d'un jardin par le numéro du jardin
	 * @param idJardin correspondant au numéro unique du jardin
	 */
	@Modifying @Transactional
	@Query(value = "DELETE FROM plante_utilisateur WHERE jardin_id = :idJardin", nativeQuery = true)
	public void deleteAllByJardin(@Param(value = "idJardin") Integer idJardin);
	
}
