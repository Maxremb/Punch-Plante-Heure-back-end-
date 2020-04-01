package com.fr.adaming.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.PlanteUtilisateur;


/**
 * Interface repository responsable de la communication avec la base de données pour l'entité Plante Utilisateur
 * @author lucie
 * @since 1.0.x
 */
@Repository
public interface IPlanteUtilisateurRepository extends JpaRepository<PlanteUtilisateur, Integer> {
	
	
	
}
