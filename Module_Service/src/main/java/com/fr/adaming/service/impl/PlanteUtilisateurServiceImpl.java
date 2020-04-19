package com.fr.adaming.service.impl;

import java.util.ArrayList;
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
import com.fr.adaming.repositories.IUtilisateurRepository;
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
	
	@Autowired
	private IUtilisateurRepository uRepo;

	@Override
	public ServiceResponse<PlanteUtilisateur> create(PlanteUtilisateur planteUtilisateur) {

		if (planteUtilisateur == null) {
			log.info("Creation echouée, l'objet d'entré est nul");
			return new ServiceResponse<PlanteUtilisateur>("Objet d'entrée null", null);
		} else if (dao.existsById(planteUtilisateur.getId())) {
			log.info("Creation echouée, l'id est dèjà existant dans la base de données");
			return new ServiceResponse<PlanteUtilisateur>("Id déjà connu dans la BD", null);
		}

		else {
			dao.save(planteUtilisateur);
			log.info("Plante Utilisateur enregistré dans la BD");
			return new ServiceResponse<PlanteUtilisateur>("Succes", planteUtilisateur);
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
			log.info("Plante Utilisateur enregistré dans la BD");
			return new ServiceResponse<PlanteUtilisateur>("Succes", dao.save(planteUtilisateur));
		}
	}

	@Override
	public ServiceResponse<Page<PlanteUtilisateur>> readByJardin(int idJardin, int p) {
		if (!jRepo.existsById(idJardin)) {
			log.info("Jardin inexistant");
			return new ServiceResponse<Page<PlanteUtilisateur>>("Jardin inexistant", null);
		} else {
			log.info("Jardin existant");
			ServiceResponse<Page<PlanteUtilisateur>> serviceResponse = new ServiceResponse<Page<PlanteUtilisateur>>();
			Pageable pageable = PageRequest.of(p, 20, Sort.by(Direction.DESC, "id"));
			Page<PlanteUtilisateur> page = repo.findByJardin(idJardin, pageable);

			serviceResponse.setBody(page);
			serviceResponse.setMessage("Succes");
			log.info("Retour d'une page contenant les plantes Utilisateur associé a ce jardin OK ");
			return serviceResponse;
		}
	}

	@Override
	public ServiceResponse<List<PlanteUtilisateur>> readByJardin(int idJardin) {
		if (!jRepo.existsById(idJardin)) {
			log.info("Jardin inexistant");
			List<PlanteUtilisateur> listeNulle = new ArrayList<>();
			return new ServiceResponse<List<PlanteUtilisateur>>("Jardin inexistant", listeNulle);
		} else {
			log.info("Jardin existant");
			ServiceResponse<List<PlanteUtilisateur>> serviceResponse = new ServiceResponse<List<PlanteUtilisateur>>();

			List<PlanteUtilisateur> liste = repo.findByJardin(idJardin);

			serviceResponse.setBody(liste);
			serviceResponse.setMessage("Succes");
			log.info("Lecture de la liste de Plante Utilisateur associée a ce jardin OK ");
			return serviceResponse;
		}
	}
	
	@Override
	public ServiceResponse<List<Integer>> readByUtilisateurId(int idUtil) {
		if (!uRepo.existsById(idUtil)) {
			log.warn("Utilisateur inexistant");
			
			return new ServiceResponse<List<Integer>>("Utilisateur inexistant", new ArrayList<Integer>());
		} else {
			log.debug("Utilisateur existant");
			ServiceResponse<List<Integer>> serviceResponse = new ServiceResponse<List<Integer>>();

			List<Integer> liste = repo.findByUtilisateurId(idUtil);

			serviceResponse.setBody(liste);
			serviceResponse.setMessage("Succes");
			log.debug("Lecture de la liste de Plante Utilisateur associée a ce jardin OK ");
			return serviceResponse;
		}
	}

	@Override
	public boolean deleteByJardin(int idJardin) {

		boolean delete = true;
		if (jRepo.existsById(idJardin)) {
			repo.deleteAllByJardin(idJardin);
			log.info("Les Plantes utilisateur du jardin ont bien été supprimées !");
		} else {
			log.info("Jardin inexistant");
			delete = false;
		}
		return delete;

	}

}
