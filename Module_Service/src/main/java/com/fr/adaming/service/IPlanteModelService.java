package com.fr.adaming.service;

import org.springframework.data.domain.Page;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;

public interface IPlanteModelService extends IService<PlanteModel> {

	/**
	 * Permet la recherche paginée de PlanteModel avec seulement 4 attibuts :
	 * nomScientifique, nomCommun, id et photo
	 * 
	 * @param p : numéro de page
	 * @return Service Response contenant une page de Plante Model
	 */
	public ServiceResponse<Page<PlanteModel>> readAllReduced(int p);

	/**
	 * Permet de recuperer tout les elements de la base de données qui contiennent
	 * nom dans leur nom commun ou leur nom scientifique.
	 * 
	 * @param page Numéro de la page consultée
	 * @param nom  string representant les caracteres recherchés
	 * @return Service response contenant une page de planteModel
	 * 
	 * @author Gregoire
	 */
	public ServiceResponse<Page<PlanteModel>> findByNom(int page, String nom);

}
