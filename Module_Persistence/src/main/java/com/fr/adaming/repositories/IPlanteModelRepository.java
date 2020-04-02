package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.PlanteModel;

/**
 * Interface repository responsable de la communication avec la base de données pour l'entité PlanteModel 
 * @author Léa
 * @since 0.0.1
 */

@Repository
public interface IPlanteModelRepository extends JpaRepository<PlanteModel, Integer> {
	

	@Query(value = "select new PlanteModel(x.id, x.nomCommun, x.nomScientifique, x.photo) from PlanteModel x")
	public Page<PlanteModel> findAllReduced(Pageable pageable);

	public boolean existsByNomScientifique(String nomScientifique);
	
	public PlanteModel findByNomScientifique(String nomScientifique);

	
	
	

	
}
