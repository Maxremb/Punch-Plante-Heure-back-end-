package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;

public interface IPlanteModelService {
	
	
	
	public ServiceResponse<PlanteModel> readByNomScientifique(String nomScientique);
	
	public ServiceResponse<List<PlanteModel>> readAllReduced();
	
	

}
