package com.fr.adaming.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.PlanteModel;

/**
 * Interface repository responsable de la communication avec la base de données
 * pour l'entité PlanteModel
 * 
 * @author Léa Coston / Grégoire Brebner
 * @since 0.0.1
 */

@Repository
public interface IPlanteModelRepository extends JpaRepository<PlanteModel, Integer> {

	/**
	 * Permet la recherche paginée de PlanteModel avec seulement 4 attibuts :
	 * nomScientifique, nomCommun, id et photo
	 * 
	 * @param pageable les attributs des pages : nombre d'articles par page et
	 *                 optionnel sortBy
	 * @return une page de PlantModel
	 */
	@Query(value = "select new PlanteModel(x.id, x.nomCommun, x.nomScientifique, x.photo) from PlanteModel x")
	public Page<PlanteModel> findAllReduced(Pageable pageable);

	/**
	 * Permet de véridfier l'éxistance d'une instance par nomScientifique
	 * 
	 * @param nomScientifique le nom recherché
	 * @return boolean true si l'objet existe, false sinon
	 */
	public boolean existsByNomScientifique(String nomScientifique);

	/**
	 * Permet de rechercher une instance par nomScientifique
	 * 
	 * @param nomScientifique le nom recherché
	 * @return PlanteModel
	 */
	public PlanteModel findByNomScientifique(String nomScientifique);

	/**
	 * Permet de rechercher tous les elements qui contiennent les caracteres
	 * nomCommun dans nom_commun ou nomScientifique dans nom_scientifique
	 * 
	 * @param pageable les attributs des pages : nombre d'articles par page et
	 *                 optionnel sortBy
	 * @param nomCommun nom commun à rechercher
	 * @param nomScientifique nom scientifique à rechercher
	 * @return Page de PlanteModel
	 */
	public Page<PlanteModel> findByNomCommunContainingOrNomScientifiqueContainingIgnoreCase(Pageable pageable, String nomCommun,
			String nomScientifique);

}
