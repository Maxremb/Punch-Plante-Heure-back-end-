package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.TypePeriod;

public interface IPeriodeService {
	
	public ServiceResponse<List<Periode>> readByDepartement(Departement departement);
	public ServiceResponse<List<Periode>> readByPlanteModel(PlanteModel planteModel);
	public ServiceResponse<List<Periode>> readByDepartementAndPlanteModel(Departement departement, PlanteModel planteModel);
	public ServiceResponse<Periode> readByPlanteModelAndDepartementAndType(Departement departement, PlanteModel planteModel, TypePeriod type);

}
