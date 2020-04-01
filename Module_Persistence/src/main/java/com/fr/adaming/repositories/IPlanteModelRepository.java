package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.PlanteModel;

/**
 * Interface repository responsable de la communication avec la base de données pour l'entité PlanteModel 
 * @author Léa
 * @since 1.0.x
 */

@Repository
public interface IPlanteModelRepository extends JpaRepository<PlanteModel, Integer> {
	
	@Query(value = "select id, nomCommun, nomScientifique, photo from PlanteModel")
	public List<PlanteModel> findAll();
	
	

	
}
