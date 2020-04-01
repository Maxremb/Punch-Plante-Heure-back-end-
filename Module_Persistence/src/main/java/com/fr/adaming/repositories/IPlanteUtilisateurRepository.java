package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteUtilisateur;


/**
 * Interface repository responsable de la communication avec la base de données pour l'entité Plante Utilisateur
 * @author lucie
 * @since 1.0.x
 */
@Repository
public interface IPlanteUtilisateurRepository extends JpaRepository<PlanteUtilisateur, Integer> {
	
	/**
	 * Methode d'affichage d'une liste de Plante Utilisateur à partir de l'id du Jardin
	 * @param idJardin
	 * @return List<PlanteUtilisateur 
	 */
	@Query(value = "from PlanteUtilisateur where jardin_id= :idJardin")
	public List<PlanteUtilisateur> findByJardin (@Param(value = "idJardin") Integer idJardin);
	
}
