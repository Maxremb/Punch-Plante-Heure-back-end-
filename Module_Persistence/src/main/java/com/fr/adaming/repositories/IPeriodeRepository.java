package com.fr.adaming.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.TypePeriod;


/** Repositoire pour periode
 * @author Gregoire
 *
 */
@Repository
public interface IPeriodeRepository extends JpaRepository<Periode, Integer>{
	
	/** Cherche les periodes de touts les utilisateurs par departement
	 * @param pageable Objet pageable pour configure la page
	 * @param departement Departement concerné
	 * @return Page de periodes
	 */
	public Page<Periode> findByDepartement(Pageable pageable, Departement departement);
	
	/** Cherche les periodes par type de plante
	 * @param pageable Objet pageable pour configure la page
	 * @param planteModel type de plante
	 * @return page de periodes
	 */
	public Page<Periode> findByPlanteModel(Pageable pageable, PlanteModel planteModel);
	
	
	/** Cherche une periode par departement et type de plante
	 * @param departement Le departement concerné
	 * @param planteModel Le type de plante
	 * @return Une liste de periodes
	 */
	public List<Periode> findByDepartementAndPlanteModel(Departement departement, PlanteModel planteModel);
	
	
	/** Cherche la periode qui correspond à un departement, un type de plante, et un type d'action
	 * @param departement Le departement concerné
	 * @param planteModel Le type de plante
	 * @param type le type d'action
	 * @return Une Periode
	 */
	public Periode findByDepartementAndPlanteModelAndType(Departement departement, PlanteModel planteModel, TypePeriod type);
	
	/** Cherche les periodes qui correspondent à un jardin
	 * @param depNum le numero du departement du jardin
	 * @param idJardin l'id du jardin
	 * @return Une liste de Periode
	 */
	@Query(value = " select * from ((periode p inner join plante_model pm on p.plante_model_id=pm.id)  inner join plante_utilisateur pu on pu.plante_model_id=pm.id) where p.departement_numero_dep = :depNum and pu.jardin_id= :idJardin", nativeQuery = true)
	public List<Periode> findByJardinAndDep(Integer idJardin, Integer depNum);

}
