package com.fr.adaming.service;

import org.springframework.data.domain.Page;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;

// TODO
public interface IPlanteModelService extends IService<PlanteModel> {

//	@Deprecated
//	public ServiceResponse<PlanteModel> readByNomScientifique(String nomScientique);

	public ServiceResponse<Page<PlanteModel>> readAllReduced(int p);

	/**
	 * Permet de recuperer tout les elements de la bas de données qui contiennet nom
	 * dans leur nom commun ou leur nom scientifique.
	 * 
	 * @param page Numéro de la page consultée
	 * @param nom  string representant les caracteres recherchés
	 * @return Service response contenant une page de planteModel
	 * 
	 * @author Gregoire
	 */
	public ServiceResponse<Page<PlanteModel>> findByNom(int page, String nom);
	
}
