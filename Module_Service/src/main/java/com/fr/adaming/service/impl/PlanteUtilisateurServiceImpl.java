package com.fr.adaming.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.Jardin;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.repositories.IPlanteUtilisateurRepository;
import com.fr.adaming.service.AbstractService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Couche Service des Plante Utilisateur qui étend la classe Abstract Service
 * <br>
 * Redéfinition des méthodes create, udpate et find Plante Utilisateur By
 * idJardin
 * </p>
 * 
 * @author Lucie
 *
 */
@Service
@Slf4j
public class PlanteUtilisateurServiceImpl extends AbstractService<PlanteUtilisateur> {

	@Autowired
	private IPlanteUtilisateurRepository repo;

	@Override
	public ServiceResponse<PlanteUtilisateur> create(PlanteUtilisateur planteUtilisateur) {

		if (planteUtilisateur == null) {
			return new ServiceResponse<PlanteUtilisateur>("Objet d'entrée null", null);
		} else if (dao.existsById(planteUtilisateur.getId())) {
			return new ServiceResponse<PlanteUtilisateur>("Id déjà connu dans la BD", null);
		}

		else {
			try {
				dao.save(planteUtilisateur);
				log.info("Plante Utilisateur enregistré dans la BD");
				return new ServiceResponse<PlanteUtilisateur>("Succes", planteUtilisateur);
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<PlanteUtilisateur>("Erreur lors de la création de l'objet", null);
			}
		}
	}

	@Override
	public ServiceResponse<PlanteUtilisateur> update(PlanteUtilisateur planteUtilisateur) {

		if (planteUtilisateur == null) {
			log.info("Objet d'entrée null");
			return new ServiceResponse<PlanteUtilisateur>("Objet d'entrée null", null);
		} else if (dao.existsById(planteUtilisateur.getId())) {
			log.info("Id déjà connu dans la BD");
			return new ServiceResponse<PlanteUtilisateur>("Id déjà connu dans la BD", null);
		}

		else {
			try {
				dao.save(planteUtilisateur);
				log.info("Plante Utilisateur enregistré dans la BD");
				return new ServiceResponse<PlanteUtilisateur>("Succes", planteUtilisateur);
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<PlanteUtilisateur>("Erreur lors de la sauvegarde des modifications", null);
			}
		}
	}

	public ServiceResponse<List<PlanteUtilisateur>> findByJardin(int idJardin) {
		if (!dao.existsById(idJardin)) {
			log.info("Jardin inexistant");
			return new ServiceResponse<List<PlanteUtilisateur>>("Jardin inexistant", null);
		} else {
			try {
				log.info("Jardin existant");
				List<PlanteUtilisateur> planteUtilList = dao.findAll();
				ServiceResponse<List<PlanteUtilisateur>> serviceResponse = new ServiceResponse<List<PlanteUtilisateur>>();
				serviceResponse.setBody(planteUtilList);
				return serviceResponse;
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<List<PlanteUtilisateur>>("Erreur lors de l'affichage de la liste", null);
			}

		}
	}

}
