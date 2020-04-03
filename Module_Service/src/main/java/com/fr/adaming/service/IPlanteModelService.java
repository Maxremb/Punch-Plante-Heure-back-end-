package com.fr.adaming.service;

import org.springframework.data.domain.Page;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;

public interface IPlanteModelService {
	
	
	
	public ServiceResponse<PlanteModel> readByNomScientifique(String nomScientique);
	
	public ServiceResponse<Page<PlanteModel>> readAllReduced(int p);
	
	public ServiceResponse<Page<PlanteModel>> findByNom(int page, String nom);

}
