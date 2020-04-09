package com.fr.adaming.repositories;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.PlanteUtilisateur;


/**
 * <p>Interface repository responsable de la communication avec la base de données pour l'entité Plante Utilisateur<br>
 * Hérite de la classe JpaRespository.</p>
 * @author lucie
 * @since 0.0.1
 */
@Repository
public interface IPlanteUtilisateurRepository extends JpaRepository<PlanteUtilisateur, Integer> {
	
	/**
	 * Methode d'affichage d'une liste de Plante Utilisateur à partir de l'id du Jardin
	 * @param idJardin identifiant de jardin
	 * @param pageable informations de pagination
	 * @return List<PlanteUtilisateur> : retourne la liste associé à l'id du Jardin.
	 */
	//@Query(value = "from PlanteUtilisateur where jardin_id= :idJardin")
	@Query(value = "select * from plante_utilisateur where jardin_id= :idJardin", nativeQuery = true)
	public Page<PlanteUtilisateur> findByJardin (@Param(value = "idJardin") Integer idJardin, Pageable pageable);
	
	@Query(value = "select * from plante_utilisateur where jardin_id= :idJardin", nativeQuery = true)
	public List<PlanteUtilisateur> findByJardin (@Param(value = "idJardin") Integer idJardin);
	
}
