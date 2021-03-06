package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteModel;
import com.fr.adaming.repositories.IPlanteModelRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IPlanteModelService;

import lombok.extern.slf4j.Slf4j;

/**
 * Classe Service relative à l'entité Plante Model ettend AbstractService,
 * Implement IPlanteModelService
 * 
 * @author Léa Coston
 * @since 0.0.1-SNAPSHOT
 */
@Service
@Slf4j
public class PlanteModelServiceImpl extends AbstractService<PlanteModel> implements IPlanteModelService {

	@Autowired
	private IPlanteModelRepository repo;

	@Override
	public ServiceResponse<PlanteModel> create(PlanteModel entity) {
		ServiceResponse<PlanteModel> retour = new ServiceResponse<PlanteModel>();
		if (entity == null) {
			retour.setBody(null);
			retour.setMessage("Création non réalisée : objet d'entrée nul");
			log.info("Création echouée, l'objet d'entré est nul !");
		} else if (entity.getNomScientifique() == null) {
			retour.setBody(null);
			retour.setMessage("Création non réalisée : Le nom Scientifique ne doit pas etre nul");
			log.info("Création echouée, le nom scientifique ne peut pas être nul !");
		} else if (dao.existsById(entity.getId())) {
			retour.setBody(null);
			retour.setMessage("Création non réalisée : cet id est déjà présent dans la base de donnée");
			log.info("Création echouée, l'id existe déjà dans la base de données !");
		} else if (repo.existsByNomScientifique(entity.getNomScientifique())) {
			retour.setBody(null);
			retour.setMessage("Création non réalisée : ce nom scientifique est déjà présent dans la base de donnée");
			log.info("Création echouée, le nom scientifique existe déjà dans la base de données !");
		} else {
			retour.setBody(dao.save(entity));
			retour.setMessage("Création de la plante modele réussie");
			log.info("Création réussie !");
		}
		return retour;
	}

	@Override
	public ServiceResponse<PlanteModel> update(PlanteModel entity) {
		ServiceResponse<PlanteModel> retour = new ServiceResponse<PlanteModel>();
		try {
			if (entity == null) {
				retour.setBody(null);
				retour.setMessage("Mise à jour non réalisée : objet d'entrée null");
				log.info("Mise à jour echouée, l'objet d'entré est nul !");
			} else if (entity.getNomScientifique() == null) {
				retour.setBody(null);
				retour.setMessage("Mise à jour non réalisée : Le nom Scientifique ne doit pas etre null");
				log.info("Mise à jour echouée, le nom scientifique ne peut pas être nul !");
			} else if (!dao.existsById(entity.getId())) {
				retour.setBody(null);
				retour.setMessage("Mise à jour non réalisée : cet id n'existe pas dans la base de donnée");
				log.info("Mise à jour echouée, l'id n'existe pas dans la base de données !");
			} else {
				retour.setBody(dao.save(entity));
				retour.setMessage("Mise à jour de la plante modele réussie");
				log.info("Mise à jour réussie !");
			}
		} catch (DataIntegrityViolationException e) {
			log.warn("Tentative échouée de modification d'une plante modele avec un nom déjà utilisé" + e.getMessage());
			retour.setBody(null);
			retour.setMessage("erreur modification");
		}
		return retour;
	}

	/**
	 * Methode permettant la recherche d'un liste de plante avec seulement quelques
	 * attributs
	 * 
	 * 
	 *
	 * @author Léa Coston
	 */
	@Override
	public ServiceResponse<Page<PlanteModel>> readAllReduced(int p) {
		ServiceResponse<Page<PlanteModel>> retour = new ServiceResponse<Page<PlanteModel>>();
		Pageable pageable = PageRequest.of(p, 20, Sort.by("id"));
		Page<PlanteModel> page = repo.findAllReduced(pageable);
		retour.setBody(page);
		retour.setMessage("Succes");
		log.info("Affichage de toutes les plantes Model via pages OK");
		return retour;
	}

	@Override
	public ServiceResponse<Page<PlanteModel>> findByNom(int page, String nom) {

		Pageable pageable = PageRequest.of(page, 20);
		Page<PlanteModel> entityList = repo.findByNomCommunContainingOrNomScientifiqueContainingIgnoreCase(pageable,
				nom, nom);
		ServiceResponse<Page<PlanteModel>> serviceResponse = new ServiceResponse<Page<PlanteModel>>();
		serviceResponse.setBody(entityList);
		log.info("Affichage d'une page de plantes Model via recherche par nom OK");
		return serviceResponse;
	}

}
