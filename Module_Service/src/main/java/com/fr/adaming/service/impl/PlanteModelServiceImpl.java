package com.fr.adaming.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Classe Service relative à l'entité Plante Model
 * ettend AbstractService, Implement IPlanteModelService
 * 
 * @author Léa
 * @since 0.0.1-SNAPSHOT
 */

@Service
public class PlanteModelServiceImpl extends AbstractService<PlanteModel>  implements IPlanteModelService{
	
	@Autowired
	private IPlanteModelRepository repo;

	@Override
	public ServiceResponse<PlanteModel> create(PlanteModel entity) {
		ServiceResponse<PlanteModel> retour = new ServiceResponse<PlanteModel>();
		if(entity==null) {
			retour.setBody(null);
			retour.setMessage("Création non réalisé : objet d'entrée null");
		}else if(entity.getNomScientifique()==null) {
			retour.setBody(null);
			retour.setMessage("Création non réalisé : Le nom Scientifique ne doit pas etre null");
		}else if(dao.existsById(entity.getId())) {
			retour.setBody(null);
			retour.setMessage("Création non réalisé : vous avez renseigné un id déjà présent dans la base de donnée");
		}else if (repo.existsByNomScientifique(entity.getNomScientifique())) {
			retour.setBody(null);
			retour.setMessage("Création non réalisé : vous avez renseigné un nom scientifique déjà présent dans la base de donnée");
		}
		else {
		retour.setBody(dao.save(entity));
		retour.setMessage("Succes de la création");
		}
		return retour;
	}

	@Override
	public ServiceResponse<PlanteModel> update(PlanteModel entity) {
		ServiceResponse<PlanteModel> retour = new ServiceResponse<PlanteModel>();
		if(entity==null) {
			retour.setBody(null);
			retour.setMessage("Update non réalisé : objet d'entrée null");
		}else if(entity.getNomScientifique()==null) {
			retour.setBody(null);
			retour.setMessage("Update non réalisé : Le nom Scientifique ne doit pas etre null");
		}else if(!dao.existsById(entity.getId())) {
			retour.setBody(null);
			retour.setMessage("Update non réalisé : vous avez renseigné un id inexistant dans la base de donnée");
		}else if (repo.existsByNomScientifique(entity.getNomScientifique()) && entity.getId()!=repo.findByNomScientifique(entity.getNomScientifique()).getId()) {
			retour.setBody(null);
			retour.setMessage("Update non réalisé : vous avez renseigné un nom scientifique déjà présent dans la base de donnée");
		}
		else {
		retour.setBody(dao.save(entity));
		retour.setMessage("Succes de la mise à jour");
		}
		return retour;
	}
	
	
	/**
	 * Methode permettant la recherche d'un liste de plante avec seulement quelques attributs
	 * 
	 * 
	 *
	 * @author Léa
	 */
	@Override
	public ServiceResponse<Page<PlanteModel>> readAllReduced(int p){
		ServiceResponse<Page<PlanteModel>> retour = new ServiceResponse<Page<PlanteModel>>();
		Pageable pageable = PageRequest.of(p, 3, Sort.by("nomScientifique"));	
		Page<PlanteModel> page = repo.findAllReduced(pageable);
			retour.setBody(page);
			retour.setMessage("Succes");
			return retour;
	}
	
	

	/**
	 * Methode permettant la recherche de plante par nom scientifique
	 * 
	 * @param nomScientique le nom recherché
	 * @return un ServiceReponse constitué d'un string "succes" et d'une entité PlanteModel
	 * @author Léa
	 */
	@Override
	public ServiceResponse<PlanteModel> readByNomScientifique(String nomScientique){
		ServiceResponse<PlanteModel> retour = new ServiceResponse<PlanteModel>();
		if(nomScientique==null) {
			retour.setBody(null);
			retour.setMessage("Objet non trouvé, entrée null");
		
		}
		else {
		retour.setBody(repo.findByNomScientifique(nomScientique));
		retour.setMessage("Succes");
		}
		return retour;
	}


}
