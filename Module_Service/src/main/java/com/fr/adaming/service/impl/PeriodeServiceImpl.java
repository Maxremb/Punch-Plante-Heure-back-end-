package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Departement;
import com.fr.adaming.entity.Periode;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.enums.TypePeriod;
import com.fr.adaming.repositories.IDepartementRepository;
import com.fr.adaming.repositories.IPeriodeRepository;
import com.fr.adaming.repositories.IPlanteModelRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IPeriodeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Couche service pour gerer les periodes
 * 
 * @author Gregoire
 *
 */
@Service
@Slf4j
public class PeriodeServiceImpl extends AbstractService<Periode> implements IPeriodeService {

	@Autowired
	private IPeriodeRepository periodeRepo;
	@Autowired
	private IDepartementRepository depRepo;
	@Autowired
	private IPlanteModelRepository planteRepo;

	@Override
	public ServiceResponse<Periode> create(Periode entity) {

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		if (entity == null) {

			serviceResponse.setMessage("Tentative de création d'une entité null");
			serviceResponse.setBody(null);
			log.info("Création echouée, l'objet est nul");

		} else if (dao.existsById(entity.getId())) {

			serviceResponse.setMessage("Une entité avec cet ID existe déjà dans la base dde données");
			serviceResponse.setBody(null);
			log.info("Création echouée, un objet avec cet id existe déjà dans la BD");

		} else {

			serviceResponse = verifierConditionsCommunes(entity);
			log.info("Création réussie !");

		}

		return serviceResponse;

	}

	@Override
	public ServiceResponse<Periode> update(Periode entity) {

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		if (entity == null) {

			serviceResponse.setMessage("Tentative de modification avec une entité null");
			serviceResponse.setBody(null);
			log.info("Mise à jour echouée, l'objet est nul");

		} else if (!dao.existsById(entity.getId())) {

			serviceResponse.setMessage("Une entité avec cet ID n'existe pas dans la base dde données");
			serviceResponse.setBody(null);
			log.info("Mise à jour echouée, un objet avec cet id n'existe pas dans la BD");

		} else {

			serviceResponse = verifierConditionsCommunes(entity);
			log.info("Mise à jour réussie !");

		}

		return serviceResponse;

	}

	@Override
	public ServiceResponse<Page<Periode>> readByDepartementId(int page, int depId) {

		ServiceResponse<Page<Periode>> serviceResponse = new ServiceResponse<Page<Periode>>();
		Pageable pageable = PageRequest.of(page, 20);
		Departement departement = depRepo.findById(depId).orElse(null);
		Page<Periode> periodeList = periodeRepo.findByDepartement(pageable, departement);
		serviceResponse.setBody(periodeList);
		log.info("Affichage d'une page contenant les Période d'un Departement OK !");
		return serviceResponse;

	}

	@Override
	public ServiceResponse<Page<Periode>> readByPlanteModelId(int page, int planteId) {

		ServiceResponse<Page<Periode>> serviceResponse = new ServiceResponse<Page<Periode>>();
		PlanteModel planteModel = planteRepo.findById(planteId).orElse(null);
		Pageable pageable = PageRequest.of(page, 20);
		Page<Periode> periodeList = periodeRepo.findByPlanteModel(pageable, planteModel);
		serviceResponse.setBody(periodeList);
		log.info("Affichage d'une page contenant les Période d'une Plante Model OK !");
		return serviceResponse;

	}

	@Override
	public ServiceResponse<List<Periode>> readByDepartementIdAndPlanteModelId(int depId, int planteId) {

		ServiceResponse<List<Periode>> serviceResponse = new ServiceResponse<List<Periode>>();
		Departement departement = depRepo.findById(depId).orElse(null);
		PlanteModel planteModel = planteRepo.findById(planteId).orElse(null);

		List<Periode> periodeList = periodeRepo.findByDepartementAndPlanteModel(departement, planteModel);
		serviceResponse.setBody(periodeList);
		log.info("Affichage d'une liste contenant les Période d'une Plante Model et d'un Departement OK !");
		return serviceResponse;

	}

	@Override
	public ServiceResponse<Periode> readByDepartementIdAndPlanteModelIdAndType(int depId, int planteId,
			TypePeriod type) {

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();
		Departement departement = depRepo.findById(depId).orElse(null);
		PlanteModel planteModel = planteRepo.findById(planteId).orElse(null);

		Periode periode = periodeRepo.findByDepartementAndPlanteModelAndType(departement, planteModel, type);
		serviceResponse.setBody(periode);
		log.info("Affichage d'une Période à partir du Departement, de la Plante Model, du Type OK !");
		return serviceResponse;

	}

	// Méthodes Privées

	/**
	 * Regroupe les if-else communs entre create et update
	 * 
	 * @param entity La periode concernée
	 * @return un objet de type serviceResponse
	 */
	private ServiceResponse<Periode> verifierConditionsCommunes(Periode entity) {

		// Regroupe les opérations qui sont communes à l'update et a create.

		ServiceResponse<Periode> serviceResponse = new ServiceResponse<Periode>();

		if (entity.getDateDebut() == null || entity.getDateFin() == null) {

			serviceResponse.setMessage("Les dates de début et de fin doivent être spécifiés");
			serviceResponse.setBody(null);
			log.info("Les dates ne peuvent pas être nulles !");

		} else if (entity.getDepartement() == null || entity.getPlanteModel() == null) {

			serviceResponse.setMessage("Les periodes doivent être liés à un département et à une plante");
			serviceResponse.setBody(null);
			log.info("Departement et Plante Model ne peuvent pas être nul !");

		} else if (entity.getDateDebut().isAfter(entity.getDateFin())) {

			serviceResponse.setMessage("La date de fin est avant la date de début pour cette periode");
			serviceResponse.setBody(null);
			log.info("La date de fin doit forcement être après la date de début !");

		} else {

			Periode periode = dao.save(entity);
			serviceResponse.setBody(periode);
			log.info("Sauvegarde de l'entité OK !");

		}

		return serviceResponse;

	}

	@Override
	public ServiceResponse<List<Periode>> readByJardinAndDep(int idDep, int idJardin) {
		ServiceResponse<List<Periode>> serviceResponse = new ServiceResponse<List<Periode>>();

		serviceResponse.setBody(periodeRepo.findByJardinAndDep(idJardin, idDep));
		serviceResponse.setMessage("Succes");
		log.info("Affichage d'une liste de Période à partir du Jardin et du Departement OK !");
		return serviceResponse;
	}

}
