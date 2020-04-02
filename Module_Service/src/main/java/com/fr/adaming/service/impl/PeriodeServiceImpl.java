package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.TypePeriod;
import com.fr.adaming.repositories.IPeriodeRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IPeriodeService;

@Service
public class PeriodeServiceImpl extends AbstractService<Periode> implements IPeriodeService {

	
	private IPeriodeRepository periodeRepo;
	
	@Override
	public ServiceResponse<Periode> create(Periode entity) {

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		if (entity == null) {

			serviceResponse.setMessage("Tentative de création d'une entité null");
			serviceResponse.setBody(null);

		} else if (dao.existsById(entity.getId())) {

			serviceResponse.setMessage("Une entité avec cet ID existe déjà dans la base dde données");
			serviceResponse.setBody(null);

		} else {

			serviceResponse = verifierConditionsCommunes(entity); 

		}

		return serviceResponse;

	}

	@Override
	public ServiceResponse<Periode> update(Periode entity) {

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		if (entity == null) {

			serviceResponse.setMessage("Tentative de modification avec une entité null");
			serviceResponse.setBody(null);

		} else if (!dao.existsById(entity.getId())) {

			serviceResponse.setMessage("Une entité avec cet ID n'existe pas dans la base dde données");
			serviceResponse.setBody(null);

		} else {

			serviceResponse = verifierConditionsCommunes(entity); 

		}

		return serviceResponse;
		
	}

	@Override
	public ServiceResponse<List<Periode>> readByDepartement(Departement departement) {

		ServiceResponse<List<Periode>> serviceResponse = new ServiceResponse<List<Periode>>();

		List<Periode> periodeList = periodeRepo.findByDepartement(departement);
		serviceResponse.setBody(periodeList);

		return serviceResponse;
		
	}

	@Override
	public ServiceResponse<List<Periode>> readByPlanteModel(PlanteModel planteModel) {

		ServiceResponse<List<Periode>> serviceResponse = new ServiceResponse<List<Periode>>();

		List<Periode> periodeList = periodeRepo.findByPlanteModel(planteModel);
		serviceResponse.setBody(periodeList);

		return serviceResponse;
		
	}

	@Override
	public ServiceResponse<List<Periode>> readByDepartementAndPlanteModel(Departement departement,
			PlanteModel planteModel) {

		ServiceResponse<List<Periode>> serviceResponse = new ServiceResponse<List<Periode>>();

		List<Periode> periodeList = periodeRepo.findByDepartementAndPlanteModel(departement, planteModel);
		serviceResponse.setBody(periodeList);

		return serviceResponse;
		
	}

	@Override
	public ServiceResponse<Periode> readByPlanteModelAndDepartementAndType(Departement departement,
			PlanteModel planteModel, TypePeriod type) {

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		Periode periode = periodeRepo.findByDepartementAndPlanteModelAndType(departement, planteModel, type);
		serviceResponse.setBody(periode);

		return serviceResponse;
		
	}
	
	// Méthodes Privées

	private ServiceResponse<Periode> verifierConditionsCommunes(Periode entity) {
		
		// Regroupe les opérations qui sont communes à l'update et a create.

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		if (entity.getDateDebut() == null || entity.getDateFin() == null) {

			serviceResponse.setMessage("Les dates de début et de fin doivent être spécifiés");
			serviceResponse.setBody(null);

		} else if (entity.getDepartement() == null || entity.getPlanteModel() == null) {

			serviceResponse.setMessage("Les periodes doivent être liés à un département et à une plante");
			serviceResponse.setBody(null);

		} else if (entity.getDateDebut().isAfter(entity.getDateFin())) {

			serviceResponse.setMessage("La date de fin est avant la date de début pour cette periode");
			serviceResponse.setBody(null);

		} else {

			Periode periode = dao.save(entity);
			serviceResponse.setBody(periode);

		}

		return serviceResponse;

	}

}
