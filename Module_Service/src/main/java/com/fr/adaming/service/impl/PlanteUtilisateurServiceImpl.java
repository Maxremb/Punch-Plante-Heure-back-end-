package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fr.adaming.dto.ServiceResponse;
import com.fr.adaming.entity.PlanteUtilisateur;
import com.fr.adaming.repositories.IJardinRepository;
import com.fr.adaming.repositories.IPlanteUtilisateurRepository;
import com.fr.adaming.service.AbstractService;
import com.fr.adaming.service.IPlanteUtilisateurService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Couche Service des Plante Utilisateur qui étend la classe Abstract Service et
 * implemente l'interface IPlanteUtilisateurService. <br>
 * Redéfinition des méthodes create, udpate et findByIdJardin.
 * </p>
 * 
 * @author Lucie
 * @since 0.0.1
 *
 */
@Service
@Slf4j
public class PlanteUtilisateurServiceImpl extends AbstractService<PlanteUtilisateur>
		implements IPlanteUtilisateurService {

	@Autowired
	private IPlanteUtilisateurRepository repo;

	@Autowired
	private IJardinRepository jRepo;

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
		} else if (!dao.existsById(planteUtilisateur.getId())) {
			log.info("Id inconnu dans la BD");
			return new ServiceResponse<PlanteUtilisateur>("Id inconnu dans la BD", null);
		}

		else {
			try {
				log.info("Plante Utilisateur enregistré dans la BD");
				return new ServiceResponse<PlanteUtilisateur>("Succes", dao.save(planteUtilisateur));
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<PlanteUtilisateur>("Erreur lors de la sauvegarde des modifications", null);
			}
		}
	}

	@Override
	public ServiceResponse<Page<PlanteUtilisateur>> readByJardin(int idJardin, int p) {
		if (!jRepo.existsById(idJardin)) {
			log.info("Jardin inexistant");
			return new ServiceResponse<Page<PlanteUtilisateur>>("Jardin inexistant", null);
		} else {
			try {
				log.info("Jardin existant");
				ServiceResponse<Page<PlanteUtilisateur>> serviceResponse = new ServiceResponse<Page<PlanteUtilisateur>>();
				Pageable pageable = PageRequest.of(p, 20, Sort.by(Direction.DESC, "id"));
				Page<PlanteUtilisateur> page = repo.findByJardin(idJardin, pageable);

				serviceResponse.setBody(page);
				serviceResponse.setMessage("Succes");
				return serviceResponse;
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<Page<PlanteUtilisateur>>("Erreur lors de l'affichage de la liste", null);
			}

		}
	}

	@Override
	public ServiceResponse<List<PlanteUtilisateur>> readByJardin(int idJardin) {
		if (!jRepo.existsById(idJardin)) {
			log.info("Jardin inexistant");
			return new ServiceResponse<List<PlanteUtilisateur>>("Jardin inexistant", null);
		} else {
			try {
				log.info("Jardin existant");
				ServiceResponse<List<PlanteUtilisateur>> serviceResponse = new ServiceResponse<List<PlanteUtilisateur>>();

				List<PlanteUtilisateur> liste = repo.findByJardin(idJardin);

				serviceResponse.setBody(liste);
				serviceResponse.setMessage("Succes");
				return serviceResponse;
			} catch (Exception e) {
				log.warn(e.getMessage());
				return new ServiceResponse<List<PlanteUtilisateur>>("Erreur lors de l'affichage de la liste", null);
			}

		}
	}

	@Override
	public boolean deleteByJardin(int idJardin) {
		log.debug("Service: deleteByJardin de l'entité jardin id : " + idJardin);
		
		boolean delete = true; 
		try {
			if (jRepo.existsById(idJardin)) {
				repo.deleteAllByJardin(idJardin);
			} else {
				log.info("Jardin inexistant");
				delete = false;
			}
				
		} catch(Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			delete = false;
		}
		return delete;

	}

}
